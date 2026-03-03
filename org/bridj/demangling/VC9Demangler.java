/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.demangling;

import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bridj.CLong;
import org.bridj.NativeLibrary;
import org.bridj.ann.Convention;
import org.bridj.demangling.Demangler;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class VC9Demangler
extends Demangler {
    List<Demangler.TypeRef> allQualifiedNames = new ArrayList<Demangler.TypeRef>();

    public VC9Demangler(NativeLibrary library, String str) {
        super(library, str);
    }

    private AccessLevelAndStorageClass parseAccessLevelAndStorageClass() throws Demangler.DemanglingException {
        AccessLevelAndStorageClass ac2 = new AccessLevelAndStorageClass();
        switch (this.consumeChar()) {
            case 'A': 
            case 'B': {
                ac2.modifiers = 2;
                break;
            }
            case 'C': 
            case 'D': {
                ac2.modifiers = 10;
                break;
            }
            case 'E': 
            case 'F': {
                ac2.modifiers = 2;
                ac2.isVirtual = true;
                break;
            }
            case 'G': 
            case 'H': {
                ac2.modifiers = 2;
                ac2.isThunk = true;
                break;
            }
            case 'I': 
            case 'J': {
                ac2.modifiers = 4;
                break;
            }
            case 'K': 
            case 'L': {
                ac2.modifiers = 12;
                break;
            }
            case 'M': 
            case 'N': {
                ac2.modifiers = 4;
                ac2.isVirtual = true;
                break;
            }
            case 'O': 
            case 'P': {
                ac2.modifiers = 4;
                ac2.isThunk = true;
                break;
            }
            case 'Q': 
            case 'R': {
                ac2.modifiers = 1;
                break;
            }
            case 'S': 
            case 'T': {
                ac2.modifiers = 9;
                break;
            }
            case 'U': 
            case 'V': {
                ac2.modifiers = 1;
                ac2.isVirtual = true;
                break;
            }
            case 'W': 
            case 'X': {
                ac2.modifiers = 1;
                ac2.isThunk = true;
                break;
            }
            case 'Y': 
            case 'Z': {
                ac2.modifiers = 0;
                break;
            }
            default: {
                throw this.error("Unknown access level + storage class");
            }
        }
        return ac2;
    }

    private Demangler.ClassRef parseTemplateType() throws Demangler.DemanglingException {
        String name = this.parseNameFragment();
        List<Demangler.TemplateArg> args = this.parseTemplateParams();
        List<Object> names = this.parseNameQualifications();
        Demangler.ClassRef tr2 = new Demangler.ClassRef(new Demangler.Ident(name, args.toArray(new Demangler.TemplateArg[args.size()])));
        tr2.setEnclosingType(VC9Demangler.reverseNamespace(names));
        this.addBackRef(tr2);
        return tr2;
    }

    private void parseFunctionProperty(Demangler.MemberRef mr2) throws Demangler.DemanglingException {
        mr2.callingConvention = this.parseCallingConvention();
        Demangler.TypeRef returnType = this.consumeCharIf('@') ? VC9Demangler.classType(Void.TYPE, new Class[0]) : this.parseType(true);
        List<Demangler.TypeRef> paramTypes = this.parseParams();
        mr2.paramTypes = paramTypes.toArray(new Demangler.TypeRef[paramTypes.size()]);
        if (!this.consumeCharIf('Z')) {
            List<Demangler.TypeRef> throwTypes = this.parseParams();
            mr2.throwTypes = throwTypes.toArray(new Demangler.TypeRef[throwTypes.size()]);
        }
        mr2.setValueType(returnType);
    }

    private Demangler.TemplateArg parseTemplateParameter() throws Demangler.DemanglingException {
        switch (this.peekChar()) {
            case '?': {
                this.consumeChar();
                return new AnonymousTemplateArg("'anonymous template param " + this.parseNumber(false) + "'");
            }
            case '$': {
                this.consumeChar();
                switch (this.consumeChar()) {
                    case '0': {
                        return new Demangler.Constant(this.parseNumber(true));
                    }
                    case '2': {
                        int a2 = this.parseNumber(true);
                        int b2 = this.parseNumber(true);
                        return new Demangler.Constant((double)a2 * Math.exp(10 * (int)Math.log((double)b2 - Math.log10(a2) + 1.0)));
                    }
                    case 'D': {
                        return new AnonymousTemplateArg("'anonymous template param " + this.parseNumber(false) + "'");
                    }
                    case 'F': {
                        return new AnonymousTemplateArg("'tuple (" + this.parseNumber(true) + ", " + this.parseNumber(true) + ")'");
                    }
                    case 'G': {
                        return new AnonymousTemplateArg("'tuple (" + this.parseNumber(true) + ", " + this.parseNumber(true) + ", " + this.parseNumber(true) + ")'");
                    }
                    case 'Q': {
                        return new AnonymousTemplateArg("'anonymous non-type template param " + this.parseNumber(false) + "'");
                    }
                }
                break;
            }
        }
        return this.parseType(true);
    }

    @Override
    public Demangler.MemberRef parseSymbol() throws Demangler.DemanglingException {
        Demangler.MemberRef mr2 = new Demangler.MemberRef();
        int iAt = this.str.indexOf(64);
        if (iAt >= 0 && this.consumeCharIf('_') && iAt > 0) {
            mr2.setMemberName(new Demangler.Ident(this.str.substring(1, iAt), new Demangler.TemplateArg[0]));
            mr2.setArgumentsStackSize(Integer.parseInt(this.str.substring(iAt + 1)));
            return mr2;
        }
        if (this.consumeCharIf('?')) {
            Demangler.TypeRef encl;
            Demangler.SpecialName specialName;
            this.consumeCharsIf('@', '?');
            Demangler.IdentLike memberName = this.parseFirstQualifiedTypeNameComponent();
            if (memberName instanceof Demangler.SpecialName && !(specialName = (Demangler.SpecialName)memberName).isFunction()) {
                return null;
            }
            mr2.setMemberName(memberName);
            List<Object> qNames = this.parseNameQualifications();
            AccessLevelAndStorageClass ac2 = this.parseAccessLevelAndStorageClass();
            CVClassModifier cvMod = null;
            if (ac2.modifiers != 0 && !Modifier.isStatic(ac2.modifiers)) {
                cvMod = this.parseCVClassModifier();
            }
            if (cvMod != null && (cvMod.isMember || memberName instanceof Demangler.SpecialName || Modifier.isPublic(ac2.modifiers))) {
                Object r2 = qNames.get(0);
                Demangler.ClassRef tr2 = r2 instanceof Demangler.ClassRef ? (Demangler.ClassRef)r2 : new Demangler.ClassRef(new Demangler.Ident((String)r2, new Demangler.TemplateArg[0]));
                qNames.remove(0);
                tr2.setEnclosingType(VC9Demangler.reverseNamespace(qNames));
                encl = tr2;
            } else {
                encl = VC9Demangler.reverseNamespace(qNames);
            }
            this.addBackRef(encl);
            mr2.setEnclosingType(encl);
            this.parseFunctionProperty(mr2);
            if (this.position != this.length) {
                this.error("Failed to demangle the whole symbol");
            }
        } else {
            mr2.setMemberName(new Demangler.Ident(this.str, new Demangler.TemplateArg[0]));
        }
        return mr2;
    }

    Demangler.TypeRef parseReturnType() throws Demangler.DemanglingException {
        Demangler.TypeRef tr2 = this.parseType(true);
        return tr2;
    }

    int parseNumber(boolean allowSign) throws Demangler.DemanglingException {
        char c2;
        int sign;
        int n2 = sign = allowSign && this.consumeCharIf('?') ? -1 : 1;
        if (Character.isDigit(this.peekChar())) {
            char c3 = this.consumeChar();
            return sign * (c3 - 48);
        }
        if (this.peekChar() == '@') {
            return 0;
        }
        StringBuilder b2 = new StringBuilder();
        long n3 = 0L;
        while ((c2 = this.consumeChar()) >= 'A' && c2 <= 'P' && c2 != '@') {
            n3 += (long)(16 * (c2 - 65));
        }
        String s2 = b2.toString().trim();
        if (c2 != '@' || s2.length() == 0) {
            throw this.error("Expected a number here", -b2.length());
        }
        return sign * Integer.parseInt(s2, 16);
    }

    Demangler.TypeRef consumeIfBackRef() throws Demangler.DemanglingException {
        char c2 = this.peekChar();
        if (Character.isDigit(c2)) {
            this.consumeChar();
            int iBack = c2 - 48;
            return this.getBackRef(iBack);
        }
        return null;
    }

    Demangler.TypeRef parseType(boolean allowVoid) throws Demangler.DemanglingException {
        Demangler.TypeRef backRef = this.consumeIfBackRef();
        if (backRef != null) {
            return backRef;
        }
        char c2 = this.consumeChar();
        switch (c2) {
            case '_': {
                Demangler.TypeRef tr2;
                switch (this.consumeChar()) {
                    case 'D': 
                    case 'E': {
                        tr2 = VC9Demangler.classType(Byte.TYPE, new Class[0]);
                        break;
                    }
                    case 'F': 
                    case 'G': {
                        tr2 = VC9Demangler.classType(Short.TYPE, new Class[0]);
                        break;
                    }
                    case 'H': 
                    case 'I': {
                        tr2 = VC9Demangler.classType(Integer.TYPE, new Class[0]);
                        break;
                    }
                    case 'J': 
                    case 'K': {
                        tr2 = VC9Demangler.classType(Long.TYPE, new Class[0]);
                        break;
                    }
                    case 'L': {
                        tr2 = VC9Demangler.classType(BigInteger.class, new Class[0]);
                        break;
                    }
                    case 'N': {
                        tr2 = VC9Demangler.classType(Boolean.TYPE, new Class[0]);
                        break;
                    }
                    case '0': {
                        this.parseCVClassModifier();
                        this.parseType(false);
                        tr2 = VC9Demangler.classType(Object[].class, new Class[0]);
                        break;
                    }
                    case 'W': {
                        tr2 = VC9Demangler.classType(Character.TYPE, new Class[0]);
                        break;
                    }
                    default: {
                        throw this.error(-1);
                    }
                }
                this.addBackRef(tr2);
                return tr2;
            }
            case 'Z': {
                return VC9Demangler.classType(Object[].class, new Class[0]);
            }
            case 'O': {
                throw this.error("'long double' type cannot be mapped !", -1);
            }
            case 'C': 
            case 'D': 
            case 'E': {
                return VC9Demangler.classType(Byte.TYPE, new Class[0]);
            }
            case 'F': 
            case 'G': {
                return VC9Demangler.classType(Short.TYPE, new Class[0]);
            }
            case 'H': 
            case 'I': {
                return VC9Demangler.classType(Integer.TYPE, new Class[0]);
            }
            case 'J': 
            case 'K': {
                return VC9Demangler.classType(CLong.class, new Class[0]);
            }
            case 'M': {
                return VC9Demangler.classType(Float.TYPE, new Class[0]);
            }
            case 'N': {
                return VC9Demangler.classType(Double.TYPE, new Class[0]);
            }
            case 'Y': {
                throw this.error("TODO handle cointerfaces", -1);
            }
            case 'X': {
                if (!allowVoid) {
                    return null;
                }
                return VC9Demangler.classType(Void.TYPE, new Class[0]);
            }
            case '?': {
                this.parseCVClassModifier();
                return this.parseType(allowVoid);
            }
            case 'A': 
            case 'B': 
            case 'P': 
            case 'Q': 
            case 'R': 
            case 'S': {
                Demangler.TypeRef tr3;
                if (!this.consumeCharsIf('$', 'A')) {
                    this.consumeCharsIf('$', 'B');
                }
                CVClassModifier cvMods = this.parseCVClassModifier();
                if (cvMods.isVariable) {
                    if (this.consumeCharIf('Y')) {
                        int dimensions = this.parseNumber(false);
                        int[] indices = new int[dimensions];
                        for (int i2 = 0; i2 < dimensions; ++i2) {
                            indices[i2] = this.parseNumber(false);
                        }
                    }
                    tr3 = VC9Demangler.pointerType(this.parseType(true));
                } else {
                    Demangler.MemberRef mr2 = new Demangler.MemberRef();
                    this.parseFunctionProperty(mr2);
                    tr3 = VC9Demangler.pointerType(new Demangler.FunctionTypeRef(mr2));
                }
                this.addBackRef(tr3);
                return tr3;
            }
            case 'T': 
            case 'U': 
            case 'V': {
                return this.parseQualifiedTypeName();
            }
            case 'W': {
                Class<Number> cl2;
                switch (this.consumeChar()) {
                    case '0': 
                    case '1': {
                        cl2 = Byte.TYPE;
                        break;
                    }
                    case '2': 
                    case '3': {
                        cl2 = Short.TYPE;
                        break;
                    }
                    case '4': 
                    case '5': {
                        cl2 = Integer.TYPE;
                        break;
                    }
                    case '6': 
                    case '7': {
                        cl2 = Integer.TYPE;
                        break;
                    }
                    default: {
                        throw this.error("Unfinished enum", -1);
                    }
                }
                Demangler.TypeRef qn = this.parseQualifiedTypeName();
                this.addBackRef(qn);
                return VC9Demangler.classType(cl2, new Class[0]);
            }
        }
        throw this.error(-1);
    }

    static Demangler.NamespaceRef reverseNamespace(List<Object> names) {
        if (names == null || names.isEmpty()) {
            return null;
        }
        Collections.reverse(names);
        return new Demangler.NamespaceRef(names.toArray());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    <T> T withEmptyQualifiedNames(DemanglingOp<T> action) throws Demangler.DemanglingException {
        List<Demangler.TypeRef> list = this.allQualifiedNames;
        try {
            this.allQualifiedNames = new ArrayList<Demangler.TypeRef>();
            T t2 = action.run();
            return t2;
        }
        finally {
            this.allQualifiedNames = list;
        }
    }

    Demangler.IdentLike parseFirstQualifiedTypeNameComponent() throws Demangler.DemanglingException {
        if (this.consumeCharIf('?')) {
            if (this.consumeCharIf('$')) {
                return this.parseTemplateType().getIdent();
            }
            return this.parseSpecialName();
        }
        return new Demangler.Ident(this.parseNameFragment(), new Demangler.TemplateArg[0]);
    }

    Demangler.TypeRef parseQualifiedTypeName() throws Demangler.DemanglingException {
        List<Object> names;
        Demangler.TypeRef backRef = this.consumeIfBackRef();
        if (backRef != null) {
            return backRef;
        }
        char c2 = this.peekChar();
        Object first = (names = this.parseNameQualifications()).get(0);
        names.set(0, first instanceof String ? new Demangler.Ident((String)first, new Demangler.TemplateArg[0]) : ((Demangler.ClassRef)first).getIdent());
        if (names.size() == 1 && names.get(0) instanceof Demangler.TypeRef) {
            return (Demangler.TypeRef)names.get(0);
        }
        Demangler.ClassRef tr2 = new Demangler.ClassRef((Demangler.Ident)names.get(0));
        names.remove(0);
        tr2.setEnclosingType(VC9Demangler.reverseNamespace(names));
        return tr2;
    }

    public Demangler.IdentLike parseSpecialName() throws Demangler.DemanglingException {
        switch (this.consumeChar()) {
            case '0': {
                return Demangler.SpecialName.Constructor;
            }
            case '1': {
                return Demangler.SpecialName.Destructor;
            }
            case '2': {
                return Demangler.SpecialName.New;
            }
            case '3': {
                return Demangler.SpecialName.Delete;
            }
            case '4': {
                return Demangler.SpecialName.OperatorAssign;
            }
            case '5': {
                return Demangler.SpecialName.OperatorRShift;
            }
            case '6': {
                return Demangler.SpecialName.OperatorLShift;
            }
            case '7': {
                return Demangler.SpecialName.OperatorLogicNot;
            }
            case '8': {
                return Demangler.SpecialName.OperatorEquals;
            }
            case '9': {
                return Demangler.SpecialName.OperatorDifferent;
            }
            case 'A': {
                return Demangler.SpecialName.OperatorSquareBrackets;
            }
            case 'B': {
                return Demangler.SpecialName.OperatorCast;
            }
            case 'C': {
                return Demangler.SpecialName.OperatorArrow;
            }
            case 'D': {
                return Demangler.SpecialName.OperatorMultiply;
            }
            case 'E': {
                return Demangler.SpecialName.OperatorIncrement;
            }
            case 'F': {
                return Demangler.SpecialName.OperatorDecrement;
            }
            case 'G': {
                return Demangler.SpecialName.OperatorSubstract;
            }
            case 'H': {
                return Demangler.SpecialName.OperatorAdd;
            }
            case 'I': {
                return Demangler.SpecialName.OperatorBitAnd;
            }
            case 'J': {
                return Demangler.SpecialName.OperatorArrowStar;
            }
            case 'K': {
                return Demangler.SpecialName.OperatorDivide;
            }
            case 'L': {
                return Demangler.SpecialName.OperatorModulo;
            }
            case 'M': {
                return Demangler.SpecialName.OperatorLower;
            }
            case 'N': {
                return Demangler.SpecialName.OperatorLowerEquals;
            }
            case 'O': {
                return Demangler.SpecialName.OperatorGreater;
            }
            case 'P': {
                return Demangler.SpecialName.OperatorGreaterEquals;
            }
            case 'Q': {
                return Demangler.SpecialName.OperatorComma;
            }
            case 'R': {
                return Demangler.SpecialName.OperatorParenthesis;
            }
            case 'S': {
                return Demangler.SpecialName.OperatorBitNot;
            }
            case 'T': {
                return Demangler.SpecialName.OperatorXOR;
            }
            case 'U': {
                return Demangler.SpecialName.OperatorBitOr;
            }
            case 'V': {
                return Demangler.SpecialName.OperatorLogicAnd;
            }
            case 'W': {
                return Demangler.SpecialName.OperatorLogicOr;
            }
            case 'X': {
                return Demangler.SpecialName.OperatorMultiplyAssign;
            }
            case 'Y': {
                return Demangler.SpecialName.OperatorAddAssign;
            }
            case 'Z': {
                return Demangler.SpecialName.OperatorSubstractAssign;
            }
            case '_': {
                switch (this.consumeChar()) {
                    case '0': {
                        return Demangler.SpecialName.OperatorDivideAssign;
                    }
                    case '1': {
                        return Demangler.SpecialName.OperatorModuloAssign;
                    }
                    case '2': {
                        return Demangler.SpecialName.OperatorLShiftAssign;
                    }
                    case '3': {
                        return Demangler.SpecialName.OperatorRShiftAssign;
                    }
                    case '4': {
                        return Demangler.SpecialName.OperatorBitAndAssign;
                    }
                    case '5': {
                        return Demangler.SpecialName.OperatorBitOrAssign;
                    }
                    case '6': {
                        return Demangler.SpecialName.OperatorXORAssign;
                    }
                    case '7': {
                        return Demangler.SpecialName.VFTable;
                    }
                    case '8': {
                        return Demangler.SpecialName.VBTable;
                    }
                    case '9': {
                        return Demangler.SpecialName.VCall;
                    }
                    case 'E': {
                        return Demangler.SpecialName.VectorDeletingDestructor;
                    }
                    case 'G': {
                        return Demangler.SpecialName.ScalarDeletingDestructor;
                    }
                }
                throw this.error("unhandled extended special name");
            }
        }
        throw this.error("Invalid special name");
    }

    private List<Demangler.TypeRef> parseParams() throws Demangler.DemanglingException {
        ArrayList<Demangler.TypeRef> paramTypes = new ArrayList<Demangler.TypeRef>();
        if (!this.consumeCharIf('X')) {
            char c2;
            while ((c2 = this.peekChar()) != '@' && c2 != '\u0000' && (c2 != 'Z' || this.peekChar(2) == 'Z')) {
                Demangler.TypeRef tr2 = this.parseType(false);
                if (tr2 == null) continue;
                paramTypes.add(tr2);
            }
            if (c2 == 'Z') {
                this.consumeChar();
            }
            if (c2 == '@') {
                this.consumeChar();
            }
        }
        return paramTypes;
    }

    private List<Demangler.TemplateArg> parseTemplateParams() throws Demangler.DemanglingException {
        return this.withEmptyQualifiedNames(new DemanglingOp<List<Demangler.TemplateArg>>(){

            @Override
            public List<Demangler.TemplateArg> run() throws Demangler.DemanglingException {
                ArrayList<Demangler.TemplateArg> paramTypes = new ArrayList<Demangler.TemplateArg>();
                if (!VC9Demangler.this.consumeCharIf('X')) {
                    char c2;
                    while ((c2 = VC9Demangler.this.peekChar()) != '@' && c2 != '\u0000') {
                        Demangler.TemplateArg tr2 = VC9Demangler.this.parseTemplateParameter();
                        if (tr2 == null) continue;
                        paramTypes.add(tr2);
                    }
                }
                return paramTypes;
            }
        });
    }

    String parseNameFragment() throws Demangler.DemanglingException {
        char c2;
        StringBuilder b2 = new StringBuilder();
        while ((c2 = this.consumeChar()) != '@') {
            b2.append(c2);
        }
        if (b2.length() == 0) {
            throw new Demangler.DemanglingException("Unexpected empty name fragment");
        }
        String name = b2.toString();
        return name;
    }

    void addBackRef(Demangler.TypeRef tr2) {
        if (tr2 == null || this.allQualifiedNames.contains(tr2)) {
            return;
        }
        this.allQualifiedNames.add(tr2);
    }

    Demangler.TypeRef getBackRef(int i2) throws Demangler.DemanglingException {
        if (i2 == this.allQualifiedNames.size()) {
            --i2;
        }
        if (i2 < 0 || i2 >= this.allQualifiedNames.size()) {
            throw this.error("Invalid back references in name qualifications", -1);
        }
        return this.allQualifiedNames.get(i2);
    }

    private List<Object> parseNameQualifications() throws Demangler.DemanglingException {
        ArrayList<Object> names = new ArrayList<Object>();
        if (Character.isDigit(this.peekChar())) {
            try {
                int i2 = this.consumeChar() - 48;
                names.add(this.getBackRef(i2));
                this.expectChars('@');
                return names;
            }
            catch (Exception ex2) {
                throw this.error("Invalid back references in name qualifications", -1);
            }
        }
        while (this.peekChar() != '@') {
            names.add(this.parseNameQualification());
        }
        this.expectChars('@');
        return names;
    }

    Object parseNameQualification() throws Demangler.DemanglingException {
        if (this.consumeCharIf('?')) {
            if (this.consumeCharIf('$')) {
                return this.parseTemplateType();
            }
            if (this.peekChar() == 'A') {
                throw this.error("Anonymous numbered namespaces not handled yet");
            }
            int namespaceNumber = this.parseNumber(false);
            return String.valueOf(namespaceNumber);
        }
        return this.parseNameFragment();
    }

    Convention.Style parseCallingConvention() throws Demangler.DemanglingException {
        Convention.Style cc2;
        boolean exported = true;
        switch (this.consumeChar()) {
            case 'A': {
                exported = false;
            }
            case 'B': {
                cc2 = Convention.Style.CDecl;
                break;
            }
            case 'C': {
                exported = false;
            }
            case 'D': {
                cc2 = Convention.Style.Pascal;
                break;
            }
            case 'E': {
                exported = false;
            }
            case 'F': {
                cc2 = Convention.Style.ThisCall;
                break;
            }
            case 'G': {
                exported = false;
            }
            case 'H': {
                cc2 = Convention.Style.StdCall;
                break;
            }
            case 'I': {
                exported = false;
            }
            case 'J': {
                cc2 = Convention.Style.FastCall;
                break;
            }
            case 'K': {
                exported = false;
            }
            case 'L': {
                cc2 = null;
                break;
            }
            case 'N': {
                cc2 = Convention.Style.CLRCall;
                break;
            }
            default: {
                throw this.error("Unknown calling convention");
            }
        }
        return cc2;
    }

    CVClassModifier parseCVClassModifier() throws Demangler.DemanglingException {
        CVClassModifier mod = new CVClassModifier();
        switch (this.peekChar()) {
            case 'E': 
            case 'F': 
            case 'I': {
                this.consumeChar();
            }
        }
        boolean based = false;
        block3 : switch (this.consumeChar()) {
            case 'M': 
            case 'N': 
            case 'O': 
            case 'P': {
                mod.isBased = true;
            }
            case 'A': 
            case 'B': 
            case 'C': 
            case 'D': 
            case 'G': 
            case 'H': 
            case 'J': 
            case 'K': 
            case 'L': {
                mod.isVariable = true;
                mod.isMember = false;
                break;
            }
            case '2': 
            case '3': 
            case '4': 
            case '5': {
                mod.isBased = true;
            }
            case '0': 
            case '1': 
            case 'Q': 
            case 'R': 
            case 'S': 
            case 'T': 
            case 'U': 
            case 'V': 
            case 'W': 
            case 'X': 
            case 'Y': 
            case 'Z': {
                mod.isVariable = true;
                mod.isMember = true;
                break;
            }
            case '_': {
                mod.isBased = true;
                switch (this.consumeChar()) {
                    case 'A': 
                    case 'B': {
                        mod.isVariable = false;
                        break block3;
                    }
                    case 'C': 
                    case 'D': {
                        mod.isVariable = false;
                        mod.isMember = true;
                        break block3;
                    }
                }
                throw this.error("Unknown extended __based class modifier", -1);
            }
            case '6': 
            case '7': {
                mod.isVariable = false;
                mod.isMember = false;
                break;
            }
            case '8': 
            case '9': {
                mod.isVariable = false;
                mod.isMember = true;
                break;
            }
            default: {
                throw this.error("Unknown CV class modifier", -1);
            }
        }
        if (mod.isBased) {
            switch (this.consumeChar()) {
                case '0': {
                    break;
                }
                case '2': {
                    this.parseNameQualifications();
                    break;
                }
            }
        }
        return mod;
    }

    static class CVClassModifier {
        boolean isVariable;
        boolean isMember;
        boolean isBased;

        CVClassModifier() {
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static interface DemanglingOp<T> {
        public T run() throws Demangler.DemanglingException;
    }

    static class AccessLevelAndStorageClass {
        int modifiers;
        boolean isVirtual = false;
        boolean isThunk = false;

        AccessLevelAndStorageClass() {
        }
    }

    static class AnonymousTemplateArg
    implements Demangler.TemplateArg {
        String v;

        public AnonymousTemplateArg(String v2) {
            this.v = v2;
        }

        public boolean matchesParam(Object param, Demangler.Annotations annotations) {
            return true;
        }

        public String toString() {
            return this.v;
        }
    }
}

