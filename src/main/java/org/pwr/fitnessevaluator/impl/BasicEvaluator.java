package org.pwr.fitnessevaluator.impl;

import org.pwr.geneticalgorithm.Genotype;
import org.pwr.geneticalgorithm.Person;
import org.pwr.fitnessevaluator.FitnessEvaluator;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BasicEvaluator implements FitnessEvaluator {
    List<Person> peopleData;

    public BasicEvaluator(List<Person> peopleData) {
        this.peopleData = peopleData;
    }

    @Override
    public double calculateFitnessScore(Genotype genotype) {
        int score = 0;
        for (int searcherIndex : genotype.getGenome().keySet()) {
            Person searcher = peopleData.get(searcherIndex);
            List<Person> matchedPeople = genotype.getGenome().get(searcherIndex).stream().map(peopleData::get).collect(Collectors.toList());

            if (matchedPeople.contains(searcher)) {
                return Double.NEGATIVE_INFINITY;
            }
            if (hasDuplicates(matchedPeople)) {
                return Double.NEGATIVE_INFINITY;
            }

            for (Person person : matchedPeople) {
                for (String profession : person.professions) {
                    int foundProffesions = Collections.frequency(searcher.preferences, profession);
                    int signum = Integer.signum(foundProffesions - 1);

                    switch (signum) {
                        case 0:
                            score += 4;
                            break;
                        case 1:
                            score += 2;
                            break;
                        case -1:
                            score -= 2;
                    }

                }
            }
        }

        return score;
    }

    private boolean hasDuplicates(List<?> list) {
        Set<Object> objects = new HashSet<>();

        for (Object object : list) {
            if (objects.contains(object)) return true;

            objects.add(object);
        }

        return false;
    }
}
