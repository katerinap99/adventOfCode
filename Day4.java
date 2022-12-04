import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 {
    private static List<String> readFileToList(File file) throws IOException {
        return Files.readAllLines(file.toPath(), Charset.defaultCharset() );
    }

    private static boolean hasTotalOverlapping(List<String> sections){
        return new HashSet<>(getRange(sections.get(0))).containsAll(getRange(sections.get(1))) ||
                new HashSet<>(getRange(sections.get(1))).containsAll(getRange(sections.get(0)));
    }

    private static boolean hasAnyOverlapping(List<String> sections){
        return (getRange(sections.get(0)).stream()
                .anyMatch(getRange(sections.get(1))::contains)) ||
                (getRange(sections.get(1)).stream()
                        .anyMatch(getRange(sections.get(0))::contains));
    }

    private static List<Integer> getRange(String sections){
        String[] rangeLimits = sections.split("-");
        return IntStream.range(Integer.parseInt(rangeLimits[0]), Integer.parseInt(rangeLimits[1])+1)
                .boxed()
                .collect(Collectors.toList());
    }

    private static long calculateOverlappingPairs(List<String> allPairs, Function<List<String>, Boolean> checkOverlapping){
        return allPairs.stream()
                .map(pair -> Arrays.stream(pair.split(",")).toList())
                .filter(checkOverlapping::apply)
                .count();
    }
    public static void main(String[] args) throws IOException {
        List<String> input = readFileToList(new File("day3.txt"));
        System.out.println(calculateOverlappingPairs(input, Day4::hasTotalOverlapping));
        System.out.println(calculateOverlappingPairs(input, Day4::hasAnyOverlapping));
    }
}
