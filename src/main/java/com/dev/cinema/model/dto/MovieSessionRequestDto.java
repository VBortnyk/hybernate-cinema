package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MovieSessionRequestDto {
    @NotNull(message = "Movie id can not be null")
    private Long movieId;
    @NotNull(message = "Cinema hall id can not be null")
    private Long hallId;
    @NotNull(message = "Show time can not be null")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}",
            message = "Showtime should match declared pattern")
    private String showTime;

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }
}
