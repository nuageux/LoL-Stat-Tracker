public class Game
{
    private int numTeamCarries;
    private int numTeamInters;
    private int numEnemyCarries;
    private int numEnemyInters;
    private boolean iCarry;
    private boolean iInt;
    private boolean win;

    // default constructor
    public Game()
    {
        this.numTeamCarries = 0;
        this.numTeamInters = 0;
        this.numEnemyCarries = 0;
        this.numEnemyInters = 0;
        this.iCarry = false;
        this.iInt = false;
        this.win = false;
    }

    // constructor with params
    public Game(int numTeamCarries, int numTeamInters, int numEnemyCarries,
                    int numEnemyInters, boolean iCarry, boolean iInt, boolean win)
    {
        this.numTeamCarries = numTeamCarries;
        this.numTeamInters = numTeamInters;
        this.numEnemyCarries = numEnemyCarries;
        this.numEnemyInters = numEnemyInters;
        this.iCarry = iCarry;
        this.iInt = iInt;
        this.win = win;
    }

    // list of access methods
    public int getTeamCarries()
    {
        return numTeamCarries;
    }

    public int getTeamInters()
    {
        return numTeamInters;
    }

    public int getEnemyCarries()
    {
        return numEnemyCarries;
    }

    public int getEnemyInters()
    {
        return numEnemyInters;
    }

    public boolean getICarry()
    {
        return iCarry;
    }

    public boolean getIInt()
    {
        return iInt;
    }

    public boolean getWin()
    {
        return win;
    }
}