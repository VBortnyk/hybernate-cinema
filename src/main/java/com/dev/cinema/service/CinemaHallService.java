package com.dev.cinema.service;

import com.dev.cinema.model.CinemaHall;
import java.util.List;

public interface CinemaHallService {

    CinemaHall create(CinemaHall cinemaHall);

    CinemaHall getById(Long hallId);

    List<CinemaHall> getAll();
}
