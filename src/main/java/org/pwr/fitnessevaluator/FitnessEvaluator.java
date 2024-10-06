package org.pwr.fitnessevaluator;

import org.pwr.Genotype;

public interface FitnessEvaluator {
    double calculateFitnessScore(Genotype genotype);
}
