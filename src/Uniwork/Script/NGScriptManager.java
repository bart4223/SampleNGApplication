package Uniwork.Script;

import Uniwork.Base.NGComponent;
import Uniwork.Misc.NGMisc;

import java.util.ArrayList;
import java.util.Iterator;

public class NGScriptManager extends NGComponent {

    protected ArrayList<NGScriptItem> FScripts;

    public NGScriptManager() {
        super();
        FScripts = new ArrayList<NGScriptItem>();
    }

    public NGScriptItem addScript(String aName, String aScript, String aDescription) {
        return new NGScriptItem(aName, aScript, aDescription);
    }

    public NGScriptItem addScriptFromFile(String aName, String aFileName, String aDescription) {
        NGScriptItem res = null;
        try {
            String script = NGMisc.LoadFileContentUnsafe(aFileName);
            res = addScript(aName, script, aDescription);
            FScripts.add(res);
        } catch (Exception e) {
            writeError(e.getMessage());
        }
        return res;
    }

    public Iterator<NGScriptItem> getScripts() {
        return FScripts.iterator();
    }

}
