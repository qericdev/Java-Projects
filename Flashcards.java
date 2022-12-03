package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static String log = "";

    public static void main(String[] args) {

        Map<String, String> map = new LinkedHashMap<>();
        Map<String, Integer> errorMap = new LinkedHashMap<>();
        boolean menuIsOn = true;
        boolean saveAtExit = false;
        int length = args.length;
        String fileName = "";

        switch(length) {
            case 2:
                if ("import".equals(args[0].substring(1))) {
                    importFile(map, errorMap, true, args[1]);
                } else if ("export".equals(args[0].substring(1))) {
                    saveAtExit = true;
                    fileName = args[1];
                }
                break;
            case 4:
                if ("import".equals(args[0].substring(1))) {
                    importFile(map, errorMap, true, args[1]);
                } else if ("export".equals(args[0].substring(1))) {
                    saveAtExit = true;
                    fileName = args[1];
                }
                if ("import".equals(args[2].substring(1))) {
                    importFile(map, errorMap, true, args[3]);
                } else if ("export".equals(args[2].substring(1))) {
                    saveAtExit = true;
                    fileName = args[3];
                }
                break;
        }

        while(menuIsOn) {
            String message = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):";
            System.out.println(message);
            log += message + "\n";
            String option = scanner.nextLine();
            log += option + "\n";

            switch(option) {
                case "add":
                    add(map, errorMap);
                    break;
                case "remove":
                    remove(map);
                    break;
                case "ask":
                    ask(map, errorMap);
                    break;
                case "import":
                    importFile(map, errorMap, false, "");
                    break;
                case "export":
                    exportFile(map, errorMap, false, "");
                    break;
                case "exit":
                    menuIsOn = false;
                    if (saveAtExit) {
                        exportFile(map, errorMap, true, fileName);
                    }
                    System.out.println("Bye bye!");
                    break;
                case "log":
                    log(map);
                    break;
                case "hardest card":
                    hardestCard(map, errorMap);
                    break;
                case "reset stats":
                    resetStats(errorMap);
                    break;
                default:
                    break;
            }
        }
    }

    public static void resetStats(Map errorMap) {
        Map<String, Integer> tempErrorMap = errorMap;
        for (String card : tempErrorMap.keySet()) {
            tempErrorMap.replace(card, 0);
        }
        System.out.println("Card statistics have been reset.\n");
        log += "Card statistics have been reset.\n";
    }

    public static void hardestCard(Map map, Map errorMap) {
         Object[] arr = errorMap.values().toArray();
         Map<String, Integer> tempErrorMap = errorMap;
         String maxErrorElements = "";
         int maxValue = 0;
         int counter = 0;
         for (Object elem : arr) {
             int tempValue = Integer.parseInt(elem.toString());
             if (tempValue > maxValue) {
                 maxValue = tempValue;
             }
         }

         if (maxValue == 0) {
             System.out.println("There are no cards with errors.");
             log += "There are no cards with errors.\n";
         } else {
             for (var entry : tempErrorMap.entrySet()) {
                 if (maxValue == entry.getValue()) {
                     counter++;
                     maxErrorElements += "\"" + entry.getKey() + "\", ";
                 }
             }
             maxErrorElements = maxErrorElements.substring(0, maxErrorElements.length() - 2);

             if (counter == 1) {
                 System.out.printf("The hardest card is %s. You have %d errors answering it.\n", maxErrorElements, maxValue);
                 log += "The hardest card is " + maxErrorElements  + ". You have " + maxValue + " errors answering it.\n";
             } else {
                 System.out.printf("The hardest cards are %s. You have %d errors answering them.\n", maxErrorElements, maxValue);
                 log += "The hardest cards are " + maxErrorElements + ". You have " + maxValue + " errors answering them.\n";
             }
         }
    }

    public static void log(Map map) {
        System.out.println("File name:");
        log += "File name:\n";
        String input = scanner.nextLine();
        log += input + "\n";
        File file = new File("./" + input);
        try(FileWriter writer = new FileWriter(file, false)) {
            log += "The Log has been saved";
            writer.write(log);
            System.out.println("The log has been saved");
        } catch (IOException e) {
            System.out.printf("An exception occurred %s\n\n", e.getMessage());
            log += "An exception occurred" + e.getMessage() + "\n\n";
        }
    }
    public static void exportFile(Map map, Map errorMap, boolean saveAtExit, String fileName) {
        String input = fileName;
        Map<String, String> tempMap = map;
        if(!saveAtExit) {
            System.out.println("File name:");
            log += "File name:\n";
            input = scanner.nextLine();
            log += input + "\n";
        }

        File file = new File("./" + input);
        try(FileWriter writer = new FileWriter(file, false)) {
            for (var entry : tempMap.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + ":" + errorMap.get(entry.getKey()) + "\n");
            }
            System.out.printf("%d cards have been saved.\n\n", tempMap.size());
            log += tempMap.size() + " cards have been saved.\n\n";
        } catch (IOException e) {
            System.out.printf("An exception occurred %s\n\n", e.getMessage());
            log += "An exception occurred" + e.getMessage() + "\n\n";
        }
    }
    public static void importFile(Map map, Map errorMap, boolean loadAtStart, String fileName) {
        String input = fileName;
        if(!loadAtStart) {
            System.out.println("File name:");
            log += "File name:\n";
            input = scanner.nextLine();
            log += input + "\n";
        }

        int lineCounter = 0;
        File file = new File("./" + input);
        try {
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(":");
                if (map.containsKey(data[0])) {
                    map.replace(data[0], data[1]);
                    errorMap.replace(data[0], Integer.parseInt(data[2]));
                }
                map.put(data[0], data[1]);
                errorMap.put(data[0], Integer.parseInt(data[2]));
                lineCounter++;
            }
            System.out.println(lineCounter + " cards have been loaded.\n");
            log += lineCounter + " cards have been loaded.\n\n";
        } catch (FileNotFoundException e) {
            System.out.println("File not found\n");
            log += "File not found\n\n";
        }
    }

    public static void add(Map map, Map errorMap) {
        System.out.println("The card:");
        log += "The card:\n";
        String card = scanner.nextLine();
        log += card + "\n";
        if (!map.containsKey(card)) {
            map.put(card, null);
            errorMap.put(card, null);
        } else {
            System.out.printf("The card \"%s\" already exists.\n\n", card);
            log += "The card " + "\"" + card + "\"" + " already exists.\n\n";
            return;
        }
        System.out.printf("The definition of the card:\n");
        log += "The definition of the card:\n";
        String definition = scanner.nextLine();
        if (!map.containsValue(definition)) {
            map.put(card, definition);
            errorMap.put(card, 0);
            System.out.printf("The pair (\"%s\":\"%s\") has been added.\n\n", card, definition);
            log += "The pair " + "\"" + card + "\"" + ":" + "\"" + definition + "\"" + " has been added.\n\n";
        } else {
            System.out.printf("The definition \"%s\" already exists.\n\n", definition);
            log += "The definition " +  "\"" + definition + "\"" + " already exists.\n\n";
        }
    }

    public static void remove(Map map) {
        System.out.println("Which card?");
        log += "Which card?\n";
        String input = scanner.nextLine();
        log += input + "\n";
        if (map.containsKey(input)) {
            map.remove(input);
            System.out.println("The card has been removed.\n");
            log += "The card has been removed.\n";
        } else {
            System.out.printf("Can't remove \"%s\": there is no such card.\n\n", input);
            log += "Can't remove " + "\"" + input + "\"" + " : there is no such card.\n\n";
        }
    }

    public static void ask(Map map, Map errorMap) {
        System.out.println("How many times to ask?");
        log += "How many times to ask?\n";
        int input = Integer.parseInt(scanner.nextLine());
        log += input + "\n";
        int counter = 0;
        Map<String, String> tempMap = map;
        Map<String, Integer> tempErrorMap = errorMap;

        for (var entry : tempMap.entrySet()) {
            if (counter < input) {
                System.out.printf("Print the definition of \"%s\":\n", entry.getKey());
                log += "Print the definition of " + "\"" + entry.getKey() + "\"" + ":\n";
                String answer = scanner.nextLine();
                log += answer + "\n";
                if (answer.equals(entry.getValue())) {
                    System.out.printf("Correct!\n");
                    log += "Correct!\n";
                } else {
                    if (map.containsValue(answer)) {
                        String correctAnswer = "";
                        for (var tempEntry: tempMap.entrySet()) {
                            if (tempEntry.getValue().equals(answer)) {
                                correctAnswer = tempEntry.getKey();
                                break;
                            }
                        }
                        System.out.printf(
                                "Wrong. The right answer is \"%s\", " +
                                        "but your definition is correct for \"%s\".\n\n", entry.getValue(), correctAnswer
                        );
                        log += "Wrong. the right answer is " + "\"" + entry.getValue() + "\", " +
                                "but your definition is correct"  + "\"" + correctAnswer + "\".\n\n";
                    } else {
                        System.out.printf("Wrong. The right answer is \"%s\".\n\n", entry.getValue());
                        log += "Wrong. The right answer is " + "\"" + entry.getValue() + "\".\n\n";
                    }
                    tempErrorMap.put(entry.getKey(), tempErrorMap.get(entry.getKey()) + 1);
                }
            } else {
                break;
            }
        }
    }
}
