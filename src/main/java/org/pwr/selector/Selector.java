package org.pwr.selector;

import org.pwr.BestFitWithScoreDTO;
import org.pwr.Genotype;

import java.util.List;

public interface Selector {
    List<Genotype> selectParents(List<Genotype> population);

    BestFitWithScoreDTO selectBestFitWithScore(List<Genotype> population);
}
