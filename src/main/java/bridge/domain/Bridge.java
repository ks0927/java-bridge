package bridge.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Bridge {

    private final List<Direction> directions;

    private Bridge(List<Direction> directions) {
        this.directions = directions;
    }

    public static Bridge of(List<String> bridgeMoves) {
        return new Bridge(bridgeMoves.stream()
                .map(n -> Direction.of(n))
                .collect(Collectors.toList()));
    }

    public boolean compare(Direction direction, int round) {
        return directions.get(round - 1).equals(direction);
    }
}
