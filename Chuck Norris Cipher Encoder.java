package chucknorris;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String operation = "";

        while(!"exit".equals(operation)) {
            System.out.println("Please input operation (encode/decode/exit):");
            operation = scanner.nextLine();

            switch(operation) {
                case "encode":
                    getEncode();
                    break;
                case "decode":
                    getDecode();
                    break;
                case "exit":
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.printf("There is no '%s' operation\n", operation);
                    break;
            }
        }
    }

    public static void getDecode() {
        System.out.println("Input encoded string:");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] binaryArr;

        // Validating correct input for encoded string
        for (int i = 0; i < line.length(); i++) {
            if (!(line.charAt(i) == '0' || line.charAt(i) == ' ')) {
                System.out.println("Encoded string is not valid.");
                return;
            }
        }

        binaryArr = line.split(" ");

        if (binaryArr.length % 2 != 0) {
            System.out.println("Encoded string is not valid.");
            return;
        }

        for (int i = 0; i < binaryArr.length; i = i + 2) {
            if (!("0".equals(binaryArr[i]) || "00".equals(binaryArr[i]))) {
                System.out.println("Encoded string is not valid.");
                return;
            }
        }

        String binary = "";

        String[] blocks = line.split(" ");
        for (int i = 0; i < blocks.length; i = i + 2) {
            if (blocks[i].length() == 1) {
                for (int j = 0; j < blocks[i + 1].length(); j++) {
                    binary += "1";
                }
            } else {
                for (int j = 0; j < blocks[i + 1].length(); j++) {
                    binary += "0";
                }
            }
        }

        if (binary.length() % 7 != 0) {
            System.out.println("Encoded string is not valid.");
            return;
        }
        System.out.println("Decoded string:");

        for (int i = 0; i < binary.length(); i = i + 7) {
            String sevenChars = binary.substring(i, i + 7);
            int tempValue = 0;
            for (int j = 0; j < sevenChars.length(); j++) {
                tempValue += Integer.parseInt(String.valueOf(sevenChars.charAt(j))) * (int) (Math.pow(2, 6 - j));
            }
            System.out.print((char) tempValue);
        }
        System.out.println();
    }

    public static void getEncode() {
        System.out.println("Input string:");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        System.out.println("Encoded string:");
        String binary = "";

        for (int i = 0; i < line.length(); i++) {
            int number = Integer.parseInt(Integer.toBinaryString(line.codePointAt(i)));
            binary += String.format("%1$07d", number);
        }

        for (int j = 0; j < binary.length(); j++) {

            int counter = 1;

            while (j + 1 < binary.length() && binary.charAt(j) == binary.charAt(j + 1)) {
                counter++;
                j++;
            }

            System.out.print('1' == binary.charAt(j) ? "0 ": "00 ");
            for (int z = 0; z < counter; z++) {
                System.out.print("0");
            }

            System.out.print(" ");
        }

        System.out.println();
    }
}