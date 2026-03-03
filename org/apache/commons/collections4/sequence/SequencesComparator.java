/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.sequence;

import java.util.List;
import org.apache.commons.collections4.Equator;
import org.apache.commons.collections4.functors.DefaultEquator;
import org.apache.commons.collections4.sequence.DeleteCommand;
import org.apache.commons.collections4.sequence.EditScript;
import org.apache.commons.collections4.sequence.InsertCommand;
import org.apache.commons.collections4.sequence.KeepCommand;

public class SequencesComparator<T> {
    private final List<T> sequence1;
    private final List<T> sequence2;
    private final Equator<? super T> equator;
    private final int[] vDown;
    private final int[] vUp;

    public SequencesComparator(List<T> sequence1, List<T> sequence2) {
        this(sequence1, sequence2, DefaultEquator.defaultEquator());
    }

    public SequencesComparator(List<T> sequence1, List<T> sequence2, Equator<? super T> equator) {
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
        this.equator = equator;
        int size = sequence1.size() + sequence2.size() + 2;
        this.vDown = new int[size];
        this.vUp = new int[size];
    }

    public EditScript<T> getScript() {
        EditScript script = new EditScript();
        this.buildScript(0, this.sequence1.size(), 0, this.sequence2.size(), script);
        return script;
    }

    private Snake buildSnake(int start, int diag, int end1, int end2) {
        int end;
        for (end = start; end - diag < end2 && end < end1 && this.equator.equate(this.sequence1.get(end), this.sequence2.get(end - diag)); ++end) {
        }
        return new Snake(start, end, diag);
    }

    private Snake getMiddleSnake(int start1, int end1, int start2, int end2) {
        int m2 = end1 - start1;
        int n2 = end2 - start2;
        if (m2 == 0 || n2 == 0) {
            return null;
        }
        int delta = m2 - n2;
        int sum = n2 + m2;
        int offset = (sum % 2 == 0 ? sum : sum + 1) / 2;
        this.vDown[1 + offset] = start1;
        this.vUp[1 + offset] = end1 + 1;
        for (int d2 = 0; d2 <= offset; ++d2) {
            int y2;
            int x2;
            int i2;
            int k2;
            for (k2 = -d2; k2 <= d2; k2 += 2) {
                i2 = k2 + offset;
                this.vDown[i2] = k2 == -d2 || k2 != d2 && this.vDown[i2 - 1] < this.vDown[i2 + 1] ? this.vDown[i2 + 1] : this.vDown[i2 - 1] + 1;
                x2 = this.vDown[i2];
                for (y2 = x2 - start1 + start2 - k2; x2 < end1 && y2 < end2 && this.equator.equate(this.sequence1.get(x2), this.sequence2.get(y2)); ++y2) {
                    this.vDown[i2] = ++x2;
                }
                if (delta % 2 == 0 || delta - d2 > k2 || k2 > delta + d2 || this.vUp[i2 - delta] > this.vDown[i2]) continue;
                return this.buildSnake(this.vUp[i2 - delta], k2 + start1 - start2, end1, end2);
            }
            for (k2 = delta - d2; k2 <= delta + d2; k2 += 2) {
                i2 = k2 + offset - delta;
                this.vUp[i2] = k2 == delta - d2 || k2 != delta + d2 && this.vUp[i2 + 1] <= this.vUp[i2 - 1] ? this.vUp[i2 + 1] - 1 : this.vUp[i2 - 1];
                x2 = this.vUp[i2] - 1;
                for (y2 = x2 - start1 + start2 - k2; x2 >= start1 && y2 >= start2 && this.equator.equate(this.sequence1.get(x2), this.sequence2.get(y2)); --y2) {
                    this.vUp[i2] = x2--;
                }
                if (delta % 2 != 0 || -d2 > k2 || k2 > d2 || this.vUp[i2] > this.vDown[i2 + delta]) continue;
                return this.buildSnake(this.vUp[i2], k2 + start1 - start2, end1, end2);
            }
        }
        throw new RuntimeException("Internal Error");
    }

    private void buildScript(int start1, int end1, int start2, int end2, EditScript<T> script) {
        Snake middle = this.getMiddleSnake(start1, end1, start2, end2);
        if (middle == null || middle.getStart() == end1 && middle.getDiag() == end1 - end2 || middle.getEnd() == start1 && middle.getDiag() == start1 - start2) {
            int i2 = start1;
            int j2 = start2;
            while (i2 < end1 || j2 < end2) {
                if (i2 < end1 && j2 < end2 && this.equator.equate(this.sequence1.get(i2), this.sequence2.get(j2))) {
                    script.append(new KeepCommand<T>(this.sequence1.get(i2)));
                    ++i2;
                    ++j2;
                    continue;
                }
                if (end1 - start1 > end2 - start2) {
                    script.append(new DeleteCommand<T>(this.sequence1.get(i2)));
                    ++i2;
                    continue;
                }
                script.append(new InsertCommand<T>(this.sequence2.get(j2)));
                ++j2;
            }
        } else {
            this.buildScript(start1, middle.getStart(), start2, middle.getStart() - middle.getDiag(), script);
            for (int i3 = middle.getStart(); i3 < middle.getEnd(); ++i3) {
                script.append(new KeepCommand<T>(this.sequence1.get(i3)));
            }
            this.buildScript(middle.getEnd(), end1, middle.getEnd() - middle.getDiag(), end2, script);
        }
    }

    private static class Snake {
        private final int start;
        private final int end;
        private final int diag;

        public Snake(int start, int end, int diag) {
            this.start = start;
            this.end = end;
            this.diag = diag;
        }

        public int getStart() {
            return this.start;
        }

        public int getEnd() {
            return this.end;
        }

        public int getDiag() {
            return this.diag;
        }
    }
}

