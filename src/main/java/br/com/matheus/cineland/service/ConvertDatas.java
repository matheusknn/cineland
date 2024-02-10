package br.com.matheus.cineland.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertDatas implements IConverDatas{
    //para converter dados no jackson usamos a classe Object Mapper
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getDatas(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);//método readValue lê o json e tenta transformar em uma classe
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
