// test program for working with iterators

import java.util.LinkedList;
import java.util.Scanner;

record Place(String name, int distance) {

    @Override
    public String toString() {
        return String.format("%s (%d))", name, distance);
    }
}

public class Main {

    public static void main(String[] args) {

        LinkedList<Place> linkList = new LinkedList<>();

        addPlace(linkList, new Place("Sydney", 0));
        addPlace(linkList, new Place("Adelaide", 1374));
        addPlace(linkList, new Place("Alice Springs", 2771));
        addPlace(linkList, new Place("Brisbane", 917));
        addPlace(linkList, new Place("Darwin", 3972));
        addPlace(linkList, new Place("Melbourne", 877));
        addPlace(linkList, new Place("Perth", 3923));

        System.out.println(linkList);
        printMenu();

        var iterator = linkList.listIterator();

        boolean quitLop = false;
        boolean forward = true;

        while (!quitLoop) {

            if (!iterator.hasPrevious()) {
                System.out.println("Originating : " + iterator.next());
                forward = true;
            }

            if (!iterator.hasNext()) {
                System.out.println("Final : " + iterator.previous());
                forward = false;
            }
            System.out.println("Enter a Selection: /n");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            switch (input.toUpperCase()) {

                case "F", "FORWARD" -> {
                    System.out.println("User wants to go forward.");
                    if (!forward) {
                        forward = true;
                        if (iterator.hasNext()) {
                            iterator.next();
                        }
                    }
                    if (iterator.hasNext())
                        System.out.println(iterator.next());
                    break;
                }
                case "B", "BACKWARD" -> {
                    System.out.println("User wants to go backward.");

                    if (forward) {
                        forward = false;
                        if (iterator.hasPrevious()) {
                            iterator.previous();
                        }
                    }

                    if (iterator.hasPrevious())
                        System.out.println(iterator.previous());
                    break;
                }
                case "L", "LIST PLACES" -> listPlaces(linkList);
                case "M", "MENU" -> printMenu();
                case "Q", "QUIT" -> quitLoop = true;
            }
        }
    }

    private void listPlaces(LinkedList<Place> places) {
        System.out.println(places);
    }

    private static void printMenu() {
        System.out.println("""
                \nAvailable actions (select word or letter):
                (F)orward
                (B)ackward
                (L)ist Places
                (M)enu
                (Q)uit
                """);

    }

    private static void addPlace(LinkedList<Place> list, Place place) {
        if (list.contains(place)) {
            System.out.println("Found duplicate: " + place);
            return;
        }
        // check that same city doesn't exist in the list (ie. mixed case)
        for (Place p : list) {
            if (p.name().equalsIgnoreCase(place.name())) {
                System.out.println("Found duplicate: " + place);
            }
        }

        int matchedIndex = 0;
        for (var listPlace : list) {
            if (place.distance() < listPlace.distance()) {
                list.add(matchedIndex, place);
                return;
            }
        }
        list.add(place);
    }
}
