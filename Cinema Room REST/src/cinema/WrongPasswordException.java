package cinema;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() { super("The password is wrong!"); }
}
