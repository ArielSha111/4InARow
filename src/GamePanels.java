import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; 
public class GamePanels extends JPanel
{    
    private final int BORD_LENGTH = 6,BORD_WIDTH = 7;
    private final int  GAP_SIZE = 5, GAME_WINNER = 4;

    private JButton cmdClear;    
    private JButton[] colums = new JButton [BORD_WIDTH];

    private Bord middle_panel;
    private LblPanel top_panel;

    private GameLogics logics = new GameLogics();
    private char [][] bord = logics.createBord(BORD_LENGTH,BORD_WIDTH,GAME_WINNER);

    private char player_name = 'R';//GAME WILL ALWAYS START WITH RED
    private int colume_to_fill;    
    private boolean game_ended = false;

    //creating some colors for the program
    private Color my_red = new Color(255, 51, 51); // Color red
    private Color my_blue = new Color(51, 153, 255); // Color blue 
    private Color my_yellow = new Color(255, 204, 51); // Color yellow
    private Color my_green = new Color(0, 204, 0); // Color green
    private Color my_brown = new Color(85,50, 0); // Color dark brown
    private Color my_gray = new Color(204, 204, 204); // Color gray

    public GamePanels()
    {        
        Listener lis = new Listener();
        Color my_gray = new Color(192, 192, 192); // Color white

        cmdClear = new JButton("Clear");//creating a "clear" button
        cmdClear.setBackground(my_green);//color it in gray
        cmdClear.setForeground(my_brown);

        cmdClear.addActionListener(lis);//add a listener to the button
        JPanel clear_panel = new JPanel();//create a panel for the button for it to be in the middle of it
        clear_panel.add(cmdClear);//add the button to the panel
        clear_panel.setBackground(Color.WHITE);//add white background to east west and south

        JPanel columns_panel = new JPanel();//creating a panel for the columns buttons
        columns_panel.setLayout(new GridLayout(0,BORD_WIDTH,GAP_SIZE,0));//chang the layout of the panel to be grid
        columns_panel.setBackground(Color.WHITE);//add white background to east west and south

        //creating a gray button for each column of the game and adding listener to the buttons
        for(int i=0;i<BORD_WIDTH;i++)
        {
            colums[i] = new JButton(""+(i+1)+"");//creating a button
            colums[i].setBackground(my_red);//color it in gray
            colums[i].setForeground(my_brown);
            colums[i].addActionListener(lis);//adding a listener to it
            columns_panel.add(colums[i]);//adding the button to the columns panel
        }   

        JPanel buttons_panel = new JPanel();//creating a panel for all the buttons
        buttons_panel.setLayout(new GridLayout(0,1));//set the panel to be a grid panel
        buttons_panel.add(columns_panel);//add the columns buttons to the first line
        buttons_panel.add(clear_panel);//add the clear button to the second line

        //creating 4 layouts which contain huge white rectangle 
        BackPanel left_background = new BackPanel(Color.WHITE);
        BackPanel right_background = new BackPanel(Color.WHITE);
        BackPanel bottom_background = new BackPanel(Color.WHITE);
        BackPanel top_background = new BackPanel(Color.WHITE);

        JPanel bottom_panel = new JPanel();//creating a bottom panel for the game window lay out
        bottom_panel.setLayout(new BorderLayout());//set it to borders
        //add white background to east west and south
        bottom_panel.add(left_background, BorderLayout.EAST);
        bottom_panel.add(right_background, BorderLayout.WEST);
        bottom_panel.add(bottom_background, BorderLayout.SOUTH);
        bottom_panel.add(top_background, BorderLayout.NORTH);
        bottom_panel.add(buttons_panel, BorderLayout.CENTER);       

        middle_panel = new Bord();//creating a middle panel for the game window lay out

        top_panel = new LblPanel();//creating a top panel for the game window lay out        
        top_panel.setBackground(Color.WHITE);//add white background to east west and south

        this.setLayout(new BorderLayout());//sets the current layout to be borders
        this.setBackground(Color.WHITE);//add white background 

        add(top_panel, BorderLayout.NORTH);//add the top panel to the top
        add(middle_panel, BorderLayout.CENTER);//add the middle panel to the middle
        add(bottom_panel, BorderLayout.SOUTH);//add the bottom panel to the bottom
    }

