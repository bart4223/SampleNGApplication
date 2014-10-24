package Uniwork.Visuals;

import Uniwork.Base.NGObject;
import javafx.application.Platform;

import java.util.ArrayList;

public class NGStageController extends NGObject {

    protected Boolean FOwnRenderThread;

    public static void renderThread(final NGStageController aStageController) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                aStageController.InternalRenderScene();
            }
        });
    }

    protected ArrayList<NGDisplayController> FDCItems;

    protected void DoBeforeRenderScene() {

    }

    protected void BeforeRenderScene() {
        DoBeforeRenderScene();
    }

    protected void DoRenderScene() {
        for (NGDisplayController controller : FDCItems) {
            controller.Render();
        }
    }

    protected void DoAfterRenderScene() {

    }

    protected void AfterRenderScene() {
        DoAfterRenderScene();
    }

    protected void CreateDisplayController() {

    }

    protected void DoBeforeInitialize() {

    }

    protected void BeforeInitialize() {
        CreateDisplayController();
        DoBeforeInitialize();
    }

    protected void DoInitialize() {
        for (NGDisplayController controller : FDCItems) {
            controller.Initialize();
        }
    }

    protected void DoAfterInitialize() {

    }

    protected void AfterInitialize() {
        DoAfterInitialize();
    }

    protected void InternalRenderScene() {
        BeforeRenderScene();
        try {
            DoRenderScene();
        }
        finally {
            AfterRenderScene();
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
        if (FOwnRenderThread) {
            renderThread(this);
        }
        else {
            InternalRenderScene();
        }
    }

    public void registerDisplayController(NGDisplayController aController) {
        FDCItems.add(aController);
    }

}
