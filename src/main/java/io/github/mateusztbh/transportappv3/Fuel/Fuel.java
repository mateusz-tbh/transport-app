package io.github.mateusztbh.transportappv3.Fuel;

import io.github.mateusztbh.transportappv3.Card.Card;

import javax.persistence.*;

@Entity
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fuel_date")
    private String fuelDate;
    @Column(name = "fuel_location")
    private String fuelLocation;
    @Column(name = "fuel_counter")
    private int fuelCounter;
    @Column(name = "refueling_quantity")
    private Integer refuelingQuantity;
    @Column(name = "refueling_sum")
    private Integer refuelingSum;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    public Fuel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getFuelDate() {
        return fuelDate;
    }

    public void setFuelDate(final String fuelDate) {
        this.fuelDate = fuelDate;
    }

    public String getFuelLocation() {
        return fuelLocation;
    }

    public void setFuelLocation(final String fuelLocation) {
        this.fuelLocation = fuelLocation;
    }

    public int getFuelCounter() {
        return fuelCounter;
    }

    public void setFuelCounter(final int fuelCounter) {
        this.fuelCounter = fuelCounter;
    }

    public Integer getRefuelingQuantity() {
        return refuelingQuantity;
    }

    public void setRefuelingQuantity(final Integer refuelingQuantity) {
        this.refuelingQuantity = refuelingQuantity;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(final Card card) {
        this.card = card;
    }

    public Integer getRefuelingSum() {
        return refuelingSum;
    }

    public void setRefuelingSum(final Integer refuelingSum) {
        this.refuelingSum = refuelingSum;
    }

    public Integer sumModel(Fuel fuel) {
        return this.refuelingQuantity;
    }
}
