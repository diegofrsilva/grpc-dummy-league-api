package io.github.diegofrsilva;

import com.google.protobuf.Empty;
import io.github.diegofrsilva.team.TeamServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static java.lang.String.format;

public class TeamClientImpl {

    private static final Logger logger = Logger.getLogger(TeamClientImpl.class.getName());

    private final ManagedChannel channel;
    private final TeamServiceGrpc.TeamServiceBlockingStub blockingStub;

    private TeamClientImpl(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build());
    }

    TeamClientImpl(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = TeamServiceGrpc.newBlockingStub(channel);
    }

    private void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    private void getTeams() {
        blockingStub.getTeams(Empty.newBuilder().build())
                .forEachRemaining(teamInfo -> logger.info(format("Team: %s", teamInfo)));

    }

    public static void main(String[] args) throws Exception {
        TeamClientImpl client = new TeamClientImpl("localhost", 50051);
        try {
            client.getTeams();
        } finally {
            client.shutdown();
        }
    }
}
