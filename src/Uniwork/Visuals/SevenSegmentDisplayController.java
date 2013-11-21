package Uniwork.Visuals;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class SevenSegmentDisplayController implements Initializable {

    protected GraphicsContext FGC;

    @FXML
    Canvas Canvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FGC = Canvas.getGraphicsContext2D();
    }

    public void RenderControl() {
        FGC.setFill(Color.BLUE);
        FGC.fillRect(10, 10, 10, 10);
    }

}