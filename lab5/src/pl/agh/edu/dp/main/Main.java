package pl.agh.edu.dp.main;

import pl.agh.edu.dp.abstract_factory.*;
import pl.agh.edu.dp.builder.*;
import pl.agh.edu.dp.maze.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.agh.edu.dp.maze.Direction.*;

public class Main {

    public static void main(String[] args) {
//        demonstrate();
        playAGame();
    }

    private static void demonstrate() {
        // Builder
        System.out.println("Builder:");
        StandardMazeBuilder standardMazeBuilder = new StandardMazeBuilder();
        CountingMazeBuilder countingMazeBuilder = new CountingMazeBuilder();

        List<MazeBuilder> builders = new ArrayList<>(Arrays.asList(
                standardMazeBuilder, countingMazeBuilder));

        for (MazeBuilder builder : builders) {
            builder.addRoom(1);
            builder.addRoom(1, South, 2);
        }
        Maze maze = new MazeGame().createMaze(standardMazeBuilder);
        System.out.println(countingMazeBuilder.getCounts());

        // Abstract factory
        System.out.println("\nAbstract factory:");
        maze = new MazeGame().createMaze(MazeFactory.getInstance());
        System.out.println("Number of rooms (standard)  = " + maze.getNumberOfRooms());

        maze = new MazeGame().createMaze(EnchantedMazeFactory.getInstance());
        System.out.println("Number of rooms (enchanted) = " + maze.getNumberOfRooms());

        maze = new MazeGame().createMaze(BombedMazeFactory.getInstance());
        System.out.println("Number of rooms (bombed)    = " + maze.getNumberOfRooms());
    }

    private static void playAGame() {
        StandardMazeBuilder builder = new StandardMazeBuilder();
        builder.addRoom(1);
        builder.addRoom(1, South, 2);
        builder.addBombedRoom(2, East, 3);

        MazeGame game = new MazeGame();
        game.createMaze(builder);
        game.play();
    }
}
