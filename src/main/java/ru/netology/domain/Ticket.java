package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@AllArgsConstructor
@Getter
@Setter
public class Ticket implements Comparable<Ticket>{
    private int id;
    private int price;
    private String departure;
    private String arrival;
    private int travelTime; // время в пути в минутах


    @Override
    public int compareTo(Ticket t) {
        return this.price - t.price;
    }

}
