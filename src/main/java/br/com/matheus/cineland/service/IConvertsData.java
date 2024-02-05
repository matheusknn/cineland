package br.com.matheus.cineland.service;

public interface IConvertsData {
    <T> T getDatas(String json, Class<T> classe);//generics
}
