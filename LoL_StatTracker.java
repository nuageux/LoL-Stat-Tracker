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
        try
        {
            Scanner csvRead = new Scanner(new File("game_stats.csv"));
            while (csvRead.hasNextLine())
            {
                String[] data = csvRead.nextLine().split(",");
                int teamCarries = Integer.parseInt(data[0]), 
                    teamInters  = Integer.parseInt(data[1]), 
                    enemyCarries= Integer.parseInt(data[2]), 
                    enemyInters = Integer.parseInt(data[3]),
                    carry       = Integer.parseInt(data[4]),
                    inter       = Integer.parseInt(data[5]),
                    won         = Integer.parseInt(data[6]);
                boolean iCarry = false, iInt = false, win = false;
                if (carry == 1)  iCarry= true;
                if (inter == 1)  iInt  = true;
                if (won == 1)    win   = true;
                myStats.addGame(new Game(teamCarries, teamInters, enemyCarries, enemyInters,
                                        iCarry, iInt, win));
            }
            csvRead.close();
        }
        catch (IOException e)
        {
            System.out.println("Error.");
            e.printStackTrace();
        }

        // Menu Prompt
        // add game? am i in losers queue? -> past 5, 10, 20 games? get stats?
        Scanner input = new Scanner(System.in);
        String option;
        do
        {
            System.out.println("\nSelect an option: ");
            System.out.println("[1] to add a game to database");
            System.out.println("[2] to see if you're in loser's queue");
            System.out.println("[3] to see some statistics");
            System.out.println("[q] to quit and exit");
            option = input.nextLine();

            if (option.equals("1"))
            {
                // Input and write new Games into the CSV file.
                //Scanner input = new Scanner(System.in);
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
                            try
                            {
                                FileWriter newEntry = new FileWriter("game_stats.csv", true);
                                int carry = 0, inter = 0, won = 0;
                                if (iCarry) carry = 1;
                                if (iInt)   inter = 1;
                                if (win)    won   = 1;
                                newEntry.write("\n" + teamCarries + "," + teamInters + "," + enemyCarries
                                 + "," + enemyInters + "," + carry + "," + inter + "," + won);
                                newEntry.close();
                                System.out.println("Recorded.");
                            }
                            catch (IOException e)
                            {
                                System.out.println("Error.");
                                e.printStackTrace();
                            }
                        }
                        else
                            System.out.println("Let's try again...");

                        System.out.println();
                    }
                } while (!record.equalsIgnoreCase("N"));
                //input.close();
            }
            if (option.equals("2"))
            {
                if (myStats.inLosersQueue())
                    System.out.println("\nYeah you're in losers queue. Keep your chin up and keep playing well!");
                else
                    System.out.println("\nJust your imagination... unfortunately.");
            }
            if (option.equals("3"))
            {
                double[] averages = myStats.getStats();
                System.out.println("\nOut of " + myStats.getTotalGames() + " games with a " + averages[6]*100 + "% winrate, " + "on average, ");
                System.out.println("your  teams have " + averages[0] + " carries and " + averages[1] + " inters per match, ");
                System.out.println("enemy teams have " + averages[2] + " carries and " + averages[3] + " inters per match, ");
                System.out.println("you carry " + averages[4]*100 + "% of your games, and");
                System.out.println("you int   " + averages[5]*100 + "% of your games.");
                System.out.println("Restarting...");
            }
        } while (!option.equalsIgnoreCase("Q"));
        System.out.println("\nClosing. GLHF!");
        input.close();   
    }
}