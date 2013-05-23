import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class StartDialog extends MMGDialog
{
	private final String 	HEADERTEXT 			= "Schwierigkeitsstufe";
	private final String 	COLORHEADERTEXT 	= "Farbe";	
	protected int 			selectedDifficulty 	= 1;
			
	@Override
	protected void addComponentsAndListeners()
	{	
		//Create Component Options Objects
		btnIncr 			= new JButton("+");
		btnDecr 			= new JButton("-");
		levelContainer 		= new JPanel(new GridLayout(1,3));
		choosenLevel 		= new JLabel("1");
		colorHeader			= new JPanel();
		colorHeaderLabel 	= new JLabel();
		colorContainer 		= new JPanel(new GridLayout(1,3));
		rbRed	 			= new JRadioButton("Rot");
		rbGreen	 			= new JRadioButton("Grün");
		rbBlue	 			= new JRadioButton("Blau");
		colorRbGroup 		= new ButtonGroup();
		
		//header
		headerLabel.setText(HEADERTEXT);
		add(header.add(headerLabel));
		
		//LevelContainer
		btnDecr.addActionListener(this);
		btnIncr.addActionListener(this);
		levelContainer.add(btnDecr);
		levelContainer.add(choosenLevel);
		levelContainer.add(btnIncr);		
		add(levelContainer);
		
		//colorHeader
		colorHeaderLabel.setText(COLORHEADERTEXT);
		add(colorHeader.add(colorHeaderLabel));
		
		//colorContainer
		colorRbGroup.add(rbRed);
		colorRbGroup.add(rbGreen);
		colorRbGroup.add(rbBlue);
		colorContainer.add(rbRed);
		colorContainer.add(rbGreen);
		colorContainer.add(rbBlue);
		add(colorContainer);		
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == btnIncr)
		{
			if(selectedDifficulty < 10)
			{
				selectedDifficulty = Integer.parseInt(choosenLevel.getText());
				selectedDifficulty++;
				choosenLevel.setText(""+selectedDifficulty);
			}		    
		}
		else if (ae.getSource() == btnDecr)
		{
			if(selectedDifficulty > 1)
			{
				selectedDifficulty = Integer.parseInt(choosenLevel.getText());
				selectedDifficulty--;
				choosenLevel.setText(""+selectedDifficulty);
			}
		}
		
	}
}
