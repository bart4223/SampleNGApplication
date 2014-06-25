package Uniwork.Base;

public class NGObjectRequestParameter extends NGObject {

    public enum ParamKind {String, Integer};

    protected String FName;
    protected ParamKind FKind;

    public NGObjectRequestParameter(String aName, ParamKind aKind) {
        super();
        FName = aName;
        FKind = aKind;
    }

    public String getName() {
        return FName;
    }

    public ParamKind getKind() {
        return FKind;
    }

}
