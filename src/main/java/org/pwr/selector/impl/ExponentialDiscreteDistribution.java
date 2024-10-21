package org.pwr.selector.impl;

import org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ExponentialDiscreteDistribution {
    final private List<Double> cumulativeProbabilities;

    public ExponentialDiscreteDistribution(ExponentialDecayFunction exponentialDecayFunction, int size) {
        cumulativeProbabilities = calculateCumulativeProbabilities(size, exponentialDecayFunction);
    }

    public int randomNumber(int endBound) {
        Random random = new Random();
        double n = random.doubles(1, 0, 1).sum();

        int index = -1;
        double cumulativeProb = 0;
        do {
            index++;
            cumulativeProb = cumulativeProbabilities.get(index);
        } while (n > cumulativeProb && index < endBound);

        return index;
    }

    private List<Double> calculateCumulativeProbabilities(int count, ExponentialDecayFunction exponentialDecayFunction) {
        List<Double> cumulativeProbabilities = new ArrayList<>();
        double sum = 0.0;
        for (int i = 0; i < count; i++) {
            double valueAtI = exponentialDecayFunction.value(i);
            sum += valueAtI;
            cumulativeProbabilities.add(sum);
        }

        return normalizeProbabilities(cumulativeProbabilities);
    }

    private List<Double> normalizeProbabilities(List<Double> probabilities) {
        double sum = probabilities.stream().mapToDouble(Double::doubleValue).sum();
        return probabilities.stream().map(i -> normalize(i, sum)).collect(Collectors.toList());
    }

    private double normalize(double val, double by) {
        return Math.floor((val / by) * 1000000) / 1000000;
    }
}
