package com.sadestorm.iesblab;

public class Auxiliar {

    public int dia ;
    public int mes ;
    public int ano ;
    public String lab;
    public String os;
    public String andar;
    public String bloco;
    public int memoria;
    public int qtdMaquinas;
    public String processador;

    public Auxiliar( String andar, String bloco,String lab, int memoria, int qtdMaquinas, String os, String processador) {

        this.os = os;
        this.andar = andar;
        this.bloco = bloco;
        this.memoria = memoria;
        this.qtdMaquinas = qtdMaquinas;
        this.processador = processador;
        this.lab = lab;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    public int getQtdMaquinas() {
        return qtdMaquinas;
    }

    public void setQtdMaquinas(int qtdMaquinas) {
        this.qtdMaquinas = qtdMaquinas;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Auxiliar() {

    }

    public Auxiliar(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
