package org.pwr;

import org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction;
import org.pwr.combinator.Combinator;
import org.pwr.combinator.impl.HalfCombinator;
import org.pwr.fitnessevaluator.FitnessEvaluator;
import org.pwr.fitnessevaluator.impl.BasicEvaluator;
import org.pwr.selector.Selector;
import org.pwr.selector.impl.ExponentialDiscreteDistribution;
import org.pwr.selector.impl.RankSelector;

import java.util.*;

import static org.pwr.Utils.*;

public class Application {
    public static void main(String[] args) {
        String filePath = "/home/lukasz/Studia/3_sem/jezyki_programowania/1_lab/data";
        int size = 1000;

        List<Person> people = Parser.parsePeopleFromFile(filePath);
        ExponentialDecayFunction exponentialDecayFunction = new ExponentialDecayFunction(0.1, 0.0001, size);
        ExponentialDiscreteDistribution discreteDistribution = new ExponentialDiscreteDistribution(exponentialDecayFunction, size);
        List<Genotype> individuals = generateRandomIndividuals(size, people);

        Combinator combinator = new HalfCombinator();
        FitnessEvaluator fitnessEvaluator = new BasicEvaluator(people);
        Selector selector = new RankSelector(fitnessEvaluator, discreteDistribution);
        Population population = new Population(selector, combinator);

        population.setPopulation(individuals);

        int counter = 0;
        while (counter < 20) {
            EvolutionResult evolutionResult = population.evolve();

            if (!evolutionResult.hasImproved) {
                counter++;
            } else {
                counter = 0;
            }
        }

        System.out.println("Score: ");
        displayScore(population, people);
    }
}
