package ru.netology.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    private Ticket vdkMsk = new Ticket(1, 15000, "VVO", "SVO", 540);
    private Ticket vdkSpb = new Ticket(2, 20000, "VVO", "LED", 780);
    private Ticket vdkHbk = new Ticket(3, 6000, "VVO", "KHV", 90);
    private Repository repository;

    @BeforeEach
    public void setUp() {
        repository = new Repository();
    }

    @Test
    public void shouldAddIfNoItems() {
        repository.add(vdkMsk);

        Ticket[] expected = {vdkMsk};
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddIfContainsItems() {
        repository.add(vdkMsk);
        repository.add(vdkSpb);

        Ticket[] expected = {vdkMsk, vdkSpb};
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    // проверяет генерацию AlreadyExistsException при попытке добавить элемент с повторяющимся id
    @Test
    public void shouldThrowExceptionWhileAddingDuplicateIds() {
        repository.add(vdkMsk);

        assertThrows(AlreadyExistsException.class, () -> {
            repository.add(vdkMsk);
        });
    }

    // проверка успешности удаления существующего элемента
    @Test
    public void shouldRemoveById() {
        repository.add(vdkMsk);
        repository.add(vdkSpb);
        repository.add(vdkHbk);
        repository.removeById(2);

        Ticket[] expected = {vdkMsk, vdkHbk};
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIdIfContainsOneItem() {
        repository.add(vdkSpb);
        repository.removeById(2);

        Ticket[] expected = {};
        Ticket[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    // проверка генерации NotFoundException при попытке удаления несуществующего элемента
    @Test
    public void shouldThrowExceptionIfNoId() {
        repository.add(vdkSpb);

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(50);
        });
    }
}