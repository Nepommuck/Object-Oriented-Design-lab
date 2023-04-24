package pl.edu.agh.dronka.shop.model.items;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.Item;

import java.time.LocalDate;

public class Music extends Item {
    private final MusicGenre genre;
    private boolean hasVideo;

    public Music() {
        super();
        this.genre = null;
    }
    public Music(String name, Category category, int price, int quantity, MusicGenre genre, boolean containsVideo) {
        super(name, category, price, quantity);
        this.genre = genre;
        this.hasVideo = containsVideo;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public boolean hasVideo() {
        return hasVideo;
    }

    public void setVideo(boolean hasVideo) {
        this.hasVideo = hasVideo;
    }
}
