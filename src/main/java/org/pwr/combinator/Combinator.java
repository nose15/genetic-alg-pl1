package org.pwr.combinator;

import org.pwr.Genotype;

public interface Combinator {
    public Genotype crossover(Genotype a, Genotype b);
}
