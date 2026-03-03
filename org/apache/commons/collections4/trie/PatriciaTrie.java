/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.trie;

import java.util.Map;
import org.apache.commons.collections4.trie.AbstractPatriciaTrie;
import org.apache.commons.collections4.trie.analyzer.StringKeyAnalyzer;

public class PatriciaTrie<E>
extends AbstractPatriciaTrie<String, E> {
    private static final long serialVersionUID = 4446367780901817838L;

    public PatriciaTrie() {
        super(new StringKeyAnalyzer());
    }

    public PatriciaTrie(Map<? extends String, ? extends E> m2) {
        super(new StringKeyAnalyzer(), m2);
    }
}

