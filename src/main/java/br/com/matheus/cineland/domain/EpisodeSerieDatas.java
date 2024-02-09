package br.com.matheus.cineland.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeSerieDate(@JsonAlias("Title") String title,
                               @JsonAlias("Episode") Integer episodeNumber,
                               @JsonAlias("imdbRating") String rating,
                               @JsonAlias("Released") String released) { //classe que descre as informações de um episódio de série
}
