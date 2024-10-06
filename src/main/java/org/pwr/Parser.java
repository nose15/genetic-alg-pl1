package org.pwr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {
    public static List<Person> parsePeopleFromFile(String filePath) {
        List<String> lines = readLines(filePath);
        return lines.stream()
                .map(Parser::parsePerson)
                .collect(Collectors.toList());
    }

    private static List<String> readLines(String filePath) {
        List<String> fileLines = new ArrayList<>();
        Path path = Paths.get(filePath);

        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(fileLines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileLines;
    }


    private static Person parsePerson(String personString) {
        List<String> words = List.of(personString.split("\t"));
        assert words.size() == 3 : "Wrong data format";

        int id = Integer.parseInt(words.get(0));
        List<String> professions = parseListOfProfessions(words.get(1));
        List<String> preferences = parseListOfProfessions(words.get(2));

        return new Person(id, professions, preferences);
    }

    private static List<String> parseListOfProfessions(String professionStr) {
        return List.of(professionStr.split(","));
    }

}
