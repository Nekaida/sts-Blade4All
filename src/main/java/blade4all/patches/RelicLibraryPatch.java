package blade4all.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.WristBlade;

@SpirePatch(clz=RelicLibrary.class, method="initialize")
public class RelicLibraryPatch {
    public static ExprEditor Instrument() {
        return new ExprEditor() {
            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                if (m.getMethodName().equals("addGreen") && m.getLineNumber() == 200) {
                    m.replace("add((com.megacrit.cardcrawl.relics.AbstractRelic)new com.megacrit.cardcrawl.relics.WristBlade());");
                }
            }
        };
    }
}
