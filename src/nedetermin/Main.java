package nedetermin;

import java.io.File;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class Main {
    private static String[] FileRead(String fileName) throws Exception {
        String s = new String(readAllBytes(get(fileName)));
        return s.split("\r\n");
    }

    public static void main(String[] args) throws Exception {
        String automatFileName = "inputedNFA.txt";
        String s = new String(readAllBytes(get(automatFileName)));
        System.out.println("Current NFA: " + s);
        String fileName = "test.txt";
        String[] words = FileRead(fileName);
        NFA a = new NFA();
        for (String word : words) {
            System.out.println("Implementation of NFA for the word: " + word);
            boolean isAccepted = a.accept(word);
            if (isAccepted){
                System.out.println("\"" + word + "\": ACCEPTED ");
                System.out.println("______________________");}

            else{
                System.out.println("\"" + word + "\": rejected");
                System.out.println("______________________");}
        }
    }
}
