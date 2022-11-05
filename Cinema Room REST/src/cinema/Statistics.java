package cinema;

public class Statistics {
    private int current_income;
    private int number_of_available_seats;
    private int number_of_purchased_tickets;

    public Statistics(
            int current_income,
            int number_of_available_seats,
            int number_of_purchased_tickets) {
        this.current_income = current_income;
        this.number_of_available_seats = number_of_available_seats;
        this.number_of_purchased_tickets = number_of_purchased_tickets;
    }

    public int getCurrent_income() {
        return current_income;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }
}
