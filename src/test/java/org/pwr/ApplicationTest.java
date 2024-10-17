package org.pwr;

import org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.pwr.Utils.calculateCumulativeProbabilities;


public class ApplicationTest {
    @Test
    public void testCalculateCumulativeProbabilities() {
        ExponentialDecayFunction exponentialDecayFunction = new ExponentialDecayFunction(0.1, 0.0001, 10);
        List<Double> results = calculateCumulativeProbabilities(10, exponentialDecayFunction);

        double last = results.get(results.size() - 1);
        assertEquals(last, 1.0);
    }
}
