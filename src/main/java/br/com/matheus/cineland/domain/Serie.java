package br.com.matheus.cineland.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
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
    private String actors;
    private String posterUrl;
    private String plot;
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episode> episodes = new ArrayList<>();

    public Serie() {}//construtor padrão para recuperar os dados o banco
    public Serie (SerieDatas s) {
        this.title = s.title();
        this.totalSeasons = s.totalSeasons();
        this.rating = OptionalDouble.of(Double.valueOf(s.rating())).orElse(0);
        this.genre = Genre.fromString(s.genres().split(",")[0].trim());
        this.actors = s.actors();
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
        return actors;
    }

    public void setActors(String actors) {
        actors = actors;
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

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        episodes.forEach(e -> e.setSerie(this));
        this.episodes = episodes;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "title='" + title + '\'' +
                ", totalSeasons=" + totalSeasons +
                ", rating=" + rating +
                ", genre=" + genre +
                ", Actors='" + actors + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", plot='" + plot + '\'' +
                ", episodes='" + episodes + '\'' +
                '}';
    }
}
