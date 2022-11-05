package cinema;

public class ReturnedTicket {
    private Seat returned_ticket;

    public ReturnedTicket(Seat seat) {
        returned_ticket = new Seat(seat.getRow(), seat.getColumn());
    }

    public Seat getReturned_ticket() {
        return returned_ticket;
    }
}
