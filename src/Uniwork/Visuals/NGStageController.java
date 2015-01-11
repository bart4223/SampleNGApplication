package Uniwork.Visuals;

import Uniwork.Base.NGObject;
import javafx.application.Platform;

import java.util.ArrayList;

public class NGStageController extends NGObject {

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

    public static void renderThread(final NGStageController aStageController, final NGDisplayController aController) {
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

    protected NGDisplayController getDisplayController(String aName) {
        DisplayControllerItem item = getDisplayControllerItem(aName);
        return item.getDisplayController();
    }

    public NGStageController() {
        super();
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
        RenderScene("");
    }

    public void RenderScene(String aName) {
        DisplayControllerItem item = getDisplayControllerItem(aName);
        Boolean ownrenderThread = FOwnRenderThread;
        NGDisplayController dc = null;
        if (item != null) {
            ownrenderThread = item.getOwnRenderThread();
            dc = item.getDisplayController();
        }
        if (ownrenderThread) {
            renderThread(this, dc);
        }
        else {
            InternalRenderScene(dc);
        }
    }

    public void registerDisplayController(NGDisplayController aController) {
        registerDisplayController(aController, FOwnRenderThread);
    }

    public void registerDisplayController(NGDisplayController aController, Boolean aOwnRenderThread) {
        DisplayControllerItem item = new DisplayControllerItem(aController, aOwnRenderThread);
        FDCItems.add(item);
    }

}
