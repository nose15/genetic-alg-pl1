package org.pwr;

import org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction;

import java.util.*;

public class Utils {
    public static List<Genotype> generateRandomIndividuals(int size, List<Person> people) {
        Random random = new Random();
        List<Genotype> population = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Map<Integer, List<Integer>> genome = new HashMap<>();
            for (int j = 0; j < people.size() - 1; j++) {
                List<Integer> connections = new ArrayList<>();

                while (connections.size() < people.get(j).preferences.size()) {
                    int newConn = random.ints(0, people.size()).findFirst().getAsInt();
                    if (newConn == j || connections.contains(newConn)) {
                        continue;
                    }
                    connections.add(newConn);
                }

                genome.put(j, connections);
            }

            population.add(new Genotype(genome));
        }

        return population;
    }

    public static List<Double> calculateCumulativeProbabilities(int count, ExponentialDecayFunction exponentialDecayFunction) {
        List<Double> cumulativeProbabilities = new ArrayList<>();
        Double sum = 0.0;
        for (int i = 0; i < count; i++) {
            double valueAtI = exponentialDecayFunction.value(i);
            sum += valueAtI;
            cumulativeProbabilities.add(sum);
        }

        List<Double> normalizedProbabilities = new ArrayList<>();
        for (Double cumulativeProbability : cumulativeProbabilities) {
            normalizedProbabilities.add(Math.floor((cumulativeProbability / sum) * 1000000) / 1000000);
        }

        return normalizedProbabilities;
    }

    public static void displayScore(Population population, List<Person> people) {
        System.out.println("The best solution with score = " + population.getHighestScore());
        Map<Integer, List<Integer>> genome = population.getBestFit().getGenome();

        for (int i : genome.keySet()) {
            StringBuilder stringBuilder = new StringBuilder((i + 1) + ". Searched for: ");
            stringBuilder.append(people.get(i).preferences).append(",");
            stringBuilder.append(" GOT: ");
            for (int j = 0; j < genome.get(i).size(); j++) {
                stringBuilder.append(genome.get(i).get(j) + 1).append(" - ").append(people.get(genome.get(i).get(j)).professions).append(", ");
            }

            System.out.println(stringBuilder);
        }
    }
}
