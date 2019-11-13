package JavaNN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("I work!");
        new Test();


        /*try {
            BufferedReader features = new BufferedReader(new FileReader("data"));
            BufferedReader label = new BufferedReader(new FileReader("labels"));

            Mlp mlp = new Mlp(new int[]{DataReader.dataPerLine, 256, 40, DataReader.dataClasses}, 0.001);
            new Application(mlp, features, label);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }
}
