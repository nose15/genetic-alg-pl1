package org.pwr.geneticalgorithm;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.pwr.combinator.Combinator;
import org.pwr.dtos.BestFitWithScoreDTO;
import org.pwr.selector.Selector;

import java.util.ArrayList;
import java.util.List;

public class Population {
    private final List<Genotype> population = new ArrayList<>();
    private final Selector selector;
    private final Combinator combinator;
    private double highestScore = Double.NEGATIVE_INFINITY;
    private Genotype bestFit;
    private NormalDistribution normalDistribution;

    public Population(Selector selector, Combinator combinator) {
        this.selector = selector;
        this.combinator = combinator;
    }

    public void setPopulation(List<Genotype> population) {
        this.population.addAll(population);
    }

    public EvolutionResult evolve() {
        List<Genotype> selectedParents = selector.selectParents(population);
        int prevSize = population.size();
        population.retainAll(selectedParents);
        int offspringCount = prevSize - population.size();
        List<Genotype> offsprings = combinator.createOffsprings(selectedParents, offspringCount);
        population.addAll(offsprings);

        BestFitWithScoreDTO bestFitWithScoreDTO = selector.selectBestFitWithScore(population);
        if (bestFitWithScoreDTO.bestFit != null) {
            boolean hasImproved = bestFitWithScoreDTO.score >= this.highestScore;
            if (hasImproved) {
                this.bestFit = bestFitWithScoreDTO.bestFit;
                this.highestScore = bestFitWithScoreDTO.score;
            }
            return new EvolutionResult(hasImproved, bestFitWithScoreDTO.bestFit, bestFitWithScoreDTO.score);
        }

        throw new NullPointerException("Population is empty");
    }

    public Genotype getBestFit() {
        return bestFit;
    }

    public double getHighestScore() {
        return highestScore;
    }
}
