package org.pwr;

import org.pwr.selector.Selector;

import java.util.List;

public class Population {
    private final List<Genotype> population;
    private final Selector selector;

    public Population(List<Genotype> population, Selector selector) {
        this.population = population;
        this.selector = selector;
    }

    public void evolve() {
        List<Genotype> selectedParents = selector.selectParents(population);

        if (selectedParents.size() % 2 != 0) {
            selectedParents = selectedParents.subList(0, selectedParents.size() - 1);
        }

        System.out.println(selectedParents);
    }
}
