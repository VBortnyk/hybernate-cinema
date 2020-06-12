package com.dev.cinema.mapper;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;
import com.dev.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    private MovieService movieService;

    @Autowired
    public MovieMapper(MovieService movieService) {
        this.movieService = movieService;
    }

    public MovieResponseDto convertToResponseDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setTitle(movie.getTitle());
        movieResponseDto.setDescription(movie.getDescription());
        return movieResponseDto;
    }

    public Movie convertDtoToMovie(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        Long id = movieService.getByTitle(movieRequestDto.getTitle()).getId();
        movie.setId(id);
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        return movie;
    }
}
