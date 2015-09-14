package Uniwork.Appl;

import Uniwork.Base.NGComponent;
import Uniwork.Misc.NGStrings;
import javafx.stage.Stage;

import java.util.ArrayList;

public class NGStageManager extends NGComponent {

    protected ArrayList<NGCustomStageItem> FItems;
    protected ArrayList<NGStageItemClass> FItemClasses;

    protected void DoInitialize() {
        super.DoInitialize();
        for (NGCustomStageItem item : FItems) {
            item.Initialize();
        }
    }

    protected void DoFinalize() {
        for (NGCustomStageItem item : FItems) {
            item.Finalize();
        }
        super.DoFinalize();
    }

    protected NGStageItemClass getItemClass(String aName) {
        for (NGStageItemClass itemclass : FItemClasses) {
            if (itemclass.getName().equals(aName)) {
                return itemclass;
            }
        }
        return null;
    }

    protected String getFullname(String aName) {
        return NGStrings.addString(NGStrings.getFirstString(FName, "."), aName, ".");
    }

    public NGStageManager() {
        this("");
    }

    public NGStageManager(String aName) {
        this(null, aName);
    }

    public NGStageManager(NGComponent aOwner, String aName) {
        super(aOwner, aName);
        FItems = new ArrayList<NGCustomStageItem>();
        FItemClasses = new ArrayList<NGStageItemClass>();
    }

    public void registerItemClass(String aName, String aClassname) {
        try {
            Class cls = getClass().getClassLoader().loadClass(aClassname);
            if (cls != null && NGCustomStageItem.class.isAssignableFrom(cls)) {
                NGStageItemClass itemclass = new NGStageItemClass(aName, cls);
                FItemClasses.add(itemclass);
            }
        }
        catch (Exception e) {

        }
    }

    public void unregisterItemClass(String aName) {
        NGStageItemClass itemclass = getItemClass(aName);
        if (itemclass != null) {
            FItemClasses.remove(itemclass);
        }
    }

    public NGCustomStageItem addStageItem(String aItemName) {
        return addStageItem(aItemName, aItemName);
    }

    public NGCustomStageItem addStageItem(String aItemName, String aName) {
        return addStageItem(aItemName, aName, null);
    }

    public NGCustomStageItem addStageItem(String aItemName, Stage aStage) {
        return addStageItem(aItemName, aItemName, aStage);
    }

    public NGCustomStageItem addStageItem(String aItemName, String aName, Stage aStage) {
        NGCustomStageItem item = null;
        NGStageItemClass itemclass = getItemClass(aItemName);
        try {
            item = (NGCustomStageItem)itemclass.getItemClass().getConstructor(NGStageManager.class, String.class, Stage.class).newInstance(this, getFullname(aName), aStage);
            FItems.add(item);
            writeInfo(String.format("Stage item %s[%s] added.", item.getName(), itemclass.getItemClass().getName()));
        }
        catch (Exception e){
            writeError(String.format("Error %s in addStageItem with name %s", e.getMessage(), aName));
        }
        return item;
    }

    public void RenderStage(String aName) {
        NGCustomStageItem item = getItem(aName);
        item.RenderStage();
    }

    public void RenderStages() {
        for (NGCustomStageItem item : FItems) {
            item.RenderStage();
        }
    }

    public void ShowStages() {
        for (NGCustomStageItem item : FItems) {
            item.Show();
        }
    }

    public void HideStages() {
        for (NGCustomStageItem item : FItems) {
            item.Hide();
        }
    }

    public void CloseStages() {
        for (NGCustomStageItem item : FItems) {
            item.Close();
        }
    }

    public NGCustomStageItem getItem(String aName) {
        String name = getFullname(aName);
        for (NGCustomStageItem item : FItems) {
            if (item.getName().equals(name))
                return item;
        }
        return null;
    }

}
