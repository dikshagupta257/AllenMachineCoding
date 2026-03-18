package exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String bookingId) {
        super("Booking with id: " + bookingId + " not found");
    }
}
