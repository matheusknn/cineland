package br.com.matheus.cineland.main;

//import br.com.matheus.cineland.domain.Episode;

import br.com.matheus.cineland.domain.Episode;
import br.com.matheus.cineland.domain.SeasonSerieDatas;
import br.com.matheus.cineland.domain.Serie;
import br.com.matheus.cineland.domain.SerieDatas;
import br.com.matheus.cineland.repository.Repository;
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
    private List<SerieDatas> serieDatas = new ArrayList<>();
    private Repository repository;
    private List<Serie> series = new ArrayList<>();
    public Main(Repository repository) {
        this.repository = repository;
    }

    public void displayMenu() {
        var userOperation = -1;
        while (userOperation != 0) {
            System.out.println("\n-------------DIGITE O NÚMERO DA OPERAÇÃO QUE DESEJA REALIZAR---------------");
            System.out.println("""
                    1 - Buscar Série
                    2 - Buscar Episódio De Uma Série
                    3 - Ver Séries Buscadas
                    4 - Buscar série por título
                    5 - Buscar séries por ator
                                    
                    0 - Sair Do Programa
                    """);
            userOperation = Integer.parseInt(scan.nextLine());

            switch (userOperation) {
                case 1:
                    searchSerie();
                    break;
                case 2:
                    searchEpisodebySerie();
                    break;
                case 3 :
                    displaySeriesSearched();
                    break;
                case 4 :
                    searchSerieByTitle();
                    break;
                case 5 :
                    searchSeriesByActor();
                case 0 :
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida");
            }
        }
    }

    private void searchSerie() {
        SerieDatas serieData = getSerieDatas();
//        serieDatas.add(serieData);
        Serie serie = new Serie(serieData);
        repository.save(serie); //salvando no banco
        System.out.println(serieDatas);
    }

    private SerieDatas getSerieDatas() {
        System.out.println("Digite o nome da série que deseja buscar");
        var userSerie = scan.nextLine();
        var json = consumeApi.getDatas(ADDRES + userSerie.replace(" ", "+")+ API_KEY);
        SerieDatas serieDatas = converter.getDatas(json, SerieDatas.class);
        return serieDatas;
    }

    private void searchEpisodebySerie() {
        displaySeriesSearched();
        System.out.println("Escolha uma série pelo nome");
        var serieName = scan.nextLine();
        List<SeasonSerieDatas>  seasonSerieDatas = new ArrayList<>();

        Optional<Serie> serie = repository.findByTitleContainingIgnoreCase(serieName);

        if(serie.isPresent()) {
            var foundSerie = serie.get();
            List<SerieDatas> seasons = new ArrayList<>();
            for (int i = 1; i <= foundSerie.getTotalSeasons(); i++) {
                var json = consumeApi.getDatas(ADDRES + foundSerie.getTitle().replace(" ", "+") + "&Season=" + i + API_KEY);
                var seasonSerieData = converter.getDatas(json, SeasonSerieDatas.class);
                seasonSerieDatas.add(seasonSerieData);
            }
            seasonSerieDatas.forEach(System.out::println);
            List<Episode> episodes = seasonSerieDatas.stream()
                    .flatMap(sd -> sd.episodesSerie().stream()
                            .map(e -> new Episode(sd.seasonNumber(), e)))
                            .collect(Collectors.toList());

            foundSerie.setEpisodes(episodes);
            repository.save(foundSerie);
        }else {
            System.out.println("Série não encontrada!");
        }
    }

    private void displaySeriesSearched() {
        series = repository.findAll();//puxa od dados do repositório e devolve um lIst genérico do que foi especificado no genérics
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenre))
                .forEach(System.out::println);
    }

    private void searchSerieByTitle() {
        System.out.println("Qual série deseja buscar?");
        var serieName = scan.nextLine();

        Optional<Serie> searchedSerie = repository.findByTitleContainingIgnoreCase(serieName);
        if(searchedSerie.isPresent()) {
            System.out.println("Dados da série: " + searchedSerie.get());
        }else {
            System.out.println("Série não encontrada!");
        }
    }
    private void searchSeriesByActor() {
        System.out.println("Digite o nome do ator para saber às séries que ele participou");
        var actorName = scan.nextLine();
        System.out.println("Deseja que a avaliação da série seja à partir de qual valor? ");
        var rating = Double.parseDouble(scan.nextLine());
        List<Serie> foundSeries = repository.findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(actorName, rating);
        System.out.println("Séries em que " + actorName + " trabalhou: ");
        foundSeries.forEach(s-> System.out.println(s.getTitle() + " avaliação: " + s.getRating()));
    }
}

