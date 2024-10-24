package org.pwr.combinator;

import org.pwr.geneticalgorithm.Genotype;
import java.util.List;

public interface Combinator {
    List<Genotype> createOffsprings(List<Genotype> parentPool, int count);
}
