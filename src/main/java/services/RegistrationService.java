package services;

import exception.UserNotEligibleException;
import models.User;
import models.VaccinationCentre;
import repository.DatabaseRepository;

public class RegistrationService {
    private DatabaseRepository repo;

    public RegistrationService(){
        repo = DatabaseRepository.getDatabaseRepositoryInstance();
    }

    public User addUser(String userId, String name, String gender, int age, String state, String district){
        if(repo.getUser(userId).isPresent()){
            System.out.println("User already registered");
            return null;
        }
        User user = new User(userId, name, gender, age, state, district);
        repo.saveUser(user);
        return user;
    }

    public VaccinationCentre addVaccinationCentre(String state, String district, String centreId){
        VaccinationCentre vaccinationCentre = new VaccinationCentre(centreId, state,district);
        repo.saveVaccinationCentre(vaccinationCentre);
        return vaccinationCentre;
    }
}
