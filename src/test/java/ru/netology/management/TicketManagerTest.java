package ru.netology.management;

import org.junit.jupiter.api.*;
import ru.netology.domain.Repository;
import ru.netology.domain.Ticket;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    private Ticket vdkMsk = new Ticket(1, 15000, "VVO", "SVO", 540);
    private Ticket vdkMskEqual = new Ticket(2, 15000, "VVO", "SVO", 600);
    private Ticket vdkMskExp = new Ticket(3, 20000, "VVO", "SVO", 510);
    private Ticket mskKgd = new Ticket(4, 4000, "SVO", "KGD", 150);
    private Ticket pkcVdk = new Ticket(5, 13000, "PKC", "VVO", 210);
    private Repository repository;
    private TicketManager manager;

    @BeforeEach
    public void setUp() {
        repository = new Repository();
        manager = new TicketManager(repository);

        manager.add(vdkMskExp);
        manager.add(mskKgd);
        manager.add(vdkMsk);
        manager.add(pkcVdk);
        manager.add(vdkMskEqual);
    }

    @Test
    public void shouldSearchAndSort() {
        Ticket[] expected = {vdkMsk, vdkMskEqual, vdkMskExp};
        Ticket[] actual = manager.findAll("VVO", "SVO");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnNoResults() {
        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("VVO", "KHV");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnOneResult() {
        Ticket[] expected = {pkcVdk};
        Ticket[] actual = manager.findAll("PKC", "VVO");

        assertArrayEquals(expected, actual);
    }

}