package br.com.matheus.cineland;

import br.com.matheus.cineland.main.Main;
import br.com.matheus.cineland.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinelandApplication implements CommandLineRunner { //CommandLinerRunner: permite qye executemos alguma ação logo após a inicialização da nossa aplicação
	@Autowired//pra dizer que se trata de uma injeção de dependência
	private SerieRepository serieRepository;
	public static void main(String[] args) {
		SpringApplication.run(CinelandApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(serieRepository);
		main.displayMenu();
	}
}
