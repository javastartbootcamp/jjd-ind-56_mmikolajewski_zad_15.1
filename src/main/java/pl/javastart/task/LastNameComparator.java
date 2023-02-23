package pl.javastart.task;

import java.util.Comparator;

public class LastNameComparator implements Comparator<Player> {
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
        if (player1.getLastName() == null && player2.getLastName() == null) {
            return 0;
        }
        if (player1.getLastName() == null) {
            return -1;
        }
        return player1.getLastName().compareTo(player2.getLastName());
    }
}
