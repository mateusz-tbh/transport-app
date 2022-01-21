package io.github.mateusztbh.transportappv3.Counters;

import io.github.mateusztbh.transportappv3.Card.Card;

import javax.persistence.*;

@Entity
public class Counters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "counter_end")
    private int counterEnd;
    @Column(name = "counter_start")
    private int counterStart;
    @Column(name = "counter_course")
    private int counterCourse;
    @Column(name = "counter_fuel")
    private Integer counterFuel = 0;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    public Counters() {
    }

    public Counters(Card card, int counterEnd, int counterStart, int counterCourse, int counterFuel) {
        this.card = card;
        this.counterEnd = counterEnd;
        this.counterStart = counterStart;
        this.counterCourse = counterCourse;
        this.counterFuel = counterFuel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public int getCounterEnd() {
        return counterEnd;
    }

    public void setCounterEnd(final int counterEnd) {
        this.counterEnd = counterEnd;
    }

    public int getCounterStart() {
        return counterStart;
    }

    public void setCounterStart(final int counterStart) {
        this.counterStart = counterStart;
    }

    public int getCounterCourse() {
        return counterCourse;
    }

    public void setCounterCourse(final int counterCourse) {
        this.counterCourse = counterCourse;
    }

    public Integer getCounterFuel() {
        return counterFuel;
    }

    public void setCounterFuel(final Integer counterFuel) {
        this.counterFuel = counterFuel;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(final Card card) {
        this.card = card;
    }
}
