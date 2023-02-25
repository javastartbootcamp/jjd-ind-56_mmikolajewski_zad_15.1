package pl.javastart.task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TournamentStats {

    private static final String STOP = "stop";
    private static final String FIRSTNAME = "imię";
    private static final String LASTNAME = "nazwisko";
    private static final String SCORE = "wynik";
    private static final int SORT_FIRSTNAME = 1;
    private static final int SORT_LASTNAME = 2;
    private static final int SORT_SCORE = 3;
    private static final int ASCENDING = 1;
    private static final int DESCENDING = 2;

    void run(Scanner scanner) {
        List<Player> playersScore = getPlayersScore(scanner);
        Comparator<Player> comparator = getPlayerComparator(scanner);
        sortData(playersScore, comparator);
        saveDataToFile(playersScore, "stats.csv");

        // tutaj dodaj swoje rozwiązanie
        // użyj przekazanego scannera do wczytywania wartości

    }

    private Comparator<Player> getPlayerComparator(Scanner scanner) {
        Comparator<Player> sortComparator = getSortComparator(scanner);
        Comparator<Player> resultComparator = getPositiveOrNegativeComparator(scanner, sortComparator);
        return resultComparator;
    }

    private Comparator<Player> getPositiveOrNegativeComparator(Scanner scanner, Comparator<Player> sortComparator) {
        System.out.printf("Sortować rosnąco czy malejąco? (%s - rosnąco, %s - malejąco)\n", ASCENDING, DESCENDING);
        Comparator<Player> sortComparator1 = sortComparator;
        int option = inputInteger(scanner);
        switch (option) {
            case ASCENDING:
                sortComparator1 = sortComparator;
                break;
            case DESCENDING:
                sortComparator1 = sortComparator.reversed();
                break;
            default:
                System.out.println("błędne wybór spsoobu sortowania");
                System.exit(0);
        }
        return sortComparator1;
    }

    private void saveDataToFile(List<Player> playersScore, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Player player : playersScore) {
                writer.write(player.toCsv());
                writer.newLine();
            }
            System.out.println("Dane posortowano i zapisano do pliku " + fileName);
        } catch (IOException e) {
            System.out.println("błąd zapisu pliku");
        }

    }

    private void sortData(List<Player> playersScore, Comparator<Player> comparator) {
        Collections.sort(playersScore, comparator);
    }

    private Comparator<Player> getSortComparator(Scanner scanner) {
        Comparator<Player> comparatotor = null;
        System.out.printf("Po jakim parametrze posortować? (%s - %s, %s - %s, %s - %s)\n",
                SORT_FIRSTNAME, FIRSTNAME, SORT_LASTNAME, LASTNAME, SORT_SCORE, SCORE);
        int option = inputIntegerForChooseComparator(scanner);
        switch (option) {
            case SORT_FIRSTNAME:
                comparatotor = new NameComparator();
                break;
            case SORT_LASTNAME:
                comparatotor = new LastNameComparator();
                break;
            case SORT_SCORE:
                comparatotor = new ScoreComparator();
                break;
            default:
        }
        return comparatotor;
    }

    private int inputIntegerForChooseComparator(Scanner scanner) {
        boolean valid = true;
        int option = -1;
        while (valid) {
            try {
                option = scanner.nextInt();
                if (option == SORT_FIRSTNAME || option == SORT_LASTNAME || option == SORT_SCORE) {
                    valid = false;
                } else {
                    System.out.println("błędne wybór spsoobu sortowania, Wprowadz parametr jeszcze raz, 1 , 2 badz 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("błędne wybór spsoobu sortowania, Wprowadz parametr jeszcze raz, 1 , 2 badz 3");
                scanner.nextLine();
            }
        }
        return option;
    }
    
    private List<Player> getPlayersScore(Scanner scanner) {
        List<Player> players = new ArrayList<>();
        String sortOrder = "";
        do {
            System.out.println("Podaj wynik kolejnego gracza (lub stop):");
            sortOrder = scanner.nextLine();
            if (!sortOrder.equalsIgnoreCase(STOP)) {
                splitingAndCreatingPlayer(players, sortOrder);
            }
        } while (!sortOrder.equalsIgnoreCase(STOP));
        return players;
    }

    private static void splitingAndCreatingPlayer(List<Player> players, String sortOrder) {
        try {
            String[] split = sortOrder.split(" ");
            players.add(new Player(split[0], split[1], Integer.parseInt(split[2])));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Przekazana informacja musi składać się z 3 parametrów [imię] [nazwisko] [liczba punktów]");
        }

    }

    int inputInteger(Scanner scanner) {
        boolean valid = true;
        int option = -1;
        while (valid) {
            try {
                option = scanner.nextInt();
                valid = false;
            } catch (InputMismatchException e) {
                System.out.println("Wprowadz parametr jeszcze raz");
                scanner.nextLine();
            }
        }
        return option;
    }
}
