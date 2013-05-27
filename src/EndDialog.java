import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class EndDialog extends MMGDialog
{
	private final String 	HEADERTEXT 			= "Spielende";
	private String			resultText;
	protected int 			selectedDifficulty 	= 1;
	protected int 			playerScore			= 0;
	protected int 			computerScore		= 0;
	protected int 			finishedLevel 		= 0;
	
	@Override
	protected void addComponentsAndListeners()
	{
		calculateWinLoose();
		
		//Create Component Options Objects
		firstLabel 		= new JLabel(HEADERTEXT+" von Level: "+finishedLevel);
		second			= new JPanel(new GridLayout(1,3));
		secondLabel 	= new JLabel(resultText);
		fourthLabel 	= new JLabel();
				
		//Panel First
		first.add(firstLabel);	
		second.add(secondLabel);	
		fourth.add(fourthLabel);
		
		//add to Grid
		add(first);
		add(second);
		add(third);
		add(fourth);
	}
	
	private void calculateWinLoose()
	{
		if(playerScore < computerScore)
		{
			resultText ="Oh nein, leider verloren";
		}
		else if (playerScore > computerScore)
		{
			resultText = "JUHU, du hast gewonnen.";
		}
		else
		{
			resultText = "Immerhin Gleichstand, toll!";
		}
	}
	
	public void setScoresandLevel(int playerScore, int computerScore, int finishedlevel)
	{
		this.playerScore = playerScore;
		this.computerScore = computerScore;
		this.finishedLevel = finishedlevel;
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// TODO Auto-generated method stub
		
	}
}
