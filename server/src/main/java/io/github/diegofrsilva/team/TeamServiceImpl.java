package io.github.diegofrsilva.team;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

import static io.github.diegofrsilva.MockStorage.getAllTeams;

public class TeamServiceImpl extends TeamServiceGrpc.TeamServiceImplBase {


    @Override
    public void getTeams(Empty request, StreamObserver<TeamData> responseObserver) {
        getAllTeams()
                .stream()
                .map(TeamServiceImpl::map)
                .forEach(responseObserver::onNext);

        responseObserver.onCompleted();
    }

    public static TeamData map(Team team) {
        return TeamData.newBuilder()
                .setLogoUrl(team.getLogoUrl())
                .setName(team.getName())
                .build();
    }
}
