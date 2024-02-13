package br.com.matheus.cineland.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.ManyToAny;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "episodios")
public class Episode {//classe de negócio
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer seasonEpisode;
    private String title;
    private Integer number;
    private Double rating;
    private LocalDate release;
    @ManyToOne
    private Serie serie;

    public Episode(Integer seasonEpisode, EpisodeSerieDatas episodeDatas) {
        this.seasonEpisode = seasonEpisode;
        this.title = episodeDatas.title();
        this.number = episodeDatas.episodeNumber();
        try {
            this.rating = Double.parseDouble(episodeDatas.rating());
        }catch (NumberFormatException e) {
            this.rating = 0.0;
        }
        try{
            this.release = LocalDate.parse(episodeDatas.release());
        }catch (DateTimeParseException e) {
            this.release = null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
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

}
