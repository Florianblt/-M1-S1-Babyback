package com.babyfoot.baby.controller;

import com.babyfoot.baby.model.Equipe;
import com.babyfoot.baby.model.Match;
import com.babyfoot.baby.model.Tournoi;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/tournoi")
public class TournoiController {
    private List<Tournoi> tournois = new ArrayList<>();


    TournoiController() {
        this.tournois = buildtournois();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Tournoi> gettournois() {
        return this.tournois;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Tournoi getTournoi(@PathVariable("id") int id) {
        return this.tournois.stream().filter(tournoi -> tournoi.getId() == id).findFirst().orElse(null);
    }

    @RequestMapping(value = "/{id}/init", method = RequestMethod.GET)
    public void initTournoi(@PathVariable("id") int id){
        this.tournois.stream()
            .filter(tournoi -> tournoi.getId() == id)
            .findFirst()
            .orElse(null)
            .initPlayOff();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Tournoi saveTournoi(@RequestBody Tournoi tournoi) {
        int nextId = 0;
        if (this.tournois.size() != 0) {
            Tournoi lastTournoi = this.tournois.stream().skip(this.tournois.size() - 1).findFirst().orElse(null);
            nextId = lastTournoi.getId() + 1;
        }

        tournoi.setId(nextId);
        this.tournois.add(tournoi);
        return tournoi;

    }

    @RequestMapping(method = RequestMethod.PUT)
    public Tournoi updateTournoi(@RequestBody Tournoi tournoi) {
        Tournoi modifiedTournoi = this.tournois.stream().filter(u -> u.getId() == tournoi.getId()).findFirst().orElse(null);
        modifiedTournoi.setNom(tournoi.getNom());
        modifiedTournoi.setEquipes(tournoi.getEquipes());
        modifiedTournoi.setMatchs(tournoi.getMatchs());
        return modifiedTournoi;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteTournoi(@PathVariable Long id) {
        Tournoi deleteTournoi = this.tournois.stream().filter(tournoi -> tournoi.getId() == id).findFirst().orElse(null);
        if (deleteTournoi != null) {
            this.tournois.remove(deleteTournoi);
            return true;
        } else  {
            return false;
        }
    }

    //Mock
    List<Tournoi> buildtournois() {
        List<Tournoi> tournois = new ArrayList<>();

        Tournoi tournoi1 = buildTournoi(1, "Le premier tournoi");
        Tournoi tournoi2 = buildTournoi(2, "Le deuxieme tournoi");
        Tournoi tournoi3 = buildTournoi(3, "Le troisieme tournoi");
        Tournoi tournoi4 = buildTournoi(4, "Le quatrieme tournoi");
        Tournoi tournoi5 = buildTournoi(5, "Le cinquième tournoi");
        Tournoi tournoi6 = buildTournoi(6, "Le sixieme tournoi");
        Tournoi tournoi7 = buildTournoi(7, "Le septieme tournoi");

        tournoi5.inscrireEquipe(new Equipe("L'équipe 1"));
        tournoi5.inscrireEquipe(new Equipe("L'équipe 2"));
        tournoi5.inscrireEquipe(new Equipe("L'équipe 3"));
        tournoi5.inscrireEquipe(new Equipe("L'équipe 4"));
        tournoi5.inscrireEquipe(new Equipe("L'équipe 5"));
        tournoi5.inscrireEquipe(new Equipe("L'équipe 6"));
        tournoi5.inscrireEquipe(new Equipe("L'équipe 7"));
        tournoi5.inscrireEquipe(new Equipe("L'équipe 8"));

        tournois.add(tournoi1);
        tournois.add(tournoi2);
        tournois.add(tournoi3);
        tournois.add(tournoi4);
        tournois.add(tournoi5);
        tournois.add(tournoi6);
        tournois.add(tournoi7);

        return tournois;

    }

    Tournoi buildTournoi(int id, String nom) {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(id);
        tournoi.setNom(nom);
        tournoi.setEquipes(new ArrayList<>());
        tournoi.setMatchs(new ArrayList<>());
        return tournoi;
    }
}