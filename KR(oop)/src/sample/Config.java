package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public abstract class Config {

    protected final String FileNameQuestions = "src\\sample\\resource\\Questions.txt";
    protected final String FileNameStatistics = "src\\sample\\resource\\Statistics.txt";
    public List<String> questions = new ArrayList<String>();
    public List<String> statistics = new ArrayList<String>();
    private final String stringLastOne = "-------------------------------------------------------------------------" +
            "---------------------------------------------";
    private String stringLastTwo = "                                        Ср. знач:    ";
    public double srOneQuest = 0;
    public double srTwoQuest = 0;
    public double srThreeQuest = 0;
    public double srFourQuest = 0;
    public double srFiveQuest = 0;
    public double srSexQuest = 0;
    public double srSevenQuest = 0;

    protected void menu(Stage stage, String s) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(s));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    protected List<String> getQuestions() throws IOException{
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(FileNameQuestions), "Windows-1251"));
        String s;
        while ((s = br.readLine()) != null) {
            questions.add(s);
        }
        br.close();
        return questions;
    }

    protected void getStatistics(int in) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(FileNameStatistics), "Windows-1251"));
        String s;
        while ((s = br.readLine()) != null) {
            statistics.add(s);
        }
        setSrQuest(in);
        br.close();
    }

    protected void upString(String s, int in, boolean bool, boolean append){
            for (int i = 0; s.length() < in; i++)
                s += " ";
            if (!append) {
                try {
                    getStatistics(2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            write(s, bool, append);
    }

    protected void write(String s, boolean bool, boolean append) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(
                    new FileOutputStream(FileNameStatistics, append), "windows-1251");
            PrintWriter out = new PrintWriter(osw);
            if (!append) {
                for (int i = 0; i < statistics.size() - 2; i++) {
                    out.println(statistics.get(i));
                }
            }
            if(bool){
                out.print(s);
            }else {
                out.print("\n");
                out.println(stringLastOne);
                getStatistics(0);
                stringLastTwo += setSt("" + srOneQuest, 10) + setSt("" + srTwoQuest, 10) +
                        setSt("" + srThreeQuest, 10) + setSt("" + srFourQuest, 10) +
                        setSt("" + srFiveQuest, 10) + setSt("" + srSexQuest, 10) +
                        setSt("" + srSevenQuest, 10);
                out.println(stringLastTwo);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setSrQuest(int in) {
        int sumOne = 0;
        int sumTwo = 0;
        int sumThree = 0;
        int sumFour = 0;
        int sumFive = 0;
        int sumSix = 0;
        int sumSeven = 0;
        if (statistics.size() > 3) {
            for (int i = 1; i < statistics.size() - in; i++) {
                String s = statistics.get(i).replaceAll("\\s+", " ");
                String[] words = s.split(" ");
                sumOne += Integer.parseInt(words[5]);
                sumTwo += Integer.parseInt(words[6]);
                sumThree += Integer.parseInt(words[7]);
                sumFour += Integer.parseInt(words[8]);
                sumFive += Integer.parseInt(words[9]);
                sumSix += Integer.parseInt(words[10]);
                sumSeven += Integer.parseInt(words[11]);
            }
        } else {
            for (int i = 1; i < statistics.size(); i++) {
                String s = statistics.get(i).replaceAll("\\s+", " ");
                String[] words = s.split(" ");
                sumOne += Integer.parseInt(words[5]);
                sumTwo += Integer.parseInt(words[6]);
                sumThree += Integer.parseInt(words[7]);
                sumFour += Integer.parseInt(words[8]);
                sumFive += Integer.parseInt(words[9]);
                sumSix += Integer.parseInt(words[10]);
                sumSeven += Integer.parseInt(words[11]);
            }
        }
        int d = statistics.size() - in - 1;
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
    }

    private String setSt(String s, int in) {
        for (int i = 0; s.length() < in; i++)
            s += " ";
        return s;
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
