package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows");
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numberOfSeats = scanner.nextInt();
        String[][] seats = new String[row][numberOfSeats];
        int[] currentIncome = new int[1];
        currentIncome[0] = 0;
        int option = -1;

        for( int i = 0; i < row; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                seats[i][j] = "S";
            }
        }
        while(option != 0){
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            option = scanner.nextInt();
            switch(option) {
                case 0:
                    return;
                case 1:
                    printDistributionSeats(seats, row, numberOfSeats);
                    break;
                case 2:
                    buyTicket(seats, row, numberOfSeats, currentIncome);
                    break;
                case 3:
                    getStatistics(seats, row, numberOfSeats, currentIncome);
                    break;
                default:
                    break;
            }
        }
    }
    public static void buyTicket(String[][] seats, int row, int numberOfSeats, int[] currentIncome) {

        Scanner scanner = new Scanner(System.in);
        boolean buyIsValid = false;
        while (!buyIsValid) {
            System.out.println("Enter a row number:");
            int rowChosen = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatChosen = scanner.nextInt();

            if (row >= rowChosen && numberOfSeats >= seatChosen && rowChosen >= 0 && seatChosen >=0) {
                if ("S".equals(seats[rowChosen-1][seatChosen-1])) {
                    int seatsSmallRoom = 60;
                    if(row * numberOfSeats <= seatsSmallRoom) {
                        int seatPriceSmallRoom = 10;
                        currentIncome[0] += seatPriceSmallRoom;
                        System.out.println("Ticket Price: $" + seatPriceSmallRoom);
                    } else {
                        int priceFrontHalf = 10;
                        int priceBackHalf = 8;
                        int finalPrice = row / 2 < rowChosen ? priceBackHalf : priceFrontHalf;
                        currentIncome[0] += finalPrice;
                        System.out.println("Ticket Price: $" + finalPrice);
                    }
                    seats[rowChosen-1][seatChosen-1] = "B";
                    buyIsValid = true;
                    printDistributionSeats(seats, row, numberOfSeats);
                } else {
                    System.out.println("That ticket has already been purchased!");
                }
            } else {
                System.out.println("Wrong Input!");
            }
        }
    }
    public static void printDistributionSeats(String[][] seats, int row, int numberOfSeats) {
        System.out.println("\nCinema:");
        for (int i = 0; i < numberOfSeats + 1; i++) {
            if (i == 0) {
                System.out.print(" ");
            } else {
                System.out.print(" " + i);
            }
        }
        System.out.println();
        for (int i = 0; i < row; i++) {
            for( int j = 0; j < numberOfSeats + 1; j++) {
                if( j == 0) {
                    System.out.print(i + 1);
                    continue;
                }
                System.out.print(" " + seats[i][j - 1]);
            }
            System.out.println();
        }
    }
    public static void getStatistics(String [][] seats, int row, int numberOfSeats, int[] currentIncome) {
        int ticketsSold = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                if("B".equals(seats[i][j])) {
                    ticketsSold++;
                }
            }
        }
        float percentage = (float) ticketsSold / (float) (row * numberOfSeats) * 100;
        String percentageString = String.format("%.2f", percentage);

        int seatsSmallRoom = 60;
        int seatPriceSmallRoom = 10;
        int priceFrontHalf = 10;
        int priceBackHalf = 8;
        int totalIncome = 0;

        if(row * numberOfSeats <= seatsSmallRoom) {
            totalIncome = row * numberOfSeats * seatPriceSmallRoom;
        } else {
            totalIncome = row / 2 * numberOfSeats * priceFrontHalf + (row - row / 2) * numberOfSeats * priceBackHalf;
        }

        System.out.println("\nNumber of purchased tickets: " + ticketsSold);
        System.out.println("Percentage: " + percentageString + "%");
        System.out.println("Current income: $" + currentIncome[0]);
        System.out.println("Total income : $" + totalIncome);

    }
}