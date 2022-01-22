import java.util.ArrayList;

public class Stats
{
    private ArrayList<Game> myData;

    // default constructor, no params anyway
    public Stats()
    {
        myData = new ArrayList<Game>();
    }

    // returns stats of the database as an array
    public double[] getStats()
    {
        double[] stats = new double[7];
        int teamTotalCarries = 0;
        int teamTotalInters = 0;
        int enemyTotalCarries = 0;
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
            if (curr.getICarry()) totalCarries++;
            if (curr.getIInt()) totalInts++;
            if (curr.getWin()) totalWins++;
        }

        stats[0] = 1.0 * teamTotalCarries / myData.size();
        stats[1] = 1.0 * teamTotalInters / myData.size();
        stats[2] = 1.0 * enemyTotalCarries / myData.size();
        stats[3] = 1.0 * enemyTotalInters / myData.size();
        stats[4] = 1.0 * totalCarries / myData.size();
        stats[5] = 1.0 * totalInts / myData.size();
        stats[6] = 1.0 * totalWins / myData.size();

        stats[0] = Math.floor(stats[0] * 100) / 100;
        stats[1] = Math.floor(stats[1] * 100) / 100;
        stats[2] = Math.floor(stats[2] * 100) / 100;
        stats[3] = Math.floor(stats[3] * 100) / 100;
        stats[4] = Math.floor(stats[4] * 100) / 100;
        stats[5] = Math.floor(stats[5] * 100) / 100;
        stats[6] = Math.floor(stats[6] * 100) / 100;

        return stats;
    }

    // Number of games that I carried and should've won but lost.
    public int deservedWins()
    {
        int total = 0;
        for (int i = 0; i < myData.size(); i++)
        {
            Game curr = myData.get(i);
            if (curr.getICarry() && !curr.getWin())
                total++;
        }
        return total;
    }

    // Number of games that I inted and should've lost but won.
    public int deservedLosses()
    {
        int total = 0;
        for (int i = 0; i < myData.size(); i++)
        {
            Game curr = myData.get(i);
            if (curr.getIInt() && curr.getWin())
                total++;
        }
        return total;
    }

    // access method
    public int getTotalGames()
    {
        return myData.size();
    }

    // mutator method
    public void addGame(Game entry)
    {
        myData.add(entry);
    }

    public boolean inLosersQueue()
    {
        boolean ans = true;

        return ans;
    }
}