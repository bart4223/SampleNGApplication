package Uniwork.Base;

import Uniwork.Misc.NGStrings;

import java.util.concurrent.CopyOnWriteArrayList;

public class NGObjectRequestMethod extends NGObject {

    protected String FName;
    protected String FAlias;
    protected String FObjectMethod;
    protected CopyOnWriteArrayList<NGObjectRequestParameter> FParams;
    protected Boolean FActive;
    protected String FDescription;

    public NGObjectRequestMethod(String aName, String aObjectMethod) {
        this(aName, aObjectMethod, "");
    }

    public NGObjectRequestMethod(String aName, String aObjectMethod, String aAlias) {
        this(aName, aObjectMethod, "", aAlias);
    }

    public NGObjectRequestMethod(String aName, String aObjectMethod, String aDescription, String aAlias) {
        super();
        FName = aName;
        FObjectMethod = aObjectMethod;
        FDescription = aDescription;
        FParams = new CopyOnWriteArrayList<NGObjectRequestParameter>();
        FActive = true;
        FAlias = aAlias;
    }

    public String getName() {
        return FName;
    }

    public String getAlias() {
        return FAlias;
    }

    public void setAlias(String aAlias) {
        FAlias = aAlias;
    }

    public Boolean IsThis(String aName) {
        return getName().toUpperCase().equals(aName.toUpperCase()) || getAlias().toUpperCase().equals(aName.toUpperCase());
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

    public String toString() {
        String res = "";
        String name = getName();
        for (NGObjectRequestParameter param : FParams) {
            res = NGStrings.addString(res, param.toString(), " ");
        }
        if (HasAlias()) {
            name = String.format("%s(%s)", name, getAlias());
        }
        return NGStrings.addString(name, res, " ");
    }

    public Boolean HasAlias() {
        return getAlias().length() > 0;
    }

    public String getDescription() {
        return FDescription;
    }

}