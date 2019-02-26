package io.github.diegofrsilva;

import com.google.protobuf.Empty;
import io.github.diegofrsilva.league.StandingsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static java.lang.String.format;

public class StandingsClientImpl {

    private static final Logger logger = Logger.getLogger(StandingsClientImpl.class.getName());

    private final ManagedChannel channel;
    private final StandingsServiceGrpc.StandingsServiceBlockingStub blockingStub;

    private StandingsClientImpl(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build());
    }

    StandingsClientImpl(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = StandingsServiceGrpc.newBlockingStub(channel);
    }

    private void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    private void getAll() {
        blockingStub.getAll(Empty.newBuilder().build())
                .forEachRemaining(standingData -> logger.info(format("Data: %s", standingData)));

    }

    public static void main(String[] args) throws Exception {
        StandingsClientImpl client = new StandingsClientImpl("localhost", 50051);
        try {
            client.getAll();
        } finally {
            client.shutdown();
        }
    }
}
