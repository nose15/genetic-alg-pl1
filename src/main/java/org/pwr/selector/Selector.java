package org.pwr.selector;

import org.pwr.Genotype;

import java.util.List;

public interface Selector {
    List<Genotype> selectParents(List<Genotype> population);
}
