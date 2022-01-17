import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoL_StatTracker
{
    public static void main(String args[])
    {
        // Create the CSV file if it doesn't exist already.
        try
        {
            File myFile = new File("game_stats.csv");
            if (myFile.createNewFile())
                System.out.println("CSV file \"game_stats\" created.");
            else 
                System.out.println("Loading file \"game_stats.csv\"");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Read the CSV file into a Stats object.
        Stats myStats = new Stats();

        // Input and write new Games into the CSV file.
        Scanner input = new Scanner(System.in);

        String record;
        do 
        {
            System.out.print("Record a game? (Y/N): ");
            record = input.nextLine();
            if (record.equalsIgnoreCase("Y"))
            {
                int teamCarries, teamInters, enemyCarries, enemyInters;
                boolean iCarry = false, iInt = false, win = false;
                String response;

                System.out.print("Did you win? (Y/N): ");
                response = input.nextLine();
                if (response.equalsIgnoreCase("Y"))
                    win = true;
                System.out.print("Did you carry? (Y/N): ");
                response = input.nextLine();
                if (response.equalsIgnoreCase("Y"))
                    iCarry = true;
                System.out.print("Did you int? (Y/N): ");
                response = input.nextLine();
                if (response.equalsIgnoreCase("Y"))
                    iInt = true;
                System.out.print("How many carries on your team? ");
                    teamCarries = input.nextInt();
                    input.nextLine();
                System.out.print("How many inters on your team? ");
                    teamInters = input.nextInt();
                    input.nextLine();
                System.out.print("How many carries on enemy team? ");
                    enemyCarries = input.nextInt();
                    input.nextLine();
                System.out.print("How many inters on enemy team? ");
                    enemyInters = input.nextInt();
                    input.nextLine();
                
                String correct;
                System.out.println("\nAre the following values correct?");
                if (win)
                    System.out.print("[Won, ");
                else
                    System.out.print("[Lost, ");
                if (iCarry)
                    System.out.print("Carried, ");
                if (iInt)
                    System.out.print("Inted, ");

                System.out.print("Team: " + teamCarries
                         + " carries " + teamInters + " inters, ");
                System.out.println("Enemy: " + enemyCarries
                         + " carries " + enemyInters + " inters]");
                System.out.print("(Y/N): ");

                correct = input.nextLine();
                if (correct.equalsIgnoreCase("Y"))
                {
                    myStats.addGame(new Game(teamCarries, teamInters, enemyCarries, 
                                        enemyInters, iCarry, iInt, win));
                    
                    // write to file

                    
                    System.out.println("Recorded.");
                }
                else
                    System.out.println("Restarting...");
                
                System.out.println();
            }
        } while (!record.equalsIgnoreCase("N"));

        input.close();
    }
}