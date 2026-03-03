/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.list.linked;

import gnu.trove.list.TLinkable;
import gnu.trove.procedure.TObjectProcedure;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TLinkedList<T extends TLinkable<T>>
extends AbstractSequentialList<T>
implements Externalizable {
    static final long serialVersionUID = 1L;
    protected T _head;
    protected T _tail;
    protected int _size = 0;

    @Override
    public ListIterator<T> listIterator(int index) {
        return new IteratorImpl(index);
    }

    @Override
    public int size() {
        return this._size;
    }

    @Override
    public void add(int index, T linkable) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException("index:" + index);
        }
        this.insert(index, linkable);
    }

    @Override
    public boolean add(T linkable) {
        this.insert(this._size, linkable);
        return true;
    }

    @Override
    public void addFirst(T linkable) {
        this.insert(0, linkable);
    }

    @Override
    public void addLast(T linkable) {
        this.insert(this.size(), linkable);
    }

    @Override
    public void clear() {
        if (null != this._head) {
            for (Object link = this._head.getNext(); link != null; link = link.getNext()) {
                Object prev = link.getPrevious();
                prev.setNext(null);
                link.setPrevious(null);
            }
            this._tail = null;
            this._head = null;
        }
        this._size = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] o2 = new Object[this._size];
        int i2 = 0;
        for (T link = this._head; link != null; link = link.getNext()) {
            o2[i2++] = link;
        }
        return o2;
    }

    public Object[] toUnlinkedArray() {
        Object[] o2 = new Object[this._size];
        int i2 = 0;
        T link = this._head;
        while (link != null) {
            o2[i2] = link;
            T tmp = link;
            link = link.getNext();
            tmp.setNext(null);
            tmp.setPrevious(null);
            ++i2;
        }
        this._size = 0;
        this._tail = null;
        this._head = null;
        return o2;
    }

    public T[] toUnlinkedArray(T[] a2) {
        int size = this.size();
        if (a2.length < size) {
            a2 = (TLinkable[])Array.newInstance(a2.getClass().getComponentType(), size);
        }
        int i2 = 0;
        T link = this._head;
        while (link != null) {
            a2[i2] = link;
            T tmp = link;
            link = link.getNext();
            tmp.setNext(null);
            tmp.setPrevious(null);
            ++i2;
        }
        this._size = 0;
        this._tail = null;
        this._head = null;
        return a2;
    }

    @Override
    public boolean contains(Object o2) {
        for (T link = this._head; link != null; link = link.getNext()) {
            if (!o2.equals(link)) continue;
            return true;
        }
        return false;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= this._size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this._size);
        }
        if (index > this._size >> 1) {
            T node = this._tail;
            for (int position = this._size - 1; position > index; --position) {
                node = node.getPrevious();
            }
            return node;
        }
        T node = this._head;
        for (int position = 0; position < index; ++position) {
            node = node.getNext();
        }
        return node;
    }

    @Override
    public T getFirst() {
        return this._head;
    }

    @Override
    public T getLast() {
        return this._tail;
    }

    public T getNext(T current) {
        return current.getNext();
    }

    public T getPrevious(T current) {
        return current.getPrevious();
    }

    @Override
    public T removeFirst() {
        T o2 = this._head;
        if (o2 == null) {
            return null;
        }
        Object n2 = o2.getNext();
        o2.setNext(null);
        if (null != n2) {
            n2.setPrevious(null);
        }
        this._head = n2;
        if (--this._size == 0) {
            this._tail = null;
        }
        return o2;
    }

    @Override
    public T removeLast() {
        T o2 = this._tail;
        if (o2 == null) {
            return null;
        }
        Object prev = o2.getPrevious();
        o2.setPrevious(null);
        if (null != prev) {
            prev.setNext(null);
        }
        this._tail = prev;
        if (--this._size == 0) {
            this._head = null;
        }
        return o2;
    }

    protected void insert(int index, T linkable) {
        if (this._size == 0) {
            this._tail = linkable;
            this._head = this._tail;
        } else if (index == 0) {
            linkable.setNext(this._head);
            this._head.setPrevious(linkable);
            this._head = linkable;
        } else if (index == this._size) {
            this._tail.setNext(linkable);
            linkable.setPrevious(this._tail);
            this._tail = linkable;
        } else {
            Object node = this.get(index);
            T before = node.getPrevious();
            if (before != null) {
                before.setNext(linkable);
            }
            linkable.setPrevious(before);
            linkable.setNext((Object)node);
            node.setPrevious(linkable);
        }
        ++this._size;
    }

    @Override
    public boolean remove(Object o2) {
        if (o2 instanceof TLinkable) {
            TLinkable link = (TLinkable)o2;
            Object p2 = link.getPrevious();
            Object n2 = link.getNext();
            if (n2 == null && p2 == null) {
                if (o2 != this._head) {
                    return false;
                }
                this._tail = null;
                this._head = null;
            } else if (n2 == null) {
                link.setPrevious(null);
                p2.setNext(null);
                this._tail = p2;
            } else if (p2 == null) {
                link.setNext(null);
                n2.setPrevious(null);
                this._head = n2;
            } else {
                p2.setNext(n2);
                n2.setPrevious(p2);
                link.setNext(null);
                link.setPrevious(null);
            }
            --this._size;
            return true;
        }
        return false;
    }

    public void addBefore(T current, T newElement) {
        if (current == this._head) {
            this.addFirst(newElement);
        } else if (current == null) {
            this.addLast(newElement);
        } else {
            T p2 = current.getPrevious();
            newElement.setNext(current);
            p2.setNext(newElement);
            newElement.setPrevious(p2);
            current.setPrevious(newElement);
            ++this._size;
        }
    }

    public void addAfter(T current, T newElement) {
        if (current == this._tail) {
            this.addLast(newElement);
        } else if (current == null) {
            this.addFirst(newElement);
        } else {
            T n2 = current.getNext();
            newElement.setPrevious(current);
            newElement.setNext(n2);
            current.setNext(newElement);
            n2.setPrevious(newElement);
            ++this._size;
        }
    }

    public boolean forEachValue(TObjectProcedure<T> procedure) {
        for (T node = this._head; node != null; node = node.getNext()) {
            boolean keep_going = procedure.execute(node);
            if (keep_going) continue;
            return false;
        }
        return true;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeInt(this._size);
        out.writeObject(this._head);
        out.writeObject(this._tail);
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this._size = in2.readInt();
        this._head = (TLinkable)in2.readObject();
        this._tail = (TLinkable)in2.readObject();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected final class IteratorImpl
    implements ListIterator<T> {
        private int _nextIndex = 0;
        private T _next;
        private T _lastReturned;

        IteratorImpl(int position) {
            if (position < 0 || position > TLinkedList.this._size) {
                throw new IndexOutOfBoundsException();
            }
            this._nextIndex = position;
            if (position == 0) {
                this._next = TLinkedList.this._head;
            } else if (position == TLinkedList.this._size) {
                this._next = null;
            } else if (position < TLinkedList.this._size >> 1) {
                this._next = TLinkedList.this._head;
                for (int pos = 0; pos < position; ++pos) {
                    this._next = this._next.getNext();
                }
            } else {
                this._next = TLinkedList.this._tail;
                for (int pos = TLinkedList.this._size - 1; pos > position; --pos) {
                    this._next = this._next.getPrevious();
                }
            }
        }

        @Override
        public final void add(T linkable) {
            this._lastReturned = null;
            ++this._nextIndex;
            if (TLinkedList.this._size == 0) {
                TLinkedList.this.add(linkable);
            } else {
                TLinkedList.this.addBefore(this._next, linkable);
            }
        }

        @Override
        public final boolean hasNext() {
            return this._nextIndex != TLinkedList.this._size;
        }

        @Override
        public final boolean hasPrevious() {
            return this._nextIndex != 0;
        }

        @Override
        public final T next() {
            if (this._nextIndex == TLinkedList.this._size) {
                throw new NoSuchElementException();
            }
            this._lastReturned = this._next;
            this._next = this._next.getNext();
            ++this._nextIndex;
            return this._lastReturned;
        }

        @Override
        public final int nextIndex() {
            return this._nextIndex;
        }

        @Override
        public final T previous() {
            if (this._nextIndex == 0) {
                throw new NoSuchElementException();
            }
            if (this._nextIndex == TLinkedList.this._size) {
                this._next = TLinkedList.this._tail;
                this._lastReturned = this._next;
            } else {
                this._next = this._next.getPrevious();
                this._lastReturned = this._next;
            }
            --this._nextIndex;
            return this._lastReturned;
        }

        @Override
        public final int previousIndex() {
            return this._nextIndex - 1;
        }

        @Override
        public final void remove() {
            if (this._lastReturned == null) {
                throw new IllegalStateException("must invoke next or previous before invoking remove");
            }
            if (this._lastReturned != this._next) {
                --this._nextIndex;
            }
            this._next = this._lastReturned.getNext();
            TLinkedList.this.remove(this._lastReturned);
            this._lastReturned = null;
        }

        @Override
        public final void set(T linkable) {
            if (this._lastReturned == null) {
                throw new IllegalStateException();
            }
            this.swap(this._lastReturned, linkable);
            this._lastReturned = linkable;
        }

        private void swap(T from, T to2) {
            Object from_p = from.getPrevious();
            Object from_n = from.getNext();
            Object to_p = to2.getPrevious();
            Object to_n = to2.getNext();
            if (from_n == to2) {
                if (from_p != null) {
                    from_p.setNext(to2);
                }
                to2.setPrevious(from_p);
                to2.setNext(from);
                from.setPrevious(to2);
                from.setNext(to_n);
                if (to_n != null) {
                    to_n.setPrevious(from);
                }
            } else if (to_n == from) {
                if (to_p != null) {
                    to_p.setNext(to2);
                }
                to2.setPrevious(from);
                to2.setNext(from_n);
                from.setPrevious(to_p);
                from.setNext(to2);
                if (from_n != null) {
                    from_n.setPrevious(to2);
                }
            } else {
                from.setNext(to_n);
                from.setPrevious(to_p);
                if (to_p != null) {
                    to_p.setNext(from);
                }
                if (to_n != null) {
                    to_n.setPrevious(from);
                }
                to2.setNext(from_n);
                to2.setPrevious(from_p);
                if (from_p != null) {
                    from_p.setNext(to2);
                }
                if (from_n != null) {
                    from_n.setPrevious(to2);
                }
            }
            if (TLinkedList.this._head == from) {
                TLinkedList.this._head = to2;
            } else if (TLinkedList.this._head == to2) {
                TLinkedList.this._head = from;
            }
            if (TLinkedList.this._tail == from) {
                TLinkedList.this._tail = to2;
            } else if (TLinkedList.this._tail == to2) {
                TLinkedList.this._tail = from;
            }
            if (this._lastReturned == from) {
                this._lastReturned = to2;
            } else if (this._lastReturned == to2) {
                this._lastReturned = from;
            }
            if (this._next == from) {
                this._next = to2;
            } else if (this._next == to2) {
                this._next = from;
            }
        }
    }
}

