package Uniwork.Visuals;

import Uniwork.Base.NGObject;
import javafx.application.Platform;

import java.util.ArrayList;

public class NGStageController extends NGObject {

    protected Boolean FOwnRenderThread;

    public static void renderThread(final NGStageController aStageController, final NGDisplayController aController) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                aStageController.InternalRenderScene(aController);
            }
        });
    }

    protected ArrayList<NGDisplayController> FDCItems;

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
            for (NGDisplayController controller : FDCItems) {
                DoRenderSceneController(controller);
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
        for (NGDisplayController controller : FDCItems) {
            DoInitializeController(controller);
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

    public NGStageController() {
        super();
        FDCItems = new ArrayList<NGDisplayController>();
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
        RenderScene(null);
    }

    public void RenderScene(NGDisplayController aController) {
        if (FOwnRenderThread) {
            renderThread(this, aController);
        }
        else {
            InternalRenderScene(aController);
        }
    }

    public void registerDisplayController(NGDisplayController aController) {
        FDCItems.add(aController);
    }

}
