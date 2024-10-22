package org.pwr.mutator.impl;

import org.pwr.geneticalgorithm.Genotype;
import org.pwr.mutator.Mutator;

import java.util.*;

public class BasicMutator implements Mutator {
    private final double mutationStrength;
    private final int geneMaxValue;

    public BasicMutator(int geneMaxValue) {
        this.geneMaxValue = geneMaxValue;
        this.mutationStrength = 0.05;
    }
    public BasicMutator(int geneMaxValue, double mutationPressure) {
        this.geneMaxValue = geneMaxValue;

        if (mutationPressure < 0 || mutationPressure > 1) {
            throw new IllegalArgumentException("Mutation pressure should be in range <0; 1>");
        }

        this.mutationStrength = mutationPressure;
    }

    @Override
    public Genotype mutate(Genotype base) {
        Map<Integer, List<Integer>> originalGenome = new HashMap<>(base.getGenome());
        Genotype newGenotype = new Genotype(originalGenome);

        Set<Integer> baseKeys = newGenotype.getGenome().keySet();
        int count = (int) Math.ceil(baseKeys.size() * mutationStrength);

        Set<Integer> indicesToMutate = pickIndicesToMutate(count, baseKeys.size());

        for(int i : indicesToMutate) {
            List<Integer> baseGeneCopy = new ArrayList<>(base.getGenome().get(i));
            List<Integer> mutatedGene = mutateGene(baseGeneCopy);
            newGenotype.getGenome().put(i, mutatedGene);
        }

        return newGenotype;
    }

    private List<Integer> mutateGene(List<Integer> gene) {
        List<Integer> newGenome = new ArrayList<>();

        for (int i = 0; i < gene.size(); i++) {
          newGenome.add(Math.abs(gene.get(i) + -2 * (i % 2) + 1) % geneMaxValue);
        }

        return newGenome;
    }

    private Set<Integer> pickIndicesToMutate(int count, int endBound) {
        Set<Integer> geneIndices = new HashSet<>();

        while (geneIndices.size() < count) {
            Random random = new Random();

            int randomIndex = random.ints(1,0, endBound)
                    .findFirst()
                    .orElseThrow(RuntimeException::new);

            geneIndices.add(randomIndex);
        }

        return geneIndices;
    }
}
