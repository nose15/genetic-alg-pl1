package org.pwr.dtos;

import org.pwr.geneticalgorithm.Genotype;

public class BestFitWithScoreDTO {
    public double score;
    public Genotype bestFit;

    public BestFitWithScoreDTO(double score, Genotype bestFit) {
        this.score = score;
        this.bestFit = bestFit;
    }
}
