package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.animations.Shake;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionController extends Config {

    @FXML
    private Button nextBut;

    @FXML
    private CheckBox fivePoints;

    @FXML
    private CheckBox fourPoints;

    @FXML
    private CheckBox threePoints;

    @FXML
    private CheckBox twoPoints;

    @FXML
    private CheckBox onePoint;

    @FXML
    private CheckBox zeroPoints;

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl2;

    private int countQuestions = 1;
    private List<String> list = new ArrayList<String>();
    private int maxIn = 70;

    @FXML
    void initialize() {
        fivePoints.setOnAction(actionEvent -> {
            fourPoints.setSelected(false);
            threeTwoPoint();
            oneZeroPoint();
        });
        fourPoints.setOnAction(actionEvent -> {
            fivePoints.setSelected(false);
            threeTwoPoint();
            oneZeroPoint();
        });
        threePoints.setOnAction(actionEvent -> {
            fiveFourPoint();
            twoPoints.setSelected(false);
            oneZeroPoint();
        });
        twoPoints.setOnAction(actionEvent -> {
            fiveFourPoint();
            threePoints.setSelected(false);
            oneZeroPoint();
        });
        onePoint.setOnAction(actionEvent -> {
            fiveFourPoint();
            threeTwoPoint();
            zeroPoints.setSelected(false);
        });
        zeroPoints.setOnAction(actionEvent -> {
            fiveFourPoint();
            threeTwoPoint();
            onePoint.setSelected(false);
        });

        try {
            list = getQuestions();
        } catch (IOException e) {
            e.printStackTrace();
        }

        lbl1.setAlignment(Pos.CENTER);
        lbl2.setAlignment(Pos.CENTER);
        labelSetText(0);

        nextBut.setOnAction(actionEvent -> {
            boolean t = fivePoints.isSelected() || fourPoints.isSelected() || threePoints.isSelected() ||
                    twoPoints.isSelected() || onePoint.isSelected() || zeroPoints.isSelected();

            if(t){
                nextUpLine();
                getNextString(countQuestions);
                fiveFourPoint();
                threeTwoPoint();
                oneZeroPoint();
            } else if(countQuestions > list.size()) {
                isSelected("", 0);
                Stage ss = (Stage) nextBut.getScene().getWindow();
                ss.close();
            } else {
                Shake fivePointAnim = new Shake(fivePoints);
                Shake fourPointAnim = new Shake(fourPoints);
                Shake threePointAnim = new Shake(threePoints);
                Shake twoPointAnim = new Shake(twoPoints);
                Shake onePointAnim = new Shake(onePoint);
                Shake zeroPointAnim = new Shake(zeroPoints);
                fivePointAnim.playAnim();
                fourPointAnim.playAnim();
                threePointAnim.playAnim();
                twoPointAnim.playAnim();
                onePointAnim.playAnim();
                zeroPointAnim.playAnim();
            }
        });
    }

    private void labelSetText(int i) {
        if (list.get(i).length() > maxIn) {
            String[] words = list.get(i).split(" ");
            String s = "";
            for (int j = 0; j < words.length; j++) {
                s += words[j] + " ";
                if (s.length() > maxIn) {
                    s = "";
                    for (int k = 0; k < j - 1; k++) {
                        s += words[k] + " ";
                    }
                    lbl1.setText(s);
                    s = "";
                    j -= 2;
                }
            }
            lbl2.setText(s);
        } else {
            lbl1.setText(list.get(i));
            lbl2.setText("");
        }
    }

    private void nextUpLine() {
        if(fivePoints.isSelected()){
            isSelected("5", 10);
        }else if(fourPoints.isSelected()){
            isSelected("4", 10);
        }else if(threePoints.isSelected()){
            isSelected("3", 10);
        }else if(twoPoints.isSelected()){
            isSelected("2", 10);
        }else if(onePoint.isSelected()){
            isSelected("1",10);
        }else if(zeroPoints.isSelected()){
            isSelected("0",10);
        }
    }

    private void getNextString(int i) {
        if(countQuestions < list.size()){
            labelSetText(i);
            countQuestions++;
        }else if(i == list.size()){
            lbl1.setText("Спасибо за прохождение анкеты!");
            lbl2.setText("");
            countQuestions++;
            nextBut.setText("Выйти");
            fiveFourPoint();
            threeTwoPoint();
            oneZeroPoint();
        }
    }

    private void oneZeroPoint(){
        onePoint.setSelected(false);
        zeroPoints.setSelected(false);
    }

    private void threeTwoPoint(){
        threePoints.setSelected(false);
        twoPoints.setSelected(false);
    }

    private void fiveFourPoint(){
        fivePoints.setSelected(false);
        fourPoints.setSelected(false);
    }

    private void isSelected(String s, int in){
        if(!nextBut.getText().equals("Выйти")){
            upString(s, in, true, true);
        } else
            upString(s, in, false, true);
    }
}