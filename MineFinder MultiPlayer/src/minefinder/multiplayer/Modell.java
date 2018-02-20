package minefinder.multiplayer;

import java.util.Random;

public class Modell {

    int remainingMines = 51;
    int redScore, blueScore = 0;
    int actualPlayer = 0;

    Integer[][] Minefield = new Integer[16][16];
    Boolean[][] MinefieldVisibilyty = new Boolean[16][16];

    Observer observer;

    public Modell(Observer observer) {
        this.observer = observer;
    }

    public void peek(final int x, final int y) {
        if (this.Minefield[x][y] == -1) {
            this.remainingMines--;
            if (this.actualPlayer == 0) {
                this.redScore++;
            } else if (this.actualPlayer == 1) {
                this.blueScore++;
            }
        }

        if (this.actualPlayer == 0) {
            this.actualPlayer = 1;
        } else if (this.actualPlayer == 1) {
            this.actualPlayer = 0;
        }
        observer.gameStateChanged(this.actualPlayer, this.remainingMines, this.redScore, this.blueScore);
    }

    private void startRevealAt(final int x, final int y) {
        if (-2 == valueAt(x, y)) {
            return;
        }
        if (MinefieldVisibilyty[x][y]) {
            return;
        }
        observer.revealedField(x, y, this.Minefield[x][y], this.actualPlayer);
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
        int result = 0;
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.valueAt(i, j) == -1) {
                    result++;
                }
            }
        }
        return result;
    }

    private void generateNumbers() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (this.valueAt(i, j) != -1) {
                    Minefield[i][j] = mineCountAround(i, j);
                }
            }
        }
    }
}
