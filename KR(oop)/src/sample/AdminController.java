package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class AdminController extends Config{

    private ObservableList<Answer> usersAnswer = FXCollections.observableArrayList();
    private ObservableList<Answer> sortUsersAnswer = FXCollections.observableArrayList();

    @FXML
    private TableView<Answer> table;

    @FXML
    private TableColumn<Answer, String> facultyColumn;

    @FXML
    private TableColumn<Answer, String> groupColumn;

    @FXML
    private TableColumn<Answer, String> courseColumn;

    @FXML
    private TableColumn<Answer, String> ageColumn;

    @FXML
    private TableColumn<Answer, String> genderColumn;

    @FXML
    private TableColumn<Answer, String> questionColumn;

    @FXML
    private TableColumn<Answer, String> questionOneColumn;

    @FXML
    private TableColumn<Answer, String> questionTwoColumn;

    @FXML
    private TableColumn<Answer, String> questionThreeColumn;

    @FXML
    private TableColumn<Answer, String> questionFourColumn;

    @FXML
    private TableColumn<Answer, String> questionFiveColumn;

    @FXML
    private TableColumn<Answer, String> questionSixColumn;

    @FXML
    private TableColumn<Answer, String> questionSevenColumn;

    @FXML
    private Button sortButton;

    @FXML
    private TextField facultySort;

    @FXML
    private TextField groupSort;

    @FXML
    private TextField courseSort;

    @FXML
    private Button viewStatistics;

    @FXML
    private Button editQuestions;

    @FXML
    void initialize() {
        try {
            initData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        editQuestions.setOnAction(actionEvent -> opening("notepad src\\sample\\resource\\Questions.txt"));
        viewStatistics.setOnAction(actionEvent -> opening("notepad src\\sample\\resource\\Statistics.txt"));
        sortButton.setOnAction(actionEvent -> sortTableView());

        facultyColumn.setCellValueFactory(new PropertyValueFactory<Answer, String>("faculty"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<Answer, String>("group"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<Answer, String>("course"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Answer, String>("age"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<Answer, String>("gender"));
        questionOneColumn.setCellValueFactory(new PropertyValueFactory<Answer, String>("questionsOne"));
        questionTwoColumn.setCellValueFactory(new PropertyValueFactory<Answer, String>("questionsTwo"));
        questionThreeColumn.setCellValueFactory(new PropertyValueFactory<Answer, String>("questionsThree"));
        questionFourColumn.setCellValueFactory(new PropertyValueFactory<Answer, String>("questionsFour"));
        questionFiveColumn.setCellValueFactory(new PropertyValueFactory<Answer, String>("questionsFive"));
        questionSixColumn.setCellValueFactory(new PropertyValueFactory<Answer, String>("questionsSix"));
        questionSevenColumn.setCellValueFactory(new PropertyValueFactory<Answer, String>("questionsSeven"));
    }

    private void initData() throws IOException {
        getStatistics(2);
        String s;
        for (int i = 0; i < statistics.size(); i++) {
            if (i > 0 && i < statistics.size() - 2) {
                s = statistics.get(i).replaceAll("\\s+", " ");
                Answer an = new Answer();
                String[] words = s.split(" ");
                an.setFaculty(words[0]);
                an.setGroup(words[1]);
                an.setCourse(words[2]);
                an.setAge(words[3]);
                an.setGender(words[4]);
                an.setQuestionsOne(words[5]);
                an.setQuestionsTwo(words[6]);
                an.setQuestionsThree(words[7]);
                an.setQuestionsFour(words[8]);
                an.setQuestionsFive(words[9]);
                an.setQuestionsSix(words[10]);
                an.setQuestionsSeven(words[11]);
                usersAnswer.add(an);
            } else if (i == statistics.size() - 2) {
                Answer an = new Answer();
                an.setFaculty(upString(14));
                an.setGroup(upString(14));
                an.setCourse(upString(8));
                an.setAge(upString(9));
                an.setGender(upString(14));
                an.setQuestionsOne(upString(6));
                an.setQuestionsTwo(upString(7));
                an.setQuestionsThree(upString(8));
                an.setQuestionsFour(upString(8));
                an.setQuestionsFive(upString(8));
                an.setQuestionsSix(upString(8));
                an.setQuestionsSeven(upString(8));
                usersAnswer.add(an);
            } else if (i == statistics.size() - 1){
                s = statistics.get(i).replaceAll("\\s+", " ");
                Answer an = new Answer();
                String[] words = s.split(" ");
                an.setFaculty("");
                an.setGroup("");
                an.setCourse("");
                an.setAge("");
                an.setGender(words[1] + " " + words[2]);
                an.setQuestionsOne("" + srOneQuest);
                an.setQuestionsTwo("" + srTwoQuest);
                an.setQuestionsThree("" + srThreeQuest);
                an.setQuestionsFour("" + srFourQuest);
                an.setQuestionsFive("" + srFiveQuest);
                an.setQuestionsSix("" + srSexQuest);
                an.setQuestionsSeven("" + srSevenQuest);
                usersAnswer.add(an);
            }
        }
        table.setItems(usersAnswer);
    }

    private void opening(String s) {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String upString(int in) {
        String s = "";
        for (int i = 0; i < in; i++) {
            s += "-";
        }
        return s;
    }

    private void sortTableView() {
        String faculty = facultySort.getText();
        String course = courseSort.getText();
        String group = groupSort.getText();

        boolean conditionOne = !faculty.equals("");
        boolean conditionTwo = !course.equals("");
        boolean conditionThree = !group.equals("");

        boolean conditionFour = conditionOne || conditionTwo || conditionThree;
        boolean conditionFive = conditionOne && conditionTwo && conditionThree;
        boolean conditionSix = conditionOne && conditionTwo;
        boolean conditionSeven = conditionOne && conditionThree;
        boolean conditionEight = conditionTwo && conditionThree;

        int nam = usersAnswer.size();

        if (conditionFour) {
            sortUsersAnswer.clear();
            if (conditionFive) {
                for (int i = 0; i < nam - 2; i++) {
                    boolean cond1 = faculty.equalsIgnoreCase(usersAnswer.get(i).getFaculty());
                    boolean cond2 = course.equalsIgnoreCase(usersAnswer.get(i).getCourse());
                    boolean cond3 = group.equalsIgnoreCase(usersAnswer.get(i).getGroup());

                    if (cond1 && cond2 && cond3) {
                        sortUsersAnswer.add(usersAnswer.get(i));
                    }
                }
                calc(nam);
            } else if (conditionSix) {
                for (int i = 0; i < nam - 2; i++) {
                    boolean cond1 = faculty.equalsIgnoreCase(usersAnswer.get(i).getFaculty());
                    boolean cond2 = course.equalsIgnoreCase(usersAnswer.get(i).getCourse());

                    if (cond1 && cond2) {
                        sortUsersAnswer.add(usersAnswer.get(i));
                    }
                }
                calc(nam);
            } else if (conditionSeven) {
                for (int i = 0; i < nam - 2; i++) {
                    boolean cond1 = faculty.equalsIgnoreCase(usersAnswer.get(i).getFaculty());
                    boolean cond3 = group.equalsIgnoreCase(usersAnswer.get(i).getGroup());

                    if (cond1 && cond3) {
                        sortUsersAnswer.add(usersAnswer.get(i));
                    }
                }
                calc(nam);
            } else if (conditionEight) {
                for (int i = 0; i < nam - 2; i++) {
                    boolean cond2 = course.equalsIgnoreCase(usersAnswer.get(i).getCourse());
                    boolean cond3 = group.equalsIgnoreCase(usersAnswer.get(i).getGroup());

                    if (cond2 && cond3) {
                        sortUsersAnswer.add(usersAnswer.get(i));
                    }
                }
                calc(nam);
            } else if (conditionOne) {
                for (int i = 0; i < nam - 2; i++) {
                    if (faculty.equalsIgnoreCase(usersAnswer.get(i).getFaculty())) {
                        sortUsersAnswer.add(usersAnswer.get(i));
                    }
                }
                calc(nam);
            } else if (conditionTwo) {
                for (int i = 0; i < nam - 2; i++) {
                    if (course.equalsIgnoreCase(usersAnswer.get(i).getCourse())) {
                        sortUsersAnswer.add(usersAnswer.get(i));
                    }
                }
                calc(nam);
            } else {
                for (int i = 0; i < nam - 2; i++) {
                    if (group.equalsIgnoreCase(usersAnswer.get(i).getGroup())) {
                        sortUsersAnswer.add(usersAnswer.get(i));
                    }
                }
                calc(nam);
            }
        } else {
            table.getItems().removeAll();
            table.setItems(usersAnswer);
        }
    }

    private void calc(int nam) {
        int sumOne = 0;
        int sumTwo = 0;
        int sumThree = 0;
        int sumFour = 0;
        int sumFive = 0;
        int sumSix = 0;
        int sumSeven = 0;
        int d = sortUsersAnswer.size();
        for (int i = 0; i < d; i++) {
            sumOne += Integer.parseInt(sortUsersAnswer.get(i).getQuestionsOne());
            sumTwo += Integer.parseInt(sortUsersAnswer.get(i).getQuestionsTwo());
            sumThree += Integer.parseInt(sortUsersAnswer.get(i).getQuestionsThree());
            sumFour += Integer.parseInt(sortUsersAnswer.get(i).getQuestionsFour());
            sumFive += Integer.parseInt(sortUsersAnswer.get(i).getQuestionsFive());
            sumSix += Integer.parseInt(sortUsersAnswer.get(i).getQuestionsSix());
            sumSeven += Integer.parseInt(sortUsersAnswer.get(i).getQuestionsSeven());
        }
        srOneQuest = (double) sumOne/d;
        srOneQuest = round(srOneQuest, 2);
        srTwoQuest = (double) sumTwo/d;
        srTwoQuest = round(srTwoQuest, 2);
        srThreeQuest = (double) sumThree/d;
        srThreeQuest = round(srThreeQuest, 2);
        srFourQuest = (double) sumFour/d;
        srFourQuest = round(srFourQuest, 2);
        srFiveQuest = (double) sumFive/d;
        srFiveQuest = round(srFiveQuest, 2);
        srSexQuest = (double) sumSix/d;
        srSexQuest = round(srSexQuest, 2);
        srSevenQuest = (double) sumSeven/d;
        srSevenQuest = round(srSevenQuest, 2);

        Answer an = new Answer();
        an.setFaculty("");
        an.setGroup("");
        an.setCourse("");
        an.setAge("");
        an.setGender("Ср. знач:");
        an.setQuestionsOne("" + srOneQuest);
        an.setQuestionsTwo("" + srTwoQuest);
        an.setQuestionsThree("" + srThreeQuest);
        an.setQuestionsFour("" + srFourQuest);
        an.setQuestionsFive("" + srFiveQuest);
        an.setQuestionsSix("" + srSexQuest);
        an.setQuestionsSeven("" + srSevenQuest);

        sortUsersAnswer.add(usersAnswer.get(nam - 2));
        sortUsersAnswer.add(an);
        table.getItems().removeAll();
        table.setItems(sortUsersAnswer);
    }
}
