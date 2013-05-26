import java.awt.BorderLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import MiniGamePackage.MiniGameObserver;

/**
 * 
 * @author mpfingsten
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class MyMiniGameWindow extends JFrame implements ActionListener, MiniGameObserver, KeyEventDispatcher
{  
	private MyMiniGame 				theGame = 			new MyMiniGame();
    private JMenuBar 				toolbar = 			new JMenuBar();
    private JMenu 					mainMenu = 			new JMenu("Datei");
    private JMenuItem 				menuItemNewGame = 	new JMenuItem("Neues Spiel");
    private JMenuItem 				menuItemExit = 		new JMenuItem("Beenden");
    private JPanel 					txtBox = 			new JPanel();
    private JLabel 					txtBoxText = 		new JLabel();    
    private int 					selectedDifficulty = 1;
    
    public MyMiniGameWindow()
    {
		setSize(640, 700);
		setLocation(100, 100);
		setLayout(new BorderLayout());
		setTitle(theGame.getName());
		addComponentsAndListeners();	
		theGame.registerMiniGameObserver(this);			
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);	
    }
    
    private void addComponentsAndListeners()
    {
    	KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
    	
    	//MainMenu
    	mainMenu.add(menuItemNewGame);
    	mainMenu.addSeparator();
		mainMenu.add(menuItemExit);
		toolbar.add(mainMenu);
		
		menuItemNewGame.addActionListener(this);
		menuItemExit.addActionListener(this);
		
		setJMenuBar(toolbar);
		
		//Infobox North
		txtBox.add(txtBoxText);
		add(txtBox, BorderLayout.NORTH);
		
		//GamePanel Center
		add(theGame, BorderLayout.CENTER);
			
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
		if (ae.getSource() == menuItemNewGame)
		{
		    theGame.startGame();
		}
		else if (ae.getSource() == menuItemExit)
		{
		    System.exit(0);
		}
    }

    @Override
    public void gameStatusUpdate(int timeLeft, int playerScore, int computerScore, boolean isRunning)
    {
    	txtBoxText.setText("Time left: " + timeLeft + " Player: " + playerScore + "  Computer: " + computerScore + " Still running:" + isRunning + "Level:" + theGame.level);
   	
    	if(timeLeft%1000 <= 30)
    	{
    		theGame.showRandomFieldSprites(theGame.getRandomNr(1, 2));
    	}   	
    }

	@Override
	public boolean dispatchKeyEvent(KeyEvent ke)
	{
		System.out.println(ke.getKeyChar());
		if (ke.getID() == KeyEvent.KEY_PRESSED)
		{
			switch (ke.getKeyCode())
			{
				case KeyEvent.VK_SPACE:
				{
					theGame.playerActionGo();
					break;
				}
										
				case KeyEvent.VK_DOWN:
				{
					theGame.playerActionDown();
					break;
				}
					
				case KeyEvent.VK_UP:
				{
					theGame.playerActionUp();
					break;
				}					
			}			
		}
		return true;
	}

}