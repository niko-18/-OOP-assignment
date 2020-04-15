package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animations.Shake;

import java.io.IOException;

public class SignUpController extends Config{

    @FXML
    private Button nextButton;

    @FXML
    private TextField signUpFaculty;

    @FXML
    private TextField signUpGroup;

    @FXML
    private TextField signUpCourse;

    @FXML
    private TextField signUpAge;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    void initialize() {
        nextButton.setOnAction(event -> signUpNewUser());

        signUpCheckBoxMale.setOnAction(event -> signUpCheckBoxFemale.setSelected(false));
        signUpCheckBoxFemale.setOnAction(event -> signUpCheckBoxMale.setSelected(false));
    }

    private void signUpNewUser() {
        String faculty = signUpFaculty.getText();
        String group = signUpGroup.getText();
        String course = signUpCourse.getText();
        String age = signUpAge.getText();
        String gender;
        if(signUpCheckBoxMale.isSelected())
            gender = "Мужской";
        else
            gender = "Женский";

        if(faculty.equals("admin") && group.equals("admin")){
            nextWindow("/sample/fxml/adminFx.fxml");
        }

        boolean boolOne = signUpCheckBoxMale.isSelected() || signUpCheckBoxFemale.isSelected();
        boolean boolTwo = !checkText(faculty) && checkText(course) && checkText(age);
        boolean boolThree = !faculty.equals("") && !group.equals("") && !course.equals("") && !age.equals("");

        if(boolOne && boolTwo && boolThree){
            upString(faculty, 11, true, false);
            upString(group, 11, true, true);
            upString(course, 7, true, true);
            upString(age, 10, true, true);
            upString(gender, 14, true, true);
            nextWindow("/sample/fxml/question.fxml");
        }else {
            if(!boolOne){
                Shake checkBoxMaleAnim = new Shake(signUpCheckBoxMale);
                Shake checkBoxFemaleAnim = new Shake(signUpCheckBoxFemale);
                checkBoxFemaleAnim.playAnim();
                checkBoxMaleAnim.playAnim();
            }
            if(checkText(faculty) || faculty.equals("")){
                Shake facultyAnim = new Shake(signUpFaculty);
                facultyAnim.playAnim();
            }
            if (!checkText(course) || course.equals("")){
                Shake courseAnim = new Shake(signUpCourse);
                courseAnim.playAnim();
            }
            if (!checkText(age) || age.equals("")){
                Shake ageAnim = new Shake(signUpAge);
                ageAnim.playAnim();
            }
            if (group.equals("")){
                Shake groupAnim = new Shake(signUpGroup);
                groupAnim.playAnim();
            }
        }
    }

    private void nextWindow(String s) {
        Stage ss = (Stage) nextButton.getScene().getWindow();
        ss.close();

        Stage stage = new Stage();
        try {
            menu(stage, s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkText(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}