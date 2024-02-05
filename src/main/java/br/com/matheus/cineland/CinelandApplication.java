package br.com.matheus.cineland;

import br.com.matheus.cineland.model.SeriesData;
import br.com.matheus.cineland.service.ConsumeApi;
import br.com.matheus.cineland.service.ConvertsData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinelandApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CinelandApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		var consumeApi = new ConsumeApi();
		var json = consumeApi.getData("https://www.omdbapi.com/?t=gilmore%20+%20girls&apikey=6409843c");
		System.out.println(json);
		ConvertsData converter = new ConvertsData();
		SeriesData datas = converter.getDatas(json, SeriesData.class);
		System.out.println(datas);
	}
}
