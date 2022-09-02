package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static boolean exit = false;
    public static int waterStock = 400;
    public static int milkStock = 540;
    public static int coffeeStock = 120;
    public static int disposableCupsStock = 9;
    public static int money = 550;

    public static void printStatus(int waterStock, int milkStock, int coffeeStock, int disposableCupsStock, int money) {
        System.out.println("\nThe coffee machine has:");
        System.out.println(waterStock + " ml of water");
        System.out.println(milkStock + " ml of milk");
        System.out.println(coffeeStock + " g of coffee beans");
        System.out.println(disposableCupsStock + " disposable cups");
        System.out.println("$" + money + " of money\n");
    }

    public static String readData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write action (buy, fill, take, remaining, exit)");
        String action = scanner.next();
        return action;
    }

    public static void processInfo(String action) {
        Scanner scanner = new Scanner(System.in);

        switch(action) {
            case "remaining":
                printStatus(waterStock, milkStock, coffeeStock, disposableCupsStock, money);
                break;
            case "buy":
                System.out.println("What do you want to buy? 1 . espresso, 2 - latte, 3 - cappuccino:, back - to main menu");
                String typeOfCoffee = scanner.next();
                switch (typeOfCoffee) {
                    case "1": //Espresso
                        if(waterStock - 250 >= 0 && coffeeStock - 16 >= 0 && disposableCupsStock - 1 >= 0) {
                            waterStock -= 250;
                            coffeeStock -= 16;
                            disposableCupsStock -= 1;
                            money += 4;
                            System.out.println("I have enough resources, making you a coffee!");
                        } else {
                            if (waterStock - 250 < 0) {
                                System.out.println("Sorry, not enough water!");
                            } else if (coffeeStock - 16 < 0) {
                                System.out.println("Sorry, not enough coffee!");
                            } else {
                                System.out.println("Sorry, not enough disposable cups!");
                            }
                        }
                        break;
                    case "2": //Latte
                        if(waterStock - 350 >= 0 && milkStock - 75 >= 0 && coffeeStock - 20 >= 0 && disposableCupsStock - 1 >= 0) {
                            waterStock -=350;
                            milkStock -= 75;
                            coffeeStock -= 20;
                            disposableCupsStock -= 1;
                            money += 7;
                            System.out.println("I have enough resources, making you a coffee!");
                        } else {
                            if (waterStock - 350 < 0) {
                                System.out.println("Sorry, not enough water!");
                            } else if (milkStock - 75 < 0) {
                                System.out.println("Sorry, not enough milk!");
                            } else if (coffeeStock - 20 < 0) {
                                System.out.println("Sorry, not enough coffee!");
                            } else {
                                System.out.println("Sorry, not enough disposable cups!");
                            }
                        }
                        break;
                    case "3": //Cappuccino
                        if(waterStock - 200 >= 0 && milkStock - 100 >= 0 && coffeeStock - 12 >= 0 && disposableCupsStock - 1 >= 0) {
                            waterStock -= 200;
                            milkStock -= 100;
                            coffeeStock -=12;
                            disposableCupsStock -= 1;
                            money += 6;
                            System.out.println("I have enough resources, making you a coffee!");
                        } else {
                            if (waterStock - 200 < 0) {
                                System.out.println("Sorry, not enough water!");
                            } else if (milkStock - 100 < 0) {
                                System.out.println("Sorry, not enough milk!");
                            } else if (coffeeStock - 12 < 0) {
                                System.out.println("Sorry, not enough coffee!");
                            } else {
                                System.out.println("Sorry, not enough disposable cups!");
                            }
                        }
                        break;
                    default:
                        break;
                }
                break;
            case "fill":
                System.out.println("Write how many ml of water you want to add:");
                int waterToAdd = scanner.nextInt();
                waterStock += waterToAdd;
                System.out.println("Write how many ml of milk you want to add:");
                int milkToAdd = scanner.nextInt();
                milkStock += milkToAdd;
                System.out.println("Write how many grams of coffee beans you want to add:");
                int coffeeToAdd = scanner.nextInt();
                coffeeStock += coffeeToAdd;
                System.out.println("Write how many disposable cups of coffee you want to add");
                int disposableCupsToAdd = scanner.nextInt();
                disposableCupsStock += disposableCupsToAdd;
                break;
            case "take":
                System.out.println("I gave you $" + money);
                money = 0;
                break;
            case "exit":
                CoffeeMachine.exit = true;
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        while (!CoffeeMachine.exit) {
            processInfo(CoffeeMachine.readData());
        }
    }
}
