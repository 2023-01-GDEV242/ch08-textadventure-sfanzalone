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
 * @version 3/24/2023
 */

public class Game 
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println();
        System.out.println("This is the main method of this " +
                            "class, as the class will take data from");
        System.out.println("the Parser class, and builds off of it.");
        System.out.println();
        System.out.println("To get more details, please run the " +
                            "class methods outside of this main");
        System.out.println("method.");
    }
    
    private Parser parser;
    private Room currentRoom; //The current room you're in.
    private Room currentItem; //The current item you're holding.
        
    /**
     * Create the game and initialise its internal map,
     * and the items within its map.
     */
    public Game() 
    {
        createRooms(); //Creates rooms
        createRooms(); //Creates items
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
     * Creates all the items that can be found in each room.
     */
    private void createItems()
    {
        Room none, crowbar, apple, pencil, officeKey, janitorKey,
            almightyKey, sandwich, rotApple, rotSandwich;
        
        // create the items
        none = new Room("You have no item on you.");
        
        crowbar = new Room("Helps with prying doors open.  It " +
                            "might even make for a good " +
                            "backtracking tool./n/n" +
                            "Weight: 1.32 lbs.");
        apple = new Room("Great snack to keep the doctor away, " +
                            "and to keep you healthy./n/n" +
                            "Weight: 0.33 lbs.");
        pencil = new Room("A standard No. 2 pencil.  Doesn't " +
                            "go beyond that./n/n" +
                            "Weight: 0.01 lbs.");
        officeKey = new Room("Gives you access to the office." +
                                "Weight: About 2.2 lbs.");
        janitorKey = new Room("Gives you access to the boiler room." +
                                "Weight: About 2.2 lbs.");
        almightyKey = new Room("Same as the other keys, but " +
                                "now you unlock every door in this " +
                                "adventure!  This key has" +
                                "no limitations!/n/n" +
                                "Weight: About 2.2 lbs.");
        sandwich = new Room("Bacon, lettuce, and tomato.  What " +
                            "more can you ask for!?/n/n" +
                            "Weight: 2.18 lbs.");
        rotApple = new Room("A snack that wasn't touched for " +
                            "several weeks. You want to eat a " +
                            "decaying fruit?/n/n" +
                            "Weight: 0.15 lbs.");
        rotSandwich = new Room("Bacon, lettuce, and terrible!  " +
                                "The contents are expired, and the " +
                                "bread is moldy./n/n" +
                                "Weight: 2.18 lbs.");
        
        currentItem = none; //Nothing was obtained
    }
    
    /**
     * Creates all the rooms and their exits and exit links.
     * However, some rooms are locked, and some have trap doors,
     * preventing you from being able to backtrack.  Luckily,
     * they have someone in the room with a hint on how you can access
     * other rooms.
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

        //Meant to give a quick summary of what two major items do
        Room crowbar = new Room("Summary: Pries open doors, " +
                                "and helps with backtracking/n/n" +
                                "Weight: 1.32 lbs.");
        Room almightyKey = new Room("Summary: Gives access to all rooms" +
                                    "Weight: About 2.2 lbs.");
        
        if(currentRoom == empty || currentRoom == gymnasium)
        {
            System.out.println("/n/nIt's locked!  It needs to " +
                                "be pried opened.");
        }
        
        else if(currentItem == crowbar && currentRoom == empty ||
                currentItem == crowbar && currentRoom == gymnasium)
        {
            System.out.println("/n/nThe door is pried opened!");
        }
        
        
        if(currentRoom == coffee)
        {
            System.out.println("/n/nIt's locked!  Is there a " +
                                "key you can get to enter the " +
                                "coffee shop?");
        }
        
        else if(currentItem == almightyKey && currentRoom == coffee)
        {
            System.out.println("/n/nThe door is unlocked!");
        }
        
        if(currentRoom == library || currentRoom == boiler)
        {
            System.out.println("/n/nYou cannot go to the previous " +
                                "room.  Surely, you chose wisely.");
        }
        
        if(currentRoom == library)
        {
            System.out.println("/n/nHead outside, and go to " +
                                "the gymnasium.  Good luck!");
        }
        
        else if(currentRoom == boiler)
        {
            System.out.println("/n/nGo to the office, and be " +
                                "swift!  A special key awaits!");
        }
        
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
        
        //This helps determine if you're viewing a food item,
        //or an obtainable collectible when using the LOOK
        //command.
        boolean collectible = false;
        boolean food = false;

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
                if(collectible == true)
                {
                    System.out.println("This is an item.");
                }
                
                else
                {
                    System.out.println("This is a food item.");
                }
                break;
                
            case EAT:
                System.out.println("It might be tasty.  Should I eat it," +
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
     * Obtain an item within a room. If the item is helpful,
     * collect it.  Otherwise, provide an error message.
     */
    private void getItem(Command command) 
    {
        if(!command.hasSecondWord())
        {
            //if there is no second word, we don't know if the item
            //is helpful or not...
            System.out.println("Collect or consume it?");
            
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        Room nextItem = currentItem.getExit(direction);

        if(nextRoom == null)
        {
            System.out.println("There is no item!");
        }
        
        else if(nextItem == null)
        {
            System.out.println("This looks bad!");
            //Saying poisonous instead does get the point across,
            //but doing so means you're addressing a food item.
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