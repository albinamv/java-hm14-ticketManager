package ru.netology.management;

import ru.netology.domain.Ticket;

import java.util.Comparator;

public class TicketByTravelTimeComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket o1, Ticket o2) {
        return o1.getTravelTime() - o2.getTravelTime();
    }

}
