package pl.agh.edu.dp.maze;

import pl.agh.edu.dp.maze.sites.standard.Room;

public class Player {
    private Room occupiedRoom;
    private int hp = 3;

    public Player() {
        this(null);
    }
    public Player(Room startingRoom) {
        this.occupiedRoom = startingRoom;
    }

    public void moveTo(Room newRoom) {
        this.occupiedRoom = newRoom;
    }

    public void decreaseHp(int amount) {
        hp -= amount;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int getHp() {
        return hp;
    }

    public Room getOccupiedRoom() {
        return occupiedRoom;
    }
}
