import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.Action;
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
	protected int			playerColorR		= 0;
	protected int			playerColorG        = 0;
	protected int 			playerColorB		= 0;
			
	@Override
	protected void addComponentsAndListeners()
	{	
		//Create Component Options Objects
		firstLabel 			= new JLabel(HEADERTEXT);
		secondRightButton 	= new JButton("+");
		secondLeftButton 	= new JButton("-");
		second 				= new JPanel(new GridLayout(1,3));
		secondMiddleLabel 	= new JLabel(""+selectedDifficulty);
		third				= new JPanel();
		thirdLabel 			= new JLabel(COLORHEADERTEXT);
		fourth 				= new JPanel(new GridLayout(1,3));
		radioButton1	 	= new JRadioButton("Rot");		
		radioButton2	 	= new JRadioButton("Grün");
		radioButton3	 	= new JRadioButton("Blau");
		buttonGroup 		= new ButtonGroup();
				
		//add Radiobuttons to Group
		buttonGroup.add(radioButton1);		
		buttonGroup.add(radioButton2);
		buttonGroup.add(radioButton3);
		radioButton1.setSelected(true);
		
		//addActionListener to Components
		secondLeftButton.addActionListener(this);
		secondRightButton.addActionListener(this);
		radioButton1.addActionListener(this);
		radioButton2.addActionListener(this);
		radioButton3.addActionListener(this);
		
		//add Options to Panels				
		first.add(firstLabel);	
		second.add(secondLeftButton);
		second.add(secondMiddleLabel);
		second.add(secondRightButton);		
		third.add(thirdLabel);
		fourth.add(radioButton1);
		fourth.add(radioButton2);
		fourth.add(radioButton3);
		
		//add Panels to Grid
		add(first);
		add(second);
		add(third);
		add(fourth);
	} 

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == secondRightButton)
		{
			if(selectedDifficulty < 10)
			{
				selectedDifficulty = Integer.parseInt(secondMiddleLabel.getText());
				selectedDifficulty++;
				secondMiddleLabel.setText(""+selectedDifficulty);
			}		    
		}
		else if (ae.getSource() == secondLeftButton)
		{
			if(selectedDifficulty > 1)
			{
				selectedDifficulty = Integer.parseInt(secondMiddleLabel.getText());
				selectedDifficulty--;
				secondMiddleLabel.setText(""+selectedDifficulty);
			}
		}		
		else if (ae.getSource() == radioButton1)
		{
			playerColorR = 255;
			playerColorG = 0;
			playerColorB = 0;
		}
		else if (ae.getSource() == radioButton2)
		{
			playerColorR = 0;
			playerColorG = 255;
			playerColorB = 0;
		}
		else if (ae.getSource() == radioButton3)
		{
			playerColorR = 0;
			playerColorG = 0;
			playerColorB = 255;
		}
		
	}
}
