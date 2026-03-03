/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.demangling;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import org.bridj.BridJ;
import org.bridj.CLong;
import org.bridj.CRuntime;
import org.bridj.FlagSet;
import org.bridj.NativeLibrary;
import org.bridj.NativeObject;
import org.bridj.Platform;
import org.bridj.Pointer;
import org.bridj.SizeT;
import org.bridj.TimeT;
import org.bridj.ValuedEnum;
import org.bridj.ann.Convention;
import org.bridj.ann.Name;
import org.bridj.ann.Ptr;
import org.bridj.ann.Template;
import org.bridj.demangling.GCC4Demangler;
import org.bridj.demangling.VC9Demangler;
import org.bridj.util.AnnotationUtils;
import org.bridj.util.DefaultParameterizedType;
import org.bridj.util.Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class Demangler {
    protected final String str;
    protected final int length;
    protected int position = 0;
    protected final NativeLibrary library;

    public static void main(String[] args) {
        for (String arg2 : args) {
            try {
                System.out.println("VC9: " + new VC9Demangler(null, arg2).parseSymbol());
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            try {
                System.out.println("GCC4: " + new GCC4Demangler(null, arg2).parseSymbol());
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
        }
    }

    public static Annotations annotations(final Annotation[] aa2) {
        return new Annotations(){

            @Override
            public <A extends Annotation> A getAnnotation(Class<A> c2) {
                if (aa2 == null) {
                    return null;
                }
                for (Annotation a2 : aa2) {
                    if (!c2.isInstance(a2)) continue;
                    return (A)a2;
                }
                return null;
            }

            @Override
            public boolean isAnnotationPresent(Class<? extends Annotation> c2) {
                return AnnotationUtils.isAnnotationPresent(c2, aa2);
            }
        };
    }

    public static Annotations annotations(Type e2) {
        return Demangler.annotations(Utils.getClass(e2));
    }

    public static Annotations annotations(final AnnotatedElement e2) {
        return new Annotations(){

            @Override
            public <A extends Annotation> A getAnnotation(Class<A> c2) {
                return e2.getAnnotation(c2);
            }

            @Override
            public boolean isAnnotationPresent(Class<? extends Annotation> c2) {
                return AnnotationUtils.isAnnotationPresent(c2, e2, new Annotation[0]);
            }
        };
    }

    public abstract MemberRef parseSymbol() throws DemanglingException;

    public Demangler(NativeLibrary library, String str) {
        this.str = str;
        this.length = str.length();
        this.library = library;
    }

    public String getString() {
        return this.str;
    }

    protected void expectChars(char ... cs2) throws DemanglingException {
        for (char c2 : cs2) {
            char cc2 = this.consumeChar();
            if (cc2 == c2) continue;
            throw this.error("Expected char '" + c2 + "', found '" + cc2 + "'");
        }
    }

    protected void expectAnyChar(char ... cs2) throws DemanglingException {
        char cc2 = this.consumeChar();
        for (char c2 : cs2) {
            if (cc2 != c2) continue;
            return;
        }
        throw this.error("Expected any of " + Arrays.toString(cs2) + ", found '" + cc2 + "'");
    }

    public static StringBuilder implode(StringBuilder b2, Object[] items, String sep) {
        return Demangler.implode(b2, Arrays.asList(items), sep);
    }

    public static StringBuilder implode(StringBuilder b2, Iterable<?> items, String sep) {
        boolean first = true;
        for (Object item : items) {
            if (first) {
                first = false;
            } else {
                b2.append(sep);
            }
            b2.append(item);
        }
        return b2;
    }

    protected char peekChar() {
        return this.peekChar(1);
    }

    protected char peekChar(int dist) {
        int p2 = this.position + dist - 1;
        return p2 >= this.length ? (char)'\u0000' : this.str.charAt(p2);
    }

    protected char lastChar() {
        return this.position == 0 ? (char)'\u0000' : this.str.charAt(this.position - 1);
    }

    protected char consumeChar() throws DemanglingException {
        char c2 = this.peekChar();
        if (c2 != '\u0000') {
            ++this.position;
        } else {
            throw new DemanglingException("Reached end of string '" + this.str + "'");
        }
        return c2;
    }

    protected boolean consumeCharsIf(char ... nextChars) {
        int initialPosition = this.position;
        try {
            for (char c2 : nextChars) {
                char cc2 = this.consumeChar();
                if (cc2 == c2) continue;
                this.position = initialPosition;
                return false;
            }
            return true;
        }
        catch (DemanglingException ex2) {
            this.position = initialPosition;
            return false;
        }
    }

    protected boolean consumeCharIf(char ... allowedChars) {
        char c2 = this.peekChar();
        for (char allowedChar : allowedChars) {
            if (allowedChar != c2) continue;
            ++this.position;
            return true;
        }
        return false;
    }

    protected DemanglingException error(int deltaPosition) {
        return this.error(null, deltaPosition);
    }

    protected DemanglingException error(String mess) {
        return this.error(mess, -1);
    }

    protected DemanglingException error(String mess, int deltaPosition) {
        StringBuilder err = new StringBuilder(this.position + 1);
        int position = this.position + deltaPosition;
        for (int i2 = 0; i2 < position; ++i2) {
            err.append(' ');
        }
        err.append('^');
        return new DemanglingException("Parsing error at position " + position + (mess == null ? "" : ": " + mess) + " \n\t" + this.str + "\n\t" + err);
    }

    public static String getMethodName(Method method) {
        Name nameAnn = method.getAnnotation(Name.class);
        if (nameAnn != null) {
            return nameAnn.value();
        }
        return method.getName();
    }

    protected static TypeRef pointerType(TypeRef tr2) {
        return new PointerTypeRef(tr2);
    }

    protected static TypeRef classType(Class<?> c2, Class<? extends Annotation> ... annotations) {
        return Demangler.classType(c2, null, annotations);
    }

    protected static TypeRef classType(Class<?> c2, Type[] genericTypes, Class<? extends Annotation> ... annotations) {
        JavaTypeRef tr2 = new JavaTypeRef();
        tr2.type = genericTypes == null ? c2 : new DefaultParameterizedType(c2, genericTypes);
        tr2.annotations = annotations;
        return tr2;
    }

    protected static TypeRef simpleType(String name, TemplateArg ... args) {
        return new ClassRef(new Ident(name, args));
    }

    protected static TypeRef simpleType(Ident ident) {
        return new ClassRef(ident);
    }

    static Class<?> getTypeClass(Type type) {
        if (type instanceof Class) {
            return (Class)type;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType pt2 = (ParameterizedType)type;
            Class<Integer> c2 = (Class<Integer>)pt2.getRawType();
            if (ValuedEnum.class.isAssignableFrom(c2)) {
                Type[] types = pt2.getActualTypeArguments();
                c2 = types == null || types.length != 1 ? Integer.TYPE : Demangler.getTypeClass(pt2.getActualTypeArguments()[0]);
            }
            return c2;
        }
        if (type instanceof GenericArrayType && Object.class.isAssignableFrom(Demangler.getTypeClass(((GenericArrayType)type).getGenericComponentType()))) {
            return Object[].class;
        }
        throw new UnsupportedOperationException("Unknown type type : " + type.getClass().getName());
    }

    static boolean equivalentTypes(Type a2, Class ac2, Annotations aAnnotations, Type b2, Class bc2, Annotations bAnnotations) {
        if (a2.equals(b2)) {
            return true;
        }
        int as2 = Demangler.getIntegralSize(a2, ac2, aAnnotations);
        int bs2 = Demangler.getIntegralSize(b2, bc2, bAnnotations);
        return as2 != -1 && as2 == bs2;
    }

    static int getIntegralSize(Type type, Class typec, Annotations annotations) {
        if (type == Integer.TYPE || type == Integer.class) {
            return 4;
        }
        if (type == Long.TYPE || type == Long.class) {
            if (annotations != null) {
                if (annotations.isAnnotationPresent(Ptr.class)) {
                    return SizeT.SIZE;
                }
                if (annotations.isAnnotationPresent(org.bridj.ann.CLong.class)) {
                    return CLong.SIZE;
                }
            }
            return 8;
        }
        if (type == CLong.class) {
            return CLong.SIZE;
        }
        if (type == SizeT.class) {
            return SizeT.SIZE;
        }
        if (type == TimeT.class) {
            return TimeT.SIZE;
        }
        if (type == Byte.TYPE || type == Byte.class) {
            return 1;
        }
        if (type == Character.TYPE || type == Character.class || type == Short.TYPE || type == Short.class) {
            return 2;
        }
        if (ValuedEnum.class.isAssignableFrom(typec)) {
            return 4;
        }
        if (Pointer.class.isAssignableFrom(typec)) {
            return SizeT.SIZE;
        }
        return -1;
    }

    public static boolean equivalentTypes(Type a2, Type b2) {
        return Demangler.equivalentTypes(a2, Demangler.getTypeClass(a2), null, b2, Demangler.getTypeClass(b2), null);
    }

    static void appendTemplateArgs(StringBuilder b2, Object[] params) {
        if (params == null || params.length == 0) {
            return;
        }
        Demangler.appendArgs(b2, '<', '>', params);
    }

    static void appendArgs(StringBuilder b2, char pre, char post, Object[] params) {
        b2.append(pre);
        if (params != null) {
            for (int i2 = 0; i2 < params.length; ++i2) {
                if (i2 != 0) {
                    b2.append(", ");
                }
                b2.append(params[i2]);
            }
        }
        b2.append(post);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class MemberRef {
        private Integer argumentsStackSize;
        private TypeRef enclosingType;
        private TypeRef valueType;
        private IdentLike memberName;
        Boolean isStatic;
        Boolean isProtected;
        Boolean isPrivate;
        public int modifiers;
        public TypeRef[] paramTypes;
        public TypeRef[] throwTypes;
        TemplateArg[] templateArguments;
        public Convention.Style callingConvention;

        public void setTemplateArguments(TemplateArg[] templateArguments) {
            this.templateArguments = templateArguments;
        }

        public Integer getArgumentsStackSize() {
            return this.argumentsStackSize;
        }

        public void setArgumentsStackSize(Integer argumentsStackSize) {
            this.argumentsStackSize = argumentsStackSize;
        }

        protected boolean matchesEnclosingType(Type type) {
            return this.getEnclosingType() != null && this.getEnclosingType().matches(type, Demangler.annotations(type));
        }

        protected boolean matchesVirtualTable(Type type) {
            return this.memberName == SpecialName.VFTable && this.matchesEnclosingType(type);
        }

        protected boolean matchesConstructor(Type type, Constructor<?> constr) {
            int overrideOffset;
            if (this.memberName != SpecialName.Constructor) {
                return false;
            }
            if (!this.matchesEnclosingType(type)) {
                return false;
            }
            Template temp = Utils.getClass(type).getAnnotation(Template.class);
            Annotation[][] anns = constr.getParameterAnnotations();
            Type[] parameterTypes = constr.getGenericParameterTypes();
            return this.matchesArgs(parameterTypes, anns, (overrideOffset = Utils.getEnclosedConstructorParametersOffset(constr)) + (temp == null ? 0 : temp.value().length));
        }

        protected boolean matchesDestructor(Type type) {
            return this.memberName == SpecialName.Destructor && this.matchesEnclosingType(type);
        }

        static boolean hasInstance(Object[] array, Class<? extends Annotation> ... cs2) {
            for (Object o2 : array) {
                for (Class<? extends Annotation> c2 : cs2) {
                    if (!c2.isInstance(o2)) continue;
                    return true;
                }
            }
            return false;
        }

        static int getArgumentsStackSize(Method method) {
            int total = 0;
            Type[] paramTypes = method.getGenericParameterTypes();
            Annotation[][] anns = method.getParameterAnnotations();
            int nArgs = paramTypes.length;
            for (int iArg = 0; iArg < nArgs; ++iArg) {
                Class<?> paramType = Demangler.getTypeClass(paramTypes[iArg]);
                if (paramType == Integer.TYPE) {
                    total += 4;
                    continue;
                }
                if (paramType == Long.TYPE) {
                    Annotation[] as2 = anns[iArg];
                    if (AnnotationUtils.isAnnotationPresent(Ptr.class, as2) || AnnotationUtils.isAnnotationPresent(org.bridj.ann.CLong.class, as2)) {
                        total += Pointer.SIZE;
                        continue;
                    }
                    total += 8;
                    continue;
                }
                if (paramType == Float.TYPE) {
                    total += 4;
                    continue;
                }
                if (paramType == Double.TYPE) {
                    total += 8;
                    continue;
                }
                if (paramType == Byte.TYPE) {
                    ++total;
                    continue;
                }
                if (paramType == Character.TYPE) {
                    total += Platform.WCHAR_T_SIZE;
                    continue;
                }
                if (paramType == CLong.class) {
                    total += Platform.CLONG_SIZE;
                    continue;
                }
                if (paramType == SizeT.class) {
                    total += Platform.SIZE_T_SIZE;
                    continue;
                }
                if (paramType == TimeT.class) {
                    total += Platform.TIME_T_SIZE;
                    continue;
                }
                if (paramType == Short.TYPE) {
                    total += 2;
                    continue;
                }
                if (paramType == Boolean.TYPE) {
                    ++total;
                    continue;
                }
                if (Pointer.class.isAssignableFrom(paramType)) {
                    total += Pointer.SIZE;
                    continue;
                }
                if (NativeObject.class.isAssignableFrom(paramType)) {
                    total = (int)((long)total + ((CRuntime)BridJ.getRuntime(paramType)).sizeOf(paramTypes[iArg], null));
                    continue;
                }
                if (FlagSet.class.isAssignableFrom(paramType)) {
                    total += 4;
                    continue;
                }
                throw new RuntimeException("Type not handled : " + paramType.getName());
            }
            return total;
        }

        protected boolean matches(Method method) {
            if (this.memberName instanceof SpecialName) {
                return false;
            }
            if (!this.matchesEnclosingType(method)) {
                return false;
            }
            return this.matchesSignature(method);
        }

        public boolean matchesEnclosingType(Method method) {
            TypeRef et2 = this.getEnclosingType();
            if (et2 == null) {
                return true;
            }
            Annotations annotations = Demangler.annotations(method);
            Class<?> dc2 = method.getDeclaringClass();
            do {
                if (!et2.matches(dc2, annotations)) continue;
                return true;
            } while ((dc2 = dc2.getSuperclass()) != null && dc2 != Object.class);
            return false;
        }

        public boolean matchesSignature(Method method) {
            if (this.getArgumentsStackSize() != null && this.getArgumentsStackSize() != MemberRef.getArgumentsStackSize(method)) {
                return false;
            }
            if (this.getMemberName() != null && !this.getMemberName().toString().equals(Demangler.getMethodName(method))) {
                return false;
            }
            if (this.getValueType() != null && !this.getValueType().matches(method.getReturnType(), Demangler.annotations(method))) {
                return false;
            }
            Template temp = method.getAnnotation(Template.class);
            Annotation[][] anns = method.getParameterAnnotations();
            Type[] parameterTypes = method.getGenericParameterTypes();
            return this.paramTypes == null || this.matchesArgs(parameterTypes, anns, temp == null ? 0 : temp.value().length);
        }

        private boolean matchesArgs(Type[] parameterTypes, Annotation[][] anns, int offset) {
            Object paramType;
            int i2;
            int n2;
            int totalArgs = offset;
            int n3 = n2 = this.templateArguments == null ? 0 : this.templateArguments.length;
            for (i2 = 0; i2 < n2; ++i2) {
                if (totalArgs >= parameterTypes.length) {
                    return false;
                }
                paramType = parameterTypes[offset + i2];
                TemplateArg arg2 = this.templateArguments[i2];
                if (arg2 instanceof TypeRef) {
                    if (!paramType.equals(Class.class)) {
                        return false;
                    }
                } else if (arg2 instanceof Constant) {
                    try {
                        Demangler.getTypeClass((Type)paramType).cast(((Constant)arg2).value);
                    }
                    catch (ClassCastException ex2) {
                        return false;
                    }
                }
                ++totalArgs;
            }
            int n4 = n2 = this.paramTypes == null ? 0 : this.paramTypes.length;
            for (i2 = 0; i2 < n2; ++i2) {
                if (totalArgs >= parameterTypes.length) {
                    return false;
                }
                paramType = this.paramTypes[i2];
                Type parameterType = parameterTypes[totalArgs];
                if (!((TypeRef)paramType).matches(parameterType, Demangler.annotations(anns == null ? null : anns[i2]))) {
                    return false;
                }
                ++totalArgs;
            }
            if (totalArgs != parameterTypes.length) {
                System.err.println("Not enough args for " + this);
                return false;
            }
            return true;
        }

        public String toString() {
            StringBuilder b2 = new StringBuilder();
            b2.append(this.valueType).append(' ');
            boolean nameWritten = false;
            if (this.enclosingType != null) {
                b2.append(this.enclosingType);
                b2.append('.');
                if (this.memberName instanceof SpecialName) {
                    switch ((SpecialName)this.memberName) {
                        case Destructor: {
                            b2.append('~');
                        }
                        case Constructor: {
                            b2.append(((ClassRef)this.enclosingType).ident.simpleName);
                            nameWritten = true;
                        }
                    }
                }
            }
            if (!nameWritten) {
                b2.append(this.memberName);
            }
            Demangler.appendTemplateArgs(b2, this.templateArguments);
            Demangler.appendArgs(b2, '(', ')', this.paramTypes);
            return b2.toString();
        }

        public void setMemberName(IdentLike memberName) {
            this.memberName = memberName;
        }

        public IdentLike getMemberName() {
            return this.memberName;
        }

        public void setValueType(TypeRef valueType) {
            this.valueType = valueType;
        }

        public TypeRef getValueType() {
            return this.valueType;
        }

        public void setEnclosingType(TypeRef enclosingType) {
            this.enclosingType = enclosingType;
        }

        public TypeRef getEnclosingType() {
            return this.enclosingType;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum SpecialName implements IdentLike
    {
        Constructor("", true, true),
        SpecialConstructor("", true, true),
        Destructor("", true, true),
        SelfishDestructor("", true, true),
        DeletingDestructor("", true, true),
        New("new", true, true),
        Delete("delete", true, true),
        NewArray("new[]", true, true),
        DeleteArray("delete[]", true, true),
        VFTable("vftable", false, true),
        VBTable("vbtable", false, true),
        VCall("vcall", false, false),
        TypeOf("typeof", false, false),
        ScalarDeletingDestructor("'scalar deleting destructor'", true, true),
        VectorDeletingDestructor("'vector deleting destructor'", true, true),
        OperatorAssign("operator=", true, true),
        OperatorRShift("operator>>", true, true),
        OperatorDivideAssign("operator/=", true, true),
        OperatorModuloAssign("operator%=", true, true),
        OperatorRShiftAssign("operator>>=", true, true),
        OperatorLShiftAssign("operator<<=", true, true),
        OperatorBitAndAssign("operator&=", true, true),
        OperatorBitOrAssign("operator|=", true, true),
        OperatorXORAssign("operator^=", true, true),
        OperatorLShift("operator<<", true, true),
        OperatorLogicNot("operator!", true, true),
        OperatorEquals("operator==", true, true),
        OperatorDifferent("operator!=", true, true),
        OperatorSquareBrackets("operator[]", true, true),
        OperatorCast("'some cast operator'", true, true),
        OperatorArrow("operator->", true, true),
        OperatorMultiply("operator*", true, true),
        OperatorIncrement("operator++", true, true),
        OperatorDecrement("operator--", true, true),
        OperatorSubstract("operator-", true, true),
        OperatorAdd("operator+", true, true),
        OperatorBitAnd("operator&=", true, true),
        OperatorArrowStar("operator->*", true, true),
        OperatorDivide("operator/", true, true),
        OperatorModulo("operator%", true, true),
        OperatorLower("operator<", true, true),
        OperatorLowerEquals("operator<=", true, true),
        OperatorGreater("operator>", true, true),
        OperatorGreaterEquals("operator>=", true, true),
        OperatorComma("operator,", true, true),
        OperatorParenthesis("operator()", true, true),
        OperatorBitNot("operator~", true, true),
        OperatorXOR("operator^", true, true),
        OperatorBitOr("operator|", true, true),
        OperatorLogicAnd("operator&&", true, true),
        OperatorLogicOr("operator||", true, true),
        OperatorMultiplyAssign("operator*=", true, true),
        OperatorAddAssign("operator+=", true, true),
        OperatorSubstractAssign("operator-=", true, true);

        final String name;
        final boolean isFunction;
        final boolean isMember;

        private SpecialName(String name, boolean isFunction, boolean isMember) {
            this.name = name;
            this.isFunction = isFunction;
            this.isMember = isMember;
        }

        public String toString() {
            return this.name;
        }

        public boolean isFunction() {
            return this.isFunction;
        }

        public boolean isMember() {
            return this.isMember;
        }
    }

    public static class FunctionTypeRef
    extends TypeRef {
        MemberRef function;

        public FunctionTypeRef(MemberRef function) {
            this.function = function;
        }

        public StringBuilder getQualifiedName(StringBuilder b2, boolean generic) {
            return null;
        }

        public String toString() {
            return this.function.toString();
        }
    }

    public static class ClassRef
    extends TypeRef {
        private TypeRef enclosingType;
        final Ident ident;

        public ClassRef(Ident ident) {
            this.ident = ident;
        }

        public StringBuilder getQualifiedName(StringBuilder b2, boolean generic) {
            if (this.getEnclosingType() instanceof ClassRef) {
                this.getEnclosingType().getQualifiedName(b2, generic).append('$');
            } else if (this.getEnclosingType() instanceof NamespaceRef) {
                this.getEnclosingType().getQualifiedName(b2, generic).append('.');
            }
            b2.append(this.ident.simpleName);
            if (generic && this.ident.templateArguments != null) {
                int args = 0;
                for (TemplateArg arg2 : this.ident.templateArguments) {
                    if (!(arg2 instanceof TypeRef)) continue;
                    if (args == 0) {
                        b2.append('<');
                    } else {
                        b2.append(", ");
                    }
                    ((TypeRef)arg2).getQualifiedName(b2, generic);
                    ++args;
                }
                if (args > 0) {
                    b2.append('>');
                }
            }
            return b2;
        }

        public Ident getIdent() {
            return this.ident;
        }

        public void setEnclosingType(TypeRef enclosingType) {
            this.enclosingType = enclosingType;
        }

        public TypeRef getEnclosingType() {
            return this.enclosingType;
        }

        public boolean matches(Type type, Annotations annotations) {
            return Demangler.getTypeClass(type).getSimpleName().equals(this.ident.simpleName);
        }

        public String toString() {
            StringBuilder b2 = new StringBuilder();
            if (this.enclosingType != null) {
                b2.append(this.enclosingType).append('.');
            }
            b2.append(this.ident);
            return b2.toString();
        }
    }

    public static class Ident
    implements IdentLike {
        Object simpleName;
        TemplateArg[] templateArguments;

        public Ident(String simpleName, TemplateArg ... templateArguments) {
            this.simpleName = simpleName;
            this.templateArguments = templateArguments;
        }

        public boolean equals(Object o2) {
            if (o2 == null || !(o2 instanceof Ident)) {
                return false;
            }
            Ident ident = (Ident)o2;
            if (!this.simpleName.equals(ident.simpleName)) {
                return false;
            }
            int n2 = this.templateArguments.length;
            if (ident.templateArguments.length != n2) {
                return false;
            }
            for (int i2 = 0; i2 < n2; ++i2) {
                if (this.templateArguments[i2].equals(ident.templateArguments[i2])) continue;
                return false;
            }
            return true;
        }

        public String toString() {
            StringBuilder b2 = new StringBuilder();
            b2.append(this.simpleName);
            Demangler.appendTemplateArgs(b2, this.templateArguments);
            return b2.toString();
        }
    }

    public static interface IdentLike {
    }

    public static class JavaTypeRef
    extends TypeRef {
        Type type;
        Class<? extends Annotation>[] annotations;

        public StringBuilder getQualifiedName(StringBuilder b2, boolean generic) {
            return b2.append(Demangler.getTypeClass(this.type).getName());
        }

        public boolean matches(Type type, Annotations annotations) {
            Class<?> typec;
            Class<?> tc2 = Demangler.getTypeClass(this.type);
            if (tc2 == (typec = Demangler.getTypeClass(type)) || tc2.equals(typec)) {
                return true;
            }
            if (type == Long.TYPE && Pointer.class.isAssignableFrom(tc2) || Pointer.class.isAssignableFrom(typec) && tc2 == Long.TYPE) {
                return true;
            }
            return Demangler.equivalentTypes(type, typec, annotations, this.type, tc2, null);
        }

        public String toString() {
            StringBuilder b2 = new StringBuilder();
            for (Class<? extends Annotation> ann2 : this.annotations) {
                b2.append(ann2.getSimpleName()).append(' ');
            }
            b2.append(this.type instanceof Class ? ((Class)this.type).getSimpleName() : this.type.toString());
            return b2.toString();
        }
    }

    public static class PointerTypeRef
    extends TypeRef {
        public TypeRef pointedType;

        public PointerTypeRef(TypeRef pointedType) {
            this.pointedType = pointedType;
        }

        public StringBuilder getQualifiedName(StringBuilder b2, boolean generic) {
            return b2.append("org.bridj.Pointer");
        }

        public String toString() {
            return this.pointedType + "*";
        }
    }

    public static class NamespaceRef
    extends TypeRef {
        Object[] namespace;

        public NamespaceRef(Object ... namespace) {
            this.namespace = namespace;
        }

        public StringBuilder getQualifiedName(StringBuilder b2, boolean generic) {
            return Demangler.implode(b2, this.namespace, ".");
        }

        public String toString() {
            return this.getQualifiedName(new StringBuilder(), true).toString();
        }
    }

    public static class Constant
    implements TemplateArg {
        Object value;

        public Constant(Object value) {
            this.value = value;
        }

        public boolean matchesParam(Object param, Annotations annotations) {
            return this.value.equals(param);
        }

        public String toString() {
            return this.value.toString();
        }
    }

    public static abstract class TypeRef
    implements TemplateArg {
        public abstract StringBuilder getQualifiedName(StringBuilder var1, boolean var2);

        public boolean matchesParam(Object param, Annotations annotations) {
            return param instanceof Type && this.matches((Type)param, annotations);
        }

        public boolean matches(Type type, Annotations annotations) {
            Class<?> typeClass;
            String typeName;
            String thisName = this.getQualifiedName(new StringBuilder(), false).toString();
            return thisName.equals(typeName = (typeClass = Demangler.getTypeClass(type)).getSimpleName()) || thisName.equals(typeClass.getName());
        }

        public boolean equals(Object obj) {
            return this.toString().equals(obj.toString());
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Symbol {
        final String symbol;
        long address;
        MemberRef ref;
        boolean refParsed;
        final NativeLibrary library;
        private Convention.Style style;

        public Symbol(String symbol, NativeLibrary library) {
            this.symbol = symbol;
            this.library = library;
        }

        public NativeLibrary getLibrary() {
            return this.library;
        }

        public MemberRef getRef() {
            return this.ref;
        }

        public Convention.Style getStyle() {
            return this.style;
        }

        public String getSymbol() {
            return this.symbol;
        }

        public String toString() {
            return this.symbol + (this.ref == null ? "" : " (" + this.ref + ")");
        }

        public long getAddress() {
            if (this.address == 0L) {
                this.address = this.library.getSymbolAddress(this.symbol);
            }
            return this.address;
        }

        public void setAddress(long address) {
            this.address = address;
        }

        public Convention.Style getInferredCallingConvention() {
            if (this.style == null) {
                if (this.symbol.matches("_.*?@\\d+")) {
                    this.style = Convention.Style.StdCall;
                } else if (this.symbol.matches("@.*?@\\d+")) {
                    this.style = Convention.Style.FastCall;
                } else if (Platform.isWindows() && this.symbol.contains("@")) {
                    try {
                        MemberRef mr2 = this.getParsedRef();
                        if (mr2 != null) {
                            this.style = mr2.callingConvention;
                        }
                    }
                    catch (Throwable throwable) {
                        // empty catch block
                    }
                }
            }
            return this.style;
        }

        public boolean matches(Method method) {
            if (!this.symbol.contains(Demangler.getMethodName(method))) {
                return false;
            }
            this.parse();
            try {
                if (this.ref != null) {
                    boolean res = this.ref.matches(method);
                    if (!res && BridJ.debug) {
                        System.err.println("Symbol " + this.symbol + " was a good candidate but expected demangled signature " + this.ref + " did not match the method " + method);
                    }
                    return res;
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            return false;
        }

        public MemberRef getParsedRef() {
            this.parse();
            return this.ref;
        }

        synchronized void parse() {
            if (!this.refParsed) {
                block4: {
                    try {
                        this.ref = this.library.parseSymbol(this.symbol);
                    }
                    catch (DemanglingException ex2) {
                        if (BridJ.debug) {
                            ex2.printStackTrace();
                        }
                        if (!BridJ.verbose) break block4;
                        BridJ.warning("Symbol parsing failed : " + ex2.getMessage());
                    }
                }
                this.refParsed = true;
            }
        }

        public String getName() {
            return this.symbol;
        }

        public boolean matchesVirtualTable(Class<?> type) {
            if (!this.symbol.contains(type.getSimpleName())) {
                return false;
            }
            this.parse();
            try {
                if (this.ref != null) {
                    return this.ref.matchesVirtualTable(type);
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            return false;
        }

        public boolean matchesConstructor(Type type, Constructor<?> constr) {
            if (!this.symbol.contains(Utils.getClass(type).getSimpleName())) {
                return false;
            }
            this.parse();
            try {
                if (this.ref != null) {
                    return this.ref.matchesConstructor(type, constr);
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            return false;
        }

        public boolean matchesDestructor(Class<?> type) {
            if (!this.symbol.contains(type.getSimpleName())) {
                return false;
            }
            this.parse();
            try {
                if (this.ref != null) {
                    return this.ref.matchesDestructor(type);
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            return false;
        }

        public boolean isVirtualTable() {
            return false;
        }
    }

    public static interface TemplateArg {
        public boolean matchesParam(Object var1, Annotations var2);
    }

    public class DemanglingException
    extends Exception {
        public DemanglingException(String mess) {
            this(mess, null);
        }

        public DemanglingException(String mess, Throwable cause) {
            super(mess + " (in symbol '" + Demangler.this.str + "')", cause);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface Annotations {
        public <A extends Annotation> A getAnnotation(Class<A> var1);

        public boolean isAnnotationPresent(Class<? extends Annotation> var1);
    }
}

