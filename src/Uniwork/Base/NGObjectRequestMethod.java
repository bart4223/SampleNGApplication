package Uniwork.Base;

import java.util.ArrayList;

public class NGObjectRequestMethod extends NGObject {

    protected String FName;
    protected ArrayList<NGObjectRequestParameter> FParams;

    public NGObjectRequestMethod(String aName) {
        super();
        FName = aName;
        FParams = new ArrayList<NGObjectRequestParameter>();
    }

    public String getName() {
        return FName;
    }

    public NGObjectRequestParameter addParam(String aName, NGObjectRequestParameter.ParamKind aKind) {
        NGObjectRequestParameter param = new NGObjectRequestParameter(aName, aKind);
        addParam(param);
        return param;
    }

    public void addParam(NGObjectRequestParameter aParam) {
        FParams.add(aParam);
    }

}
