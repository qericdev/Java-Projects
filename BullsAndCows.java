package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        int secretCodeLength = 0;
        String secretCodeLengthString = "";
        try {
            secretCodeLengthString = scanner.nextLine();
            secretCodeLength = Integer.parseInt(secretCodeLengthString);
        } catch (Exception e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", secretCodeLengthString);
            return;
        }

        System.out.println("Input the number of possible symbols in the code:");
        int numberOfSymbols = scanner.nextInt();

        StringBuilder sb = new StringBuilder();

        if(secretCodeLength > 36 || numberOfSymbols > 36 || secretCodeLength <= 0 || numberOfSymbols <= 0) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        } else if (secretCodeLength > numberOfSymbols) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", secretCodeLength, numberOfSymbols);
        } else {
            boolean isValid = false;
            while(!isValid) {
                String pseudoRandomString = "";
                for (int i = 0; i < secretCodeLength; i++) {
                    int pseudoRandomNumber = (int) Math.floor(Math.random() * numberOfSymbols);
                    if (pseudoRandomNumber < 10) {
                        pseudoRandomString += pseudoRandomNumber;
                    } else {
                        pseudoRandomString += 'a' + pseudoRandomNumber - 10;
                    }
                }

                int index = pseudoRandomString.length() - 1;
                while (sb.length() != secretCodeLength && index >= 0) {
                    if (sb.indexOf(Character.toString(pseudoRandomString.charAt(index))) == -1) {
                        sb.append(pseudoRandomString.charAt(index));
                    }
                    index--;
                }
                if (sb.length() == secretCodeLength) {
                    isValid = true;
                }
            }
        }

        String asterisks = "";
        for (int i = 0; i < secretCodeLength; i++) {
            asterisks += "*";
        }
        System.out.printf("The secret is prepared: %s (0-9", asterisks);
        if (numberOfSymbols == 11) {
            System.out.print(", a");
        } else if (numberOfSymbols > 11) {
            final int DIGITS = 10;
            System.out.printf(", a-%c", 'a' + numberOfSymbols - DIGITS - 1);
        }
        System.out.print(").\n");
        System.out.println("Okay, let's start a game!");

        String secretCode = sb.toString();

        int bulls = 0;
        int cows;
        int turn = 1;

        while (bulls < secretCode.length()) {
            bulls = 0;
            cows = 0;
            System.out.printf("Turn %d:\n", turn);
            String number = scanner.next();
            System.out.print("Grade: ");
            for (int i = 0; i < number.length(); i++) {
                for (int j = 0; j < secretCode.length(); j++) {
                    if (number.charAt(i) == secretCode.charAt(j)) {
                        if (i == j) {
                            bulls++;
                        } else {
                            cows++;
                        }
                    }
                }
            }

            if (bulls > 1 && cows > 1) {
                System.out.printf("%d bulls and %d cows\n", bulls, cows);
            } else if (bulls > 1 && cows == 1) {
                System.out.printf("%d bulls and %d cow\n", bulls, cows);
            } else if (bulls == 1 && cows > 1) {
                System.out.printf("%d bull and %d cows\n", bulls, cows);
            } else if (bulls == 1 && cows == 1) {
                System.out.printf("%d bull and %d cow\n", bulls, cows);
            } else if (cows > 1) {
                System.out.printf("%d cows\n", cows);
            } else if (bulls > 1) {
                System.out.printf("%d bulls\n", bulls);
            } else if (cows == 1) {
                System.out.printf("%d cow\n", cows);
            } else if (bulls == 1) {
                System.out.printf("%d bull\n", bulls);
            }
            else {
                System.out.print("None\n");
            }
            turn++;
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }
}
