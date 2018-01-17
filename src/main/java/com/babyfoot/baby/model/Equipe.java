package com.babyfoot.baby.model;

public class Equipe {

    private int id;
    private String nom;


    public Equipe(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String name) {
        this.nom = name;
    }
}
