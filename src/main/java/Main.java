import models.Booking;
import models.User;
import models.VaccinationCentre;
import services.BookingService;
import services.CentreService;
import services.RegistrationService;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        RegistrationService registrationService = new RegistrationService();
        BookingService bookingService = new BookingService();
        CentreService centreService =new CentreService();

        User user1 = registrationService.addUser("U1", "Harry", "Male", 35, "Karnataka", "Bangalore");
        User user2 = registrationService.addUser("U2", "Ron", "Male", 30, "Karnataka", "Bangalore");
        User user3 = registrationService.addUser("U3", "Albus", "Male", 30, "Karnataka", "Bangalore");
        User user4 = registrationService.addUser("U4", "Draco", "Male", 15, "Karnataka", "Bangalore");
        User user5 = registrationService.addUser("U5", "Dobby", "Male", 30, "Gujarat", "Surat");

        VaccinationCentre centre1 = registrationService.addVaccinationCentre("Karnataka", "Bangalore", "VC1");
        VaccinationCentre centre2 = registrationService.addVaccinationCentre("Karnataka", "Bangalore", "VC2");
        VaccinationCentre centre3 = registrationService.addVaccinationCentre("Karnataka", "Mysore", "VC3");

        centreService.addCapacity("VC1", 1, 1);
        centreService.addCapacity("VC2", 1, 3);
        centreService.addCapacity("VC1", 5, 10);
        centreService.addCapacity("VC3", 3, 4);

        centreService.listVaccinationCentres("Bangalore");

        bookingService.bookVaccination("VC1", 1, "U1");
        bookingService.listBookings("VC1", 1);

        bookingService.bookVaccination("VC2", 1, "U2");
        Booking booking = bookingService.bookVaccination("VC2", 1, "U3");
        bookingService.listBookings("VC2", 1);
        bookingService.bookVaccination("VC1", 1, "U5");

    }
}