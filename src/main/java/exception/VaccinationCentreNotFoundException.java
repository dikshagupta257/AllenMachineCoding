package exception;

public class VaccinationCentreNotFoundException extends RuntimeException {
    public VaccinationCentreNotFoundException(String centreId) {
        super(
                "Vaccination centre with id: " + centreId + "not found"
        );
    }
}
