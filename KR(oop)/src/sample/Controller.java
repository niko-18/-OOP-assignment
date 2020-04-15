package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller extends Config {

    @FXML
    private Button exitButton;

    @FXML
    private Button goFurther;

    @FXML
    void initialize() {
        goFurther.setOnAction(actionEvent -> {
            Stage ss = (Stage) goFurther.getScene().getWindow();
            ss.close();

            Stage stage = new Stage();
            try {
                menu(stage, "/sample/fxml/signUp.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        exitButton.setOnAction(actionEvent -> {
            Stage ss = (Stage) exitButton.getScene().getWindow();
            ss.close();
        });
    }
}
