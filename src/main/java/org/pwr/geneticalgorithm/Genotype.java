package org.pwr.geneticalgorithm;

import java.util.*;

public class Genotype {
    private final Map<Integer, List<Integer>> genome;

    public Genotype(Map<Integer, List<Integer>> genome) {
        this.genome = genome;
    }

    public Map<Integer, List<Integer>> getGenome() {
        return genome;
    }
}
