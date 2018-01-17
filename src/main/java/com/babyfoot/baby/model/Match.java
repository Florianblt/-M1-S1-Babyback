package com.babyfoot.baby.model;

public class Match {

    private int id;
    private Equipe equipe1;
    private Equipe equipe2;
    private int scoreEquipe1;
    private int scoreEquipe2;
    private int vainqueur;

    public Match(Equipe equipe1, Equipe equipe2){
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.scoreEquipe1 = 0;
        this.scoreEquipe2 = 0;
        this.vainqueur = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }

    public int getScoreEquipe1() {
        return scoreEquipe1;
    }

    public void setScoreEquipe1(int scoreEquipe1) {
        this.scoreEquipe1 = scoreEquipe1;
    }

    public int getScoreEquipe2() {
        return scoreEquipe2;
    }

    public void setScoreEquipe2(int scoreEquipe2) {
        this.scoreEquipe2 = scoreEquipe2;
    }

    public int getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(int vainqueur) {
        this.vainqueur = vainqueur;
    }

    public void setResult(int sc1, int sc2){
        this.setScoreEquipe1(sc1);
        this.setScoreEquipe2(sc2);
        if(sc1 > sc2)
            this.setVainqueur(1);
        else
            this.setVainqueur(2);
    }
}
