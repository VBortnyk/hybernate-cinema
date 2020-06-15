package com.dev.cinema.dao;

import com.dev.cinema.model.CinemaHall;
import java.util.List;

public interface CinemaHallDao {

    CinemaHall create(CinemaHall cinemaHall);

    CinemaHall getById(Long hallId);

    List<CinemaHall> getALl();
}
