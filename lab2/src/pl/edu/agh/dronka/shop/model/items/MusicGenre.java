package pl.edu.agh.dronka.shop.model.items;

public enum MusicGenre {
    POP("Pop"),
    RAP("Rap"),
    ROCK("Rock"),
    BALLAD("Ballada");

    private String displayName;
    MusicGenre(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }

    public static MusicGenre parse(String string) {
        return switch (string) {
            case "POP" -> POP;
            case "RAP" -> RAP;
            case "BALLADA" -> BALLAD;
            case "ROCK" -> ROCK;
            default -> throw new IllegalStateException("Unexpected value: " + string);
        };
    }
}
