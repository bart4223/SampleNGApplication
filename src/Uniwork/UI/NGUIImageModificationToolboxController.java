package Uniwork.UI;

import Uniwork.Appl.NGCustomStageItem;
import Uniwork.Visuals.NGColorQuantizeImageDisplayController;
import Uniwork.Visuals.NGDisplayController;
import Uniwork.Visuals.NGDisplayView;
import Uniwork.Visuals.NGToolboxController;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;

public class NGUIImageModificationToolboxController extends NGToolboxController {

    protected class SliderChangeListener implements ChangeListener {

        protected Slider FSlider;

        public SliderChangeListener(Slider aSlider) {
            super();
            FSlider = aSlider;
        }

        @Override
        public void changed(ObservableValue observableValue, Object o, Object t1) {
            Double value = (Double)t1;
            if (FSliderInitialized) {
                ((NGUIImageModificationToolboxItem)FStageItem).setCQColorCount(value.intValue());
                FSlider.getTooltip().setText(String.format("%.0f", value));
                FStageItem.Invalidate();
            }
        }

    }

    protected Boolean FSliderInitialized = false;

    @FXML
    private Canvas Layer0;

    @FXML
    private Slider slCQ;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnOK;

    @FXML
    private Button btnCancel;

    @FXML
    protected void handleReset() {
        FSliderInitialized = !FSliderInitialized;
        FStageItem.Reset();
    }

    @FXML
    protected void handleOK() {
        FStageItem.DialogOK();
    }

    @FXML
    protected void handleCancel() {
        FStageItem.DialogCancel();
    }

    @Override
    protected void DoBeforeRenderScene(NGDisplayController aDisplayController) {
        super.DoBeforeRenderScene(aDisplayController);
        NGDisplayController dc = getDisplayController("Image");
        ((NGColorQuantizeImageDisplayController)dc).setImage(((NGUIImageModificationToolboxItem)FStageItem).getImage(), ((NGUIImageModificationToolboxItem)FStageItem).getColorOctree());
        slCQ.setMin(1);
        slCQ.setMax(((NGUIImageModificationToolboxItem)FStageItem).getOrgColorCount());
        if (!FSliderInitialized) {
            slCQ.setValue(((NGUIImageModificationToolboxItem)FStageItem).getOrgColorCount());
            slCQ.setTooltip(new Tooltip(String.format("%.0f", slCQ.getValue())));
            FSliderInitialized = true;
        }
    }

    @Override
    protected void CreateDisplayController() {
        super.CreateDisplayController();
        NGDisplayView dv = new NGDisplayView(Layer0.getWidth(), Layer0.getHeight());
        NGDisplayController dc = new NGColorQuantizeImageDisplayController(Layer0, "Image");
        dc.setView(dv);
        registerDisplayController(dc);
    }
    
    public void setStageItem(NGCustomStageItem aStageItem) {
        super.setStageItem(aStageItem);
        slCQ.valueProperty().addListener(new SliderChangeListener(slCQ));
    }

}