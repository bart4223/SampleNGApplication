package Uniwork.Base;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGObjectRequestMethod extends NGObject {

    protected String FName;
    protected String FObjectMethod;
    protected CopyOnWriteArrayList<NGObjectRequestParameter> FParams;
    protected Boolean FActive;

    public NGObjectRequestMethod(String aName, String aObjectMethod) {
        super();
        FName = aName;
        FObjectMethod = aObjectMethod;
        FParams = new CopyOnWriteArrayList<NGObjectRequestParameter>();
        FActive = true;
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

    public void Activate() {
        FActive = true;
    }

    public void Deactivate() {
        FActive = false;
    }

    public Boolean IsActive() {
        return FActive;
    }

    public Integer getParamCount() {
        return FParams.size();
    }

    public NGObjectRequestParameter.ParamKind getParamKind(Integer aIndex) {
        return FParams.get(aIndex).getKind();
    }

}
