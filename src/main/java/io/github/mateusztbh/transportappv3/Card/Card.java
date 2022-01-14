package io.github.mateusztbh.transportappv3.Card;

import io.github.mateusztbh.transportappv3.Trip.Trip;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 45, nullable = false, unique = true)
    private String number;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "card")
    private List<Trip> trip = new ArrayList<>();

    public Card() {
    }

    public Card(final String number, final List<Trip> trip) {
        this.number = number;
        this.trip = trip;
    }

    public Card(final Integer id) {
        this.id = id;
    }

    public Card(final String number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public List<Trip> getTrip() {
        return trip;
    }

    public void setTrip(final List<Trip> trip) {
        this.trip = trip;
    }
}
