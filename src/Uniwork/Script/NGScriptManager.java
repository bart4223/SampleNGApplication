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

    protected NGScriptItem getItem(String aName) {
        for (NGScriptItem item : FScripts) {
            if (item.getName().toUpperCase().equals(aName.toUpperCase())) {
                return item;
            }
        }
        return null;
    }

    private void LoadScript(NGScriptItem aItem) {
        try {
            String script = NGMisc.LoadFileContentUnsafe(aItem.getFileName());
            aItem.setScript(script);
        } catch (Exception e) {
            writeError(e.getMessage());
        }
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
        NGScriptItem item = getItem(aName);
        if (item != null) {
            return item.getScript();
        }
        return "";
    }

    public Integer getScriptCount() {
        return FScripts.size();
    }

    public void Reload() {
        for (NGScriptItem item : FScripts) {
            LoadScript(item);
        }
    }

    public Boolean Reload(String aName) {
        NGScriptItem item = getItem(aName);
        if (item != null) {
            LoadScript(item);
        }
        return item != null;
    }

}
