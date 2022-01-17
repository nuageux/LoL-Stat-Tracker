import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoL_StatTracker
{
    public static void main(String args[])
    {
        try
        {
            File myFile = new File("game_stats.csv");
            if (myFile.createNewFile())
                System.out.println("CSV file \"game_stats\" created.");
            else 
                System.out.println("Loading file \"game_stats.csv\".");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Scanner input = new Scanner(System.in);

        String record;
        do 
        {
            System.out.print("Record a game? (Y/N): ");
            record = input.nextLine();
        } while (record.compareTo("N") != 0);
    }
}