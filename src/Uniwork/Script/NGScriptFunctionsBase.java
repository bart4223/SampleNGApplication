package Uniwork.Script;

import Uniwork.Base.NGObjectRequestMethod;
import Uniwork.Base.NGObjectRequestParameter;
import Uniwork.Base.NGObjectRequestRegistration;

public class NGScriptFunctionsBase extends NGCustomScriptFunctions {

    public static String CBase = "Base";

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        NGObjectRequestMethod orm = registerObjectRequest("Add", "add");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("Sub", "sub");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("Neg", "neg");
        orm.addParam("Operand", NGObjectRequestParameter.ParamKind.Double);
    }

    public NGScriptFunctionsBase(NGObjectRequestRegistration aORR) {
        super(aORR);
        FDomain = CBase;
    }

    public Double add(Double aOperand1, Double aOperand2) {
        Double res = aOperand1 + aOperand2;
        writeInfo(res.toString());
        return res;
    }

    public Double sub(Double aOperand1, Double aOperand2) {
        Double res = aOperand1 - aOperand2;
        writeInfo(res.toString());
        return res;
    }

    public Double neg(Double aOperand) {
        Double res = -aOperand;
        writeInfo(res.toString());
        return res;
    }

}
