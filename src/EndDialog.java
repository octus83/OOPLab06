import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class EndDialog extends MMGDialog
{
	protected int 	selectedDifficulty 	= 1;
	
	public void setScores(int playerScore, int computerScore)
	{	

		if(playerScore < computerScore)
		{
			headerLabel.setText("Leider verloren");
		}
		else if (playerScore > computerScore)
		{
			headerLabel.setText("Juhu gewonnen");
		}
		else
		{
			headerLabel.setText("Unentschieden, immerhin");
		}
	}
	
	@Override
	protected void addComponentsAndListeners()
	{
		//header
		add(header.add(headerLabel));		
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// TODO Auto-generated method stub
		
	}
}
