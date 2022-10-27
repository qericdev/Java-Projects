package asciimirror;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        List<String> whitespaces = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the file path:");
        String filepath = scanner.nextLine();
        File file = new File(filepath);

        try (Scanner scannerFile = new Scanner(file)) {
            while(scannerFile.hasNextLine()) {
                String text = scannerFile.nextLine();
                list.add(text);
            }
            int maxLength = 0;
            for (String line : list) {
                if (line.length() > maxLength) {
                    maxLength = line.length();
                }
            }
            for (String line : list) {
                if (line.length() <= maxLength) {
                    String tempWhitespaceLine = "";
                    for (int i = 0; i < maxLength - line.length(); i++) {
                        tempWhitespaceLine = tempWhitespaceLine.concat(" ");
                    }
                    whitespaces.add(tempWhitespaceLine);
                }
            }

            for (int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i) + whitespaces.get(i));
            }

            for (String line : list) {
                String reverseLine = "";
                char tempChar;
                for (int i = line.length() - 1; i >= 0; i--) {
                    tempChar = line.charAt(i);
                    if(tempChar == '<') {
                        tempChar = '>';
                    } else if(tempChar == '>') {
                        tempChar = '<';
                    } else if(tempChar == '[') {
                        tempChar = ']';
                    } else if(tempChar == ']') {
                        tempChar = '[';
                    } else if(tempChar == '{') {
                        tempChar = '}';
                    } else if(tempChar == '}') {
                        tempChar = '{';
                    } else if(tempChar == '(') {
                        tempChar = ')';
                    } else if(tempChar == ')') {
                        tempChar = '(';
                    } else if(tempChar == '/') {
                        tempChar = '\\';
                    } else if(tempChar == '\\') {
                        tempChar = '/';
                    }
                    reverseLine += tempChar;
                }
                System.out.println(line + " | " + reverseLine);
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
