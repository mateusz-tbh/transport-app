package io.github.mateusztbh.transportappv3.Card;

import io.github.mateusztbh.transportappv3.Counters.Counters;
import io.github.mateusztbh.transportappv3.Fuel.Fuel;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "card")
    private List<Fuel> fuel = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "card")
    private List<Counters> counters = new ArrayList<>();

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

    public List<Fuel> getFuel() {
        return fuel;
    }

    public void setFuel(final List<Fuel> fuel) {
        this.fuel = fuel;
    }

    public List<Counters> getCounters() {
        return counters;
    }

    public void setCounters(final List<Counters> counters) {
        this.counters = counters;
    }
}
