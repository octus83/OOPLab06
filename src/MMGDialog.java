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
public abstract class MMGDialog extends JDialog implements ActionListener
{
	//Components Standart
	protected String 		titleText 		= "MMGDialog";
	protected JPanel 		header 			= new JPanel();
	protected JLabel 		headerLabel 	= new JLabel();
	protected JPanel		buttonContainer = new JPanel(new GridLayout(1,2));
	protected JPanel		placeholder		= new JPanel();
	protected JButton 		btnOk			= new JButton("Ok");
	
	//Components Options
	protected JButton		btnIncr;
	protected JButton 		btnDecr;
	protected JPanel 		levelContainer;
	protected JLabel		choosenLevel;
	protected JPanel		colorHeader;
	protected JLabel		colorHeaderLabel;
	protected JPanel		colorContainer;
	protected JRadioButton 	rbRed;
	protected JRadioButton 	rbGreen;
	protected JRadioButton 	rbBlue;
	protected ButtonGroup 	colorRbGroup;
	
	/**
	 * Default Constructor
	 */
	public MMGDialog()
	{
		this.setModal(true);
		this.setTitle(titleText);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
		setLayout(new GridLayout(5, 1));
		setSize(200, 150);
		setLocation(350, 350);
		addComponentsAndListeners();		
	}
	
	protected boolean showDialog()
	{
		btnOk.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
				
			}
		});
		buttonContainer.add(placeholder);
		buttonContainer.add(btnOk);
		add(buttonContainer);
		setVisible(true);
		return true;
	}
	
	public abstract void actionPerformed(ActionEvent ae);	
	abstract protected void addComponentsAndListeners();

}
