package Uniwork.Visuals;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;

public class SevenSegmentDisplay extends Control {

    private final String resourcePath = "%s.fxml";

    protected Node FView;
    protected SevenSegmentDisplayController FController;

    private String getViewPath() {
        return String.format(resourcePath, this.getClass().getSimpleName());
    }

    private URL getViewURL() {
        return this.getClass().getResource(this.getViewPath());
    }

    public SevenSegmentDisplay() {
        FXMLLoader fxmlLoader = new FXMLLoader();//getClass().getResource("SevenSegmentDisplay.fxml"));
        fxmlLoader.setLocation(this.getViewURL());
        fxmlLoader.setRoot(this);
        //fxmlLoader.setController(this);
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