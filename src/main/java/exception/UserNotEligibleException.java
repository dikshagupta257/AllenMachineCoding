package exception;

public class UserNotEligibleException extends RuntimeException {
    public UserNotEligibleException(String userName) {
        super("User : " + userName + " not eligible for vaccination");
    }
}
