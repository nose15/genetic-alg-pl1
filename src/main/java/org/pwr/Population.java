package org.pwr;

import org.pwr.combinator.Combinator;
import org.pwr.selector.Selector;

import java.util.List;
import java.util.stream.Collectors;

public class Population {
    private final List<Genotype> population;
    private final Selector selector;
    private final Combinator combinator;

    public Population(List<Genotype> population, Selector selector, Combinator combinator) {
        this.population = population;
        this.selector = selector;
        this.combinator = combinator;
    }

    public void evolve() {
        List<Genotype> selectedParents = selector.selectParents(population);
        System.out.println(population.size());

        if (selectedParents.size() % 2 != 0) {
            selectedParents = selectedParents.subList(0, selectedParents.size() - 1);
        }

        System.out.println(selectedParents.size());

        population.retainAll(selectedParents);

        for (int i = 0; i < selectedParents.size(); i += 2) {
            System.out.println("Cross");
            population.add(combinator.crossover(selectedParents.get(i), selectedParents.get(i + 1)));
            population.add(combinator.crossover(selectedParents.get(i + 1), selectedParents.get(i)));
        }

        System.out.println(population);
    }
}
