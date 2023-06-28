package pl.agh.edu.dp.builder;

import pl.agh.edu.dp.maze.Direction;

public interface MazeBuilder {
    public void reset();
    public void addRoom(Integer roomNumber);
    public void addRoom(Integer existingRoomNumber, Direction direction, Integer newRoomNumber);
}
