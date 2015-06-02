package Uniwork.Appl;

import Uniwork.Base.NGComponent;
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
        super.DoFinalize();
        for (NGCustomStageItem item : FItems) {
            item.Finalize();
        }
    }

    protected NGStageItemClass getItemClass(String aName) {
        for (NGStageItemClass itemclass : FItemClasses) {
            if (itemclass.getName().equals(aName)) {
                return itemclass;
            }
        }
        return null;
    }

    public NGStageManager() {
        this(null);
    }

    public NGStageManager(NGComponent aOwner) {
        super(aOwner);
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

    public NGCustomStageItem addStageItem(String aName) {
        return addStageItem(aName, null);
    }

    public NGCustomStageItem addStageItem(String aName, Stage aStage) {
        NGCustomStageItem item = null;
        NGStageItemClass itemclass = getItemClass(aName);
        try {
            item = (NGCustomStageItem)itemclass.getItemClass().getConstructor(NGStageManager.class, String.class, Stage.class).newInstance(this, aName, aStage);
            FItems.add(item);
            writeInfo(String.format("Stage item %s[%s] added.", aName, itemclass.getItemClass().getName()));
        }
        catch (Exception e){
            writeError(String.format("Error %s in addStageItem with name %s", e.getMessage(), aName));
        }
        return item;
    }

}
