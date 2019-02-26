package io.github.diegofrsilva.team;

public class Team {

    private int id;
    private String name;
    private String logoUrl;

    public Team(int id, String name, String logoUrl) {
        this.id = id;
        this.name = name;
        this.logoUrl = logoUrl;
    }

    public int getId() {
        return id;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getName() {
        return name;
    }
}
