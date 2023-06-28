package pl.agh.edu.dp.maze;

public class DirectionParser {
    private DirectionParser() {}

    public static Direction parse(String input) {
        return switch (input.trim().toUpperCase()) {
            case "S", "SOUTH" -> Direction.South;
            case "N", "NORTH" -> Direction.North;
            case "E", "EAST" -> Direction.East;
            case "W", "WEST" -> Direction.West;
            default -> null;
        };
    }
}
