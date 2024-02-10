package br.com.matheus.cineland.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonSerieDatas(@JsonAlias("Season") Integer seasonNumber,
                               @JsonAlias("Episodes") List<EpisodeSerieDatas> episodesSerie) {
}
