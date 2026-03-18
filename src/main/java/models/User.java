package models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String id;
    private final String name;
    private final String gender;
    private final int age;
    private final String state;
    private final String district;
    private final List<String> centreIds = new ArrayList<>();

    public User(String id, String name, String gender, int age, String state, String district) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.state = state;
        this.district = district;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getState() {
        return state;
    }

    public String getDistrict() {
        return district;
    }

    public List<String> getCentreIds() {
        return centreIds;
    }
}
