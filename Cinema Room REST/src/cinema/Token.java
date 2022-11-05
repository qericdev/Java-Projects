package cinema;

import java.util.UUID;

public class Token {
    private UUID token;
    private Seat ticket;

    public Token(UUID token, Seat ticket) {
        this.token = token;
        this.ticket = new Seat(ticket.getRow(), ticket.getColumn());
    }

    public UUID getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }
}
