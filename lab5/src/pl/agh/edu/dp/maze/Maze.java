package pl.agh.edu.dp.maze;

import pl.agh.edu.dp.maze.sites.standard.Room;

import java.util.List;

public class Maze {
//    private Vector<Room> rooms;
    private final List<Room> rooms;

    public Maze(List<Room> rooms) {
        this.rooms = rooms;
    }
    public int getNumberOfRooms()
    {
        return rooms.size();
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
