package Randomization.Tests;

import pseudo.random.generators.CombinedLinearCongruential;
import pseudo.random.generators.MidSquare;
import pseudo.random.generators.MultilicativeCongruential;

public class RunTest {
    
    private double[] SampleData;
    private int RunsNumber;
    private double Mean, Variance, Alpha, CriticalValue;

    public RunTest(double[] SampleData, double Alpha) {
        this.SampleData = SampleData;
        this.Alpha = Alpha;
    }
    
    private void calculateRunsNumber() {
        boolean positive, negative, lastP, lastN;
        positive = (SampleData[0] < SampleData[1])? true: false;
        negative = !positive;
        lastP = positive;
        lastN = negative;
        for (int i = 1; i < SampleData.length - 1; i++) {
            lastP = positive;
            lastN = negative;
            
            positive = (SampleData[i] < SampleData[i + 1])? true: false;
            negative = !positive;
            
            if(lastP != positive && lastN != negative)
                RunsNumber++;
        }
        RunsNumber++;
    }
    
    private void calculateMean() {
        Mean = (2 * SampleData.length - 1) / 3;
        Variance = (16 * SampleData.length - 29) / 90;
        CriticalValue = (RunsNumber - Mean) / Math.sqrt(Variance);
    }
    
    private boolean Acceptance() {
        ZTable zTable = new ZTable();
        if(CriticalValue > zTable.getZ(1 - (double)Alpha / 2) || CriticalValue < -1 * zTable.getZ(1 - (double)Alpha / 2))
            return false;
        return true;
    }
    
    public static void main(String[] args) {
//        CombinedLinearCongruential RG = new CombinedLinearCongruential();
        MultilicativeCongruential RG = new MultilicativeCongruential();
//        MidSquare RG = new MidSquare();
        double[] d = new double[1000];
        for (int i = 0; i < 1000; i++) {
            d[i] = RG.Random();
        }
//        double[] d = {0.87, 0.15, 0.23, 0.45, 0.69, 0.32, 0.30, 0.19, 0.24, 0.18, 0.65, 0.82, 0.93, 0.22, 0.81};
        RunTest x = new RunTest(d, 0.05);
        x.calculateRunsNumber();
        x.calculateMean();
        System.out.println(x.Acceptance());
    }
}