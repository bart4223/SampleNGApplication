package Uniwork.Base;

public class NGObjectRequestParameter extends NGObject {

    public enum ParamKind {String, Integer, Double, Object}

    protected String FName;
    protected ParamKind FKind;
    protected String FDescription;

    public NGObjectRequestParameter(String aName, ParamKind aKind) {
        this(aName, aKind, "");
    }

    public NGObjectRequestParameter(String aName, ParamKind aKind, String aDescription) {
        super();
        FName = aName;
        FKind = aKind;
        FDescription = aDescription;
    }

    public String getName() {
        return FName;
    }

    public ParamKind getKind() {
        return FKind;
    }

    public String getDescription() {
        return FDescription;
    }

    public String toString() {
        return String.format("[%s]", FName);
    }

}
