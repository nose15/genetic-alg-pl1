package org.pwr.mutator.impl;

import org.pwr.Genotype;
import org.pwr.mutator.Mutator;

public class NoOpMutator implements Mutator {
    @Override
    public Genotype mutate(Genotype a) {
        return a;
    }
}
