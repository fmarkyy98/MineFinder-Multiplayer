package minefinder.multiplayer;

public interface Observer {
    
    void revealedField(int x, int y, int value, int player);

    void gameStateChanged(int player, int remainingMines, int redScore, int blueScore);
}