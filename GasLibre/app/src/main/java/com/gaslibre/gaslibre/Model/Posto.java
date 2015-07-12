package com.gaslibre.gaslibre.Model;

/**
 * Created by renato on 6/25/15.
 */
public class Posto {
    private int id;
    private String name;
    private String bandeira;
    private float gasolina;
    private float etanol;
    private float diesel;
    private String servico;
    private String endereco;
    private float coordenateX;
    private float coordenateY;
    private String classificacao;

    public Posto() {
    }

    public Posto(int id, float gasolina, float etanol, float diesel, String servico, String endereco, float coordenateX, float coordenateY, String classificacao) {
        this.id = id;
        this.gasolina = gasolina;
        this.etanol = etanol;
        this.diesel = diesel;
        this.servico = servico;
        this.endereco = endereco;
        this.coordenateX = coordenateX;
        this.coordenateY = coordenateY;
        this.classificacao = classificacao;
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

    public float getGasolina() {
        return gasolina;
    }

    public void setGasolina(float gasolina) {
        this.gasolina = gasolina;
    }

    public float getEtanol() {
        return etanol;
    }

    public void setEtanol(float etanol) {
        this.etanol = etanol;
    }

    public float getDiesel() {
        return diesel;
    }

    public void setDiesel(float diesel) {
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

    public float getCoordenateX() {
        return coordenateX;
    }

    public void setCoordenateX(float coordenateX) {
        this.coordenateX = coordenateX;
    }

    public float getCoordenateY() {
        return coordenateY;
    }

    public void setCoordenateY(float coordenateY) {
        this.coordenateY = coordenateY;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }
}
