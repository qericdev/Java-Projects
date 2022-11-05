package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.UUID;

@RestController
public class SeatsController {

    public static ArrayList<Seat> purchaseBook = new ArrayList<>();
    public static ArrayList<Token> purchaseTicket = new ArrayList<>();

    public static int current_income = 0;
    public static int number_of_available_seats = 81;
    public static int number_of_purchased_tickets = 0;

    @GetMapping("/seats")
    public Seats seats() {
        return new Seats(9, 9);
    }

    @PostMapping("/purchase")
    public Token purchaseSeat(@RequestBody Request request) {
        boolean isEqual = false;
        for(Seat seat : purchaseBook) {
            if (seat.getRow() == request.getRow() && seat.getColumn() == request.getColumn()) {
                isEqual = true;
                break;
            }
        }
        if (request.getRow() > 9 || request.getColumn() > 9 || request.getRow() < 1 || request.getColumn() < 1) {
            throw new OutOfBoundException();
        } else if (isEqual) {
            throw new AlreadyPurchasedException();
        } else {
            SeatsController.purchaseBook.add(new Seat(request.getRow(), request.getColumn()));
            SeatsController.purchaseTicket.add(new Token(UUID.randomUUID(), new Seat(request.getRow(), request.getColumn())));
            SeatsController.current_income += SeatsController.purchaseBook.get(SeatsController.purchaseBook.size() - 1).getPrice();
            SeatsController.number_of_available_seats -= 1;
            SeatsController.number_of_purchased_tickets += 1;
            return SeatsController.purchaseTicket.get(SeatsController.purchaseTicket.size() - 1);
        }
    }

    @PostMapping("/return")
    public ReturnedTicket returnTicket(@RequestBody TokenReq token) {
        for (Token ticket: purchaseTicket) {
            if (ticket.getToken().equals(token.getToken())) {
                SeatsController.current_income -= ticket.getTicket().getPrice();
                SeatsController.number_of_available_seats += 1;
                SeatsController.number_of_purchased_tickets -= 1;
                return new ReturnedTicket(ticket.getTicket());
            }
        }
        throw new WrongTokenException();
    }

    @PostMapping("/stats")
    public Statistics getStatistics(@RequestParam(value = "password", defaultValue = "Wrong") String password) {
        if ("super_secret".equals(password)) {
            return new Statistics(current_income, number_of_available_seats, number_of_purchased_tickets);
        } else {
            throw new WrongPasswordException();
        }
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<CustomErrorMessage> handleWrongPassword
            (WrongPasswordException e, WebRequest request) {
        CustomErrorMessage body = new CustomErrorMessage("The password is wrong!");
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AlreadyPurchasedException.class)
    public ResponseEntity<CustomErrorMessage> handleAlreadyPurchased
            (AlreadyPurchasedException e, WebRequest request) {
        CustomErrorMessage body = new CustomErrorMessage("The ticket has been already purchased!");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OutOfBoundException.class)
    public ResponseEntity<CustomErrorMessage> handleOutOfBounds
            (OutOfBoundException e, WebRequest request) {
        CustomErrorMessage body = new CustomErrorMessage("The number of a row or a column is out of bounds!");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongTokenException.class)
    public ResponseEntity<CustomErrorMessage> handleWrongToken
            (WrongTokenException e, WebRequest request) {
        CustomErrorMessage body = new CustomErrorMessage("Wrong token!");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
