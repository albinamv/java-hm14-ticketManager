package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Repository {
    private Ticket[] tickets = new Ticket[0];

    public Ticket[] findAll() {
        return tickets;
    }

    public void add(Ticket ticket) {
        if (findById(ticket.getId()) != null) {
            throw new AlreadyExistsException("Нельзя добавить товар с id " + ticket.getId() + ", т.к. такой id уже существует");
        } else {
            int length = tickets.length + 1;
            Ticket[] tmp = new Ticket[length];
            System.arraycopy(tickets, 0, tmp, 0, tickets.length);

            int lastIndex = tmp.length - 1;
            tmp[lastIndex] = ticket;
            tickets = tmp;
        }
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Не найден товар с id " + id);
        } else {
            int length = tickets.length - 1;
            Ticket[] tmp = new Ticket[length];
            int index = 0;
            for (Ticket product : tickets) {
                if (product.getId() != id) {
                    tmp[index] = product;
                    index++;
                }
            }
            tickets = tmp;
        }
    }

    public Ticket findById(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }
}
