import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


@SuppressWarnings("serial")
public class EndDialog extends JDialog implements ActionListener
{
	private final String 	titleText 			= "MarcusMiniGame";
	private final String 	headerText 			= "Schwierigkeitsstufe";
	private final String 	headerColorText 	= "Farbe";
	
	private JPanel 			header 				= new JPanel();
	private JLabel 			headerLabel 		= new JLabel(headerText);
	private JPanel 			levelContainer 		= new JPanel(new GridLayout(1,3));
	private JButton			btnIncr 			= new JButton("+");
	private JButton 		btnDecr 			= new JButton("-");
	private JLabel			choosenLevel 		= new JLabel("1");
	private JPanel			colorHeader			= new JPanel();
	private JLabel			colorHeaderLabel 	= new JLabel(headerColorText);
	private JPanel			colorContainer 		= new JPanel(new GridLayout(1,3));
	private JRadioButton 	rbRed	 			= new JRadioButton("Rot");
	private JRadioButton 	rbGreen	 			= new JRadioButton("Grün");
	private JRadioButton 	rbBlue	 			= new JRadioButton("Blau");
	private ButtonGroup 	colorRbGroup 		= new ButtonGroup();
	private JPanel			buttonContainer 	= new JPanel(new GridLayout(1,2));
	private JPanel			placeholder			= new JPanel();
	private JButton			btnOk				= new JButton("Ok");
	
	protected int 			selectedDifficulty 	= 1;
	boolean 				submitted = false;
	
	public EndDialog()
	{
		super();
		this.setModal(true);
		//setUndecorated(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(titleText);
		setLayout(new GridLayout(5, 1));
		addComponentsAndListeners();
		setSize(200, 150);
		setLocation(350, 350);
		setVisible(true);
		
	}
	
	public boolean showDialog()
	{
		
		return true;
	}
	
	private void addComponentsAndListeners()
	{
		//header
		add(header.add(headerLabel));
		
		//LevelContainer
		btnDecr.addActionListener(this);
		btnIncr.addActionListener(this);
		levelContainer.add(btnDecr);
		levelContainer.add(choosenLevel);
		levelContainer.add(btnIncr);		
		add(levelContainer);
		
		//colorHeader
		add(colorHeader.add(colorHeaderLabel));
		
		//colorContainer
		colorRbGroup.add(rbRed);
		colorRbGroup.add(rbGreen);
		colorRbGroup.add(rbBlue);
		colorContainer.add(rbRed);
		colorContainer.add(rbGreen);
		colorContainer.add(rbBlue);
		add(colorContainer);
		
		//buttonContainer
		btnOk.addActionListener(this);
		buttonContainer.add(placeholder);
		buttonContainer.add(btnOk);
		add(buttonContainer);
	}
	
	public void setTitle()
	{
		
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
		else if (ae.getSource() == btnOk)
		{
			submitted = true;
			setVisible(false);
		}		
	}
}
