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

    public Ticket[] findAll(String from, String to) {
        Ticket[] result = search(from, to);
        if (result.length > 1) {
            Arrays.sort(result); // стандартная сортировка по цене
        }
        return result;
    }

    public Ticket[] findAll(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] result = search(from, to);
        if (result.length > 1) {
            Arrays.sort(result, comparator); // сортировка по времени в пути при помощи компаратора
        }
        return result;
    }

    // метод определения соответствия билета запросу отправления и прилёта
    public boolean matches(Ticket ticket, String from, String to) {
        return ticket.getDeparture().equals(from) && ticket.getArrival().equals(to);
    }

    // логика поиска вынесена в отдельный метод
    public Ticket[] search(String from, String to) {
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
        return result;
    }
}
