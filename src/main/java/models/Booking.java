package models;

import enums.BookingStatus;

import java.util.UUID;

public class Booking {
    private UUID id;
    private String userId;
    private String centreId;
    private BookingStatus bookingStatus;
    private int day;

    public Booking(UUID id, String userId, String centreId, BookingStatus bookingStatus, int day) {
        this.id = id;
        this.userId = userId;
        this.centreId = centreId;
        this.bookingStatus = bookingStatus;
        this.day = day;
    }

    public UUID getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getCentreId() {
        return centreId;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public int getDay() {
        return day;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", centreId='" + centreId + '\'' +
                ", bookingStatus=" + bookingStatus +
                '}';
    }
}
