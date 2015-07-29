package Randomization.Tests;

import pseudo.random.generators.CombinedLinearCongruential;
import pseudo.random.generators.MidSquare;
import pseudo.random.generators.MultilicativeCongruential;

public class KolmogrovSmirnov {
    
    private double[] SampleData, IOverN, DPlus, DMinus;
    private double DPlusVal, DMinusVal, ALpha;

    public KolmogrovSmirnov(double[] SampleData,double Alpha) {
        this.SampleData = SampleData;
        this.ALpha = Alpha;
        sortAsc(SampleData);
        calculateIOverN();
        calculateDPlus();
        calculateDMinus();
        
    }
    
    public boolean Acceptance() {
        double D;
        if(DPlusVal > DMinusVal)
            D = DPlusVal;
        else 
            D = DMinusVal;
        
        double DAlpha = 2;
        if(ALpha == 0.1) 
            DAlpha = 1.224 / Math.sqrt(SampleData.length);
        
        if(ALpha == 0.05)
            DAlpha = 1.358 / Math.sqrt(SampleData.length);
        
        if(ALpha == 0.01)
            DAlpha = 1.268 / Math.sqrt(SampleData.length);
        if(DAlpha > D)
            return true;
        
        return false;
    }
    
    private void sortAsc(double[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }            
        }
    }
    
    private double getMax(double[] arr) {
        double start = arr[0];
        for (int i = 0; i < arr.length; i++)
            if(start < arr[i])
                start = arr[i];
        return start;
    }
    
    private void calculateIOverN() {
        IOverN = new double[SampleData.length];
        for(int i = 0; i < IOverN.length; i++) {
            IOverN[i] = (double) (i + 1) / SampleData.length;
        }
    }
    
    private void calculateDPlus() {
        DPlus = new double[SampleData.length];
        for(int i = 0; i < DPlus.length; i++) {
            DPlus[i] = IOverN[i] - SampleData[i];
        }
        DPlusVal = getMax(DPlus);
    }
    
    private void calculateDMinus() {
        DMinus = new double[SampleData.length];
        for(int i = 0; i < DMinus.length; i++) {
            if(i == 0)
                DMinus[i] = SampleData[i];
            else
                DMinus[i] = SampleData[i] - IOverN[i - 1];
        }
        DMinusVal = getMax(DMinus);
    }
    
    public static void main(String[] args) {
        MultilicativeCongruential RG = new MultilicativeCongruential();
//        MidSquare RG = new MidSquare();
//        CombinedLinearCongruential RG = new CombinedLinearCongruential();
        double[] arr = new double[10000];
        for (int i = 0; i < 10000; i++) {
            arr[i] = RG.Random();
        }
        System.out.println(new KolmogrovSmirnov(arr, 0.05).Acceptance());
    }
    
}
