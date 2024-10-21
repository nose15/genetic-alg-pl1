package org.pwr.dtos;

import org.pwr.geneticalgorithm.Person;

import java.util.List;
import java.util.Map;

public class GenAlgResult {
    public double score;
    public Map<Integer, List<Integer>> genome;

    public GenAlgResult(double score, Map<Integer, List<Integer>> genome) {
        this.score = score;
        this.genome = genome;
    }
}
