package Uniwork.Appl;

import Uniwork.Base.NGObject;

public class NGApplicationModuleItemDefinition extends NGObject {

    protected String Name = "";
    protected String ClassName = "";
    protected String ConfigurationFilename = "";
    protected String Description = "";

    public NGApplicationModuleItemDefinition() {
        super();
    }

    public void setClassName(String value) {
        ClassName = value;
    }
    public String getClassName() {
        return ClassName;
    }

    public void setName(String value) {
        Name = value;
    }
    public String getName() {
        return Name;
    }

    public void setDescription(String value) {
        Description = value;
    }
    public String getDescription() {
        return Description;
    }

    public void setConfigurationFilename(String value) {
        ConfigurationFilename = value;
    }
    public String getConfigurationFilename() {
        return ConfigurationFilename;
    }

}
