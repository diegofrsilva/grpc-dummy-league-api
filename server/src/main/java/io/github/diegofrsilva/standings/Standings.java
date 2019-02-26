package io.github.diegofrsilva.standings;

import io.github.diegofrsilva.team.Team;

public class Standings {

    private Team team;
    private int position;
    private int points;
    private int games;
    private int wins;
    private int losses;
    private int draws;

    public Standings(Team team, int position, int points, int games, int wins, int losses, int draws) {
        this.team = team;
        this.position = position;
        this.points = points;
        this.games = games;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }

    public int getDraws() {
        return draws;
    }

    public int getGames() {
        return games;
    }

    public int getLosses() {
        return losses;
    }

    public int getPoints() {
        return points;
    }

    public int getPosition() {
        return position;
    }

    public int getWins() {
        return wins;
    }

    public Team getTeam() {
        return team;
    }
}
