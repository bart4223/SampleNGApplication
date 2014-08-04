package Uniwork.Visuals;

import Uniwork.Base.NGObject;

import java.util.ArrayList;

public class NGStageController extends NGObject {

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

    public NGStageController() {
        super();
        FDCItems = new ArrayList<NGDisplayController>();
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
        BeforeRenderScene();
        try {
            DoRenderScene();
        }
        finally {
            AfterRenderScene();
        }
    }

    public void registerDisplayController(NGDisplayController aController) {
        FDCItems.add(aController);
    }

}
