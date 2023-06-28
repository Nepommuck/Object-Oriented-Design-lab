package pl.agh.edu.dp.abstract_factory;

import pl.agh.edu.dp.maze.Direction;
import pl.agh.edu.dp.maze.sites.bombed.*;
import pl.agh.edu.dp.maze.sites.standard.Room;
import pl.agh.edu.dp.maze.sites.standard.Wall;

public class BombedMazeFactory extends MazeFactory {
    private static BombedMazeFactory instance = null;

    protected BombedMazeFactory() {}

    public static BombedMazeFactory getInstance() {
        if (instance == null)
            instance = new BombedMazeFactory();
        return instance;
    }

    @Override
    public Room makeRoom(Integer roomNumber) {
        BombedRoom room = new BombedRoom(roomNumber);
        room.setSide(Direction.South, makeWall());
        room.setSide(Direction.North, makeWall());
        room.setSide(Direction.East, makeWall());
        room.setSide(Direction.West, makeWall());

        return room;
    }

    @Override
    public Wall makeWall() {
        return new BombedWall();
    }
}
