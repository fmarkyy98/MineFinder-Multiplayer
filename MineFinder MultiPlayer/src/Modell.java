
public class Modell implements Observer {

    int remainingmines = 51;
    int redscore, bluescore = 0;
    Player actualplayer = Player.Red;

    Integer[][] Minefield = new Integer[16][16];
    Boolean[][] MinefieldVisibilyty = new Boolean[16][16];

    @Override
    public void revealedField(int x, int y, int value, int player) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void gameStateChanged(Player player, int remainingmines, int redscore, int bluescore) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void peek(final int x, final int y) {
        if (this.Minefield[x][y] == -1) {
            this.remainingmines--;
            if (this.actualplayer == Player.Red) {
                this.redscore++;
                return;
            }
            this.bluescore++;
            return;
        }
        if (this.actualplayer == Player.Red) {
            this.actualplayer = Player.Blue;
            return;
        }
        this.actualplayer = Player.Red;
        gameStateChanged(this.actualplayer, this.remainingmines, this.redscore, this.bluescore);
    }

    private void startRevealAt(final int x, final int y) {
        
    }

}
