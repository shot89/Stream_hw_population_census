import ru.netology.person.Education;
import ru.netology.person.Person;
import ru.netology.person.Sex;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static final int POPULATION = 10_000_000;

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < POPULATION; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Long underageCount = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        System.out.println("Колличество несовершеннолетних в переписи: \n" + underageCount);

        List<String> conscript = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() >= 18 && person.getAge() < 27)
                .map(person -> person.getFamily())
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Колличество всех мужчин призывного возраста: \n" + conscript.size());

        List<Person> workers = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> {
                    if (person.getSex().equals(Sex.MAN)) return person.getAge() >= 18 && person.getAge() < 65;
                    else return person.getAge() >= 18 && person.getAge() < 60;
                })
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Колличество всех людей рабочего возраста с высшим образованием: \n" + workers.size());
    }
}