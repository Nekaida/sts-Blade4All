package blade4all.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.WristBlade;

/*@SpirePatch(
        clz= RelicLibraryPatch.class,
        method="initialize"
)
public class RelicLibraryPatch {
    public static void Postfix(RelicLibrary lib) {
        AbstractRelic relic = lib.greenRelics.get("WristBlade");
        lib.greenRelics.remove(relic.relicId);
        lib.greenList.remove(relic);
        lib.sharedRelics.put(relic.relicId, relic);
        lib.sortLists();
    }
}*/



import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

@SpirePatch(clz=RelicLibrary.class, method="initialize")
public class RelicLibraryPatch {
    public static ExprEditor Instrument() {
        return new ExprEditor() {
            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                if (m.getMethodName().equals("addGreen") && m.getLineNumber() == 200) {
                    m.replace("add(new com.megacrit.cardcrawl.relics.WristBlade());");
                }
            }
        };
    }
}
