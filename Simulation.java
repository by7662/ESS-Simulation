/*
 * Name: Benson Yan
 * Class: CSCI 331
 * Instructor: Prof.Borrelli
 */

import java.util.*;

public class Simulation {

    private final int popSize;
    private final double percentHawk;
    private final int cost;
    private final int resources;
    private final int numHawk;
    private final int numDove;
    private int alive;
    private final HashMap<Integer, Individual> individual = new HashMap<>();
    private int INTERACTION = 1;

    public Simulation(int popSize, double percentHawk, int resources, int cost) {
        this.popSize = popSize;
        this.percentHawk = percentHawk;
        this.resources = resources;
        this.cost = cost;
        numHawk = (int) (popSize * (percentHawk / 100));
        numDove = popSize - numHawk;
        alive = popSize;
    }

    /**
     * Populating HashMap with an ID as key and Individual class as value
     */
    public void insertIndividual() {
        for (int i = 0; i < numHawk; i++) {
            individual.put(i, new Individual(Strategy.Hawk, false, 0));
        }
        for (int j = numHawk; j < popSize; j++) {
            individual.put(j, new Individual(Strategy.Dove, false, 0));
        }
    }

    /**
     * Helper function for user input "1"
     */
    public void printStats() {
        System.out.println("\nPopulation size: " + popSize);
        System.out.println("Percentage of Hawks: " + ((int) percentHawk) + "%");
        System.out.println("Number of Hawks: " + numHawk);
        System.out.println("\nPercentage of Doves: " + ((int) (100 - percentHawk)) + "%");
        System.out.println("Number of Doves: " + numDove);
        System.out.println("\nEach resource is worth: " + resources);
        System.out.println("Cost of Hawk-Hawk interaction: " + cost + "\n");
        printMenu();
    }

    /**
     * Helper function for user input "2"
     */
    public void displayIndividual() {
        System.out.println(" ");
        for (int i = 0; i < popSize; i++) {
            System.out.println("Individual[" + i + "]=" + individual.get(i).getStrategy() + ":" + individual.get(i).getResources());
        }
        System.out.println("Living: " + alive + "\n");
        printMenu();
    }

    /**
     * Helper function for user input "3"
     */
    public void printSort() {
        System.out.println(" ");
        List<Individual> allIndividuals = new ArrayList<>();
        for (int i = 0; i < popSize; i++) {
            allIndividuals.add(individual.get(i));
        }
        allIndividuals.sort(Collections.reverseOrder());
        for (Individual allIndividual : allIndividuals) {
            System.out.println(allIndividual.getStrategy() + ":" + allIndividual.getResources());
        }
        System.out.println(" ");
        printMenu();
    }

    /**
     * Helper function to generate two IDs in which represent that their strategies are not dead and not duplicated
     *
     * @return ArrayList of two valid IDs
     */
    public ArrayList<Integer> generateTwoID() {
        Random random = new Random();
        ArrayList<Integer> IDs = new ArrayList<>(2);
        int a = random.nextInt(popSize);
        int b = random.nextInt(popSize);
        while (a == b || individual.get(a).isDead() || individual.get(b).isDead()) {
            a = random.nextInt(popSize);
            b = random.nextInt(popSize);
        }
        IDs.add(0, a);
        IDs.add(1, b);
        return IDs;
    }

