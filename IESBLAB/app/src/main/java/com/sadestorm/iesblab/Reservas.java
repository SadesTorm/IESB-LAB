package com.sadestorm.iesblab;

import java.io.Serializable;

public class Reservas implements Serializable {
    public String matricula;
    public String data;
    public String entrada;
    public String lab;
    public String saida;
    public String status;
    public Reservas() {

    }

    public Reservas(String data, String entrada, String lab, String status) {

        this.data = data;
        this.entrada = entrada;
        this.lab = lab;
        this.status = status;
    }

    public Reservas(String matricula, String data, String entrada, String lab, String saida,String status) {
        this.matricula = matricula;
        this.data = data;
        this.entrada = entrada;
        this.lab = lab;
        this.saida = saida;
        this.status = status;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
