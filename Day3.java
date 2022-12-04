import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day3 {
    private static List<String> readFileToList(File file) throws IOException {
        return Files.readAllLines(file.toPath(), Charset.defaultCharset() );
    }

    private static List<String> stringToCharactersList(String s){
        return Arrays.stream(s.split("")).toList();
    }

    private static Map<String, Integer> getPriorities(List<String> alphabet){
        return IntStream.range(0, alphabet.size())
                .boxed()
                .collect(Collectors.toMap(alphabet::get, i -> i + 1));
    }

    private static List<String> separateRucksackItems(String rucksackItems){
        return List.of(rucksackItems.substring(0, rucksackItems.length()/2), rucksackItems.substring(rucksackItems.length()/2));
    }

    private static List<String> findCommonItem(List<String> firstHalf, List<String> secondHalf){
        return firstHalf.stream()
                .flatMap(item -> secondHalf.stream()
                        .filter(item2 -> item2.equals(item)))
                .findFirst()
                .stream()
                .collect(Collectors.toList());
    }

    private static int calculatePrioritiesSum(List<String> rucksackItems, Map<String, Integer> priorities){
        return rucksackItems.stream()
                .map(Day3::separateRucksackItems)
                .map(items -> findCommonItem(stringToCharactersList(items.get(0)), stringToCharactersList(items.get(1))))
                .map(commonItem -> priorities.get(commonItem.get(0)))
                .reduce(0, Integer::sum);
    }

    public static void main(String[] args) throws IOException {
        List<String> lowercase = Arrays.stream("abcdefghijklmnopqrstuvwxyz".split("")).toList();
        List<String> uppercase = Arrays.stream("abcdefghijklmnopqrstuvwxyz".toUpperCase().split("")).toList();
        Map<String, Integer> priorities = getPriorities(Stream.concat(lowercase.stream(), uppercase.stream()).collect(Collectors.toList()));
        List<String> input = readFileToList(new File("day3.txt"));
        System.out.println(calculatePrioritiesSum(input, priorities));
    }
}
