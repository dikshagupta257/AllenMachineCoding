package models;

import java.util.HashMap;
import java.util.Map;

public class VaccinationCentre {
    private String id;
    private String state;
    private String district;

    private Map<Integer, VaccinationCentrePerDayCapacity> capacityPerDay;

    public VaccinationCentre(String id, String state, String district) {
        this.id = id;
        this.state = state;
        this.district = district;

        this.capacityPerDay = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getDistrict() {
        return district;
    }

    public Map<Integer, VaccinationCentrePerDayCapacity> getCapacityPerDay() {
        return capacityPerDay;
    }
}

