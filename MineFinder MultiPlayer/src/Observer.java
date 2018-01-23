public interface Observer {
    
    void revealedField(int x, int y, int value, int player);

    void gameStateChanged(int player, int remainingmines, int redscore, int bluescore);
}