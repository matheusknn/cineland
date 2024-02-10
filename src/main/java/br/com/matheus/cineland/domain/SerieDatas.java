package br.com.matheus.cineland.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//para ignorar propriedades não mapeadas
public record SerieDatas(@JsonAlias("Title") String title, //O @JsonAlias é usado para definir um ou mais apelidos para o nome da propriedade JSON associada ao campo Java.
                         @JsonAlias("totalSeasons") Integer totalSeasons,
                         @JsonAlias("imdbRating") String rating) {
    //@Jsonproperty("") Essa anotação é usada para definir o nome da propriedade JSON que está associada ao campo Java.
}
