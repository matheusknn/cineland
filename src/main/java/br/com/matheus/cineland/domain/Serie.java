package br.com.matheus.cineland.domain;

import java.util.OptionalDouble;

public class Serie {
    private String title;
    private Integer totalSeasons;
    private Double rating;
    private Genre  genre;
    private String Actors;
    private String posterUrl;
    private String plot;

    public Serie (SerieDatas s) {
        this.title = s.title();
        this.totalSeasons = s.totalSeasons();
        this.rating = OptionalDouble.of(Double.valueOf(s.rating())).orElse(0);
        this.genre = Genre.fromString(s.genres().split(",")[0].trim());
        this.Actors = s.actors();
        this.posterUrl = s.poster();
        this.plot = s.plot();
    }
}
