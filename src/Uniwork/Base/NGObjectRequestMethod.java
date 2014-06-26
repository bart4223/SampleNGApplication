package Uniwork.Base;

import java.util.ArrayList;

public class NGObjectRequestMethod extends NGObject {

    protected String FName;
    protected String FObjectMethod;
    protected ArrayList<NGObjectRequestParameter> FParams;

    public NGObjectRequestMethod(String aName, String aObjectMethod) {
        super();
        FName = aName;
        FObjectMethod = aObjectMethod;
        FParams = new ArrayList<NGObjectRequestParameter>();
    }

    public String getName() {
        return FName;
    }

    public String getObjectMethod() {
        return FObjectMethod;
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
