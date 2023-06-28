package pl.agh.edu.dp.maze;

import pl.agh.edu.dp.abstract_factory.MazeFactory;
import pl.agh.edu.dp.builder.StandardMazeBuilder;
import pl.agh.edu.dp.maze.sites.MapSite;
import pl.agh.edu.dp.maze.sites.bombed.BombedRoom;
import pl.agh.edu.dp.maze.sites.bombed.BombedWall;
import pl.agh.edu.dp.maze.sites.enchanted.Portal;
import pl.agh.edu.dp.maze.sites.standard.Door;
import pl.agh.edu.dp.maze.sites.standard.Room;
import pl.agh.edu.dp.maze.sites.standard.Wall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MazeGame {
    private Player player;
    private Maze maze;

    public Maze createMaze(StandardMazeBuilder builder) {
        maze = builder.build();
        return maze;
    }

    public Maze createMaze(MazeFactory factory){
        Room room1 = factory.makeRoom(1);
        Room room2 = factory.makeRoom(2);
        factory.makeDoor(room1, Direction.South, room2);

        List<Room> rooms = new ArrayList<>(Arrays.asList(room1, room2));
        maze = new Maze(rooms);
        return maze;
    }

    public void play() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        player = new Player(maze.getRooms().get(0));

        System.out.println("Welcome to the maze game!");
        System.out.println("Your HP: " + player.getHp());
        System.out.println();

        while (player.isAlive()) {
            Direction moveDirection = null;
            while (moveDirection == null) {
                System.out.print("Choose direction: ");
                try {
                    moveDirection = DirectionParser.parse(consoleReader.readLine());
                } catch (IOException e) {
                    System.out.println("IOException encountered.");
                }
                if (moveDirection == null)
                    System.out.println("Try one of the following: 'N', 'S', 'W', 'E'. \n");
            }

            MapSite siteAtDirection = player.getOccupiedRoom().getSide(moveDirection);
            if (siteAtDirection instanceof Wall) {
                System.out.println("Unable to move in this direction.");
            }
            if (siteAtDirection instanceof BombedWall) {
                System.out.println("Bomb explodes at your face.");
                decreasePlayerHp(1);
                if (!player.isAlive())
                    break;
            }

            if (siteAtDirection instanceof Portal) {
                System.out.println("*TELEPORTATION*");
            }
            if (siteAtDirection instanceof Door) {
                Room newRoom = ((Door) siteAtDirection).getRoomOtherThan(player.getOccupiedRoom());
                player.moveTo(newRoom);
                System.out.println("Successfully moved to another room.");

                if (newRoom instanceof BombedRoom) {
                    System.out.println("You stepped upon a mine.");
                    decreasePlayerHp(2);
                    if (!player.isAlive())
                        break;
                }
            }
            System.out.println();
        }
        System.out.println("Game over :/");
    }

    public void decreasePlayerHp(int amount) {
        player.decreaseHp(amount);
        System.out.println("Your HP decreased by " + amount + ".");
        if (player.isAlive())
            System.out.println("You have " + player.getHp() + " HP left.");
        else
            System.out.println("Your free trial of life has expired.");
    }
}
