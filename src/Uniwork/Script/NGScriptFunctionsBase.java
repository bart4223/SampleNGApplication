package Uniwork.Script;

import Uniwork.Base.NGObjectRequestMethod;
import Uniwork.Base.NGObjectRequestParameter;
import Uniwork.Base.NGObjectRequestRegistration;

public class NGScriptFunctionsBase extends NGCustomScriptFunctions {

    public static String CBase = "Base";

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        NGObjectRequestMethod orm;
        orm = registerObjectRequest("Let", "let", "Defines a variable.");
        orm.addParam("Variable", NGObjectRequestParameter.ParamKind.Object);
        orm = registerObjectRequest("Equal", "eqaul", "Comparison between two operands.");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("NotEqual", "notequal", "Comparison between two operands.");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("Less", "less", "Comparison between two operands.");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("LessEqual", "lessequal", "Comparison between two operands.");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("Greater", "Greater", "Comparison between two operands.");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("GreaterEqual", "greaterequal", "Comparison between two operands.");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("Add", "addition", "Adds two operands.");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("Sub", "substraction", "Subtracts two operands.");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("Neg", "negation", "Negate a operand.");
        orm.addParam("Operand", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("Mult", "multiplication", "Multiplies two operands.");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("Div", "division", "Divide two operands.");
        orm.addParam("Operand1", NGObjectRequestParameter.ParamKind.Double);
        orm.addParam("Operand2", NGObjectRequestParameter.ParamKind.Double);
        orm = registerObjectRequest("AddString", "addString", "Adds two strings.");
        orm.addParam("String1", NGObjectRequestParameter.ParamKind.String);
        orm.addParam("String2", NGObjectRequestParameter.ParamKind.String);
    }

    public NGScriptFunctionsBase(NGObjectRequestRegistration aORR) {
        super(aORR);
        FDomain = CBase;
    }

    public Object let(Object aVariable) {
        return aVariable;
    }

    public Boolean equal(Double aOperand1, Double aOperand2) {
        Boolean res = aOperand1 == aOperand2;
        writeInfo(res.toString());
        return res;
    }

    public Boolean notequal(Double aOperand1, Double aOperand2) {
        Boolean res = aOperand1 != aOperand2;
        writeInfo(res.toString());
        return res;
    }

    public Boolean less(Double aOperand1, Double aOperand2) {
        Boolean res = aOperand1 < aOperand2;
        writeInfo(res.toString());
        return res;
    }

    public Boolean lessequal(Double aOperand1, Double aOperand2) {
        Boolean res = aOperand1 <= aOperand2;
        writeInfo(res.toString());
        return res;
    }

    public Boolean greater(Double aOperand1, Double aOperand2) {
        Boolean res = aOperand1 > aOperand2;
        writeInfo(res.toString());
        return res;
    }

    public Boolean greaterequal(Double aOperand1, Double aOperand2) {
        Boolean res = aOperand1 >= aOperand2;
        writeInfo(res.toString());
        return res;
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

    public String addString(String aStr1, String aStr2) {
        String res = aStr1 + aStr2;
        writeInfo(res);
        return res;
    }

}
