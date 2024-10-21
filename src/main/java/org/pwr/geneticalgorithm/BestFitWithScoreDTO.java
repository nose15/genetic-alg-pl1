package org.pwr.geneticalgorithm;

public class BestFitWithScoreDTO {
    public double score;
    public Genotype bestFit;

    public BestFitWithScoreDTO(double score, Genotype bestFit) {
        this.score = score;
        this.bestFit = bestFit;
    }
}
