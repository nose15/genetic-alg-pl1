package org.pwr.mutator;
import org.pwr.Genotype;

public interface Mutator {
    Genotype mutate(Genotype a);
}
