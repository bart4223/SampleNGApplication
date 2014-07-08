package Uniwork.Visuals;

import Uniwork.Base.NGObject;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NGStageController extends NGObject implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
