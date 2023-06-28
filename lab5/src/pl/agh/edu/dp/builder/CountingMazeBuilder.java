package pl.agh.edu.dp.builder;

import pl.agh.edu.dp.maze.Direction;

import java.util.HashMap;
import java.util.Map;

public class CountingMazeBuilder implements MazeBuilder {
    private int numberOfRooms = 0;
    private int numberOfDoors = 0;
    private int numberOfWalls = 0;
    @Override
    public void reset() {
        numberOfRooms = 0;
        numberOfDoors = 0;
        numberOfWalls = 0;
    }

    @Override
    public void addRoom(Integer roomNumber) {
        numberOfRooms++;
        numberOfWalls += 4;
    }

    @Override
    public void addRoom(Integer existingRoomNumber, Direction direction, Integer newRoomNumber) {
        // Existing room will have one wall less
        numberOfWalls--;

        numberOfRooms++;
        numberOfDoors++;
        numberOfWalls += 3;
    }

    public Map<String, Integer> getCounts() {
        Map<String, Integer> result = new HashMap<>();
        result.put("numberOfRooms", numberOfRooms);
        result.put("numberOfDoors", numberOfDoors);
        result.put("numberOfWalls", numberOfWalls);

        return result;
    }
}
