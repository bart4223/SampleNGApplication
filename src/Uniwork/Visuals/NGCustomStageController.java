package Uniwork.Visuals;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Base.NGObject;
import javafx.application.Platform;

import java.util.ArrayList;

public abstract class NGCustomStageController extends NGObject {

    protected NGCustomStageItem FStageItem;

    protected class DisplayControllerItem {

        protected NGDisplayController FDisplayController;
        protected Boolean FOwnRenderThread;

        public DisplayControllerItem(NGDisplayController aDisplayController, Boolean aOwnRenderThread) {
            FDisplayController = aDisplayController;
            FOwnRenderThread = aOwnRenderThread;
        }

        public NGDisplayController getDisplayController() {
            return FDisplayController;
        }

        public Boolean getOwnRenderThread() {
            return FOwnRenderThread;
        }

    }

    protected Boolean FOwnRenderThread;

    public static void renderThread(final NGCustomStageController aStageController, final NGDisplayController aController) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                aStageController.InternalRenderScene(aController);
            }
        });
    }

    protected ArrayList<DisplayControllerItem> FDCItems;

    protected void DoBeforeRenderScene(NGDisplayController aController) {

    }

    protected void BeforeRenderScene(NGDisplayController aController) {
        DoBeforeRenderScene(aController);
    }

    protected void DoRenderSceneController(NGDisplayController aController) {
        aController.Render();
    }

    protected void DoRenderScene(NGDisplayController aController) {
        if (aController == null) {
            for (DisplayControllerItem item : FDCItems) {
                DoRenderSceneController(item.getDisplayController());
            }
        }
        else {
            aController.Render();
        }
    }

    protected void DoAfterRenderScene(NGDisplayController aController) {

    }

    protected void AfterRenderScene(NGDisplayController aController) {
        DoAfterRenderScene(aController);
    }

    protected void CreateDisplayController() {

    }

    protected void DoBeforeInitialize() {

    }

    protected void BeforeInitialize() {
        CreateDisplayController();
        DoBeforeInitialize();
    }

    protected void DoInitializeController(NGDisplayController aController) {
        aController.Initialize();
    }

    protected void DoInitialize() {
        for (DisplayControllerItem item : FDCItems) {
            DoInitializeController(item.getDisplayController());
        }
    }

    protected void DoAfterInitialize() {

    }

    protected void AfterInitialize() {
        DoAfterInitialize();
    }

    protected void InternalRenderScene(NGDisplayController aController) {
        BeforeRenderScene(aController);
        try {
            DoRenderScene(aController);
        }
        finally {
            AfterRenderScene(aController);
        }
    }

    protected DisplayControllerItem getDisplayControllerItem(String aName) {
        for (DisplayControllerItem item : FDCItems) {
            if (item.getDisplayController().getName().equals(aName)) {
                return item;
            }
        }
        return null;
    }

    protected DisplayControllerItem getDisplayControllerItem(NGDisplayController aController) {
        for (DisplayControllerItem item : FDCItems) {
            if (item.getDisplayController().equals(aController)) {
                return item;
            }
        }
        return null;
    }

    protected NGDisplayController getDisplayController(String aName) {
        DisplayControllerItem item = getDisplayControllerItem(aName);
        return item.getDisplayController();
    }

    protected void RenderScene(DisplayControllerItem aItem) {
        Boolean ownrenderThread = FOwnRenderThread;
        NGDisplayController dc = null;
        if (aItem != null) {
            ownrenderThread = aItem.getOwnRenderThread();
            dc = aItem.getDisplayController();
        }
        if (ownrenderThread) {
            renderThread(this, dc);
        }
        else {
            InternalRenderScene(dc);
        }
    }

    public NGCustomStageController() {
        this(null);
    }

    public NGCustomStageController(NGCustomStageItem aStageItem) {
        super();
        FStageItem = aStageItem;
        FDCItems = new ArrayList<DisplayControllerItem>();
        FOwnRenderThread = false;
    }

    public void Initialize() {
        BeforeInitialize();
        try
        {
            DoInitialize();
        }
        finally {
            AfterInitialize();
        }
    }

    public void RenderScene() {
        DisplayControllerItem item = null;
        RenderScene(item);
    }

    public void RenderScene(String aName) {
        DisplayControllerItem item = getDisplayControllerItem(aName);
        RenderScene(item);
    }

    public void RenderScene(NGDisplayController aDisplayController) {
        DisplayControllerItem item = getDisplayControllerItem(aDisplayController);
        RenderScene(item);
    }

    public void registerDisplayController(NGDisplayController aController) {
        registerDisplayController(aController, false);
    }

    public void registerDisplayController(NGDisplayController aController, Boolean aInitialize) {
        registerDisplayController(aController, FOwnRenderThread, aInitialize);
    }

    public void registerDisplayController(NGDisplayController aController, Boolean aOwnRenderThread, Boolean aInitialize) {
        DisplayControllerItem item = new DisplayControllerItem(aController, aOwnRenderThread);
        FDCItems.add(item);
        if (aInitialize)
            DoInitializeController(aController);
    }

    public void unregisterDisplayController(NGDisplayController aController) {
        FDCItems.remove(aController);
    }

    public NGCustomStageItem getStageItem() {
        return FStageItem;
    }

    public void setStageItem(NGCustomStageItem aStageItem) {
        FStageItem = aStageItem;
    }

}
