package org.pwr;

import org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction;
import org.pwr.combinator.Combinator;
import org.pwr.combinator.impl.HalfCombinator;
import org.pwr.dtos.GenAlgResult;
import org.pwr.fitnessevaluator.FitnessEvaluator;
import org.pwr.fitnessevaluator.impl.BasicEvaluator;
import org.pwr.geneticalgorithm.*;
import org.pwr.mutator.Mutator;
import org.pwr.mutator.impl.BasicMutator;
import org.pwr.selector.Selector;
import org.pwr.selector.impl.ExponentialDiscreteDistribution;
import org.pwr.selector.impl.RankSelector;

import java.util.List;

import static org.pwr.geneticalgorithm.Utils.generateRandomIndividuals;

public class GeneticAlgorithm {
    final Population population;

    public GeneticAlgorithm(int size, double selectionPressure, List<Person> people) {
        this.population = initializePopulation(size, selectionPressure, people);
    }

    public GenAlgResult run() {
        int counter = 0;
        while (counter < 100) {
            EvolutionResult evolutionResult = population.evolve();

            if (!evolutionResult.hasImproved) {
                counter++;
            } else {
                counter = 0;
            }
        }

        return new GenAlgResult(population.getHighestScore(), population.getBestFit().getGenome());
    }

    private Population initializePopulation(int size, double selectionPressure, List<Person> people) {
        ExponentialDecayFunction exponentialDecayFunction = new ExponentialDecayFunction(selectionPressure, 0.0001, size);
        ExponentialDiscreteDistribution discreteDistribution = new ExponentialDiscreteDistribution(exponentialDecayFunction, size);

//        Mutator mutator = new BasicMutator(people.size(), 0.05);
        Combinator combinator = new HalfCombinator();
        FitnessEvaluator fitnessEvaluator = new BasicEvaluator(people);
        Selector selector = new RankSelector(fitnessEvaluator, discreteDistribution);
        Population population = new Population(selector, combinator);
        List<Genotype> individuals = generateRandomIndividuals(size, people);

        population.setPopulation(individuals);

        return population;
    }
}
