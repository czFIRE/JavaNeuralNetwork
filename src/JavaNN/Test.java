package JavaNN;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Test {

    public Test () throws IOException {
        readerTest("D:\\Java\\JavaNeuralNetwork\\testData\\dataTest.txt", "D:\\Java\\JavaNeuralNetwork\\testData\\labelTest.txt");
        printerTest("D:\\Java\\JavaNeuralNetwork\\testData\\test_print.csv", new int[] {1, 2, 3});
    }

    private void readerTest (String data, String labels) throws IOException {
        BufferedReader features = new BufferedReader(new FileReader(data));
        BufferedReader label = new BufferedReader(new FileReader(labels));

        LabelPoint[] labelPoints = new LabelPoint[2];

        for (int i = 0; i < 2; i++) {
            DataReader.readBatch(features, label, 2, labelPoints);
            for (int j = 0; j < 2; j++) {
                //System.out.println(labelPoints[j].getLabel() + "");
                System.out.print(labelPoints[j].getLabel());
                System.out.print(" - ");
                System.out.println(Arrays.toString(labelPoints[j].getFeatures()));
            }
        }

        if (features != null) {
            try {
                features.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (label != null) {
            try {
                features.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void printerTest(String file, int[] answers) {
        DataReader.write(file, answers);
    }
}
