package br.com.matheus.cineland.main;

//import br.com.matheus.cineland.domain.Episode;

import br.com.matheus.cineland.domain.SeasonSerieDatas;
import br.com.matheus.cineland.domain.Serie;
import br.com.matheus.cineland.domain.SerieDatas;
import br.com.matheus.cineland.service.ConsumeApi;
import br.com.matheus.cineland.service.ConvertDatas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner scan = new Scanner(System.in);
    private ConsumeApi consumeApi = new ConsumeApi();//instanciando objeto que consome api e retorna json em string
    private ConvertDatas converter = new ConvertDatas();//instanciando conversor de datas para instancia de classes
    private final String ADDRES = "https://www.omdbapi.com/?t=";//final = constante/imutável
    private final String API_KEY = "&apikey=6409843c";
    private List<SerieDatas> serieDatas = new ArrayList<>();

    public void displayMenu() {
        var userOperation = -1;
        while (userOperation != 0) {
            System.out.println("\n-------------DIGITE O NÚMERO DA OPERAÇÃO QUE DESEJA REALIZAR---------------");
            System.out.println("""
                    1 - Buscar Série
                    2 - Buscar Episódio De Uma Série
                    3 - Ver Séries Buscadas
                                    
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
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida");
            }
        }
    }

    private void searchSerie() {
        SerieDatas serieData = getSerieDatas();
        serieDatas.add(serieData);
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
        SerieDatas serieDatas = getSerieDatas();
        List<SeasonSerieDatas>  seasonSerieDatas = new ArrayList<>();

        for (int i = 1; i <= serieDatas.totalSeasons(); i++) {
            var json = consumeApi.getDatas(ADDRES + serieDatas.title().replace(" ", "+") + "&Season=" + i + API_KEY);
            var seasonSerieData = converter.getDatas(json, SeasonSerieDatas.class);
            seasonSerieDatas.add(seasonSerieData);
        }
        seasonSerieDatas.forEach(System.out::println);
    }

    private void displaySeriesSearched() {
        List<Serie> series = new ArrayList<>();
        series = serieDatas.stream()
                        .map(ds -> new Serie(ds))
                                .collect(Collectors.toList());
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenre))
                .forEach(System.out::println);
    }
}

