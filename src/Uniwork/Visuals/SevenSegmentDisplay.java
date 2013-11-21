package Uniwork.Visuals;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import java.io.IOException;

public class SevenSegmentDisplay extends Control {

    protected Node FView;
    protected SevenSegmentDisplayController FController;

    public SevenSegmentDisplay() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SevenSegmentDisplay.fxml"));
        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                return FController = new SevenSegmentDisplayController();
            }
        });
        try {
            FView = (Node)fxmlLoader.load();

        } catch (IOException ex) {
        }
        getChildren().add(FView);
        FController.RenderControl();
    }

}