package JavaNN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("I work!");
        System.out.println("Seed: " + Utils.getSeed());
        //new Test();

        Utils.setSeed(138);   //change for better results
        System.out.println("Seed: " + Utils.getSeed());

        int epochs = 16;
        double learningRate = 0.05;
        double momentum = 0.80;
        int []architecture = new int[] {784, 128, 10};
        int miniBatchSize = 200;
        /* Data provided to us by the teacher */
        //D:\Java\JavaNeuralNetwork\MNIST_DATA\
        String trainData = "D:\\Java\\JavaNeuralNetwork\\MNIST_DATA\\mnist_train_vectors.csv";
        String trainLabels = "D:\\Java\\JavaNeuralNetwork\\MNIST_DATA\\mnist_train_labels.csv";
        String testData = "D:\\Java\\JavaNeuralNetwork\\MNIST_DATA\\mnist_test_vectors.csv";
        String testLabels = "D:\\Java\\JavaNeuralNetwork\\MNIST_DATA\\mnist_test_labels.csv";

        BufferedReader tData = null, tLabels = null, control = null;
        try {
            tData = new BufferedReader(new FileReader(trainData));
            tLabels = new BufferedReader(new FileReader(trainLabels));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert tData != null;
        assert tLabels != null;

        int [][][] batches = new int[Utils.dataSetLength/miniBatchSize][DataReader.dataPerLine][miniBatchSize];
        int [][][] labels = new int[Utils.dataSetLength/miniBatchSize][DataReader.dataClasses][miniBatchSize];

        DataReader.readData(tData, batches, DataReader.dataPerLine, miniBatchSize, Utils.dataSetLength);
        DataReader.readData(tLabels, labels, 1, miniBatchSize, Utils.dataSetLength);

        Mlp mlp = new Mlp(architecture, learningRate, miniBatchSize, momentum);

        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < Utils.dataSetLength/miniBatchSize; i++) {
                mlp.evaluate(batches[i]);
                mlp.momentumLayer3BackProp(batches[i], labels[i]);
                if (i % 10 == 0) System.out.println("epoch: " + (epoch + 1) + " iteration: " + i);
            }
            mlp.setLearningRate(learningRate/Math.sqrt(epoch + 1));
        }
        System.out.println("done learning");

        double [][][] testLabelsCheck = new double [Utils.dataSetLength/miniBatchSize][][];
        for (int i = 0; i < Utils.dataSetLength/miniBatchSize; i++) {
            mlp.evaluate(batches[i]);
            testLabelsCheck[i] = Utils.transposeMat(mlp.activations.get(1));
        }

        DataReader.write("D:\\Java\\JavaNeuralNetwork\\evaluator\\trainPredictions", testLabelsCheck);
        //D:\Java\JavaNeuralNetwork\evaluator\trainPredictions

        try {
            control = new BufferedReader(new FileReader(testData));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert control != null;

        int [][][] controlTest = new int[Utils.testSetLength/miniBatchSize][DataReader.dataPerLine][miniBatchSize];
        DataReader.readData(control, controlTest, DataReader.dataPerLine, miniBatchSize, Utils.testSetLength);

        double [][][] answers = new double [Utils.testSetLength/miniBatchSize][][];
        for (int i = 0; i < Utils.testSetLength/miniBatchSize; i++) {
            mlp.evaluate(controlTest[i]);
            answers[i] = Utils.transposeMat(mlp.activations.get(1));
        }

        DataReader.write("D:\\Java\\JavaNeuralNetwork\\evaluator\\actualTestPredictions", answers);
    }
}
