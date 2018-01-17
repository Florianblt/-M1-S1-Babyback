package com.babyfoot.baby.controller;

import com.babyfoot.baby.model.Equipe;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/equipe")
public class EquipeController {
    private List<Equipe> equipes = new ArrayList<>();


    EquipeController() {
        this.equipes = buildequipes();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Equipe> getequipes() {
        return this.equipes;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Equipe getEquipe(@PathVariable("id") Long id) {
        return this.equipes.stream().filter(equipe -> equipe.getId() == id).findFirst().orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Equipe saveEquipe(@RequestBody Equipe equipe) {
        int nextId = 0;
        if (this.equipes.size() != 0) {
            Equipe lastEquipe = this.equipes.stream().skip(this.equipes.size() - 1).findFirst().orElse(null);
            nextId = lastEquipe.getId() + 1;
        }

        equipe.setId(nextId);
        this.equipes.add(equipe);
        return equipe;

    }

    @RequestMapping(method = RequestMethod.PUT)
    public Equipe updateEquipe(@RequestBody Equipe equipe) {
        Equipe modifiedEquipe = this.equipes.stream().filter(u -> u.getId() == equipe.getId()).findFirst().orElse(null);
        modifiedEquipe.setNom(equipe.getNom());
        return modifiedEquipe;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteEquipe(@PathVariable Long id) {
        Equipe deleteEquipe = this.equipes.stream().filter(equipe -> equipe.getId() == id).findFirst().orElse(null);
        if (deleteEquipe != null) {
            this.equipes.remove(deleteEquipe);
            return true;
        } else  {
            return false;
        }


    }

    //Mock
    List<Equipe> buildequipes() {
        List<Equipe> equipes = new ArrayList<>();

        /*Equipe equipe1 = buildEquipe(1, "premiere equipe");
        Equipe equipe2 = buildEquipe(2, "deuxieme equipe");
        Equipe equipe3 = buildEquipe(3, "troisieme equipe");
        Equipe equipe4 = buildEquipe(4, "quatrieme equipe");
        Equipe equipe5 = buildEquipe(5, "cinquieme equipe");
        Equipe equipe6 = buildEquipe(6, "sixieme equipe");
        Equipe equipe7 = buildEquipe(7, "septieme equipe");

        equipes.add(equipe1);
        equipes.add(equipe2);
        equipes.add(equipe3);
        equipes.add(equipe4);
        equipes.add(equipe5);
        equipes.add(equipe6);
        equipes.add(equipe7);*/

        return equipes;

    }

    Equipe buildEquipe(int id, String nom) {
        Equipe equipe = new Equipe(nom);
        equipe.setId(id);
        return equipe;
    }
}