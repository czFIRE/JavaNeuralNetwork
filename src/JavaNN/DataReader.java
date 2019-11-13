package JavaNN;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

class DataReader {

    public final static int dataPerLine = 28 * 28;
    public final static int dataClasses = 10;

    /**
     * Reads batchSize lines from data and labels and puts it into labeled points.
     */
    static void readBatch(BufferedReader features, BufferedReader labels, int batchSize, LabelPoint[] labelPoints) {
        String point;
        String label;
        int lineCount = 0;

        try {
            while (lineCount < batchSize && (point = features.readLine()) != null && (label = labels.readLine()) != null) {
                String[] lineValues = point.split(",");
                int[][] values = new int[dataPerLine][1];
                for (int i = 0; i < dataPerLine; i++) {
                    values[i][0] = Integer.parseInt(lineValues[i]);
                }
                int[][] lab = new int[1][dataClasses];
                lab[0][Integer.parseInt(label)] = 1;
                LabelPoint labelPoint = new LabelPoint(lab, values);
                labelPoints[lineCount] = labelPoint;
                lineCount++;
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO
     * Writes array of ints into file.
     */
    static void write(String file, int[][] answers) {
        java.io.PrintWriter outfile = null;

        try {
            java.io.File answerCSV = new java.io.File(file);
            outfile = new java.io.PrintWriter(answerCSV);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert outfile != null;
        for (int[] answer : answers) {  //change to max
            outfile.println(Arrays.toString(answer));
        }
        outfile.close();
    }
}
