package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.CinemaHall;
import java.util.List;

public interface CinemaHallService {

    CinemaHall create(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
