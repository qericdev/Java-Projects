package cinema;

public class OutOfBoundException extends RuntimeException{

    public OutOfBoundException() {
        super("The number of a row or a column is out of bounds!");
    }
}
