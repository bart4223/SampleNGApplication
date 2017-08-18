package Uniwork.Script;

import Uniwork.Base.NGObjectRequestRegistration;

public class NGScriptFunctionsBase extends NGCustomScriptFunctions {

    public static String CBase = "Base";

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        registerObjectRequest(CBase, this, "add", "add");
    }

    public NGScriptFunctionsBase(NGObjectRequestRegistration aORR) {
        super(aORR);
    }

    public void add(double aOperand1, double aOperand2) {
        double res = aOperand1 + aOperand2;
        writeInfo(String.format(".2f", res));
    }

}
