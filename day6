import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day6 {
    private static List<String> readFileContent(File file) throws IOException {
        return Files.readAllLines(Paths.get(file.toURI()), StandardCharsets.UTF_8);
    }

    private static boolean hasDuplicates(List<String> chars){
        return chars.stream()
                .filter(i -> Collections.frequency(chars, i) > 1)
                .collect(Collectors.toSet())
                .size() > 0;
    }

    private static List<String> stringToCharactersList(String s){
        return Arrays.stream(s.split("")).collect(Collectors.toList());
    }

    private static int countProcessedChars(List<String> characters){
        List<String> characterBuffer = new ArrayList<>();
        int counter = 0;
        for (int i=0; i< characters.size(); i++){
            if (i<=13){
                characterBuffer.add(characters.get(i));
            }
            else{
                if (hasDuplicates(characterBuffer)){
                    characterBuffer.remove(0);
                    characterBuffer.add(characters.get(i));
                }
                else{
                    break;
                }
            }
            counter = counter + 1 ;
        }
        return counter;
    }


    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("day2.txt"));
        List<String> inputToChars = stringToCharactersList(input);
        System.out.println(countProcessedChars(inputToChars));
    }
}
