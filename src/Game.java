import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; 
public class Game extends JPanel

{
    private static final int SIZE_OF_FRAME=450;
    public static void main(String [] args)
    {
        JFrame frame = new JFrame("4InARow");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SIZE_OF_FRAME,SIZE_OF_FRAME); 
        frame.setLocation(700,200);
        
        GamePanels G = new GamePanels();                 
        frame.setVisible(true); 
        frame.add(G);
    }
}
