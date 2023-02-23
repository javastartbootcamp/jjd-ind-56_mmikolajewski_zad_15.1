package pl.javastart.task;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Player> {
    @Override
    public int compare(Player player1, Player player2) {
        if (player1 == null && player2 == null) {
            return 0;
        }
        if (player1 != null && player2 == null) {
            return -1;
        }
        if (player1 == null && player2 != null) {
            return 1;
        }
        return player1.getScore().compareTo(player2.getScore());
    }
}
