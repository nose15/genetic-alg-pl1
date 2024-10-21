package org.pwr.fitnessevaluator;

import org.pwr.geneticalgorithm.Genotype;

public interface FitnessEvaluator {
    double calculateFitnessScore(Genotype genotype);
}
