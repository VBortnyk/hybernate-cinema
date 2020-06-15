package com.dev.cinema.controllers;

import com.dev.cinema.dao.MovieDao;
import com.dev.cinema.mapper.MovieMapper;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;
import java.util.List;
import java.util.stream.Collectors;

import com.dev.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping()
    public void add(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.create(movieMapper.convertDtoToMovie(movieRequestDto));
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        List<Movie> movieList = movieService.getALl();
        return movieList.stream()
                .map(m -> movieMapper.convertToResponseDto(m))
                .collect(Collectors.toList());
    }
}
