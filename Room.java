import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Room - a room in this adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Salvatore Anzalone
 * @version 3/24/2023
 */

public class Room 
{
    public static void main(String[] args)
    {
        System.out.println("This is the text adventure's Room class!");
        System.out.println();
        System.out.println("This gives details on which room " +
                            "you are currently in.");
        System.out.println();
        System.out.println("To get more details, please run the " +
                            "class methods outside of this main");
        System.out.println("method.");
    }
    
    private String description;
    private HashMap<String, Room> exits;  // stores exits of this room.
    private HashMap<String, Room> items;  // stores items in a room.

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Create an item description. This helps indicate
     * which item you are holding onto, its condition, and so on.
     * 
     * @param description The item's description.
     */
    public void Item(String description) 
    {
        this.description = description;
        items = new HashMap<>();
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a description of the itemm in the form:
     *     This room contains a(n) apple.
     * @return A long description of this item
     */
    public String getItemDescription()
    {
        return "This room contains a(n)" + description + ".\n";
    }
    
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        
        for(String exit : keys)
        {
            returnString += " " + exit;
        }
        
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}