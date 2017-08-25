package Uniwork.Script;

import Uniwork.Base.NGComponent;
import Uniwork.Misc.NGMisc;

import java.util.ArrayList;
import java.util.Iterator;

public class NGScriptManager extends NGComponent {

    protected ArrayList<NGScriptItem> FScripts;

    protected void DoInitialize() {
        super.DoInitialize();
        Reload();
    }

    public NGScriptManager() {
        super();
        FScripts = new ArrayList<NGScriptItem>();
    }

    public NGScriptItem addScript(String aName, String aFileName, String aDescription) {
        NGScriptItem res = new NGScriptItem(aName, aFileName, aDescription);
        FScripts.add(res);
        return res;
    }

    public Iterator<NGScriptItem> getScripts() {
        return FScripts.iterator();
    }

    public String getScript(String aName) {
        for (NGScriptItem script: FScripts ) {
            if (script.getName().toUpperCase().equals(aName.toUpperCase())) {
                return script.getScript();
            }
        }
        return "";
    }

    public Integer getScriptCount() {
        return FScripts.size();
    }

    public void Reload() {
        for (NGScriptItem item : FScripts) {
            try {
                String script = NGMisc.LoadFileContentUnsafe(item.getFileName());
                item.setScript(script);
            } catch (Exception e) {
                writeError(e.getMessage());
            }
        }
    }

}
