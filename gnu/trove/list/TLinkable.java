/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.list;

import java.io.Serializable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface TLinkable<T extends TLinkable>
extends Serializable {
    public static final long serialVersionUID = 997545054865482562L;

    public T getNext();

    public T getPrevious();

    public void setNext(T var1);

    public void setPrevious(T var1);
}

