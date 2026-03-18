package services;

import enums.BookingStatus;
import exception.*;
import models.Booking;
import models.User;
import models.VaccinationCentre;
import models.VaccinationCentrePerDayCapacity;
import repository.DatabaseRepository;

import java.util.UUID;

public class BookingService {
    DatabaseRepository repo;

    public BookingService(){
        this.repo = DatabaseRepository.getDatabaseRepositoryInstance();
    }

    public Booking bookVaccination(String centreId, int day, String userId){
        User user = repo.getUser(userId)
                .orElseThrow(() -> new UserNotFoundException("User not registered"));

        VaccinationCentre centre = repo.getVaccinationCentre(centreId)
                .orElseThrow(() -> new VaccinationCentreNotFoundException(centreId));

        if(user.getCentreIds().contains(centreId)){
            throw new UserAlreadyBookedException("User has already booked an appintment");
        }

        if(user.getAge() < 18){
            throw new UserNotEligibleException(user.getName());
        }

        VaccinationCentrePerDayCapacity centrePerDayCapacity = centre.getCapacityPerDay().get(day);
        if(centrePerDayCapacity == null){
            throw new CapacityForDayNotFoundException(centreId, day);
        }

        if(centrePerDayCapacity.isCapacityFull()){
            throw new NoSlotAvailableException("Booking for this day is full now!");
        }

        synchronized (centrePerDayCapacity){
            if(centrePerDayCapacity.isCapacityFull()){
                throw new NoSlotAvailableException("Booking for this day is full now!");
            }

            Booking booking = new Booking(UUID.randomUUID(), userId, centreId, BookingStatus.SCHEDULED, day);
            centrePerDayCapacity.getBookings().add(booking);
            centrePerDayCapacity.incrementNoOfBooking();
            user.getCentreIds().add(booking.getCentreId());
            repo.saveBooking(booking);

            System.out.println("Booking for user: " + user.getName() + " made successfully!");
            return booking;
        }
    }

    public void listBookings(String centreId, int day){
        VaccinationCentre centre = repo.getVaccinationCentre(centreId)
                .orElseThrow(() -> new VaccinationCentreNotFoundException(centreId));

        VaccinationCentrePerDayCapacity centrePerDayCapacity = centre.getCapacityPerDay().get(day);
        if(centrePerDayCapacity == null){
            throw new CapacityForDayNotFoundException(centreId, day);
        }

        System.out.println("Bookings for centreId: " + centreId);
        centrePerDayCapacity.getBookings().stream()
                .filter(booking -> booking.getBookingStatus() == BookingStatus.SCHEDULED)
                .forEach(System.out::println);
        System.out.println();
    }

    public void cancelBooking(UUID bookingId, String userId){
        Booking booking = repo.getBooking(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(bookingId.toString()));

        if(!booking.getUserId().equals(userId)){
            throw new RuntimeException("Booking is not done by given user");
        }

        if(booking.getBookingStatus() != BookingStatus.SCHEDULED){
            throw new RuntimeException("Cannot cancel the booking now");
        }

        VaccinationCentre centre = repo.getVaccinationCentre(booking.getCentreId()).get();
        VaccinationCentrePerDayCapacity centrePerDayCapacity = centre.getCapacityPerDay().get(booking.getDay());

        synchronized (centrePerDayCapacity){
            booking.setBookingStatus(BookingStatus.CANCELLED);
            centrePerDayCapacity.decrementNoOfBooking();

            User user = repo.getUser(userId).get();
            user.getCentreIds().remove(centre.getId());

            System.out.println("Booking successfully cancelled for user: " + user.getName());
        }
    }
}
