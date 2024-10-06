package org.pwr.fitnessevaluator.impl;

import org.pwr.Genotype;
import org.pwr.Person;
import org.pwr.fitnessevaluator.FitnessEvaluator;

import java.util.List;
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

            for (Person person : matchedPeople) {
                for (String profession : person.professions) {
                    if (searcher.preferences.contains(profession)) {
                        score += 2;
                    } else {
                        score -= 1;
                    }
                }
            }
        }

        return score;
    }
}
