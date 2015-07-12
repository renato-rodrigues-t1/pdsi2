package com.gaslibre.gaslibre.Model;

/**
 * Created by renato on 6/25/15.
 */
public class Posto {
    private int id;
    private String name;
    private String bandeira;
    private double gasolina;
    private double etanol;
    private double diesel;
    private String servico;
    private String endereco;
    private double coordenateX;
    private double coordenateY;
    private String classificacao;
    private double distance;

    public Posto() {
    }

    public Posto(int id, String name, String bandeira, double gasolina, double etanol, double diesel, String servico, String endereco, double coordenateX, double coordenateY, String classificacao, double distance) {
        this.id = id;
        this.name = name;
        this.bandeira = bandeira;
        this.gasolina = gasolina;
        this.etanol = etanol;
        this.diesel = diesel;
        this.servico = servico;
        this.endereco = endereco;
        this.coordenateX = coordenateX;
        this.coordenateY = coordenateY;
        this.classificacao = classificacao;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public double getGasolina() {
        return gasolina;
    }

    public void setGasolina(double gasolina) {
        this.gasolina = gasolina;
    }

    public double getEtanol() {
        return etanol;
    }

    public void setEtanol(double etanol) {
        this.etanol = etanol;
    }

    public double getDiesel() {
        return diesel;
    }

    public void setDiesel(double diesel) {
        this.diesel = diesel;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getCoordenateX() {
        return coordenateX;
    }

    public void setCoordenateX(double coordenateX) {
        this.coordenateX = coordenateX;
    }

    public double getCoordenateY() {
        return coordenateY;
    }

    public void setCoordenateY(double coordenateY) {
        this.coordenateY = coordenateY;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
