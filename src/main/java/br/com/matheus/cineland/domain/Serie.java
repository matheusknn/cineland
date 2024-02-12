package br.com.matheus.cineland.domain;

import jakarta.persistence.*;

import java.util.OptionalDouble;

@Entity//indicando que a classe série será uma tabela do banco
@Table(name = "series")//indicando o nome da tabela no banco
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)//indicando que title não pode ser repetido na tabela
    private String title;
    private Integer totalSeasons;
    private Double rating;
    @Enumerated(EnumType.STRING)//dizendo que se trata de um unum
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "title='" + title + '\'' +
                ", totalSeasons=" + totalSeasons +
                ", rating=" + rating +
                ", genre=" + genre +
                ", Actors='" + Actors + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", plot='" + plot + '\'' +
                '}';
    }
}
