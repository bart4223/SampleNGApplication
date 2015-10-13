package Uniwork.Appl;

import Uniwork.Base.*;
import Uniwork.Misc.NGLogManager;
import Uniwork.Misc.NGStrings;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class NGCustomApplicationModule extends NGComponent {

    protected String FDescription = "";
    protected NGComponentManager FComponentManager;
    protected String FConfigurationFilename = "";
    protected String FDefinitionFilename = "";
    protected Properties FConfiguration;
    protected Boolean FConfigLoaded = false;
    protected NGApplicationModuleDefinition FDefinition;

    protected String getConfigurationProperty(String aName) {
        if (FConfigLoaded) {
            String res = FConfiguration.getProperty(aName);
            if (res != null)
                    return res;
        }
        return "";
    }

    protected Boolean LoadConfiguration() {
        Boolean res = FConfigurationFilename.length() > 0;
        if (res) {
            try {
                InputStream is = new FileInputStream(FConfigurationFilename);
                FConfiguration.load(is);
                FConfigLoaded = true;
                FDefinitionFilename = getConfigurationProperty("DefinitionFilename");
                writeInfo(String.format("Module [%s] configuration file %s loaded.", getName(), FConfigurationFilename));
            }
            catch ( Exception e) {
                writeError(String.format("Error at module %s.LoadConfiguration: %s", getName(), e.getMessage()));
            }
        }
        return res;
    }

    protected void LoadDefinition() {
        Boolean res = FDefinitionFilename.length() > 0;
        if (res) {
            writeInfo(String.format("Load module [%s] definition file %s ...", getName(), FDefinitionFilename));
            NGObjectXMLDeserializerFile loader = new NGObjectXMLDeserializerFile(null, FDefinitionFilename);
            loader.deserializeObject();
            FDefinition = (NGApplicationModuleDefinition)loader.getTarget();
            writeInfo(String.format("Module [%s] definition file %s loaded.", getName(), FDefinitionFilename));
        }
    }

    @Override
    protected void DoBeforeInitialize() {
        super.DoBeforeInitialize();
        LoadConfiguration();
        LoadDefinition();
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        FComponentManager.Initialize();
    }

    @Override
    protected void DoAfterInitialize () {
        super.DoAfterInitialize();
        registerObjectRequests();
    }

    protected void registerObjectRequests() {

    }

    protected NGObjectRequestMethod registerObjectRequest(String aName, Object aObject, String aMethod, String aObjectMethod) {
        String name = NGStrings.addString(FName, aName, ".");
        return NGApplication.Application.registerObjectRequest(name, aObject, aMethod, aObjectMethod);
    }

    protected void DoFinalize() {
        FComponentManager.Finalize();
        super.DoFinalize();
    }


    public NGCustomApplicationModule(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        Application = NGApplication.Application;
        FComponentManager = new NGComponentManager(this);
        FConfiguration = new Properties();
        FDefinitionFilename = "";
        FDescription = "";
        FDefinition = null;
    }

    public String getDescription() {
        return FDescription;
    }

    public void setConfigurationFilename(String aFilename) {
        FConfigurationFilename = aFilename;
    }

    public String getConfigurationFilename() {
        return FConfigurationFilename;
    }

    public void setLogManager(NGLogManager aLogManager) {
        super.setLogManager(aLogManager);
        FComponentManager.setLogManager(aLogManager);
    }

    public NGApplication Application;

}
