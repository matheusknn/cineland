package br.com.matheus.cineland.service;

public interface IConverDatas {
    <T> T getDatas(String json, Class<T> classe); //método que recebe uma string json, uma classe
    //e tenta transformar esse json em uma classe específica
}
