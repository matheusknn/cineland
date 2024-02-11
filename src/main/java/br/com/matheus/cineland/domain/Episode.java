package br.com.matheus.cineland.domain;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {//classe de negócio
    private Integer seasonEpisode;
    private String title;
    private Integer number;
    private Double rating;
    private LocalDate release;

    public Episode(Integer seasonEpisode, EpisodeSerieDatas episodeDatas) {
        this.seasonEpisode = seasonEpisode;
        this.title = episodeDatas.title();
        this.number = episodeDatas.episodeNumber();
        try {
            this.rating = Double.valueOf(episodeDatas.rating());
        }catch (NumberFormatException e) {
            this.rating = 0.0;
        }
        try{
            this.release = LocalDate.parse(episodeDatas.release());
        }catch (DateTimeParseException e) {
            this.release = null;
        }
    }

    @Override
    public String toString() {
        return "Episode{" +
                "seasonEpisode=" + seasonEpisode +
                ", title='" + title + '\'' +
                ", number=" + number +
                ", rating=" + rating +
                ", release=" + release +
                '}';
    }

    public Integer getSeasonEpisode() {
        return seasonEpisode;
    }

    public void setSeasonEpisode(Integer seasonEpisode) {
        this.seasonEpisode = seasonEpisode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getReleased() {
        return release;
    }

    public void setReleased(LocalDate released) {
        this.release = released;
    }
}
