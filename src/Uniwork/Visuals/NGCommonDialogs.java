package Uniwork.Visuals;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NGCommonDialogs {

        public enum Response { No, Yes, Cancel };

        private static Response buttonSelected = Response.Cancel;

        static class NGDialog extends Stage {

            public NGDialog(String title, Stage owner, Scene scene) {
                setTitle(title);
                initStyle(StageStyle.UTILITY);
                initModality(Modality.APPLICATION_MODAL);
                initOwner(owner);
                setResizable(false);
                setScene(scene);
            }

            public void showDialog() {
                sizeToScene();
                centerOnScreen();
                showAndWait();
            }

        }

        static class Message extends Text {
            public Message(String msg) {
                super(msg);
                setWrappingWidth(250);
            }
        }

        public static Response showConfirmDialog(Stage owner, String aTitle, String aMessage) {
            VBox vbComplete = new VBox();
            Scene scene = new Scene(vbComplete);
            final NGDialog dlg = new NGDialog(aTitle, owner, scene);
            vbComplete.setPadding(new Insets(15, 12, 15, 12));
            vbComplete.setSpacing(10);
            Button btnYes = new Button("Yes");
            btnYes.setMinWidth(80);
            btnYes.setOnAction( new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    dlg.close();
                    buttonSelected = Response.Yes;
                }
            });
            Button btnNo = new Button("No");
            btnNo.setCancelButton(true);
            btnNo.setMinWidth(80);
            btnNo.setOnAction( new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    dlg.close();
                    buttonSelected = Response.No;
                }
            });
            BorderPane bpBottom = new BorderPane();
            HBox hbBottom = new HBox();
            hbBottom.setAlignment(Pos.CENTER);
            hbBottom.setSpacing(10);
            hbBottom.getChildren().addAll(btnYes, btnNo);
            bpBottom.setRight(hbBottom);
            HBox hbMsg = new HBox();
            hbMsg.setSpacing(10);
            hbMsg.getChildren().addAll(new Message(aMessage));
            vbComplete.getChildren().addAll(hbMsg, bpBottom);
            btnNo.requestFocus();
            dlg.showDialog();
            return buttonSelected;
        }

        public static void showMessageDialog(Stage owner, String aTitle, String aMessage) {
            showMessageDialog(owner, aTitle, new Message(aMessage));
        }

        public static void showMessageDialog(Stage owner, String title, Node message) {
            VBox vbComplete = new VBox();
            Scene scene = new Scene(vbComplete);
            final NGDialog dlg = new NGDialog(title, owner, scene);
            vbComplete.setPadding(new Insets(15, 10, 15, 10));
            vbComplete.setSpacing(10);
            Button btnOK = new Button("OK");
            btnOK.setAlignment(Pos.CENTER);
            btnOK.setMinWidth(80);
            btnOK.setOnAction( new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    dlg.close();
                }
            });
            BorderPane bpBottom = new BorderPane();
            bpBottom.setRight(btnOK);
            HBox hbMsg = new HBox();
            hbMsg.setSpacing(10);
            hbMsg.getChildren().addAll(message);
            vbComplete.getChildren().addAll(hbMsg, bpBottom);
            dlg.showDialog();
        }

}
