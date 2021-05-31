import java.util.Scanner;
public class GameLogics
{
    private int GAME_WINNER;
    /**
     * this method create a game bord for this game as a char[][]
     *
     * @return    char [][] the metric for the bord spots
     */
    public char [][] createBord(int bord_length, int bord_width, int game_winner)
    {
        GAME_WINNER = game_winner;
        char bord [][] = new char[bord_length][bord_width];
        for(int i=0;i<bord.length;i++)
            for(int j=0;j<bord[0].length;j++)
                bord[i][j]=' ';
        return bord;
    }

    /**
     * this method return the last spot that changed on a bord
     *
     * @return    int - the last spot that changed
     */
    private int getCurrentMoveLine(char [][] bord, int column, char player_name)
    {
        for(int i=0; i<bord.length ; i++)
            if(bord[i][column]==player_name)
                return i; 
        return 0;
    }

    /**
     * this method checks if there are 4 moves in a column
     *
     * @return    boolean true if the are 4 in a column
     */
    private boolean fourInAcolumn(char [][] bord, int line, int column, char player_name)
    {
        int counter=0;
        for(int i =line-(GAME_WINNER-1);i<(line+GAME_WINNER);i++)
        {
            if(i<0)
                i=0;
            if(i>=bord.length)
                break;
            if(bord[i][column]==player_name)
                counter++;
            else 
                counter=0;
            if(counter==GAME_WINNER)
                return true;
        }
        return false;
    }

    /**
     * this method checks if there are 4 moves in a line
     *
     * @return    boolean true if the are 4 in a line
     */
    private boolean fourInALine(char [][] bord, int line, int column, char player_name)
    {
        int counter=0;
        for(int i =column-(GAME_WINNER-1);i<=(column+GAME_WINNER);i++)
        {
            if(i<0)
                i=0;
            if(i>bord.length)
                break;
            if(bord[line][i]==player_name)
                counter++;
            else 
                counter=0;
            if(counter==GAME_WINNER)
                return true;
        }
        return false;
    }

    /**
     * this method checks if there are 4 moves in a down Diagonal
     *
     * @return    boolean true if there are 4 in down diagonal
     */
    private boolean fourInDownDiagonal(char [][] bord, int line, int column, char player_name)
    {
        int counter=0;
        int i=line-(GAME_WINNER-1);
        int j=column-(GAME_WINNER-1);
        boolean out_of_bord = false;
        while(!out_of_bord)
        {
            if(i<0||j<0)
            {
                i++;
                j++;
            }
            else if(i>=bord.length||j>=bord[0].length)
                out_of_bord = true;
            else
            {
                if(bord[i][j]==player_name)
                    counter++;
                else 
                    counter=0;
                if(counter==GAME_WINNER)
                    return true;
                i++;
                j++;
            }
        }
        return false;
    }

    /**
     * this method checks if there are 4 moves in a up Diagonal
     *
     * @return    boolean true if there are 4 in up diagonal
     */
    private boolean fourInUpDiagonal(char [][] bord, int line, int column, char player_name)
    {
        int counter=0;
        int i=line+(GAME_WINNER-1);
        int j=column-(GAME_WINNER-1);
        boolean out_of_bord = false;
        while(!out_of_bord)
        {
            if(i>=bord.length||j<0)
            {
                i--;
                j++;
            }
            else if(i<0||j>=bord[0].length)
                out_of_bord = true;
            else
            {
                if(bord[i][j]==player_name)
                    counter++;
                else 
                    counter=0;
                if(counter==GAME_WINNER)
                    return true;
                i--;
                j++;
            }
        }
        return false;
    }

    /**
     * this method checks if there are 4 moves in a Diagonal
     *
     * @return    boolean true if there are 4 in diagonal
     */
    private boolean fourInADiagonal(char [][] bord, int line, int column, char player_name)
    {
        if(fourInDownDiagonal(bord,line, column, player_name))
            return true;
        if(fourInUpDiagonal(bord,line, column, player_name))
            return true;
        return false;
    }

    /**
     * this method checks if there is a winner
     *
     * @return    boolean true if there is a winner
     */
    public boolean thereIsAWinner(char [][] bord, int player_move, char player_name)
    {
        int column = player_move-1;
        int line = getCurrentMoveLine(bord,column,player_name);
        if(fourInALine(bord,line, column, player_name))
            return true;
        if(fourInAcolumn(bord,line, column, player_name))
            return true;
        if(fourInADiagonal(bord,line, column, player_name))
            return true;
        return false;
    }

    /**
     * this method return the updated bord after a move was done
     *
     * @return   the new bord with all the moves on it
     */
    public char [][]  bordAfterMove(char [][] bord, int player_move, char player_name)
    {
        int column = player_move-1;
        for(int i=bord.length-1;i>=0;i--)
        {
            if(bord[i][column]!='R'&&bord[i][column]!='B')
            {
                bord[i][column] = player_name;
                break;
            }
        }
        return bord;
    }

    /**
     * this method checks if there is no place left on the column
     * 
     * @return    boolean - true if the column is full
     */
    public boolean iscolumnFull(char [][] bord, int player_move)
    {
        int column = player_move-1;
        if(bord[0][column]!='R'&&bord[0][column]!='B')
            return false ;       
        return true;
    }

    /**
     * this method checks if there is no place left on the bord
     *
     * @return    boolean - true if the bord is full
     */
    public boolean bordIsFull(char [][] bord)
    {        
        for(int i=0;i<bord.length;i++)        
            if(!iscolumnFull(bord,i+1))//if there is any unfurl column then this bord is not full yet
                return false;
        return true;
    }

}
