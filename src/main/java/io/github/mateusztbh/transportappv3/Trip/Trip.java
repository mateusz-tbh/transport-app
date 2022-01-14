package io.github.mateusztbh.transportappv3.Trip;

import io.github.mateusztbh.transportappv3.Card.Card;

import javax.persistence.*;

@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "departure_day")
    private String departureDay;
    @Column(name = "arrival_day")
    private String arrivalDay;
    @Column(name = "departure_clock")
    private String departureClock;
    @Column(name = "arrival_clock")
    private String arrivalClock;
    @Column(name = "departure_location")
    private String departureLocation;
    @Column(name = "arrival_location")
    private String arrivalLocation;
    @Column(name = "departure_country")
    private String departureCountry;
    @Column(name = "arrival_country")
    private String arrivalCountry;
    @Column(name = "departure_counter")
    private int departureCounter;
    @Column(name = "arrival_counter")
    private int arrivalCounter;
    @Column(name = "course")
    private int course;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    public Trip() {
    }

    public Trip(final String arrivalCountry, final int departureCounter) {
        this.arrivalCountry = arrivalCountry;
        this.departureCounter = departureCounter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(final String departureDay) {
        this.departureDay = departureDay;
    }

    public String getArrivalDay() {
        return arrivalDay;
    }

    public void setArrivalDay(final String arrivalDay) {
        this.arrivalDay = arrivalDay;
    }

    public String getDepartureClock() {
        return departureClock;
    }

    public void setDepartureClock(final String departureClock) {
        this.departureClock = departureClock;
    }

    public String getArrivalClock() {
        return arrivalClock;
    }

    public void setArrivalClock(final String arrivalClock) {
        this.arrivalClock = arrivalClock;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(final String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(final String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public String getDepartureCountry() {
        return departureCountry;
    }

    public void setDepartureCountry(final String departureCountry) {
        this.departureCountry = departureCountry;
    }

    public String getArrivalCountry() {
        return arrivalCountry;
    }

    public void setArrivalCountry(final String arrivalCountry) {
        this.arrivalCountry = arrivalCountry;
    }

    public int getDepartureCounter() {
        return departureCounter;
    }

    public void setDepartureCounter(final int departureCounter) {
        this.departureCounter = departureCounter;
    }

    public int getArrivalCounter() {
        return arrivalCounter;
    }

    public void setArrivalCounter(final int arrivalCounter) {
        this.arrivalCounter = arrivalCounter;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(final int course) {
        this.course = course;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(final Card card) {
        this.card = card;
    }

    int subtract(final Trip trip) {
        return this.arrivalCounter - this.departureCounter;
    }
}
