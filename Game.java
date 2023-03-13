/** 
 *  Welcome to the World of Zuul!  This text adventure game has users 
 *  walking around some scenery, which is represented in the form
 *  of an empty university!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Salvatore Anzalone
 * @version 3/13/2023
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Print out a list of valid command words,
     * like in the Parser class.
     */
    public void showCommands()
    {
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office, bathroom,
            lounge, library, boiler, bookstore, coffee, observatory,
            gymnasium, study, empty;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        bathroom = new Room("in a bathroom");
        lounge = new Room("in the student lounge");
        library = new Room("in the library");
        boiler = new Room("in a boiler room.  Janitors are allowed in " +
                            "here, so make it quick!");
        bookstore = new Room("in the bookstore");
        coffee = new Room("in a coffee shop.  You can make something " +
                            "to drink, if you want to.");
        observatory = new Room("in the observatory.  Great for " +
                                "viewing projections of stars, and " +
                                "much more!");
        gymnasium = new Room("in the gymnasium");
        study = new Room("in a study room.  This is meant for " +
                            "test taking and preparation.");
        empty = new Room("in an empty room.  Was it going " +
                            "to be a standard classroom?");
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        
        bathroom.setExit("east", theater);
        
        lounge.setExit("west", outside);
        lounge.setExit("east", empty);
        lounge.setExit("south", study);
        
        library.setExit("south", office);
        library.setExit("north", outside);
        library.setExit("east", pub);
        
        boiler.setExit("west", lab);
        
        bookstore.setExit("east", library);
        bookstore.setExit("west", coffee);
        
        coffee.setExit("east", bookstore);
        coffee.setExit("south", lounge);
        
        observatory.setExit("south", outside);
        
        gymnasium.setExit("north", empty);
        gymnasium.setExit("south", outside);
        
        study.setExit("north", lounge);
        
        empty.setExit("west", lounge);
        empty.setExit("south", gymnasium);
        empty.setExit("east", pub);
        empty.setExit("north", outside);

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands
        // and execute them until the game is over.
                
        boolean finished = false;
        
        while (! finished)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("This is a text adventure game that puts " +
                            "you in an empty university!");
        System.out.println("Type '" + CommandWord.HELP +
                            "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord)
        {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case LOOK:
                System.out.println("This is an item.");
                break;
                
            case EAT:
                System.out.println("It looks tasty.  Should I eat it," +
                " save it, or toss it?");
                
            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
        {
            System.out.println("There is no door!");
        }
        
        else
        {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord())
        {
            System.out.println("Quit what?");
            
            return false;
        }
        
        else
        {
            return true;  // signal that we want to quit
        }
    }
}