package pl.javastart.task;

import java.util.Comparator;

public class NameComparator implements Comparator<Player> {
    @Override
    public int compare(Player player1, Player player2) {
        if (player1 == null && player2 == null) {
            return 0;
        }
        if (player1 != null && player2 == null) {
            return - 1;
        }
        if (player1 == null && player2 != null) {
            return 1;
        }
        if (player1.getFirstName() == null && player2.getFirstName() == null) {
            return 0;
        }
        if (player1.getFirstName() == null) {
            return -1;
        }
        return player1.getFirstName().compareTo(player2.getFirstName());
    }
}