    /**
     * Execute one interaction.
     */
    public void oneInteraction() {
        if (alive > 1) {
            System.out.print("\n");
            System.out.println("Encounter: " + INTERACTION);
            ArrayList<Integer> IDs = generateTwoID();
            int a = IDs.get(0);
            int b = IDs.get(1);
            Strategy strategy1 = individual.get(a).getStrategy();
            Strategy strategy2 = individual.get(b).getStrategy();
            System.out.println("Individual " + a + ": " + strategy1);
            System.out.println("Individual " + b + ": " + strategy2);
            if (strategy1.equals(Strategy.Hawk) && strategy2.equals(Strategy.Hawk)) {
                System.out.println("Hawk/Hawk: Hawk: " + (resources - cost) + "\tHawk: " + (-cost));
                individual.get(a).setResources(individual.get(a).getResources() + (resources - cost));
                individual.get(b).setResources(individual.get(b).getResources() - cost);
            } else if (strategy1.equals(Strategy.Hawk) && strategy2.equals(Strategy.Dove)) {
                System.out.println("Hawk/Dove: Hawk: +" + resources + "\tDove: + 0");
                individual.get(a).setResources(individual.get(a).getResources() + resources);
            } else if (strategy1.equals(Strategy.Dove) && strategy2.equals(Strategy.Hawk)) {
                System.out.println("Dove/Hawk: Dove: +0" + "\tHawk: +" + resources);
                individual.get(b).setResources(individual.get(b).getResources() + resources);
            } else if (strategy1.equals(Strategy.Dove) && strategy2.equals(Strategy.Dove)) {
                System.out.println("Dove/Dove: Dove: +" + (resources / 2) + "\tDove: +" + resources / 2);
                individual.get(a).setResources(individual.get(a).getResources() + (resources / 2));
                individual.get(b).setResources(individual.get(b).getResources() + (resources / 2));
            }
            if (individual.get(a).getResources() < 0) {
                System.out.println(strategy1 + " one has died!");
                individual.get(a).setDead(true);
                alive -= 1;
            }
            if (individual.get(b).getResources() < 0) {
                System.out.println(strategy2 + " two has died!");
                individual.get(b).setDead(true);
                alive -= 1;
            }
            System.out.println("Individual " + a + ": " + individual.get(a).getResources() + "\tIndividual " + b + ": " + individual.get(b).getResources());
            System.out.println(" ");
            INTERACTION += 1;
        } else {
            System.err.println("Simulation is terminated. Number of alive individuals is less than one.");
            System.exit(0);
        }
    }

    /**
     * Execute Interaction with the given times. This covers default user input "4" and "5"
     *
     * @param interaction number of interaction user inputs
     */
    public void executeInteractions(int interaction) {
        while (interaction > 0) {
            oneInteraction();
            interaction -= 1;
        }
        System.out.println(" ");
        printMenu();
    }

    /**
     * Helper function for user input "6"
     */
    public void nInteraction() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        try {
            int interaction = Integer.parseInt(scanner.nextLine().strip());
            if (interaction > 0) {
                executeInteractions(interaction);
            } else {
                System.out.println("Please enter a positive number of encounter.");
                nInteraction();
            }
        } catch (NumberFormatException mfe) {
            System.out.println("Please try again with an integer input.");
            nInteraction();
        }
    }

    /**
     * Helper function for user input "7"
     */
    public void stepThrough() {
        oneInteraction();
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        while (!scanner.nextLine().strip().equals("Stop")) {
            oneInteraction();
            System.out.print("> ");
        }
        System.out.println(" ");
        printMenu();
    }

    /**
     * Helper function in printing the Menu.
     */
    public void printMenu() {
        String top = "===============MENU=============\n";
        String one = "1 )Starting Stats\n";
        String two = "2 )Display Individuals and Points\n";
        String three = "3 )Display Sorted\n";
        String four = "4 )Have 1000 interactions\n";
        String five = "5 )Have 10000 interactions\n";
        String six = "6 )Have N interactions\n";
        String seven = "7 )Step through interactions \"Stop\" to return to menu\n";
        String eight = "8 )Quit\n";
        String bottom = "================================";
        System.out.println(top + one + two + three + four + five + six + seven + eight + bottom);
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String input = scanner.nextLine().strip();
        while (!input.equals("8")) {
            switch (input) {
                case "1" -> printStats();
                case "2" -> displayIndividual();
                case "3" -> printSort();
                case "4" -> executeInteractions(1000);
                case "5" -> executeInteractions(10000);
                case "6" -> nInteraction();
                case "7" -> stepThrough();
            }
            System.out.print("> ");
            input = scanner.nextLine().strip();
        }
        System.exit(0);
    }


    public static void main(String[] args) {
        Simulation simulation = null;
        if (args.length == 0 || args.length > 4) {
            System.err.println("Usage: ./project02 popSize [percent] [resourceAmt] [costHawk-Hawk]");
            System.exit(0);
        } else if (args.length == 1) {
            simulation = new Simulation(Integer.parseInt(args[0]), 20, 50, 100);
        } else if (args.length == 2) {
            simulation = new Simulation(Integer.parseInt(args[0]), Double.parseDouble(args[1]), 50, 100);
        } else if (args.length == 3) {
            simulation = new Simulation(Integer.parseInt(args[0]), Double.parseDouble(args[1]), Integer.parseInt(args[2]), 100);
        } else {
            simulation = new Simulation(Integer.parseInt(args[0]), Double.parseDouble(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        }
        simulation.insertIndividual();
        simulation.printMenu();
    }
}
