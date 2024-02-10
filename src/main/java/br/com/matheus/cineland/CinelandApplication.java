package br.com.matheus.cineland;

import br.com.matheus.cineland.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinelandApplication implements CommandLineRunner { //CommandLinerRunner: permite qye executemos alguma ação logo após a inicialização da nossa aplicação

	public static void main(String[] args) {
		SpringApplication.run(CinelandApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.displayMenu();
	}
}
