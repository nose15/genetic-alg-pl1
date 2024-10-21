package org.pwr.geneticalgorithm;

import java.util.List;

public class Person {
    public int id;
    public List<String> professions;
    public List<String> preferences;

    public Person(int id, List<String> professions, List<String> preferences) {
        this.id = id;
        this.professions = professions;
        this.preferences = preferences;
    }
}
