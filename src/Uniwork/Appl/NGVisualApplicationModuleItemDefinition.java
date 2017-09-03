package Uniwork.Appl;

public class NGVisualApplicationModuleItemDefinition extends NGApplicationModuleItemDefinition {

    protected Boolean ShowStagesAfterInitialize = true;

    public NGVisualApplicationModuleItemDefinition() {
        super();
    }

    public void setShowStagesAfterInitialize(Boolean value) {
        ShowStagesAfterInitialize = value;
    }
    public Boolean getShowStagesAfterInitialize() {
        return ShowStagesAfterInitialize;
    }

}
