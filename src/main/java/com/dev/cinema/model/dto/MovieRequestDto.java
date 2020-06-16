package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MovieRequestDto {
    @NotNull(message = "Movie title can not be null")
    private String title;
    @NotNull(message = "Movie description can not be null")
    @Size(min = 10, max = 200, message = "Movie description should contain 10 - 200 symbols")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
