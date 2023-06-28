package pl.agh.edu.dp.builder;

import pl.agh.edu.dp.maze.*;
import pl.agh.edu.dp.maze.sites.bombed.BombedRoom;
import pl.agh.edu.dp.maze.sites.bombed.BombedWall;
import pl.agh.edu.dp.maze.sites.standard.Door;
import pl.agh.edu.dp.maze.sites.standard.Room;
import pl.agh.edu.dp.maze.sites.standard.Wall;

import java.util.ArrayList;
import java.util.HashMap;

public class StandardMazeBuilder implements MazeBuilder {
    private HashMap<Integer, Room> rooms = new HashMap<>();

    private Room makeRoom(Integer roomNumber) {
        Room room = new Room(roomNumber);
        for (Direction direction : Direction.values())
            room.setSide(direction, new Wall());
        return room;
    }
    private BombedRoom makeBombedRoom(Integer roomNumber) {
        BombedRoom room = new BombedRoom(roomNumber);
        for (Direction direction : Direction.values())
            room.setSide(direction, new BombedWall());
        return room;
    }

    @Override
    public void reset() {
        rooms = new HashMap<>();
    }

    @Override
    public void addRoom(Integer roomNumber) {
        if (rooms.containsKey(roomNumber))
            throw new IllegalArgumentException("Room with number " + roomNumber + "already exists.");
        rooms.put(roomNumber, makeRoom(roomNumber));
    }

    @Override
    public void addRoom(Integer existingRoomNumber, Direction direction, Integer newRoomNumber) {
        addRoom(existingRoomNumber, direction, newRoomNumber, makeRoom(newRoomNumber));
    }
    public void addBombedRoom(Integer existingRoomNumber, Direction direction, Integer newRoomNumber) {
        addRoom(existingRoomNumber, direction, newRoomNumber, makeBombedRoom(newRoomNumber));
    }
    private void addRoom(Integer existingRoomNumber, Direction direction, Integer newRoomNumber, Room newRoom) {
        if (!rooms.containsKey(existingRoomNumber))
            throw new IllegalArgumentException("Room with number " + existingRoomNumber + "doesn't exist.");
        if (rooms.containsKey(newRoomNumber))
            throw new IllegalArgumentException("Room with number " + newRoomNumber + "already exists.");

        Room existingRoom = rooms.get(existingRoomNumber);

        if (existingRoom.getSide(direction) instanceof Door)
            throw new IllegalArgumentException("Room with number " + existingRoomNumber +
                    "already has doors in direction: " + direction + ".");

        Door newDoor = new Door(existingRoom, newRoom);

        existingRoom.setSide(direction, newDoor);
        newRoom.setSide(direction.opposite(), newDoor);

        rooms.put(newRoomNumber, newRoom);
    }

    public Maze build() {
        return new Maze(new ArrayList<>(rooms.values()));
    }
}
