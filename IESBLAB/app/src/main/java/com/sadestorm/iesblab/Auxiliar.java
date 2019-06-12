package com.sadestorm.iesblab;

public class Auxiliar {

    public int dia ;
    public int mes ;
    public int ano ;
    public int hora;
    public int minuto;
    public String teste;
    public String lab;
    public String os;
    public String andar;
    public String bloco;
    public String memoria;
    public String num_maquina;
    public String processador;

    public Auxiliar( String andar, String bloco,String lab, String memoria, String num_maquina, String os, String processador) {

        this.os = os;
        this.andar = andar;
        this.bloco = bloco;
        this.memoria = memoria;
        this.num_maquina = num_maquina;
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

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getNum_maquina() {
        return num_maquina;
    }

    public void setNum_maquina(String num_maquina) {
        this.num_maquina = num_maquina;
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

    ///// AUXILIAR NA GRAVAÇAO DA RESERVA

    public Auxiliar(int hora, int minuto){
        this.hora = hora;
        this.minuto = minuto;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;

    }

    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }
}
