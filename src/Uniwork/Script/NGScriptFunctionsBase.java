package Uniwork.Script;

import Uniwork.Base.NGObjectRequestMethod;
import Uniwork.Base.NGObjectRequestParameter;
import Uniwork.Base.NGObjectRequestRegistration;

public class NGScriptFunctionsBase extends NGCustomScriptFunctions {

    public static String CBase = "Base";

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        NGObjectRequestMethod orm = registerObjectRequest("Add", "addition");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("Sub", "substraction");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("Neg", "negation");
        orm.addParam("Operand", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("Mult", "multiplication");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("Div", "division");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
    }

    public NGScriptFunctionsBase(NGObjectRequestRegistration aORR) {
        super(aORR);
        FDomain = CBase;
    }

    public Double addition(Double aOperand1, Double aOperand2) {
        Double res = aOperand1 + aOperand2;
        writeInfo(res.toString());
        return res;
    }

    public Double substraction(Double aOperand1, Double aOperand2) {
        Double res = aOperand1 - aOperand2;
        writeInfo(res.toString());
        return res;
    }

    public Double negation(Double aOperand) {
        Double res = -aOperand;
        writeInfo(res.toString());
        return res;
    }

    public Double multiplication(Double aOperand1, Double aOperand2) {
        Double res = aOperand1 * aOperand2;
        writeInfo(res.toString());
        return res;
    }

    public Double division(Double aOperand1, Double aOperand2) {
        Double res = aOperand1 / aOperand2;
        writeInfo(res.toString());
        return res;
    }

}
