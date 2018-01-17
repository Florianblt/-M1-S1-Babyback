package com.babyfoot.baby.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Tournoi {

    private int id;
    private String nom;
    private ArrayList<Equipe> equipes;
    private ArrayList<Match> matchs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(ArrayList<Equipe> equipes) {
        this.equipes = equipes;
    }

    public ArrayList<Match> getMatchs() {
        return matchs;
    }

    public void setMatchs(ArrayList<Match> matchs) {
        this.matchs = matchs;
    }

    public void initPlayOff() {
        matchs.clear();
        for (int i = 0; i < equipes.size(); i = i + 2) {
            matchs.add(new Match(equipes.get(i), equipes.get(i+1)));
        }
    }

    public void updateRound() {
        boolean rondesTerminees = true;
        for (Match match : matchs)
            if (match.getScoreEquipe1() != 10 || match.getScoreEquipe2() != 10) {
                rondesTerminees = false;
            }
        if (rondesTerminees) {
            matchs.forEach(match -> {
                if (match.getScoreEquipe1() == 10)
                    equipes.remove(match.getEquipe2());
                else
                    equipes.remove(match.getEquipe1());
            });
            if (equipes.size() > 1)
                this.initPlayOff();
        }
    }

    public void inscrireEquipe(Equipe e){
        equipes.add(e);
    }
}
