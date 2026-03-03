/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bridj.NativeLibrary;
import org.bridj.demangling.Demangler;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class HeadersReconstructor {
    public static void reconstructHeaders(Iterable<NativeLibrary> libraries, PrintWriter out) {
        ArrayList<Demangler.MemberRef> orphanMembers = new ArrayList<Demangler.MemberRef>();
        HashMap<Demangler.TypeRef, ArrayList<Demangler.MemberRef>> membersByClass = new HashMap<Demangler.TypeRef, ArrayList<Demangler.MemberRef>>();
        for (NativeLibrary nativeLibrary : libraries) {
            for (Demangler.Symbol symbol : nativeLibrary.getSymbols()) {
                Demangler.MemberRef mr2 = symbol.getParsedRef();
                if (mr2 == null) continue;
                Demangler.TypeRef et2 = mr2.getEnclosingType();
                if (et2 == null) {
                    orphanMembers.add(mr2);
                    continue;
                }
                ArrayList<Demangler.MemberRef> mrs = (ArrayList<Demangler.MemberRef>)membersByClass.get(et2);
                if (mrs == null) {
                    mrs = new ArrayList<Demangler.MemberRef>();
                    membersByClass.put(et2, mrs);
                }
                mrs.add(mr2);
            }
        }
        for (Demangler.TypeRef typeRef : membersByClass.keySet()) {
            out.println("class " + typeRef + ";");
        }
        for (Demangler.MemberRef memberRef : orphanMembers) {
            out.println(memberRef + ";");
        }
        for (Map.Entry entry : membersByClass.entrySet()) {
            Demangler.TypeRef tr3 = (Demangler.TypeRef)entry.getKey();
            List mrs = (List)entry.getValue();
            out.println("class " + tr3 + " \n{");
            for (Demangler.MemberRef mr4 : mrs) {
                out.println("\t" + mr4 + ";");
            }
            out.println("}");
        }
    }
}

