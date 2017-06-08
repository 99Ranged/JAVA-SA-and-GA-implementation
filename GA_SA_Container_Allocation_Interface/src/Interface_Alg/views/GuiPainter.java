package Interface_Alg.views;

public class GuiPainter extends Thread
{
    
    GUI gui;
    int val;
    
    public GuiPainter(GUI gui, int val)
    {
        this.gui = gui; 
        this.val = val;
    }
    

    
}
