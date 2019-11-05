package JavaNN;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

class DataReader {

    private final static int dataPerLine = 28 * 28;

    /**
     * Tested and functional, reads batchSize lines from data and labels and puts it into labeled points.
     */
    static void readBatch(BufferedReader features, BufferedReader labels, int batchSize, LabelPoint[] labelPoints) {
        String point;
        String label;
        int lineCount = 0;

        try {
            while (lineCount < batchSize && (point = features.readLine()) != null && (label = labels.readLine()) != null) {
                String[] lineValues = point.split(",");
                int[] values = new int[dataPerLine];
                for (int i = 0; i < dataPerLine; i++) {
                    values[i] = Integer.parseInt(lineValues[i]);
                }

                LabelPoint labelPoint = new LabelPoint(Integer.parseInt(label), values);
                labelPoints[lineCount] = labelPoint;
                lineCount++;
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tested and functional, writes array of ints into file.
     */
    static void write(String file, int[] answers) {
        java.io.PrintWriter outfile = null;

        try {
            java.io.File answerCSV = new java.io.File(file);
            outfile = new java.io.PrintWriter(answerCSV);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert outfile != null;
        for (int answer : answers) {
            outfile.println(answer);
        }
        outfile.close();
    }
}
