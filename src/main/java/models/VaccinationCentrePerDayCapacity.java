package models;

import java.util.ArrayList;
import java.util.List;

public class VaccinationCentrePerDayCapacity {
    private int day;
    private int totalCapacity;
    private int bookingCount;

    private List<Booking> bookings;

    public VaccinationCentrePerDayCapacity(int day, int totalCapacity) {
        this.day = day;
        this.totalCapacity = totalCapacity;
        this.bookingCount = 0;
        this.bookings = new ArrayList<>();;
    }

    public boolean isCapacityFull(){
        return this.bookingCount >= this.totalCapacity;
    }

    public int getDay() {
        return day;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public int getBookingCount() {
        return bookingCount;
    }

    public int getAvailableNofSlots(){
        return totalCapacity - bookingCount;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void incrementNoOfBooking(){
        bookingCount++;
    }

    public void decrementNoOfBooking(){
        bookingCount--;
    }
}
