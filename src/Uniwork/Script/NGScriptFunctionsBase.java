package Uniwork.Script;

import Uniwork.Base.NGObjectRequestMethod;
import Uniwork.Base.NGObjectRequestParameter;
import Uniwork.Base.NGObjectRequestRegistration;

public class NGScriptFunctionsBase extends NGCustomScriptFunctions {

    public static String CBase = "Base";

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        NGObjectRequestMethod orm = registerObjectRequest(CBase, this, "Add", "add");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest(CBase, this, "Sub", "sub");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest(CBase, this, "Neg", "neg");
        orm.addParam("Operand", NGObjectRequestParameter.ParamKind.Double);
    }

    public NGScriptFunctionsBase(NGObjectRequestRegistration aORR) {
        super(aORR);
    }

    public void add(Double aOperand1, Double aOperand2) {
        double res = aOperand1 + aOperand2;
        writeInfo(String.format("%.2f", res));
    }

    public void sub(Double aOperand1, Double aOperand2) {
        double res = aOperand1 - aOperand2;
        writeInfo(String.format("%.2f", res));
    }

    public void neg(Double aOperand) {
        double res = -aOperand;
        writeInfo(String.format("%.2f", res));
    }

}
