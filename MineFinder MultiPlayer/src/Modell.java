
import java.util.Random;

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
        Random rnd = new Random();
        for (int i = 0; i < m;) {
            int x = rnd.nextInt(16);
            int y = rnd.nextInt(16);
            if (Minefield[x][y] != -1) {
                Minefield[x][y] = -1;
                i++;
            }
        }
    }

    private int valueAt(final int x, final int y) {
        if (x < 16 && x > -1 && y < 16 && y > -1) {
            return Minefield[x][y];
        }
        return -2;
    }

    private int mineCountAround(final int x, final int y) {
        for (int i = x-1; i < x+2; i++) {
            for (int j = 0; j < 10; j++) {
                
            }
        }
    }

    private void generateNumbers() {

    }

}
