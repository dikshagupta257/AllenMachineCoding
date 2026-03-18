package services;

import exception.VaccinationCentreNotFoundException;
import models.VaccinationCentre;
import models.VaccinationCentrePerDayCapacity;
import repository.DatabaseRepository;

import java.util.List;

public class CentreService {
    private DatabaseRepository repo;

    public CentreService(){
        repo = DatabaseRepository.getDatabaseRepositoryInstance();
    }

    public void addCapacity(String centreId, int day, int capacity){
        VaccinationCentre centre = repo.getVaccinationCentre(centreId)
                .orElseThrow(() -> new VaccinationCentreNotFoundException(centreId));

        VaccinationCentrePerDayCapacity centrePerDayCapacity = new VaccinationCentrePerDayCapacity(day, capacity);
        centre.getCapacityPerDay().put(day, centrePerDayCapacity);
    }

    public void listVaccinationCentres(String district){
        List<VaccinationCentre> centres = repo.findVaccinationCentreByDistrict(district);

        if(centres.isEmpty()){
            System.out.println("No centres available in this district");
            return;
        }

        for(VaccinationCentre centre: centres){
            System.out.println("Centre: " + centre.getId() + ", district: " + centre.getDistrict());
            for(VaccinationCentrePerDayCapacity centrePerDayCapacity : centre.getCapacityPerDay().values()){
                System.out.println("Day: " + centrePerDayCapacity.getDay() + ", TotalCapacity: " +
                        centrePerDayCapacity.getTotalCapacity() +
                         ", currentNoOfBookings: " + centrePerDayCapacity.getBookingCount());
            }
            System.out.println();
        }
    }
}
