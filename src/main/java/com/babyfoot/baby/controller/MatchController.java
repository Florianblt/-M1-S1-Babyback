package com.babyfoot.baby.controller;

import com.babyfoot.baby.model.Equipe;
import com.babyfoot.baby.model.Match;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/match")
public class MatchController {
    private List<Match> matchs = new ArrayList<>();


    MatchController() {
        this.matchs = buildmatchs();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Match> getmatchs() {
        return this.matchs;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Match getMatch(@PathVariable("id") Long id) {
        return this.matchs.stream().filter(match -> match.getId() == id).findFirst().orElse(null);
    }

    @RequestMapping(value = "/{id}/{sc1}/{sc2}", method = RequestMethod.GET)
    public void result(@PathVariable("id") int id, @PathVariable("sc1") int sc1, @PathVariable("sc2") int sc2) {
        this.matchs.stream()
            .filter(match -> match.getId() == id)
            .findFirst()
            .orElse(null)
            .setResult(sc1, sc2);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Match saveMatch(@RequestBody Match match) {
        int nextId = 0;
        if (this.matchs.size() != 0) {
            Match lastMatch = this.matchs.stream().skip(this.matchs.size() - 1).findFirst().orElse(null);
            nextId = lastMatch.getId() + 1;
        }

        match.setId(nextId);
        this.matchs.add(match);
        return match;

    }

    @RequestMapping(method = RequestMethod.PUT)
    public Match updateMatch(@RequestBody Match match) {
        Match modifiedMatch = this.matchs.stream().filter(u -> u.getId() == match.getId()).findFirst().orElse(null);
        modifiedMatch.setEquipe1(match.getEquipe1());
        modifiedMatch.setEquipe2(match.getEquipe2());
        modifiedMatch.setScoreEquipe1(match.getScoreEquipe1());
        modifiedMatch.setScoreEquipe2(match.getScoreEquipe2());
        modifiedMatch.setVainqueur(match.getVainqueur());
        return modifiedMatch;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteMatch(@PathVariable Long id) {
        Match deleteMatch = this.matchs.stream().filter(match -> match.getId() == id).findFirst().orElse(null);
        if (deleteMatch != null) {
            this.matchs.remove(deleteMatch);
            return true;
        } else  {
            return false;
        }


    }

    //Mock
    List<Match> buildmatchs() {
        List<Match> matchs = new ArrayList<>();

        Match match1 = buildMatch(1, new Equipe("Première Equipe"), new Equipe("Deuxieme Equipe"));
        Match match2 = buildMatch(2, new Equipe("Première Equipe"), new Equipe("Deuxieme Equipe"));
        Match match3 = buildMatch(3, new Equipe("Première Equipe"), new Equipe("Deuxieme Equipe"));
        Match match4 = buildMatch(4, new Equipe("Première Equipe"), new Equipe("Deuxieme Equipe"));
        Match match5 = buildMatch(5, new Equipe("Première Equipe"), new Equipe("Deuxieme Equipe"));
        Match match6 = buildMatch(6, new Equipe("Première Equipe"), new Equipe("Deuxieme Equipe"));
        Match match7 = buildMatch(7, new Equipe("Première Equipe"), new Equipe("Deuxieme Equipe"));

        matchs.add(match1);
        matchs.add(match2);
        matchs.add(match3);
        matchs.add(match4);
        matchs.add(match5);
        matchs.add(match6);
        matchs.add(match7);

        return matchs;

    }

    Match buildMatch(int id, Equipe equipe1, Equipe equipe2) {
        Match match = new Match(equipe1,equipe2);
        match.setId(id);
        return match;
    }
}