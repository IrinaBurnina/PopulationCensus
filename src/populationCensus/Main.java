package populationCensus;

import workWithCounts.StreamMain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_0; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long children = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(children);
        List<String> conscripts = persons.stream()
                .filter(x -> x.getAge() > 18 && x.getAge() < 27)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
        System.out.println(conscripts);
        List<Person> workers = persons.stream()
                .filter(x -> x.getSex() == Sex.MAN && x.getAge() > 18 && x.getAge() < 65 ||
                        x.getSex() == Sex.WOMAN && x.getAge() > 18 && x.getAge() < 60)
                .filter(x -> x.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(x -> x.getFamily()))
                .collect(Collectors.toList());
        System.out.println(workers);

    }
}
