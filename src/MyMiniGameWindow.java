import java.awt.BorderLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

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
    private JMenu 					optionsMenu = 		new JMenu("Optionen");
    private JMenu 					menuItemDifficulty= new JMenu("Level wählen");
    private ButtonGroup 			difficultyRbGroup = new ButtonGroup();
    private JRadioButtonMenuItem[] 	rbDifficulty = 		new JRadioButtonMenuItem[10];
    private JPanel 					txtBox = 			new JPanel();
    private JLabel 					txtBoxText = 		new JLabel();
       
    private int 					selectedDifficulty = 1;
    
    /**
     * Default Constructor
     */
    public MyMiniGameWindow()
    {
		setSize(700, 700);
		setLayout(new BorderLayout());
		setTitle(theGame.getName());
		addComponentsAndListeners();	
		theGame.registerMiniGameObserver(this);	
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
    }
    
    /**
     * adds JComponents and theirs Listeners to this JFrame
     */
    private void addComponentsAndListeners()
    {
    	//MainMenu
    	mainMenu.add(menuItemNewGame);
    	mainMenu.addSeparator();
		mainMenu.add(menuItemExit);
		toolbar.add(mainMenu);
		
		menuItemNewGame.addActionListener(this);
		menuItemExit.addActionListener(this);
		
		//OptionsMenu
		for (int i = 0; i < rbDifficulty.length; i++)
		{
			final int rbNo = i+1;
			rbDifficulty[i] = new JRadioButtonMenuItem("Level "+(i+1));
			difficultyRbGroup.add(rbDifficulty[i]);
			menuItemDifficulty.add(rbDifficulty[i]);
			
			rbDifficulty[i].addActionListener(new ActionListener()
			{				
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					selectedDifficulty = rbNo;					
				}
			});
		}
		rbDifficulty[selectedDifficulty-1].setSelected(true);
		optionsMenu.add(menuItemDifficulty);
		toolbar.add(optionsMenu);	
		setJMenuBar(toolbar);
		
		//Infoboxt South	
		txtBox.add(txtBoxText);
		add(txtBox, BorderLayout.SOUTH);
		
		//GamePanel Center
		add(theGame, BorderLayout.CENTER);
			
    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
		if (ae.getSource() == menuItemNewGame)
		{
		    theGame.newGame(selectedDifficulty);
		}
		else if (ae.getSource() == menuItemExit)
		{
		    System.exit(0);
		}
    }

    /*
     * (non-Javadoc)
     * @see MiniGamePackage.MiniGameObserver#gameStatusUpdate(int, int, int, boolean)
     */
    @Override
    public void gameStatusUpdate(int timeLeft, int playerScore, int computerScore, boolean isRunning)
    {
    	txtBoxText.setText("Time left: " + timeLeft + " Player: " + playerScore + "  Computer: " + computerScore + " Still running:" + isRunning + "Level:" + selectedDifficulty);
    }

    /*
     * (non-Javadoc)
     * @see java.awt.KeyEventDispatcher#dispatchKeyEvent(java.awt.event.KeyEvent)
     */
	@Override
	public boolean dispatchKeyEvent(KeyEvent ke)
	{
		System.out.println(ke.getKeyChar());
		if (ke.getID() == KeyEvent.KEY_PRESSED)
		{
			switch (ke.getKeyCode())
			{
				case KeyEvent.VK_SPACE:
					theGame.playerActionGo();
					return true;
					
				case KeyEvent.VK_DOWN:
					theGame.playerActionDown();
					return true;
					
				case KeyEvent.VK_UP:
					theGame.playerActionUp();
					return true;
					
				case KeyEvent.VK_F11:
					System.out.println("F11");
					theGame.newGame(selectedDifficulty);
					return true;
			}			
		}
		return true;
	}

}