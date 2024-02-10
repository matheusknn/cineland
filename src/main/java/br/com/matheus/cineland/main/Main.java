package br.com.matheus.cineland.main;

import br.com.matheus.cineland.domain.EpisodeSerieDatas;
import br.com.matheus.cineland.domain.SeasonSerieDatas;
import br.com.matheus.cineland.domain.SerieDatas;
import br.com.matheus.cineland.service.ConsumeApi;
import br.com.matheus.cineland.service.ConvertDatas;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private Scanner scan = new Scanner(System.in);
    private ConsumeApi consumeApi = new ConsumeApi();//instanciando objeto que consome api e retorna json em string
    private ConvertDatas converter = new ConvertDatas();//instanciando conversor de datas para instancia de classes
    private final String ADDRES = "https://www.omdbapi.com/?t=";//final = constante/imutável
    private final String API_KEY = "&apikey=6409843c";

    public void displayMenu() {
        //representando dados da série
        System.out.println("Digite o nome da série");
        String userSerie = scan.nextLine();
        String json = consumeApi.getDatas(ADDRES + userSerie.replace(" ", "+") + API_KEY);
        SerieDatas datasSerie = converter.getDatas(json, SerieDatas.class);
        System.out.println(datasSerie);

		//representando temporada de uma serie
		List<SeasonSerieDatas> seasons = new ArrayList<>();
		for (int i = 1; i <= datasSerie.totalSeasons(); i++) {
			String jsonSeasonSerieDatas = consumeApi.getDatas(ADDRES + userSerie.replace(" ", "+")+"&Season="+i+API_KEY);
			SeasonSerieDatas seasonData = converter.getDatas(jsonSeasonSerieDatas, SeasonSerieDatas.class);
			seasons.add(seasonData);
		}
		seasons.forEach(System.out::println);

//        for (int i = 0; i < datasSerie.totalSeasons(); i++) {
//            List<EpisodeSerieDatas> episodesSeason = seasons.get(i).episodesSerie();
//            System.out.println(episodesSeason);
//            for (int j = 0; j < episodesSeason.size(); j++) {
//                System.out.println(episodesSeason.get(j).title());
//            }
//        }

        seasons.forEach(s -> s.episodesSerie().forEach(e-> System.out.println(e.title())));

//        List<String> nomes = Arrays.asList("Matheus", "Kenji", "Nishimura");
        //stream:
//        nomes.stream()
//                .sorted()//ordenar(ação intermediária), tudo que gera outra stream
//                .limit(2)//pega os 2 primeiros elementos do array
//                .forEach(System.out::println);
//
//        System.out.println(nomes);
        List<EpisodeSerieDatas> episodeSerieDatas = seasons.stream()
                .flatMap(s -> s.episodesSerie().stream())
                .collect(Collectors.toList());
                //ou .toList() --- toList retorna lista imutável

        System.out.println(episodeSerieDatas);

        System.out.println("-----------Top 5 melhores episódios de todas às temporadas------------");
        episodeSerieDatas.stream()
                .filter( e-> !e.rating().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodeSerieDatas::rating).reversed())
                .limit(5)
                .forEach(System.out::println);

    }

}

