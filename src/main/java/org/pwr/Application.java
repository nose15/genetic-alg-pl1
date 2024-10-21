package org.pwr;

import org.apache.commons.cli.*;
import org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction;
import org.pwr.combinator.Combinator;
import org.pwr.combinator.impl.HalfCombinator;
import org.pwr.dtos.GenAlgResult;
import org.pwr.fitnessevaluator.FitnessEvaluator;
import org.pwr.fitnessevaluator.impl.BasicEvaluator;
import org.pwr.geneticalgorithm.EvolutionResult;
import org.pwr.geneticalgorithm.Genotype;
import org.pwr.geneticalgorithm.Person;
import org.pwr.geneticalgorithm.Population;
import org.pwr.selector.Selector;
import org.pwr.selector.impl.ExponentialDiscreteDistribution;
import org.pwr.selector.impl.RankSelector;

import java.util.*;

import static org.pwr.geneticalgorithm.Utils.*;

public class Application {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("f", "file", true, "Data source file (mandatory)");
        options.addOption("s", "size", true, "Count of individuals in the population (1000 by default)");
        options.addOption("p", "pressure", true, "Selection pressure to be applied (0.1 by default)");

        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = commandLineParser.parse(options, args);
        } catch (ParseException e) {
            System.out.println("Error while parsing arguments");
            System.exit(1);
        }

        String filePath = null;
        int size = 1000;
        double selectionPressure = 0.1;


        if (cmd.hasOption('f')) {
            filePath = cmd.getOptionValue('f');
        } else {
            System.out.println("No source file specified. Exiting.");
            System.exit(0);
        }

        if (cmd.hasOption('s')) {
            try {
                size = Integer.parseInt(cmd.getOptionValue('s'));
            } catch (NumberFormatException e) {
                System.out.println("Invalid argument for size");
                System.exit(1);
            }
        }

        if (cmd.hasOption('p')) {
            try {
                selectionPressure = Double.parseDouble(cmd.getOptionValue('p'));
            } catch (NumberFormatException e) {
                System.out.println("Invalid argument for pressure");
                System.exit(1);
            }
        }

        List<Person> people = Parser.parsePeopleFromFile(filePath);
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(size, selectionPressure, people);

        GenAlgResult result = geneticAlgorithm.run();
        displayScore(result, people);
    }
}
