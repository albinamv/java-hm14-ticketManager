package ru.netology.management;

import ru.netology.domain.Repository;
import ru.netology.domain.Ticket;

import java.util.Arrays;
import java.util.Comparator;

public class TicketManager {
    private Repository repository;

    public TicketManager(Repository repository) {
        this.repository = repository;
    }

    public void add(Ticket ticket) {
        repository.add(ticket);
    }

    // TODO вынести логику поиска в отдельный метод
    public Ticket[] searchBy(String from, String to) {
        Ticket[] result = new Ticket[0]; // тут будем хранить подошедшие запросу продукты
        for (Ticket ticket : repository.findAll()) {
            if (matches(ticket, from, to)) {
                int length = result.length + 1;
                Ticket[] tmp = new Ticket[length];
                System.arraycopy(result, 0, tmp, 0, result.length);

                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = ticket;
                result = tmp;
            }
        }
        if (result.length > 1) {
            Arrays.sort(result);
        }
        return result;
    }

    public Ticket[] searchAndSortByTravelTime(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] result = new Ticket[0]; // тут будем хранить подошедшие запросу продукты
        for (Ticket ticket : repository.findAll()) {
            if (matches(ticket, from, to)) {
                int length = result.length + 1;
                Ticket[] tmp = new Ticket[length];
                System.arraycopy(result, 0, tmp, 0, result.length);

                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = ticket;
                result = tmp;
            }
        }
        if (result.length > 1) {
            Arrays.sort(result, comparator);
        }
        return result;
    }

    // метод определения соответствия билета запросу отправления и прилёта
    public boolean matches(Ticket ticket, String from, String to) {
        return ticket.getDeparture().equals(from) && ticket.getArrival().equals(to);
    }
}
