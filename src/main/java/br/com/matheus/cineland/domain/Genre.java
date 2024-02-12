package br.com.matheus.cineland.domain;

public enum Genre {
    AÇAO("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIMINAL("Crime");

    private String caregoryOmdb;

    Genre(String caregoryOmdb) {
        this.caregoryOmdb = caregoryOmdb;
    }

    public static Genre fromString(String text) {
        for (Genre genre : Genre.values()) {
            if(genre.caregoryOmdb.equalsIgnoreCase(text)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para essa série");
    }
}
