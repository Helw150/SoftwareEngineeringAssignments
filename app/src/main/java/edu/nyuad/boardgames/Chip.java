package edu.nyuad.boardgames;

public enum Chip {
    EMPTY,
    RED,
    BLUE;

    public boolean isEmpty() {
        return equals(Chip.EMPTY);
    }
}
