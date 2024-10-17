package org.pwr.selector.impl;

import org.pwr.BestFitWithScoreDTO;
import org.pwr.Genotype;
import org.pwr.fitnessevaluator.FitnessEvaluator;
import org.pwr.selector.Selector;

import java.util.*;
import java.util.stream.Collectors;

public class RankSelector implements Selector {
    final private FitnessEvaluator fitnessEvaluator;
    final private List<Double> cumulativeProbabilities;

    public RankSelector(FitnessEvaluator fitnessEvaluator, List<Double> cumulativeProbabilities) {
        this.fitnessEvaluator = fitnessEvaluator;
        this.cumulativeProbabilities = cumulativeProbabilities;
    }

    @Override
    public List<Genotype> selectParents(List<Genotype> population) {
        List<Genotype> populationSortedByFitness = population.stream()
                .sorted(this::compare)
                .collect(Collectors.toList());

        return pickParents(populationSortedByFitness, populationSortedByFitness.size() / 2);
    }

    private List<Genotype> pickParents(List<Genotype> from, int count) {
        List<Genotype> pickedParents = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Random random = new Random();
            double n = random.doubles(1, 0, 1).sum();

            int index = -1;
            double cumulativeProb = 0;
            do {
                index++;
                cumulativeProb = cumulativeProbabilities.get(index);
            } while (n > cumulativeProb && index < from.size() - 1);

            pickedParents.add(from.get(index));
        }

        return pickedParents;
    }

    @Override
    public BestFitWithScoreDTO selectBestFitWithScore(List<Genotype> population) {
        Genotype bestFit = population.stream().max(this::compare).get();
        double score = fitnessEvaluator.calculateFitnessScore(bestFit);
        return new BestFitWithScoreDTO(score, bestFit);
    }

    private int compare(Genotype a, Genotype b) {
        double scoreA = fitnessEvaluator.calculateFitnessScore(a);
        double scoreB = fitnessEvaluator.calculateFitnessScore(b);

        if (scoreA < scoreB) {
            return 1;
        } else if (scoreA > scoreB) {
            return -1;
        }
        return 0;
    }
}
