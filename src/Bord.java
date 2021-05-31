import javax.swing.*;
import java.awt.*;  
public class Bord extends JPanel
{
    private final int ROWS_AMNT = 6,columns_AMNT = 7;
    private final int LINES_WIDTH = 5;    
    private final int PROPORTIONS_FOR_GRID = 50;
    private final int CIRCLE_FACTOR = 3;
    private int _Column_num = -1;
    private int _row_num = -1;
    private char _player_name = ' ';
    private char[][] _matrix = new char[ROWS_AMNT][columns_AMNT];
/**
     * this method receive a bord as matrix and help make a decision where to draw circles
     *
     */
    public void setCirclePlace(char[][] matrix)
    {
        _matrix = matrix;
    }


    protected void paintComponent(Graphics g)
    {
        Color myBrown = new Color(222,184,135); // Color brown              
        g.setColor(myBrown);

        int height = getHeight();
        int width = getWidth();

        int num_of_rows = ROWS_AMNT;
        int num_of_columns = columns_AMNT;

        int top_left_x = width/PROPORTIONS_FOR_GRID;//the to left corner of the grid for the length of rows
        int top_right_x = width - (width/PROPORTIONS_FOR_GRID);//the top right corner of the grid length of rows

        int top_left_y = height/PROPORTIONS_FOR_GRID;//the to left corner of the grid for the height of Columns
        int bottum_left_y = height-(height/PROPORTIONS_FOR_GRID);//the to right corner of the grid for the height of Columns

        int Column_height = bottum_left_y - top_left_y; //setting the height of a Column by subtract the lowest height from the height one
        int row_length = top_right_x - top_left_x ;//setting the length of a Column by subtract the lowest length from the height one

        int rows_gap = Column_height/num_of_rows;
        int columns_gap = row_length/num_of_columns;

        //lop to create the rows
        for (int i = 0 ; i <=num_of_rows;i++)
        {
            g.fillRect(top_left_x, top_left_y,row_length,LINES_WIDTH);//draw row
            top_left_y = top_left_y + rows_gap;//moving down the starting point of a row
        }

        top_left_x = width/PROPORTIONS_FOR_GRID;//initialize the point for the columns to start
        top_left_y = height/PROPORTIONS_FOR_GRID;//initialize the point for the columns to start
        //lop to create the columns
        for (int i = 0 ; i <=num_of_columns;i++)
        {
            g.fillRect(top_left_x, top_left_y,LINES_WIDTH,Column_height);//draw new Column
            top_left_x = top_left_x + columns_gap;
        }

        top_left_x = (columns_gap/2)-(columns_gap/columns_AMNT);//initialize the point for the columns to start
        top_left_y = (height/PROPORTIONS_FOR_GRID)+LINES_WIDTH*2;//initialize the point for the columns to start

        //now creates the circles
        Color my_red = new Color(204, 0, 0); // Color red
        Color my_blue = new Color(0, 0, 153); // Color blue        
        int circle_size;
        if(rows_gap<columns_gap)
            circle_size = rows_gap-(CIRCLE_FACTOR*LINES_WIDTH);
        else
            circle_size = columns_gap-(CIRCLE_FACTOR*LINES_WIDTH*2);

        // if(_player_name =='R')//if player is red the circle will be red               
        // g.setColor(my_red);
        // else if( _player_name =='B')//if player is blue the circle will be blue
        // g.setColor(my_blue);

        //setting the relative point for the game first circle here will be at the bottom left corner of the game bord
        int buttom_left_x = top_left_x;
        int buttom_left_y = top_left_y +((ROWS_AMNT-1)*rows_gap);
        //g.fillOval(buttom_left_x, buttom_left_y, circle_size, circle_size);

        //GameLogics G = new GameLogics();
        int current_x;
        int current_y;
        for(int i=0;i<ROWS_AMNT;i++)
        {
            for(int j=0;j<columns_AMNT;j++)
            {
                if(_matrix[i][j]=='R'||_matrix[i][j]=='B')
                {
                    if(_matrix[i][j] =='R')//if player is red the circle will be red               
                        g.setColor(my_red);
                    else if(_matrix[i][j] =='B')//if player is blue the circle will be blue
                        g.setColor(my_blue);
                    current_x =buttom_left_x+(columns_gap*j);
                    current_y = buttom_left_y-(rows_gap*(ROWS_AMNT-(i+1)));
                    g.fillOval(current_x, current_y, circle_size, circle_size);
                }
            }
        }
        //conclusion(may be helpful)
        //Starting point of most left circle is top_left_x,(top_left_y +(6*rows_gap))
        //move it up by subtract rows_gap from top_left_y 
        //move it right by add columns_gap to top_left_x
        //to move up n times subtract rows_gap*n from top_left_y
        //to move right n times add columns_gap*n to top_left_x
    }
}
