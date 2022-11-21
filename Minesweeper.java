package minesweeper;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static boolean gameContinue = true;
    static int minesCounter = 0;
    static final int ROWS = 9;
    static final int COLUMNS = 9;

    public static void main(String[] args) {
        System.out.println("How many mines do you want on the field?");
        Scanner scanner = new Scanner(System.in);
        int mines = Integer.parseInt(scanner.nextLine());

        String[][] field = new String[ROWS][COLUMNS];
        String[][] fieldPrinted = new String[ROWS][COLUMNS];
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        int x;
        int y;
        String action;


        // Generating location
        for (int i = 0; i < mines; i++) {
            while (true) {
                int tempRandomNumber = (int) Math.floor((Math.random() * ROWS * COLUMNS));
                if (!randomNumbers.contains(tempRandomNumber)) {
                    randomNumbers.add(tempRandomNumber);
                    break;
                }
            }
        }
        // Populating the field
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                    /* Random numbers are generated from 0 to ROW * COLUMNS, for that reason is
                    used the expression "i * ROWS + j"
                    */
                field[i][j] = randomNumbers.contains(i * ROWS + j) ? "X" : ".";
                fieldPrinted[i][j] = ".";
            }
        }

        // Replacing "." to numbers
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (".".equals(field[i][j])) {
                    int counter = 0;
                    try {
                        if ("X".equals(field[i - 1][j - 1])) {
                            counter++;
                        }
                    } catch (Exception e) {
                    }
                    try {
                        if ("X".equals(field[i - 1][j])) {
                            counter++;
                        }
                    } catch (Exception e) {
                    }
                    try {
                        if ("X".equals(field[i - 1][j + 1])) {
                            counter++;
                        }
                    } catch (Exception e) {
                    }
                    try {
                        if ("X".equals(field[i][j - 1])) {
                            counter++;
                        }
                    } catch (Exception e) {
                    }
                    try {
                        if ("X".equals(field[i][j + 1])) {
                            counter++;
                        }
                    } catch (Exception e) {
                    }
                    try {
                        if ("X".equals(field[i + 1][j - 1])) {
                            counter++;
                        }
                    } catch (Exception e) {
                    }
                    try {
                        if ("X".equals(field[i + 1][j])) {
                            counter++;
                        }
                    } catch (Exception e) {
                    }
                    try {
                        if ("X".equals(field[i + 1][j + 1])) {
                            counter++;
                        }
                    } catch (Exception e) {
                    }
                    if (counter > 0) {
                        field[i][j] = Integer.toString(counter);
                    }
                }
            }
        }

        // Printing the field
        while (true) {
            System.out.println(" |123456789|");
            System.out.println("-|---------|");
            for (int i = 0; i < ROWS; i++) {
                System.out.printf("%d|", i + 1);
                for (int j = 0; j < COLUMNS; j++) {
                    System.out.print(fieldPrinted[i][j]);
                }
                System.out.print("|");
                System.out.println();
            }
            System.out.println("-|---------|");


            if (!gameContinue) {
                break;
            }
            // Logic for evaluating if there is a winner or not
            if (Main.minesCounter == mines) {
                System.out.println("Congratulations! You found all mines!");
                break;
            } else {
                while (true) {
                    System.out.println("Set/unset mines marks or claim a cell as free:");
                    String[] input = scanner.nextLine().split("\s+");
                    try {
                        y = Integer.parseInt(input[0]) - 1;
                        x = Integer.parseInt(input[1]) - 1;
                        action = input[2];
                    } catch (Exception e) {
                        System.out.println("There are no valid inputs.");
                        continue;
                    }
                    checkField(field, fieldPrinted, x, y, action);
                    break;
                }
            }
        }
    }

    public static void checkField(String[][] field, String[][] fieldPrinted, int x, int y, String action) {
        if ("free".equals(action)) {
            if (".".equals(field[x][y])) {
                field[x][y] = "/";
                fieldPrinted[x][y] = "/";
                try {
                    if (".".equals(field[x - 1][y - 1])) {
                        checkField(field, fieldPrinted, x - 1, y - 1, "free");
                        field[x - 1][y - 1] = "/";
                        fieldPrinted[x - 1][y - 1] = "/";
                    } else if (!"X".equals(field[x - 1][y - 1])) {
                        fieldPrinted[x - 1][y - 1] = field[x - 1][y - 1];
                    }
                } catch (Exception e) {
                }
                try {
                    if (".".equals(field[x - 1][y])) {
                        checkField(field, fieldPrinted, x - 1, y, "free");
                        field[x - 1][y] = "/";
                        fieldPrinted[x - 1][y] = "/";
                    } else if (!"X".equals(field[x - 1][y])) {
                        fieldPrinted[x - 1][y] = field[x - 1][y];
                    }
                } catch (Exception e) {
                }
                try {
                    if (".".equals(field[x - 1][y + 1])) {
                        checkField(field, fieldPrinted, x - 1, y + 1, "free");
                        field[x - 1][y + 1] = "/";
                        fieldPrinted[x - 1][y + 1] = "/";
                    } else if (!"X".equals(field[x - 1][y + 1])) {
                        fieldPrinted[x - 1][y + 1] = field[x - 1][y + 1];
                    }
                } catch (Exception e) {
                }
                try {
                    if (".".equals(field[x][y - 1])) {
                        checkField(field, fieldPrinted, x, y - 1, "free");
                        field[x][y - 1] = "/";
                        fieldPrinted[x][y - 1] = "/";
                    } else if (!"X".equals(field[x][y - 1])) {
                        fieldPrinted[x][y - 1] = field[x][y - 1];
                    }
                } catch (Exception e) {
                }
                try {
                    if (".".equals(field[x][y + 1])) {
                        checkField(field, fieldPrinted, x, y + 1, "free");
                        field[x][y + 1] = "/";
                        fieldPrinted[x][y + 1] = "/";
                    } else if (!"X".equals(field[x][y + 1])) {
                        fieldPrinted[x][y + 1] = field[x][y + 1];
                    }
                } catch (Exception e) {
                }
                try {
                    if (".".equals(field[x + 1][y - 1])) {
                        checkField(field, fieldPrinted, x + 1, y - 1, "free");
                        field[x + 1][y - 1] = "/";
                        fieldPrinted[x + 1][y - 1] = "/";
                    } else if (!"X".equals(field[x + 1][y - 1])) {
                        fieldPrinted[x + 1][y - 1] = field[x + 1][y - 1];
                    }
                } catch (Exception e) {
                }
                try {
                    if (".".equals(field[x + 1][y])) {
                        checkField(field, fieldPrinted, x + 1, y, "free");
                        field[x + 1][y] = "/";
                        fieldPrinted[x + 1][y] = "/";
                    } else if (!"X".equals(field[x + 1][y])) {
                        fieldPrinted[x + 1][y] = field[x + 1][y];
                    }
                } catch (Exception e) {
                }
                try {
                    if (".".equals(field[x + 1][y + 1])) {
                        checkField(field, fieldPrinted, x + 1, y + 1, "free");
                        field[x + 1][y + 1] = "/";
                        fieldPrinted[x + 1][y + 1] = "/";
                    } else if (!"X".equals(field[x + 1][y + 1])) {
                        fieldPrinted[x + 1][y + 1] = field[x + 1][y + 1];
                    }
                } catch (Exception e) {
                }
            } else if (!"X".equals(field[x][y])) {
                fieldPrinted[x][y] = field[x][y];
            } else {
                System.out.println("You stepped on a mine and failed!");
                for (int i = 0; i < ROWS; i++) {
                    for (int j = 0; j < COLUMNS; j++) {
                        if ("X".equals(field[x][y])) {
                            fieldPrinted[x][y] = field[x][y];
                        }
                    }
                }
                Main.gameContinue = false;
            }
        } else if("mine".equals(action)) {
            if ("*".equals(fieldPrinted[x][y])) {
                fieldPrinted[x][y] = ".";
                if ("X".equals(field[x][y])) {
                    Main.minesCounter--;
                }
            } else {
                fieldPrinted[x][y] = "*";
                if ("X".equals(field[x][y])) {
                    Main.minesCounter++;
                }
            }
        }
    }
}