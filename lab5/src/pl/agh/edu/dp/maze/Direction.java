package pl.agh.edu.dp.maze;

public enum Direction {
    North, East, South, West;

    public Direction opposite() {
        return switch(this) {
            case North -> South;
            case East -> West;
            case South -> North;
            case West -> East;
        };
    }
}