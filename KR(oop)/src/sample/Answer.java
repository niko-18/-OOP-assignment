package sample;

import javafx.beans.property.SimpleStringProperty;

public class Answer {
    private SimpleStringProperty faculty = new SimpleStringProperty();
    private SimpleStringProperty group = new SimpleStringProperty();
    private SimpleStringProperty course = new SimpleStringProperty();
    private SimpleStringProperty age = new SimpleStringProperty();
    private SimpleStringProperty gender = new SimpleStringProperty();
    private SimpleStringProperty questionsOne = new SimpleStringProperty();
    private SimpleStringProperty questionsTwo = new SimpleStringProperty();
    private SimpleStringProperty questionsThree = new SimpleStringProperty();
    private SimpleStringProperty questionsFour = new SimpleStringProperty();
    private SimpleStringProperty questionsFive = new SimpleStringProperty();
    private SimpleStringProperty questionsSix = new SimpleStringProperty();
    private SimpleStringProperty questionsSeven = new SimpleStringProperty();

    public String getFaculty() {
        return faculty.get();
    }

    public SimpleStringProperty facultyProperty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty.set(faculty);
    }

    public String getGroup() {
        return group.get();
    }

    public SimpleStringProperty groupProperty() {
        return group;
    }

    public void setGroup(String group) {
        this.group.set(group);
    }

    public String getCourse() {
        return course.get();
    }

    public SimpleStringProperty courseProperty() {
        return course;
    }

    public void setCourse(String course) {
        this.course.set(course);
    }

    public String getAge() {
        return age.get();
    }

    public SimpleStringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getQuestionsOne() {
        return questionsOne.get();
    }

    public SimpleStringProperty questionsOneProperty() {
        return questionsOne;
    }

    public void setQuestionsOne(String questionsOne) {
        this.questionsOne.set(questionsOne);
    }

    public String getQuestionsTwo() {
        return questionsTwo.get();
    }

    public SimpleStringProperty questionsTwoProperty() {
        return questionsTwo;
    }

    public void setQuestionsTwo(String questionsTwo) {
        this.questionsTwo.set(questionsTwo);
    }

    public String getQuestionsThree() {
        return questionsThree.get();
    }

    public SimpleStringProperty questionsThreeProperty() {
        return questionsThree;
    }

    public void setQuestionsThree(String questionsThree) {
        this.questionsThree.set(questionsThree);
    }

    public String getQuestionsFour() {
        return questionsFour.get();
    }

    public SimpleStringProperty questionsFourProperty() {
        return questionsFour;
    }

    public void setQuestionsFour(String questionsFour) {
        this.questionsFour.set(questionsFour);
    }

    public String getQuestionsFive() {
        return questionsFive.get();
    }

    public SimpleStringProperty questionsFiveProperty() {
        return questionsFive;
    }

    public void setQuestionsFive(String questionsFive) {
        this.questionsFive.set(questionsFive);
    }

    public String getQuestionsSix() {
        return questionsSix.get();
    }

    public SimpleStringProperty questionsSixProperty() {
        return questionsSix;
    }

    public void setQuestionsSix(String questionsSix) {
        this.questionsSix.set(questionsSix);
    }

    public String getQuestionsSeven() {
        return questionsSeven.get();
    }

    public SimpleStringProperty questionsSevenProperty() {
        return questionsSeven;
    }

    public void setQuestionsSeven(String questionsSeven) {
        this.questionsSeven.set(questionsSeven);
    }
}
