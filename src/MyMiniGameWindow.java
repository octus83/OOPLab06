import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import MiniGamePackage.MiniGameObserver;

@SuppressWarnings("serial")
public class MyMiniGameWindow extends JFrame implements ActionListener, MiniGameObserver
{

    JButton buttonUp = new JButton("UP!");
    JButton buttonDown = new JButton("DOWN!");
    JButton buttonGo = new JButton("GO!");
    JButton buttonStartGame = new JButton("Start Game");

    MyMiniGame theGame = new MyMiniGame();

    public MyMiniGameWindow()
    {
	setSize(700, 700);

	setLayout(new BorderLayout());

	JToolBar toolbar = new JToolBar();
	add(toolbar, BorderLayout.NORTH);
	toolbar.add(buttonUp);
	toolbar.add(buttonDown);
	toolbar.add(buttonGo);
	toolbar.add(buttonStartGame);

	buttonUp.addActionListener(this);
	buttonDown.addActionListener(this);
	buttonGo.addActionListener(this);
	buttonStartGame.addActionListener(this);

	add(theGame, BorderLayout.CENTER);

	theGame.registerMiniGameObserver(this);

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
	else if (ae.getSource() == buttonStartGame)
	{
	    theGame.newGame(1);
	}
    }

    @Override
    public void gameStatusUpdate(int timeLeft, int playerScore, int computerScore, boolean isRunning)
    {
	setTitle("Time left: " + timeLeft + " Player: " + playerScore + "  Computer: " + computerScore + " Still running:" + isRunning);
    }

}