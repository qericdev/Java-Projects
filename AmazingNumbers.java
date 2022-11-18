package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to Amazing Numbers!\n");

        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println(" * the first parameter represents a starting number;");
        System.out.println(" * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.\n");

        long number = -1;
        final String[] PROPERTIES = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD", "SQUARE", "SUNNY", "JUMPING", "HAPPY", "SAD"};

        while (number != 0) {
            System.out.print("Enter a request: ");

            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();
            String[] inputComponents = input.split("\s+");

            try {
                number = Long.parseLong(inputComponents[0]);
            } catch (Exception e) {
                System.out.println("\nThe first parameter should be a natural number or zero.\n");
                continue;
            }

            if(inputComponents.length == 2) {
                int secondParameter = Integer.parseInt(inputComponents[1]);
                if (!checkIfIsNatural(secondParameter)) {
                    System.out.println("\nThe second parameter should be a natural number.\n");
                    continue;
                }
                System.out.println();
                for (int i = 0; i < secondParameter; i++) {
                    String buzzProp = checkIfIsBuzz(number + i) ? "buzz, " : "";
                    String duckProp = checkIfIsDuck(number + i) ? "duck, " : "";
                    String palindromicProp = checkIfIsPalindromic(number + i) ? "palindromic, " : "";
                    String gapfulProp = checkIfIsGapful(number + i) ? "gapful, " : "";
                    String spyProp = checkIfIsSpy(number + i) ? "spy, " : "";
                    String squareProp = checkIfIsSquare(number + i) ? "square, " : "";
                    String sunnyProp = checkIfIsSunny(number + i) ? "sunny, " : "";
                    String jumpingProp = checkIfIsJumping(number + i) ? "jumping, " : "";
                    String evenProp = checkIfIsEven(number + i) ? "even, " : "";
                    String oddProp = checkIfIsEven(number + i) ? "" : "odd, ";
                    String happyProp = checkIfIsHappy(number + i) ? "happy, " : "";
                    String sadProp = checkIfIsHappy(number + i) ? "" : "sad, ";

                    String prevResult = (buzzProp + duckProp + palindromicProp + gapfulProp + spyProp + squareProp + sunnyProp + jumpingProp + evenProp + oddProp + happyProp + sadProp);
                    String result = prevResult.substring(0, prevResult.length() - 2);

                    System.out.printf("%d is %s\n", number + i, result);
                }
                System.out.println();
                continue;

            } else if (inputComponents.length > 2) {

                ArrayList<String> wrongProperties = new ArrayList<>();
                ArrayList<String> propArrList = new ArrayList<>(List.of(PROPERTIES));

                // Loop begins with 2 because first two parameters are numbers and no properties.
                for (int i = 2; i < inputComponents.length; i++) {
                    inputComponents[i] = inputComponents[i].toUpperCase();
                    if (!(propArrList.contains(inputComponents[i]) || propArrList.contains(inputComponents[i].substring(1)))) {
                        wrongProperties.add(inputComponents[i]);
                    }
                }

                ArrayList<String> inputArrList = new ArrayList<>(List.of(inputComponents));

                if (wrongProperties.size() == 1) {
                    System.out.printf("\nThe property %s is wrong.\n", wrongProperties);
                    System.out.printf("Available properties: %s\n\n", Arrays.toString(PROPERTIES));
                } else if (wrongProperties.size() > 1) {
                    System.out.printf("\nThe properties %s are wrong.\n", wrongProperties);
                    System.out.printf("Available properties: %s\n\n", Arrays.toString(PROPERTIES));
                } else if (inputArrList.contains("ODD") && inputArrList.contains("-ODD")) {
                    System.out.print("\nThe request contains mutually exclusive properties: [ODD, -ODD]\n\n");
                    System.out.printf("There are no numbers with these properties.\n\n");
                } else if (inputArrList.contains("EVEN") && inputArrList.contains("-EVEN")) {
                    System.out.print("\nThe request contains mutually exclusive properties: [EVEN, -EVEN]\n\n");
                    System.out.printf("There are no numbers with these properties.\n\n");
                } else if (inputArrList.contains("BUZZ") && inputArrList.contains("-BUZZ")) {
                    System.out.print("\nThe request contains mutually exclusive properties: [BUZZ, -BUZZ]\n\n");
                    System.out.printf("There are no numbers with these properties.\n\n");
                } else if (inputArrList.contains("DUCK") && inputArrList.contains("-DUCK")) {
                    System.out.print("\nThe request contains mutually exclusive properties: [DUCK, -DUCK]\n\n");
                    System.out.printf("There are no numbers with these properties.\n\n");
                } else if (inputArrList.contains("PALINDROMIC") && inputArrList.contains("-PALINDROMIC")) {
                    System.out.print("\nThe request contains mutually exclusive properties: [PALINDROMIC, -PALINDROMIC]\n\n");
                    System.out.printf("There are no numbers with these properties.\n\n");
                } else if (inputArrList.contains("GAPFUL") && inputArrList.contains("-GAPFUL")) {
                    System.out.print("\nThe request contains mutually exclusive properties: [GAPFUL, -GAPFUL]\n\n");
                    System.out.printf("There are no numbers with these properties.\n\n");
                } else if (inputArrList.contains("SPY") && inputArrList.contains("-SPY")) {
                    System.out.print("\nThe request contains mutually exclusive properties: [SPY, -SPY]\n\n");
                    System.out.printf("There are no numbers with these properties.\n\n");
                } else if (inputArrList.contains("SQUARE") && inputArrList.contains("-SQUARE")) {
                    System.out.print("\nThe request contains mutually exclusive properties: [SQUARE, -SQUARE]\n\n");
                    System.out.printf("There are no numbers with these properties.\n\n");
                } else if (inputArrList.contains("SUNNY") && inputArrList.contains("-SUNNY")) {
                    System.out.print("\nThe request contains mutually exclusive properties: [SUNNY, -SUNNY]\n\n");
                    System.out.printf("There are no numbers with these properties.\n\n");
                } else if (inputArrList.contains("JUMPING") && inputArrList.contains("-JUMPING")) {
                    System.out.print("\nThe request contains mutually exclusive properties: [JUMPING, -JUMPING]\n\n");
                    System.out.printf("There are no numbers with these properties.\n\n");
                } else if (inputArrList.contains("HAPPY") && inputArrList.contains("-HAPPY")) {
                    System.out.print("\nThe request contains mutually exclusive properties: [HAPPY, -HAPPY]\n\n");
                    System.out.printf("There are no numbers with these properties.\n\n");
                } else if (inputArrList.contains("SAD") && inputArrList.contains("-SAD")) {
                    System.out.print("\nThe request contains mutually exclusive properties: [SAD, -SAD]\n\n");
                    System.out.printf("There are no numbers with these properties.\n\n");
                } else if ((inputArrList.contains("ODD") && inputArrList.contains("EVEN")) || (inputArrList.contains("-ODD") && inputArrList.contains("-EVEN")) ) {
                    System.out.print("\nThe request contains mutually exclusive properties: [ODD, EVEN]\n\n");
                    System.out.printf("There are no numbers with these properties.\n\n");
                } else if ((inputArrList.contains("DUCK") && inputArrList.contains("SPY")) || (inputArrList.contains("-DUCK") && inputArrList.contains("-SPY"))) {
                    System.out.print("\nThe request contains mutually exclusive properties: [DUCK, SPY]\n\n");
                    System.out.printf("There are no numbers with these properties.\n\n");
                } else if ((inputArrList.contains("SUNNY") && inputArrList.contains("SQUARE")) || (inputArrList.contains("-SUNNY") && inputArrList.contains("-SQUARE")) ) {
                    System.out.printf("\nThe request contains mutually exclusive properties: [SUNNY, SQUARE]\n\n");
                    System.out.printf("There are no numbers with these properties.\n\n");
                } else if ((inputArrList.contains("HAPPY") && inputArrList.contains("SAD")) || (inputArrList.contains("-HAPPY") && inputArrList.contains("-SAD"))) {
                    System.out.printf("\nThe request contains mutually exclusive properties: [HAPPY, SAD]\n\n");
                    System.out.printf("There are no numbers with these properties.\n\n");
                } else {
                    int secondParameter = Integer.parseInt(inputComponents[1]);
                    if (!checkIfIsNatural(secondParameter)) {
                        System.out.println("\nThe second parameter should be a natural number.\n");
                        continue;
                    }
                    long[] numbers = new long[secondParameter];
                    int numbersWithTheseConditions = 0;
                    long tempNumber = number;

                    while (numbersWithTheseConditions < secondParameter) {
                        boolean isValid = true;
                        for (int i = 2; i < inputComponents.length; i++) {
                            if (!selectSpecificFunction(inputComponents[i].toLowerCase(), tempNumber)) {
                                isValid = false;
                                break;
                            }
                        }
                        if (isValid) {
                            numbers[numbersWithTheseConditions] = tempNumber;
                            numbersWithTheseConditions++;
                        }
                        tempNumber++;
                    }

                    System.out.println();
                    for (long num : numbers) {
                        String buzzProp = checkIfIsBuzz(num) ? "buzz, " : "";
                        String duckProp = checkIfIsDuck(num) ? "duck, " : "";
                        String palindromicProp = checkIfIsPalindromic(num) ? "palindromic, " : "";
                        String gapfulProp = checkIfIsGapful(num) ? "gapful, " : "";
                        String spyProp = checkIfIsSpy(num) ? "spy, " : "";
                        String squareProp = checkIfIsSquare(num) ? "square, " : "";
                        String sunnyProp = checkIfIsSunny(num) ? "sunny, " : "";
                        String jumpingProp = checkIfIsJumping(num) ? "jumping, " : "";
                        String evenProp = checkIfIsEven(num) ? "even, " : "";
                        String oddProp = checkIfIsEven(num) ? "" : "odd, ";
                        String happyProp = checkIfIsHappy(num) ? "happy, " : "";
                        String sadProp = checkIfIsHappy(num) ? "" : "sad, ";

                        String prevResult = (buzzProp + duckProp + palindromicProp + gapfulProp + spyProp + squareProp + sunnyProp + jumpingProp + evenProp + oddProp + happyProp + sadProp);
                        String result = prevResult.substring(0, prevResult.length() - 2);

                        System.out.printf("%d is %s\n", num, result);
                    }
                    System.out.println();
                    continue;
                }
                continue;
            }

            if (number == 0) {
                System.out.println("\nGoodbye!");
                continue;
            }

            boolean isNatural = checkIfIsNatural(number);
            if (!isNatural) {
                continue;
            }

            boolean isEven = checkIfIsEven(number);
            boolean isOdd = !isEven;
            boolean isBuzz = checkIfIsBuzz(number);
            boolean isDuck = checkIfIsDuck(number);
            boolean isPalindromic = checkIfIsPalindromic(number);
            boolean isGapful = checkIfIsGapful(number);
            boolean isSpy = checkIfIsSpy(number);
            boolean isSquare = checkIfIsSquare(number);
            boolean isSunny = checkIfIsSunny(number);
            boolean isJumping = checkIfIsJumping(number);
            boolean isHappy = checkIfIsHappy(number);
            boolean isSad = !isHappy;

            System.out.println();
            System.out.printf("Properties of %d\n", number);
            System.out.printf("\tbuzz: %b\n", isBuzz);
            System.out.printf("\tduck: %b\n", isDuck);
            System.out.printf("\tpalindromic: %b\n", isPalindromic);
            System.out.printf("\tgapful: %b\n", isGapful);
            System.out.printf("\tspy: %b\n", isSpy);
            System.out.printf("\tsquare: %b\n", isSquare);
            System.out.printf("\tsunny: %b\n", isSunny);
            System.out.printf("\tjumping: %b\n", isJumping);
            System.out.printf("\teven: %b\n", isEven);
            System.out.printf("\todd: %b\n", isOdd);
            System.out.printf("\thappy: %b\n", isHappy);
            System.out.printf("\tsad: %b\n", isSad);
            System.out.println();
        }
    }

    public static boolean checkIfIsNatural(long number) {
        if (number <= 0 || number != (long) number) {
            System.out.println("The first parameter should be a natural number or zero.\n");
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkIfIsEven(long number) {
        return number % 2 == 0 ? true : false;
    }

    public static boolean checkIfIsBuzz(long number) {
        boolean isDivisibleBySeven = number % 7 == 0 ? true : false;
        boolean endsWithSeven = number % 10 == 7 ? true : false;
        return isDivisibleBySeven || endsWithSeven ? true : false;
    }

    public static boolean checkIfIsDuck(long number) {
        String input = Long.toString(number);
        return input.contains("0") ? true : false;
    }

    public static boolean checkIfIsPalindromic(long number) {
        String numberString = Long.toString(number);
        StringBuilder numberSb = new StringBuilder(numberString);
        return numberSb.reverse().toString().equals(numberString) ? true : false;
    }

    public static boolean checkIfIsGapful(long number) {
        String numberString = Long.toString(number);
        if (numberString.length() < 3) {
            return false;
        } else {
            long firstDigit = Long.parseLong(Character.toString(numberString.charAt(0)));
            long lastDigit = Long.parseLong(Character.toString(numberString.charAt(numberString.length() - 1)));
            long divisor = firstDigit * 10 + lastDigit;
            return number % divisor == 0 ? true : false;
        }
    }

    public static boolean checkIfIsSpy(long number) {
        long sum = 0;
        long mul = 1;
        while (number > 0) {
            long digit = number % 10;
            sum += digit;
            mul *= digit;
            number = (number - number % 10) / 10;
        }
        return sum == mul ? true : false;
    }

    public static boolean checkIfIsSquare(long number) {
        long result = (long) Math.sqrt(number);
        return result * result == number ? true : false;
    }

    public static boolean checkIfIsSunny(long number) {
        long result = (long) Math.sqrt((number + 1));
        return result * result == (number + 1)? true : false;
    }

    public static boolean checkIfIsJumping(long number) {
        String str = Long.toString(number);
        for (int i = 0; i < str.length() - 1; i++) {
            int actualDigit = Integer.parseInt(Character.toString(str.charAt(i)));
            int nextDigit = Integer.parseInt(Character.toString(str.charAt(i + 1)));
            if (Math.abs(actualDigit - nextDigit) != 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIfIsHappy(long number) {

        ArrayList<Long> quadraticSumResults = new ArrayList<>();

        while (true) {
            long temporalQuadraticSum = 0;
            while (number > 0) {
                temporalQuadraticSum += (number % 10) * (number % 10);
                number = (number - (number % 10)) / 10;
            }
            if (temporalQuadraticSum == 1) {
                return true;
            } else if (quadraticSumResults.contains(temporalQuadraticSum)) {
                return false;
            } else {
                quadraticSumResults.add(temporalQuadraticSum);
                number = temporalQuadraticSum;
            }
        }
    }

    public static boolean selectSpecificFunction(String name, long number) {
        switch(name) {
            case "buzz":
                return checkIfIsBuzz(number);
            case "-buzz":
                return !checkIfIsBuzz(number);
            case "duck":
                return checkIfIsDuck(number);
            case "-duck":
                return !checkIfIsDuck(number);
            case "palindromic":
                return checkIfIsPalindromic(number);
            case "-palindromic":
                return !checkIfIsPalindromic(number);
            case "gapful":
                return checkIfIsGapful(number);
            case "-gapful":
                return !checkIfIsGapful(number);
            case "spy":
                return checkIfIsSpy(number);
            case "-spy":
                return !checkIfIsSpy(number);
            case "square":
                return checkIfIsSquare(number);
            case "-square":
                return !checkIfIsSquare(number);
            case "sunny":
                return checkIfIsSunny(number);
            case "-sunny":
                return !checkIfIsSunny(number);
            case "jumping":
                return checkIfIsJumping(number);
            case "-jumping":
                return !checkIfIsJumping(number);
            case "even":
                return checkIfIsEven(number);
            case "-even":
                return !checkIfIsEven(number);
            case "odd":
                return !checkIfIsEven(number);
            case "-odd":
                return checkIfIsEven(number);
            case "happy":
                return checkIfIsHappy(number);
            case "-happy":
                return !checkIfIsHappy(number);
            case "sad":
                return !checkIfIsHappy(number);
            case "-sad":
                return checkIfIsHappy(number);
            default:
                return false;
        }
    }
}

