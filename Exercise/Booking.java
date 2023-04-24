package Exercise;

import java.time.LocalDate;

public class Booking {

    private int id;
    private String fitnessType;
    private String timeSlot;
    private BookingStatus status;
    private int rating;
    private String review;
    private String name;
    private LocalDate date;
    private int price;
    private String day;

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Booking(int id, String name, LocalDate date, String day, String fitnessType, String timeSlot) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.fitnessType = fitnessType;
        this.timeSlot = timeSlot;
        this.day = day;
        this.status = BookingStatus.BOOKED; // Set default status
        if (fitnessType.equals("yoga")) {
            this.price = 10;
        } else if (fitnessType.equals("pilates")) {
            this.price = 12;
        } else if (fitnessType.equals("cardio")) {
            this.price = 8;
        } else if (fitnessType.equals("Zumba")) {
            this.price = 15;
        }

    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    // other methods

    public enum BookingStatus {
        BOOKED, CHANGED,
        CANCELLED,
        COMPLETED, REVIEWED
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int phone;

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFitnessType() {
        return fitnessType;
    }

    public void setFitnessType(String fitnessType) {
        this.fitnessType = fitnessType;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + " | Name: " + this.name + " | Fitness Type: " + this.fitnessType + " | Time Slot: "
                + this.timeSlot + " | Status: " + (this.status != null ? this.status : "unknown");
    }

    public LocalDate getDate() {
        return this.date;
    }

}
