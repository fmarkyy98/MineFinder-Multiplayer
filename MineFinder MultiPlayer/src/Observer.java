
public interface Observer {

    public enum Player {
        Red, Blue
    }
    
    void revealedField(int x, int y, int value, int player);

    void gameStateChanged(Player player, int remainingmines, int redscore, int bluescore);
}
