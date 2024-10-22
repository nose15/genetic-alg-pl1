package org.pwr.mutator.impl;

import org.pwr.geneticalgorithm.Genotype;
import org.pwr.mutator.Mutator;

public class NoOpMutator implements Mutator {
    @Override
    public Genotype mutate(Genotype base) {
        return base;
    }
}
