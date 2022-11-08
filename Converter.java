package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int source;
        int target;
        String option = "";

        while (!"/exit".equals(option)) {
            System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
            option = scanner.nextLine();
            try {
                if (!"/exit".equals(option)) {
                    source = Integer.parseInt(option.split(" ")[0]);
                    target = Integer.parseInt(option.split(" ")[1]);

                    if (source < 2 || source > 36 || target < 2 || target > 36) {
                        throw new Exception("Not in a valid range");
                    }
                    String input = "";

                    while (!"/back".equals(input)) {
                        System.out.printf("Enter number in base %d to convert to base %d (To go back type /back)\n",
                                source, target);
                        input = scanner.nextLine();

                        if (!"/back".equals(input)) {
                            try {
                                /* Due to the program receive a String, It can be classified as a BigInteger or as a
                                BigDecimal if it contains a "dot" inside it. */
                                if (input.contains(".")) {
                                    // First, the program converts the Integer part of the number to a BigInteger
                                    BigInteger tempNumber = new BigInteger(input.substring(0, input.indexOf(".")), source);
                                    // Building the rational part
                                    String rationalString = "0" + input.substring(input.indexOf("."));
                                    BigDecimal rationalNumber = new BigDecimal("0");
                                    // i = 2 means that the algorithm starts after the point
                                    for (int i = 2; i < rationalString.length(); i++) {
                                        rationalNumber = rationalNumber.add((new BigDecimal((new BigInteger(Character.toString(rationalString.charAt(i)), source))).divide((new BigDecimal(Integer.toString(source))).pow(i - 1), 5, RoundingMode.HALF_DOWN)));
                                    }

                                    String finalRationalNumber = "";

                                    for (int i = 0; i < 5; i++) {
                                        rationalNumber = rationalNumber.multiply(new BigDecimal(Integer.toString(target)));
                                        String tempString = rationalNumber.toString().substring(0, rationalNumber.toString().indexOf("."));
                                        char tempChar = ' ';
                                        if (Integer.parseInt(tempString) >= 10) {
                                            tempChar = (char) (Character.codePointAt("a", 0) + Integer.parseInt(tempString) - 10);
                                        }
                                        finalRationalNumber += Integer.parseInt(tempString) >= 10 ? Character.toString(tempChar) : tempString;
                                        rationalNumber = rationalNumber.subtract(new BigDecimal(tempString));
                                    }

                                    System.out.printf("Conversion result: %s.%s\n", toTargetBase(tempNumber, target), finalRationalNumber);

                                } else {
                                    BigInteger number = new BigInteger(input, source);
                                    System.out.printf("Conversion result: %s\n", toTargetBase(number, target));
                                }
                            } catch (Exception e) {
                                System.out.println("Input is not a number");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Source or target inputs are not numbers between 2 and 36");
            }
        }
    }
    public static String toTargetBase(BigInteger number, int target) {
        String result = "";
        BigInteger targetBigInteger = new BigInteger(Integer.toString(target));
        char remainder;
        while (number.compareTo(BigInteger.ZERO) > 0) {
            if (number.remainder(targetBigInteger).compareTo(BigInteger.TEN) >= 0) {
                remainder = (char) (Character.codePointAt("a", 0) +
                            Integer.parseInt(number.remainder(targetBigInteger).subtract(BigInteger.TEN).toString()));
                result = remainder + result;
            } else {
                result = number.remainder(targetBigInteger) + result;
            }
            number = number.divide(targetBigInteger);
        }
        return result.length() == 0 ? "0" : result;
    }
}
