package com.sadestorm.iesblab;

public class Usuario {

    String matricula;
    String nome;
    String telefone;
    String email;
    String funcao;
    String senha;
    String confirma;

    public Usuario(){

        }

    public Usuario(String matricula, String nome, String funcao,String telefone,String email, String confirma) {
        this.matricula = matricula;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.funcao = funcao;
        this.confirma = confirma;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirma() {
        return confirma;
    }

    public void setConfirma(String confirma) {
        this.confirma = confirma;
    }

}
