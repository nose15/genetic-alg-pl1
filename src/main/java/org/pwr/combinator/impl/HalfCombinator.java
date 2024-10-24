package org.pwr.combinator.impl;

import org.pwr.geneticalgorithm.Genotype;
import org.pwr.combinator.Combinator;
import org.pwr.mutator.Mutator;
import org.pwr.mutator.impl.NoOpMutator;

import java.util.*;
import java.util.stream.Collectors;

public class HalfCombinator implements Combinator {
    final private Mutator mutator;

    public HalfCombinator(Mutator mutator) {
        this.mutator = mutator;
    }

    public HalfCombinator() {
        this.mutator = new NoOpMutator();
    }

    @Override
    public List<Genotype> createOffsprings(List<Genotype> parentPool, int count) {
        List<Genotype> result = new ArrayList<>();
        Collections.shuffle(parentPool);
        int i = 0;
        while (result.size() < count) {
            Genotype offSpring = crossover(parentPool.get(i), parentPool.get(i + 1));


            offSpring = mutator.mutate(offSpring);

            result.add(offSpring);
            i++;
            if (i >= parentPool.size() - 2) {
                i = 0;
                Collections.shuffle(parentPool);
            }
        }

        return result;
    }

    private Genotype crossover(Genotype a, Genotype b) {
        var genomeA = a.getGenome();
        var genomeB = b.getGenome();
        Map<Integer, List<Integer>> newGenome = new HashMap<>();

        assert genomeA.size() == genomeB.size() : "Genomes are not of the same size";

        for (int i = 0; i < genomeA.size(); i++) {
            List<Integer> connectionsA = genomeA.get(i);
            List<Integer> connectionsB = genomeB.get(i);
            List<Integer> newConnections = new ArrayList<>();
            newConnections.addAll(connectionsA.stream().limit(connectionsA.size() / 2).collect(Collectors.toList()));
            newConnections.addAll(connectionsB.stream().skip(connectionsA.size() / 2).collect(Collectors.toList()));

            newGenome.put(i, newConnections);
        }

        return new Genotype(newGenome);
    }

    private boolean mutateOffspring() {
        Random random = new Random();
        double randomNum = random.doubles(1, 0, 1).findFirst().orElse(0);

        return randomNum > 0.1;
    }
}
