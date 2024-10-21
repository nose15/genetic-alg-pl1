package org.pwr.selector;

import org.pwr.geneticalgorithm.BestFitWithScoreDTO;
import org.pwr.geneticalgorithm.Genotype;

import java.util.List;

public interface Selector {
    List<Genotype> selectParents(List<Genotype> population);

    BestFitWithScoreDTO selectBestFitWithScore(List<Genotype> population);
}
