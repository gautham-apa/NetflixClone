package com.hari.netflix.dto;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Setter
@Component
public class VideoDataRequestDto {
    @Nullable
    String genre;
    @Nullable
    Integer year;
    @Nullable
    Integer page;

    public Integer getPage() {
        return page;
    }

    public Integer getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }
}
