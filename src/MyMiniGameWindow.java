import java.awt.BorderLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import MiniGamePackage.MiniGameObserver;

@SuppressWarnings("serial")
public class MyMiniGameWindow extends JFrame implements ActionListener, MiniGameObserver, KeyEventDispatcher
{
    JButton buttonUp = new JButton("UP!");
    JButton buttonDown = new JButton("DOWN!");
    JButton buttonGo = new JButton("GO!");

    
    private JMenuBar toolbar = new JMenuBar();
    private JMenu mainMenu = new JMenu("Datei");
    private JMenuItem menuItemNewGame = new JMenuItem("Neues Spiel");
    private JMenuItem menuItemExit = new JMenuItem("Beenden");

    MyMiniGame theGame = new MyMiniGame();

    public MyMiniGameWindow()
    {
		setSize(700, 700);
	
		setLayout(new BorderLayout());
	
		mainMenu.add(menuItemNewGame);
		mainMenu.add(menuItemExit);
		toolbar.add(mainMenu);
		setJMenuBar(toolbar);
	
		buttonUp.addActionListener(this);
		buttonDown.addActionListener(this);
		buttonGo.addActionListener(this);
		menuItemNewGame.addActionListener(this);
		menuItemExit.addActionListener(this);
	
		add(theGame, BorderLayout.CENTER);
	
		theGame.registerMiniGameObserver(this);
	
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
		if (ae.getSource() == buttonUp)
		{
		    theGame.playerActionUp();
		}
		else if (ae.getSource() == buttonDown)
		{
		    theGame.playerActionDown();
		}
		else if (ae.getSource() == buttonGo)
		{
		    theGame.playerActionGo();
		}
		else if (ae.getSource() == menuItemNewGame)
		{
		    theGame.newGame(1);
		}
		else if (ae.getSource() == menuItemExit)
		{
		    System.exit(0);
		}
    }

    @Override
    public void gameStatusUpdate(int timeLeft, int playerScore, int computerScore, boolean isRunning)
    {
	setTitle("Time left: " + timeLeft + " Player: " + playerScore + "  Computer: " + computerScore + " Still running:" + isRunning);
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
					theGame.newGame(1);
					return true;
			}			
		}
		return true;
	}

}