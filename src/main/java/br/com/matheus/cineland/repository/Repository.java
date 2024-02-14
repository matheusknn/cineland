package br.com.matheus.cineland.repository;

import br.com.matheus.cineland.domain.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Repository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTitleContainingIgnoreCase(String serieTitle);
    List<Serie> findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(String actorName, Double rating);
}
