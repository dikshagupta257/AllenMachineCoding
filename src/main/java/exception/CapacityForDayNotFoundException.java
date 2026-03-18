package exception;

public class CapacityForDayNotFoundException extends RuntimeException {
    public CapacityForDayNotFoundException(String centreId, int day) {
        super(
                "Capacity for given centre: " + centreId + " and day: " + day + " not found"
        );
    }
}
