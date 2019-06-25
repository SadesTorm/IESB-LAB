package com.sadestorm.iesblab;

public class Reclame {

    public String reclame;
    public String matricula;

    public Reclame(String reclame, String matricula) {
        this.reclame = reclame;
        this.matricula = matricula;
    }

    public String getReclame() {
        return reclame;
    }

    public void setReclame(String reclame) {
        this.reclame = reclame;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
