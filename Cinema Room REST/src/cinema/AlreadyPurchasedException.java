package cinema;

public class AlreadyPurchasedException extends RuntimeException{

    public AlreadyPurchasedException() {
        super("The ticket has been already purchased!");
    }

}
