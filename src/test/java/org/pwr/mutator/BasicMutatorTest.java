package org.pwr.mutator;

import org.junit.Before;
import org.junit.Test;
import org.pwr.geneticalgorithm.Genotype;
import org.pwr.mutator.impl.BasicMutator;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasicMutatorTest {
    private final int geneMaxValue = 5;
    private final Mutator mutator = new BasicMutator(geneMaxValue, 0.6);
    private Genotype genotype;

    @Before
    public void before() {
        Map<Integer, List<Integer>> genome = new HashMap<>();
        genome.put(0, new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
        genome.put(1, new ArrayList<>(Arrays.asList(5, 3, 2, 1)));
        genome.put(2, new ArrayList<>(Arrays.asList(2, 4, 5, 2)));

        genotype = new Genotype(genome);
    }

    @Test
    public void mutatedGenesInRangeTest() {
        Genotype newGenotype = mutator.mutate(genotype);
        boolean inRange = true;

        for (var key : newGenotype.getGenome().keySet()) {
            List<Integer> gene = newGenotype.getGenome().get(key);
            if (gene.stream().anyMatch(i -> i > geneMaxValue)) {
                inRange = false;
                break;
            }
        }

        assertTrue(inRange);
    }

    @Test
    public void mutateMutationTest() {
        Genotype newGenotype = mutator.mutate(genotype);
        assertEquals(newGenotype.getGenome().keySet(), genotype.getGenome().keySet());

        boolean change = false;
        for (var key : genotype.getGenome().keySet()) {
            if (genotype.getGenome().get(key) != newGenotype.getGenome().get(key)) {
                change = true;
                break;
            }
        }

        assertTrue(change);
    }

    @Test
    public void mutateKeySetChangeTest() {
        Genotype newGenotype = mutator.mutate(genotype);

        assertEquals(newGenotype.getGenome().keySet(), genotype.getGenome().keySet());
    }
}
