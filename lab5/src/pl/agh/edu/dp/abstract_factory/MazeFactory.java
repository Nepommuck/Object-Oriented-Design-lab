package pl.agh.edu.dp.abstract_factory;

import pl.agh.edu.dp.maze.Direction;
import pl.agh.edu.dp.maze.sites.standard.*;


public class MazeFactory {
    private static MazeFactory instance = null;

    protected MazeFactory() {}

    public static MazeFactory getInstance() {
        if (instance == null)
            instance = new MazeFactory();
        return instance;
    }

    public Room makeRoom(Integer roomNumber) {
        Room room = new Room(roomNumber);
        room.setSide(Direction.South, makeWall());
        room.setSide(Direction.North, makeWall());
        room.setSide(Direction.East, makeWall());
        room.setSide(Direction.West, makeWall());

        return room;
    }

    public Wall makeWall() {
        return new Wall();
    }

    public Door makeDoor(Room room1, Direction direction, Room room2) {
        if (room1.getSide(direction) instanceof Door)
            throw new IllegalArgumentException("Room with number " + room1.getRoomNumber() +
                    "already has doors in direction: " + direction + ".");

        if (room2.getSide(direction.opposite()) instanceof Door)
            throw new IllegalArgumentException("Room with number " + room2.getRoomNumber() +
                    "already has doors in direction: " + direction.opposite() + ".");

        Door newDoor = new Door(room1, room2);
        room1.setSide(direction, newDoor);
        room2.setSide(direction.opposite(), newDoor);
        return newDoor;
    }
}
