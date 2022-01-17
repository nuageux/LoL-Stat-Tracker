import java.util.ArrayList;

public class Stats
{
    private ArrayList<Game> myData;

    public Stats()
    {
        myData = new ArrayList<Game>();
    }

    public int[] getStats()
    {
        int[] stats = new int[7];
        int teamTotalCarries = 0;
        int teamTotalInters = 0;
        int ememyTotalCarries = 0;
        int enemyTotalInters = 0;
        int totalCarries = 0;
        int totalInts = 0;
        int totalWins = 0;

        for (int i = 0; i < myData.size(); i++)
        {
            Game curr = myData.get(i);
            teamTotalCarries += curr.getTeamCarries();
            teamTotalInters += curr.getTeamInters();
            enemyTotalCarries += curr.getEnemyCarries();
            enemyTotalInters += curr.getEnemyInters();
            totalCarries += curr.getICarry();
            totalInts += curr.getIInt();
            totalWins += curr.getWins();
        }

        stats[0] = teamTotalCarries / myData.size();
        stats[1] = teamTotalInters / myData.size();
        stats[2] = enemyTotalCarries / myData.size();
        stats[3] = enemyTotalInters / myData.size();
        stats[4] = totalCarries / myData.size();
        stats[5] = totalInts / myData.size();
        stats[6] = totalWins / myData.size();

        return stats;
    }

    // Games that I carried and should've won but lost.
    public int deservedWins()
    {
        int total = 0;
        for (int i = 0; i < myData.size(); i++)
        {
            Game curr = myData.get(i);
            if (curr.getICarry() == 1 && !curr.getIWin())
                total++;
        }
        return total;
    }

    // Games that I inted and should've lost but won.
    public int deservedLosses()
    {
        int total = 0;
        for (int i = 0; i < myData.size(); i++)
        {
            Game curr = myData.get(i);
            if (curr.getIInt() == 1 && curr.getIWin())
                total++;
        }
        return total;
    }

    public int getTotalGames()
    {
        return myData.size();
    }

    public void addGame(Game entry)
    {
        myData.add(entry);
    }
}