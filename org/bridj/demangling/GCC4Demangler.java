/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.demangling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bridj.CLong;
import org.bridj.NativeLibrary;
import org.bridj.demangling.Demangler;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class GCC4Demangler
extends Demangler {
    private Map<String, List<Demangler.IdentLike>> prefixShortcuts = new HashMap<String, List<Demangler.IdentLike>>(){
        {
            this.put("t", Arrays.asList(new Demangler.Ident("std", new Demangler.TemplateArg[0])));
            this.put("a", Arrays.asList(new Demangler.Ident("std", new Demangler.TemplateArg[0]), new Demangler.Ident("allocator", new Demangler.TemplateArg[0])));
            this.put("b", Arrays.asList(new Demangler.Ident("std", new Demangler.TemplateArg[0]), new Demangler.Ident("basic_string", new Demangler.TemplateArg[0])));
            Demangler.TypeRef chartype = Demangler.classType(Byte.TYPE, new Class[0]);
            Demangler.ClassRef charTraitsOfChar = this.enclosed("std", new Demangler.ClassRef(new Demangler.Ident("char_traits", chartype)));
            Demangler.ClassRef allocatorOfChar = this.enclosed("std", new Demangler.ClassRef(new Demangler.Ident("allocator", chartype)));
            this.put("d", Arrays.asList(new Demangler.Ident("std", new Demangler.TemplateArg[0]), new Demangler.Ident("basic_iostream", chartype, charTraitsOfChar)));
            this.put("i", Arrays.asList(new Demangler.Ident("std", new Demangler.TemplateArg[0]), new Demangler.Ident("basic_istream", chartype, charTraitsOfChar)));
            this.put("o", Arrays.asList(new Demangler.Ident("std", new Demangler.TemplateArg[0]), new Demangler.Ident("basic_ostream", chartype, charTraitsOfChar)));
            this.put("s", Arrays.asList(new Demangler.Ident("std", new Demangler.TemplateArg[0]), new Demangler.Ident("basic_string", Demangler.classType(Byte.TYPE, new Class[0]), charTraitsOfChar, allocatorOfChar)));
        }

        private Demangler.ClassRef enclosed(String ns2, Demangler.ClassRef classRef) {
            classRef.setEnclosingType(new Demangler.NamespaceRef(new Demangler.Ident(ns2, new Demangler.TemplateArg[0])));
            return classRef;
        }
    };
    private Set<String> shouldContinueAfterPrefix = new HashSet<String>(Arrays.asList("t"));
    private Map<String, Demangler.TypeRef> typeShortcuts = new HashMap<String, Demangler.TypeRef>();
    int nextShortcutId = -1;

    public GCC4Demangler(NativeLibrary library, String symbol) {
        super(library, symbol);
    }

    private <T> T ensureOfType(Object o2, Class<T> type) throws Demangler.DemanglingException {
        if (type.isInstance(o2)) {
            return type.cast(o2);
        }
        throw new Demangler.DemanglingException("Internal error in demangler: trying to cast to " + type.getCanonicalName() + " the object '" + o2.toString() + "'");
    }

    private String nextShortcutId() {
        int n2;
        return (n2 = this.nextShortcutId++) == -1 ? "_" : Integer.toString(n2, 36).toUpperCase() + "_";
    }

    private Demangler.TypeRef parsePointerType() throws Demangler.DemanglingException {
        Demangler.TypeRef pointed = this.parseType();
        Demangler.TypeRef res = GCC4Demangler.pointerType(pointed);
        String id2 = this.nextShortcutId();
        this.typeShortcuts.put(id2, res);
        return res;
    }

    public Demangler.TemplateArg parseTemplateArg() throws Demangler.DemanglingException {
        if (this.consumeCharIf('L')) {
            char c2;
            Demangler.TypeRef tr2 = this.parseType();
            StringBuffer b2 = new StringBuffer();
            while (Character.isDigit(c2 = this.peekChar())) {
                this.consumeChar();
                b2.append(c2);
            }
            this.expectChars('E');
            return new Demangler.Constant(Integer.parseInt(b2.toString()));
        }
        return this.parseType();
    }

    public Demangler.TypeRef parseType() throws Demangler.DemanglingException {
        if (Character.isDigit(this.peekChar())) {
            Demangler.Ident name = this.ensureOfType(this.parseNonCompoundIdent(), Demangler.Ident.class);
            String id2 = this.nextShortcutId();
            Demangler.TypeRef res = GCC4Demangler.simpleType(name);
            this.typeShortcuts.put(id2, res);
            return res;
        }
        char c2 = this.consumeChar();
        switch (c2) {
            case 'S': {
                char cc2 = this.peekChar();
                int delta = 0;
                if (Character.isDigit(cc2) || Character.isUpperCase(cc2) || cc2 == '_') {
                    String id3 = "";
                    while ((c2 = this.peekChar()) != '_' && c2 != '\u0000') {
                        id3 = id3 + this.consumeChar();
                        ++delta;
                    }
                    if (this.peekChar() == '\u0000') {
                        throw new Demangler.DemanglingException("Encountered a unexpected end in gcc mangler shortcut '" + id3 + "' " + this.prefixShortcuts.keySet());
                    }
                    id3 = id3 + this.consumeChar();
                    ++delta;
                    if (this.typeShortcuts.containsKey(id3)) {
                        if (this.peekChar() != 'I') {
                            return this.typeShortcuts.get(id3);
                        }
                        ArrayList<Demangler.IdentLike> nsPath = new ArrayList<Demangler.IdentLike>((Collection)this.prefixShortcuts.get(id3));
                        String templatedId = this.parsePossibleTemplateArguments(nsPath);
                        if (templatedId != null) {
                            return this.typeShortcuts.get(templatedId);
                        }
                    }
                    this.position -= delta;
                }
            }
            case 'N': {
                --this.position;
                ArrayList<Demangler.IdentLike> ns2 = new ArrayList<Demangler.IdentLike>();
                String newShortcutId = this.parseSimpleOrComplexIdentInto(ns2, false);
                Demangler.ClassRef res = new Demangler.ClassRef(this.ensureOfType(ns2.remove(ns2.size() - 1), Demangler.Ident.class));
                if (!ns2.isEmpty()) {
                    res.setEnclosingType(new Demangler.NamespaceRef(ns2.toArray()));
                }
                if (newShortcutId != null) {
                    this.typeShortcuts.put(newShortcutId, res);
                }
                return res;
            }
            case 'P': {
                return this.parsePointerType();
            }
            case 'F': {
                while (this.consumeChar() != 'E') {
                }
                return null;
            }
            case 'K': {
                return this.parseType();
            }
            case 'v': {
                return GCC4Demangler.classType(Void.TYPE, new Class[0]);
            }
            case 'a': 
            case 'c': 
            case 'h': {
                return GCC4Demangler.classType(Byte.TYPE, new Class[0]);
            }
            case 'b': {
                return GCC4Demangler.classType(Boolean.TYPE, new Class[0]);
            }
            case 'l': 
            case 'm': {
                return GCC4Demangler.classType(CLong.class, new Class[0]);
            }
            case 'x': 
            case 'y': {
                return GCC4Demangler.classType(Long.TYPE, new Class[0]);
            }
            case 'i': 
            case 'j': {
                return GCC4Demangler.classType(Integer.TYPE, new Class[0]);
            }
            case 's': 
            case 't': {
                return GCC4Demangler.classType(Short.TYPE, new Class[0]);
            }
            case 'f': {
                return GCC4Demangler.classType(Float.TYPE, new Class[0]);
            }
            case 'd': {
                return GCC4Demangler.classType(Double.TYPE, new Class[0]);
            }
            case 'z': {
                return GCC4Demangler.classType(Object[].class, new Class[0]);
            }
        }
        throw this.error("Unexpected type char '" + c2 + "'", -1);
    }

    String parseName() throws Demangler.DemanglingException {
        int len;
        char c2;
        StringBuilder b2 = new StringBuilder();
        while (Character.isDigit(c2 = this.peekChar())) {
            this.consumeChar();
            b2.append(c2);
        }
        try {
            len = Integer.parseInt(b2.toString());
        }
        catch (NumberFormatException ex2) {
            throw this.error("Expected a number", 0);
        }
        b2.setLength(0);
        for (int i2 = 0; i2 < len; ++i2) {
            b2.append(this.consumeChar());
        }
        return b2.toString();
    }

    private String parseSimpleOrComplexIdentInto(List<Demangler.IdentLike> res, boolean isParsingNonShortcutableElement) throws Demangler.DemanglingException {
        String newlyAddedShortcutForThisType = null;
        boolean shouldContinue = false;
        boolean expectEInTheEnd = false;
        if (this.consumeCharIf('N')) {
            if (this.consumeCharIf('S')) {
                this.parseShortcutInto(res);
            }
            shouldContinue = true;
            expectEInTheEnd = true;
        } else if (this.consumeCharIf('S')) {
            shouldContinue = this.parseShortcutInto(res);
        } else {
            res.add(this.parseNonCompoundIdent());
        }
        if (shouldContinue) {
            do {
                String id2;
                newlyAddedShortcutForThisType = id2 = this.nextShortcutId();
                Demangler.IdentLike part = this.parseNonCompoundIdent();
                res.add(part);
                this.prefixShortcuts.put(id2, new ArrayList<Demangler.IdentLike>(res));
                this.parsePossibleTemplateArguments(res);
            } while (Character.isDigit(this.peekChar()) || this.peekChar() == 'C' || this.peekChar() == 'D');
            if (isParsingNonShortcutableElement) {
                --this.nextShortcutId;
            }
        }
        this.parsePossibleTemplateArguments(res);
        if (expectEInTheEnd) {
            this.expectAnyChar('E');
        }
        return newlyAddedShortcutForThisType;
    }

    private String parsePossibleTemplateArguments(List<Demangler.IdentLike> res) throws Demangler.DemanglingException {
        if (this.consumeCharIf('I')) {
            ArrayList<Demangler.TemplateArg> args = new ArrayList<Demangler.TemplateArg>();
            while (!this.consumeCharIf('E')) {
                args.add(this.parseTemplateArg());
            }
            String id2 = this.nextShortcutId();
            Demangler.Ident templatedIdent = new Demangler.Ident(this.ensureOfType(res.remove(res.size() - 1), Demangler.Ident.class).toString(), args.toArray(new Demangler.TemplateArg[args.size()]));
            res.add(templatedIdent);
            this.prefixShortcuts.put(id2, new ArrayList<Demangler.IdentLike>(res));
            ArrayList<Demangler.IdentLike> ns2 = new ArrayList<Demangler.IdentLike>(res);
            Demangler.ClassRef clss = new Demangler.ClassRef(this.ensureOfType(ns2.remove(ns2.size() - 1), Demangler.Ident.class));
            if (!ns2.isEmpty()) {
                clss.setEnclosingType(new Demangler.NamespaceRef(ns2.toArray()));
            }
            this.typeShortcuts.put(id2, clss);
            return id2;
        }
        return null;
    }

    private boolean parseShortcutInto(List<Demangler.IdentLike> res) throws Demangler.DemanglingException {
        char c2 = this.peekChar();
        if (c2 == '_') {
            List<Demangler.IdentLike> toAdd = this.prefixShortcuts.get(Character.toString(this.consumeChar()));
            if (toAdd == null) {
                throw new Demangler.DemanglingException("Encountered a yet undefined gcc mangler shortcut S_ (first one), i.e. '_' " + this.prefixShortcuts.keySet());
            }
            res.addAll(toAdd);
            return false;
        }
        if (Character.isDigit(c2) || Character.isUpperCase(c2)) {
            String id2 = "";
            while ((c2 = this.peekChar()) != '_' && c2 != '\u0000') {
                id2 = id2 + this.consumeChar();
            }
            if (this.peekChar() == '\u0000') {
                throw new Demangler.DemanglingException("Encountered a unexpected end in gcc mangler shortcut '" + id2 + "' " + this.prefixShortcuts.keySet());
            }
            List<Demangler.IdentLike> toAdd = this.prefixShortcuts.get(id2 = id2 + this.consumeChar());
            if (toAdd == null) {
                throw new Demangler.DemanglingException("Encountered a unexpected gcc mangler shortcut '" + id2 + "' " + this.prefixShortcuts.keySet());
            }
            res.addAll(toAdd);
            return false;
        }
        if (Character.isLowerCase(c2)) {
            String id3 = Character.toString(this.consumeChar());
            List<Demangler.IdentLike> toAdd = this.prefixShortcuts.get(id3);
            if (toAdd == null) {
                throw new Demangler.DemanglingException("Encountered a unexpected gcc mangler built-in shortcut '" + id3 + "' " + this.prefixShortcuts.keySet());
            }
            res.addAll(toAdd);
            return this.shouldContinueAfterPrefix.contains(id3);
        }
        throw new Demangler.DemanglingException("Encountered a unexpected gcc unknown shortcut '" + c2 + "' " + this.prefixShortcuts.keySet());
    }

    Demangler.IdentLike parseNonCompoundIdent() throws Demangler.DemanglingException {
        if (this.consumeCharIf('C')) {
            if (this.consumeCharIf('1')) {
                return Demangler.SpecialName.Constructor;
            }
            if (this.consumeCharIf('2')) {
                return Demangler.SpecialName.SpecialConstructor;
            }
            throw this.error("Unknown constructor type 'C" + this.peekChar() + "'");
        }
        if (this.consumeCharIf('D')) {
            if (this.consumeCharIf('0')) {
                return Demangler.SpecialName.DeletingDestructor;
            }
            if (this.consumeCharIf('1')) {
                return Demangler.SpecialName.Destructor;
            }
            if (this.consumeCharIf('2')) {
                return Demangler.SpecialName.SelfishDestructor;
            }
            throw this.error("Unknown destructor type 'D" + this.peekChar() + "'");
        }
        String n2 = this.parseName();
        return new Demangler.Ident(n2, new Demangler.TemplateArg[0]);
    }

    @Override
    public Demangler.MemberRef parseSymbol() throws Demangler.DemanglingException {
        Demangler.MemberRef mr2 = new Demangler.MemberRef();
        if (!this.consumeCharIf('_')) {
            mr2.setMemberName(new Demangler.Ident(this.str, new Demangler.TemplateArg[0]));
            return mr2;
        }
        this.consumeCharIf('_');
        this.expectChars('Z');
        if (this.consumeCharIf('T')) {
            if (this.consumeCharIf('V')) {
                mr2.setEnclosingType(this.ensureOfType(this.parseType(), Demangler.ClassRef.class));
                mr2.setMemberName(Demangler.SpecialName.VFTable);
                return mr2;
            }
            return null;
        }
        if (this.consumeCharsIf('d', 'l', 'P', 'v')) {
            mr2.setMemberName(Demangler.SpecialName.Delete);
            return mr2;
        }
        if (this.consumeCharsIf('d', 'a', 'P', 'v')) {
            mr2.setMemberName(Demangler.SpecialName.DeleteArray);
            return mr2;
        }
        if (this.consumeCharsIf('n', 'w', 'm')) {
            mr2.setMemberName(Demangler.SpecialName.New);
            return mr2;
        }
        if (this.consumeCharsIf('n', 'a', 'm')) {
            mr2.setMemberName(Demangler.SpecialName.NewArray);
            return mr2;
        }
        ArrayList<Demangler.IdentLike> ns2 = new ArrayList<Demangler.IdentLike>();
        this.parseSimpleOrComplexIdentInto(ns2, true);
        mr2.setMemberName((Demangler.IdentLike)ns2.remove(ns2.size() - 1));
        if (!ns2.isEmpty()) {
            Demangler.ClassRef parent = new Demangler.ClassRef(this.ensureOfType(ns2.remove(ns2.size() - 1), Demangler.Ident.class));
            if (mr2.getMemberName() == Demangler.SpecialName.Constructor || mr2.getMemberName() == Demangler.SpecialName.SpecialConstructor) {
                this.typeShortcuts.put(this.nextShortcutId(), parent);
            }
            if (!ns2.isEmpty()) {
                parent.setEnclosingType(new Demangler.NamespaceRef(ns2.toArray()));
            }
            mr2.setEnclosingType(parent);
        }
        if (this.consumeCharIf('v')) {
            if (this.position < this.length) {
                this.error("Expected end of symbol", 0);
            }
            mr2.paramTypes = new Demangler.TypeRef[0];
        } else {
            ArrayList<Demangler.TypeRef> paramTypes = new ArrayList<Demangler.TypeRef>();
            while (this.position < this.length) {
                paramTypes.add(this.parseType());
            }
            mr2.paramTypes = paramTypes.toArray(new Demangler.TypeRef[paramTypes.size()]);
        }
        return mr2;
    }
}

