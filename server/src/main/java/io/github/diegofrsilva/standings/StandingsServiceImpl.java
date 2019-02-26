package io.github.diegofrsilva.standings;

import com.google.protobuf.Empty;
import io.github.diegofrsilva.MockStorage;
import io.github.diegofrsilva.league.StandingsByTeamNamePayload;
import io.github.diegofrsilva.league.StandingsServiceGrpc;
import io.github.diegofrsilva.league.TeamStandingData;
import io.github.diegofrsilva.team.TeamServiceImpl;
import io.grpc.stub.StreamObserver;

public class StandingsServiceImpl extends StandingsServiceGrpc.StandingsServiceImplBase {

    @Override
    public void getAll(Empty request, StreamObserver<TeamStandingData> responseObserver) {
        MockStorage.getAllStandings()
                .stream()
                .map(this::map)
                .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public void getByTeamName(StandingsByTeamNamePayload request, StreamObserver<TeamStandingData> responseObserver) {
        super.getByTeamName(request, responseObserver);
    }

    private TeamStandingData map(Standings standings) {
        return TeamStandingData.newBuilder()
                .setPoints(standings.getPoints())
                .setDraws(standings.getDraws())
                .setGames(standings.getGames())
                .setLosses(standings.getLosses())
                .setPosition(standings.getPosition())
                .setWins(standings.getWins())
                .setTeam(TeamServiceImpl.map(standings.getTeam()))
                .build();
    }
}
