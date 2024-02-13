package br.com.matheus.cineland.repository;

import br.com.matheus.cineland.domain.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {

}
