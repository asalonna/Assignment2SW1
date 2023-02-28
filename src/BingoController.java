import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class BingoController {

    private final String[] mainMenuItems = {"Exit",
            "Play bingo",
            "Set number separator",
            "Create a bingo card",
            "List existing cards",
            "Set bingo card size"};

    /* TODO
          complete constants attached to mainMenuItems
     */
    private final String OPTION_EXIT = " 0: ";
    private final String OPTION_PLAY = " 1: ";
    private final String OPTION_SEPARATOR = " 2: ";
    private final String OPTION_CREATE_CARD = " 3: ";
    private final String OPTION_LIST_CARDS = " 4: ";
    private final String OPTION_SIZE = " 5: ";

    /* TODO
          complete default size of rows / columns as specified in the Defaults class
          create an arraylist of BingoCard cards
          include getters and setters for row / column sizes
     */
    private int currentRowSize = Defaults.DEFAULT_NUMBER_OF_ROWS;
    private int currentColumnSize = Defaults.DEFAULT_NUMBER_OF_COLUMNS;

    /* TODO
          create an ArrayList of BingoCard cards
     */

    ArrayList<BingoCard> cards = new ArrayList<BingoCard>();

    /* TODO
          implement getters and setters for currentRowSize / currentColumnSize
     */
    public int getCurrentRowSize() {
        /* TODO
              change the return from 0 to the appropriate return
     */
        return currentRowSize;
    }

    public void setCurrentRowSize(int currentRowSize) {
       /* TODO
             implement code here
     */
        this.currentRowSize = currentRowSize;
    }

    public int getCurrentColumnSize() {
        /* TODO
              change the return from 0 to the appropriate return
     */
        return currentColumnSize;
    }

    public void setCurrentColumnSize(int currentColumnSize) {
       /* TODO
             implement code here
     */
        this.currentColumnSize = currentColumnSize;
    }

    /* TODO
          add a new BingoCard card
     */
    public void addNewCard(BingoCard card) {
        //implement code here
        cards.add(card);
    }

    /* TODO
          include an appropriate message to the the number of rows as well as the number of columns
     */
    public void setSize() {
        setCurrentRowSize(parseInt(Toolkit.getInputForMessage(
                "Enter the number of rows for the card")));
        setCurrentColumnSize(parseInt(Toolkit.getInputForMessage(
                "Enter the number of columns for the card")));
        System.out.printf("The bingo card size is set to %d rows X %d columns%n",
                getCurrentRowSize(),
                getCurrentColumnSize());
    }

    /* TODO
           ensure that the correct amount of numbers are entered
           print a message that shows the numbers entered using Toolkit.printArray(numbers)
           create, setCardNumbers and add the card as a BingoCard
     */
    public void createCard() {
        /* TODO
              calculate how many numbers are required to be entered based on the number or rows / columns
         */
        int numbersRequired = getCurrentRowSize() * getCurrentColumnSize();

        String[] numbers;

        boolean correctAmountOfNumbersEntered;

        do {
            numbers = Toolkit.getInputForMessage(
                            String.format(
                                    "Enter %d numbers for your card (separated by " +
                                            "'%s')",
                                    numbersRequired,
                                    Defaults.getNumberSeparator()))
                    .trim()
                    .split(Defaults.getNumberSeparator());
        /* TODO
              verify if the correctAmountOfNumbersEntered is true or false dependant on the numbersRequired calculation
         */
            //changes according to calculation inserted here
            if (numbersRequired == numbers.length) {
                correctAmountOfNumbersEntered = true;
            } else {
                correctAmountOfNumbersEntered = false;
            }
        /* TODO
              verify whether the numbers entered is not correct by printing an appropriate message
              verify against the expected output files
         */
            //insert code here
            if (correctAmountOfNumbersEntered == false) {
                System.out.println("Try again: you entered " + numbers.length + " numbers instead of " + numbersRequired);
            }
        } while (!correctAmountOfNumbersEntered);

        /* TODO
              print an appropriate message using ToolKit.printArray() to show the numbers entered
         */
        System.out.println("You entered");
        Toolkit.printArray(numbers);//insert code here
        /* TODO
              create new BingoCard
         */
        BingoCard card1 = new BingoCard(currentRowSize, currentColumnSize);
        /* TODO
              setCardNumbers for the new card
         */
        //insert code here
        card1.setCardNumbers(numbers);
        /* TODO
              add the card to the ArrayList
         */
        //insert code here
        addNewCard(card1);
    }

    /* TODO
         this method goes with printCardAsGrid() seen below
         when option 4 is chosen to list existing cards it prints each card accordingly
         for example, it should show the following
         Card 0 numbers:
         1  2
         3  4 (check with expected output files)
    */
    public void listCards() {
        /* TODO
              insert code here to find all cards to be printed accordingly
         */
        for (int i = 0; i < cards.toArray().length; i++) {
            System.out.printf("Card %2d numbers:%n", i);
            printCardAsGrid(cards.get(i).getCardNumbers());
        }
        /* TODO
              call printCardAsGrid() method here, Hint: use getCardNumbers() when getting cards
         */
    }

    /* TODO
          this is for option 4, list existing cards where all the cards are printed as a grid
          of rows / columns, so numbers 3 4 5 6 will be printed as follows:
          3  4
          5  6
          it is a follow on method from listCards() and ensures that the grid structure is printed
     */
    public void printCardAsGrid(String numbers) {
        //insert code here to print numbers as a grid
        System.out.printf(numbers);
    }

    /* TODO
          use Toolkit.getInputForMessage to enter a new separator
          print a message what the new separator is
     */
    public void setSeparator() {
        String sep = Toolkit.getInputForMessage("Enter the new separator");
        /* TODO
              make use of setNumberSeparator() and getNumberSeparator()
         */
        Defaults.setNumberSeparator(sep);
        System.out.println("Separator is '" + Defaults.getNumberSeparator() + "'");
    }

    /* TODO
         reset all BingoCards using resetMarked (to false)
     */
    public void resetAllCards() {
        //insert code here
        for (int i = 0; i < cards.toArray().length; i++) {
            cards.get(i).resetMarked();
        }
    }

    /* TODO
          mark off a number that was called when it equals one of the numbers on the BingoCard
     */
    public void markNumbers(int number) {
        //insert code here
        for (int i = 0; i < cards.toArray().length; i++) {
            System.out.printf("Checking card %d for %d%n", i, number);
            cards.get(i).markNumber(number);
        }
    }

    /* TODO
          make use of isWinner() to determine who the winner is
          the method should return the index of who the winner is
     */
    public int getWinnerId() {
        //insert code here
        int winner = Defaults.NO_WINNER;
        for (int i = 0; i < cards.toArray().length; i++) {
            cards.get(i).isWinner();
            if (cards.get(i).isWinner() == true) {
                winner = i;
            }
        }
        return winner;
    }

    /* TODO
          please take note that the game will not end until there is a winning sequence
     */
    public void play() {
        System.out.println("Eyes down, look in!");
        resetAllCards();

        boolean weHaveAWinner;
        do {
            markNumbers(parseInt(
                    Toolkit.getInputForMessage("Enter the next number")
                            .trim()));

            int winnerID = getWinnerId();
            weHaveAWinner = winnerID != Defaults.NO_WINNER;
            if (weHaveAWinner)
                System.out.printf("And the winner is card %d%n", winnerID);
        } while (!weHaveAWinner);
    }

    public String getMenu(String[] menuItems) {
    /* TODO
        change this method so it prints a proper numbered menu
        analyse the correct format from the ExpectedOutput files
        menuText is returned
     */
        StringBuilder menuText = new StringBuilder();
        menuText.append(String.format("%s%s%n", OPTION_EXIT, mainMenuItems[0]));
        menuText.append(String.format("%s%s%n", OPTION_PLAY, mainMenuItems[1]));
        menuText.append(String.format("%s%s%n", OPTION_SEPARATOR, mainMenuItems[2]));
        menuText.append(String.format("%s%s%n", OPTION_CREATE_CARD, mainMenuItems[3]));
        menuText.append(String.format("%s%s%n", OPTION_LIST_CARDS, mainMenuItems[4]));
        menuText.append(String.format("%s%s%n", OPTION_SIZE, mainMenuItems[5]));
        //insert code here
        return menuText.toString();
    }

    /* TODO
          complete the menu using switch to call the appropriate method calls
     */
    public void run() {
        boolean finished = false;
        do {
            switch (Toolkit.getInputForMessage(getMenu(mainMenuItems))) {
                //insert code here
                case "0":
                    finished = true;
                    break;
                case "1":
                    play();
                    break;
                case "2":
                    setSeparator();
                    break;
                case "3":
                    createCard();
                    break;
                case "4":
                    listCards();
                    break;
                case "5":
                    setSize();
                    break;
            }
        } while (!finished);
    }
}
