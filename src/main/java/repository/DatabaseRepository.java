package repository;

import models.Booking;
import models.User;
import models.VaccinationCentre;

import java.util.*;
import java.util.stream.Collectors;

public class DatabaseRepository {

    private static DatabaseRepository INSTANCE;
    private Map<String, User> userMap;
    private Map<String, VaccinationCentre> vaccinationCentreMap;
    private Map<UUID, Booking> bookingMap;

    private DatabaseRepository(){
        this.userMap = new HashMap<>();
        this.vaccinationCentreMap = new HashMap<>();
        this.bookingMap = new HashMap<>();
    }

    public static DatabaseRepository getDatabaseRepositoryInstance(){
        if(INSTANCE == null){
            synchronized (DatabaseRepository.class){
                if(INSTANCE == null){
                    INSTANCE = new DatabaseRepository();
                }
            }
        }

        return INSTANCE;
    }

    public User saveUser(User user){
        userMap.put(user.getId(), user);
        return user;
    }

    public Optional<User> getUser(String userId){
        return Optional.ofNullable(userMap.get(userId));
    }

    public VaccinationCentre saveVaccinationCentre(VaccinationCentre centre){
        vaccinationCentreMap.put(centre.getId(), centre);
        return centre;
    }

    public Optional<VaccinationCentre> getVaccinationCentre(String centreId){
        return Optional.ofNullable(vaccinationCentreMap.get(centreId));
    }

    public List<VaccinationCentre> findVaccinationCentreByDistrict(String district){
        return vaccinationCentreMap.values().stream()
                .filter(centre -> centre.getDistrict().equals(district))
                .collect(Collectors.toList());
    }

   public void saveBooking(Booking booking){
        bookingMap.put(booking.getId(),booking);
   }

   public Optional<Booking> getBooking(UUID bookingId){
        return Optional.ofNullable(bookingMap.get(bookingId));
   }
}