    private class Listener extends MouseAdapter implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {                                    
            if(game_ended)
            {
                if(e.getSource()==cmdClear)
                {
                    bord = logics.createBord(BORD_LENGTH,BORD_WIDTH,GAME_WINNER);//restarting the bord
                    middle_panel.setCirclePlace(bord);//draw the new empty bord
                    top_panel.setLblPanel(5);//set the words panel to say whose turn is it and if someone won
                    top_panel.setBackground(Color.WHITE);
                    game_ended = false;
                    for(int i=0;i<BORD_WIDTH;i++)
                        colums[i].setBackground(my_red);
                }
            }
            else 
            {                
                if(e.getSource()==cmdClear)
                    colume_to_fill = -1; 
                else
                {
                    for(int i = 0;i<BORD_WIDTH;i++)
                    {
                        if(e.getSource()==colums[i])
                        {
                            colume_to_fill = i+1;
                            break;
                        }
                    }
                }
                boolean valid_move=true;
                boolean there_is_winner = false;
                boolean bord_is_full = false;
                if(colume_to_fill==-1)//if the clear button pushed
                {
                    bord = logics.createBord(BORD_LENGTH,BORD_WIDTH,GAME_WINNER);//restarting the bord
                    middle_panel.setCirclePlace(bord);//draw the new empty bord
                }

                else //else any of the columns button pushed
                {
                    if(logics.iscolumnFull(bord,colume_to_fill))
                    {
                        valid_move = false;
                        top_panel.setLblPanel(6);//set the words panel to say whose turn is it and if someone won
                    }
                    else
                    {
                        bord = logics.bordAfterMove(bord, colume_to_fill, player_name);//add to bord the new move
                        middle_panel.setCirclePlace(bord);//draw the new bord
                        if(logics.thereIsAWinner(bord,colume_to_fill,player_name))
                        {
                            game_ended=true;
                            there_is_winner = true;
                        }

                        else if(logics.bordIsFull(bord))
                        {
                            game_ended=true;
                            bord_is_full =true;
                        }
                    }
                }
                if(there_is_winner)
                {
                    if(player_name == 'R')
                    {
                        top_panel.setLblPanel(0);
                        top_panel.setBackground(my_red);
                    }
                    else 
                    {
                        top_panel.setLblPanel(1);
                        top_panel.setBackground(my_blue);
                    }
                    for(int i=0;i<BORD_WIDTH;i++)
                        colums[i].setBackground(my_gray);//color it in gray
                    player_name = 'R';
                }
                else if(bord_is_full)
                {
                    player_name = 'R';
                    top_panel.setLblPanel(2);//set the words panel to say whose turn is it and if someone won
                    top_panel.setBackground(my_yellow);
                    for(int i=0;i<BORD_WIDTH;i++)
                        colums[i].setBackground(my_gray);//color it in gray
                }
                else if(player_name =='B'&&valid_move)
                {
                    player_name = 'R';
                    top_panel.setLblPanel(3);//set the words panel to say whose turn is it and if someone won
                    for(int i=0;i<BORD_WIDTH;i++)
                        colums[i].setBackground(my_red);//color it in gray
                }
                else if(player_name =='R'&&valid_move)
                {
                    player_name = 'B';
                    top_panel.setLblPanel(4);//set the words panel to say whose turn is it and if someone won
                    for(int i=0;i<BORD_WIDTH;i++)
                        colums[i].setBackground(my_blue);//color it in gray
                }
            }
            repaint();//making sure to apply the new changes to the paint component by making him think that the screen has been change
        }
    }
}
