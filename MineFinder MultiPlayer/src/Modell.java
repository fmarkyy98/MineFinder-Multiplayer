
public class Modell {

    int remainingmines = 51;
    int redscore, bluescore = 0;
    int actualplayer = 0;

    Integer[][] Minefield = new Integer[16][16];
    Boolean[][] MinefieldVisibilyty = new Boolean[16][16];

    Observer observer;

    public Modell(Observer observer) {
        this.observer = observer;
    }

    public void peek(final int x, final int y) {
        if (this.Minefield[x][y] == -1) {
            this.remainingmines--;
            if (this.actualplayer == 0) {
                this.redscore++;
                return;
            }
            this.bluescore++;
            return;
        }
        if (this.actualplayer == 0) {
            this.actualplayer = 1;
            return;
        }
        this.actualplayer = 0;
        observer.gameStateChanged(this.actualplayer, this.remainingmines, this.redscore, this.bluescore);
    }

    private void startRevealAt(final int x, final int y) {
        if (-2 == valueAt(x, y)) {
            return;
        }
        if (MinefieldVisibilyty[x][y]) {
            return;
        }
        observer.revealedField(x, y, this.Minefield[x][y], this.actualplayer);
        if (Minefield[x][y] == 0) {
            for (int i = x - 1; i < x + 2; i++) {
                for (int j = y - 1; j < y + 2; j++) {
                    if (!MinefieldVisibilyty[i][j]) {
                        startRevealAt(i, j);
                    }
                }
            }
        }
    }

    private void generateMines(final int m) {

    }

    private int valueAt(final int x, final int y) {
        return 0;
    }

    private int mineCountAround(final int x, final int y) {
        return 0;
    }

    private void generateNumbers (){
    
    }
    
}
