import java.util.Scanner;

/**
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two-word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Salvatore Anzalone
 * @version 3/24/2023
 */
public class Parser 
{
    public static void main(String[] args)
    {
        System.out.println("This is the text adventure's Parser class!");
        System.out.println();
        System.out.println("Here, inputs, outputs, and commands " +
                            "are managed.");
        System.out.println();
        System.out.println("To get more details, please run the " +
                            "class methods outside of this main");
        System.out.println("method.");
    }
    
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * @return The next command from the user.
     */
    public Command getCommand() 
    {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;

        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        
        if(tokenizer.hasNext())
        {
            word1 = tokenizer.next();      // get first word
            
            if(tokenizer.hasNext())
            {
                word2 = tokenizer.next();      // get second word
                // note: we just ignore the rest of the input line.
            }
        }

        return new Command(commands.getCommandWord(word1), word2);
    }

    /**
     * Print out a list of valid command words.
     */
    public void showCommands()
    {
        commands.showAll();
    }
}