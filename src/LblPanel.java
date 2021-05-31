import javax.swing.*;
public class LblPanel extends JPanel
{
    private JLabel lblHello;
    /**
     * this method creates a jlabel panel and add it to a jlabel
     */
    public LblPanel()
    {
        lblHello = new JLabel(" Hello this is 4InARow lets start with Red ");        
        add(lblHello);       
    }

    /**
     * this method set a jlabel panel according to num
     *
     */
    public void setLblPanel(int num)
    {                
        if(num==0)
            lblHello.setText(" Red won press clear the to clear bord ");  
        else if(num==1)
            lblHello.setText(" Blue won press clear to clear the bord ");  
        else if(num==2)
            lblHello.setText(" the bord is full, no winners press clear to start again  ");
        else if(num==3)
            lblHello.setText(" its Red's turn now ");
        else if(num==4)
            lblHello.setText(" its Blue's turn now ");
        else if(num==5)
            lblHello.setText(" hello again lets start with Red ");
        else  
            lblHello.setText(" this column is full try again ");
    }
}