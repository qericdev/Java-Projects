package cinema;

import java.util.ArrayList;

public class Seats {
    private final int total_rows;
    private final int total_columns;
    private final ArrayList<Seat> available_seats;

    public Seats(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = new ArrayList<>();
        for (int i = 0; i < total_rows; i++) {
            for (int j = 0; j < total_columns; j++) {
                Seat tempSeat = new Seat(i + 1, j + 1);
                if(!SeatsController.purchaseBook.contains(tempSeat)) {
                    available_seats.add(tempSeat);
                }
            }
        }
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public ArrayList<Seat> getAvailable_seats() {
        return available_seats;
    }
}
