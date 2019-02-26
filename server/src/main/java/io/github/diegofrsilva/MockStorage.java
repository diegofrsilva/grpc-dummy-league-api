package io.github.diegofrsilva;

import io.github.diegofrsilva.standings.Standings;
import io.github.diegofrsilva.team.Team;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class MockStorage {

    private static final int TEAMS_COUNT = 20;

    public static List<Team> getAllTeams() {
        Team corinthians = new Team(1, "Corinthians", "https://upload.wikimedia.org/wikipedia/en/thumb/5/5a/Sport_Club_Corinthians_Paulista_crest.svg/400px-Sport_Club_Corinthians_Paulista_crest.svg.png");

        Stream<Team> randomGenerated = IntStream
                .rangeClosed(2, TEAMS_COUNT)
                .mapToObj(index -> new Team(index, format("Team %d", index), format("https://dummyUrl%d", index)));

        return Stream.concat(Stream.of(corinthians), randomGenerated).collect(toList());
    }

    public static List<Standings> getAllStandings() {
        return getAllTeams()
                .stream()
                .map(MockStorage::generateRandomStandings)
                .collect(toList());
    }

    private static Standings generateRandomStandings(Team team) {
        int position = team.getId();
        int wins = TEAMS_COUNT - position;
        int games = TEAMS_COUNT - 1;
        int losses = games - wins;
        int draws = 0;
        int points = (wins * 3) + (draws);

        return new Standings(team, position, points, games, wins, losses, draws);
    }
}
