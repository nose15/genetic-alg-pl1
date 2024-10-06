package org.pwr;

import org.pwr.combinator.impl.HalfCombinator;
import org.pwr.fitnessevaluator.impl.BasicEvaluator;
import org.pwr.selector.Selector;
import org.pwr.selector.impl.RankSelector;

import java.util.*;
import java.util.stream.Collectors;


// TODO: Cleanup
// TODO: Advance further
// I want the cleanup first so we have a nice foundation to build the algorithm on

public class Application {
    public static void main(String[] args) {
        String filePath = "/home/lukasz/Studia/3_sem/jezyki_programowania/1_lab/data";
        List<Person> people = Parser.parsePeopleFromFile(filePath);
        var population = generateRandomPopulation(10, 5, people);
        population.evolve();
    }

    public static Population generateRandomPopulation(int size, int geneValues, List<Person> people) {
        Random random = new Random();
        List<Genotype> population = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Map<Integer, List<Integer>> genome = new HashMap<>();
            for (int j = 0; j < people.size() - 1; j++) {
                List<Integer> connections = random.ints(geneValues, 0, people.size() - 1).boxed().collect(Collectors.toList());
                genome.put(j, connections);
            }

            population.add(new Genotype(genome));
        }

        return new Population(population, new RankSelector(new BasicEvaluator(people)), new HalfCombinator());
    }
}
