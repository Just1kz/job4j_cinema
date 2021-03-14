package ru.job4j.cinema.service;

import ru.job4j.cinema.persistence.Account;
import ru.job4j.cinema.persistence.Place;
import java.util.Collection;
import java.util.List;

public interface CinemaService extends AutoCloseable {

    void buy(Account account, int id);

    List<Place> findAllPlaces();

    public Place findByIdPlace(int id);
}
