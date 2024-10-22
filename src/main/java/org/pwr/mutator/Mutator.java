package org.pwr.mutator;
import org.pwr.geneticalgorithm.Genotype;

public interface Mutator {
    Genotype mutate(Genotype base);
}
