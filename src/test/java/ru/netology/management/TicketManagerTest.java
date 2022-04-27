package ru.netology.management;

import org.junit.jupiter.api.*;
import ru.netology.domain.Repository;
import ru.netology.domain.Ticket;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    private Ticket vdkMsk = new Ticket(1, 15000, "VVO", "SVO", 540);
    private Ticket vdkMskEqual = new Ticket(2, 15000, "VVO", "SVO", 600); // самый долгий
    private Ticket vdkMskExp = new Ticket(3, 20000, "VVO", "SVO", 510); // самый быстрый
    private Ticket mskKgd = new Ticket(4, 4000, "SVO", "KGD", 150);
    private Ticket pkcVdk = new Ticket(5, 13000, "PKC", "VVO", 210);
    private Repository repository;
    private TicketManager manager;
    private TicketByTravelTimeComparator comparator;

    @BeforeEach
    public void setUp() {
        repository = new Repository();
        manager = new TicketManager(repository);

        manager.add(vdkMskExp);
        manager.add(mskKgd);
        manager.add(vdkMskEqual);
        manager.add(pkcVdk);
        manager.add(vdkMsk);
    }

    @Test
    public void shouldSearchAndSortByPrice() {
        Ticket[] expected = {vdkMskEqual, vdkMsk, vdkMskExp};
        Ticket[] actual = manager.searchBy("VVO", "SVO");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortByTravelTime() {
        comparator = new TicketByTravelTimeComparator();

        Ticket[] expected = {mskKgd};
        Ticket[] actual = manager.searchAndSortByTravelTime("SVO", "KGD", comparator);

        assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldSearchAndSortByTravelTimeIfOneResult() {
        comparator = new TicketByTravelTimeComparator();

        Ticket[] expected = {vdkMskExp, vdkMsk, vdkMskEqual};
        Ticket[] actual = manager.searchAndSortByTravelTime("VVO", "SVO", comparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnNoResults() {
        Ticket[] expected = {};
        Ticket[] actual = manager.searchBy("VVO", "KHV");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnOneResult() {
        Ticket[] expected = {};
        Ticket[] actual = manager.searchBy("PKC", "VDK");

        assertArrayEquals(expected, actual);
    }

}