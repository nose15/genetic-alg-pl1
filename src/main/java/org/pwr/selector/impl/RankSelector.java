package org.pwr.selector.impl;

import org.pwr.Genotype;
import org.pwr.fitnessevaluator.FitnessEvaluator;
import org.pwr.selector.Selector;

import java.util.List;
import java.util.stream.Collectors;

public class RankSelector implements Selector {
    FitnessEvaluator fitnessEvaluator;

    public RankSelector(FitnessEvaluator fitnessEvaluator) {
        this.fitnessEvaluator = fitnessEvaluator;
    }

    @Override
    public List<Genotype> selectParents(List<Genotype> population) {
        return population.stream()
                .sorted(this::compare)
                .limit(population.size() / 2)
                .collect(Collectors.toList());
    }

    private int compare(Genotype a, Genotype b) {
        if (fitnessEvaluator.calculateFitnessScore(a) > fitnessEvaluator.calculateFitnessScore(b)) {
            return 1;
        }
        return 0;
    }
}
