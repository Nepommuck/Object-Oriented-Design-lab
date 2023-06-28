package pl.agh.edu.dp.abstract_factory;

import pl.agh.edu.dp.maze.Direction;
import pl.agh.edu.dp.maze.sites.enchanted.*;
import pl.agh.edu.dp.maze.sites.standard.*;

public class EnchantedMazeFactory extends MazeFactory {
    private static EnchantedMazeFactory instance = null;

    protected EnchantedMazeFactory() {}

    public static EnchantedMazeFactory getInstance() {
        if (instance == null)
            instance = new EnchantedMazeFactory();
        return instance;
    }
    @Override
    public Room makeRoom(Integer roomNumber) {
        Dimension dimention = new Dimension(roomNumber);
        dimention.setSide(Direction.South, makeWall());
        dimention.setSide(Direction.North, makeWall());
        dimention.setSide(Direction.East, makeWall());
        dimention.setSide(Direction.West, makeWall());

        return dimention;
    }

    @Override
    public Wall makeWall() {
        return new MagicBarrier();
    }

    @Override
    public Door makeDoor(Room room1, Direction direction, Room room2) {
        if (room1.getSide(direction) instanceof Door)
            throw new IllegalArgumentException("Room with number " + room1.getRoomNumber() +
                    "already has doors in direction: " + direction + ".");

        if (room2.getSide(direction.opposite()) instanceof Door)
            throw new IllegalArgumentException("Room with number " + room2.getRoomNumber() +
                    "already has doors in direction: " + direction.opposite() + ".");

        Portal newPortal = new Portal(room1, room2);
        room1.setSide(direction, newPortal);
        room2.setSide(direction.opposite(), newPortal);
        return newPortal;
    }
}
