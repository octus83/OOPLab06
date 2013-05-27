import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * 
 * @author mpfingsten
 *@version 1.0
 *
 *Provides an extended JDialog with GridLayout and OK-Button and optional Components
 * _______________________
 *|__________________- O X| WindowTitle
 *|_______________________| Panel first
 *|_______|_______|_______| Panel secondLeft, secondMiddle, secondRight
 *|_______________________|	Panel third
 *|_______________________| Panel fourth
 *|___________|___________|	Panel fifthLeft, fifthRight
 *
 */

@SuppressWarnings("serial")
public abstract class MMGDialog extends JDialog implements ActionListener
{
	//Dialog Panels	Standart
	protected JPanel 		first 			= new JPanel();	
	protected JPanel 		second			= new JPanel(new GridLayout(1,3));
	protected JPanel 		secondLeft		= new JPanel();
	protected JPanel 		secondMiddle	= new JPanel();
	protected JPanel 		secondRight		= new JPanel();
	protected JPanel		third			= new JPanel();
	protected JPanel		fourth			= new JPanel();
	protected JPanel		fifth 			= new JPanel(new GridLayout(1,2));
	protected JPanel		fifthLeft		= new JPanel();
	protected JButton 		fiftRightButton	= new JButton("Ok");
		
	//Components Options
	protected String 		titleText 		= "MMGDialog";
	protected JLabel 		firstLabel;
	protected JLabel 		secondLabel;
	protected JButton		secondRightButton;
	protected JButton 		secondLeftButton;	
	protected JLabel		secondMiddleLabel;	
	protected JLabel		thirdLabel;	
	protected JLabel		fourthLabel;
	protected JRadioButton 	radioButton1;
	protected JRadioButton 	radioButton2;
	protected JRadioButton 	radioButton3;
	protected ButtonGroup 	buttonGroup;
	
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
	}
	
	/**
	 * Adds Components to Dialog
	 * @see addComponentsAndListeners
	 * @return true when 'OK'-button is hit
	 */
	protected boolean showDialog()
	{
		addComponentsAndListeners();	
		fiftRightButton.addActionListener(new ActionListener()
		{			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);			
			}
		});
		fifth.add(fifthLeft);
		fifth.add(fiftRightButton);
		add(fifth);
		setVisible(true);
		return true;
	}
	
	abstract public void actionPerformed(ActionEvent ae);	
	abstract protected void addComponentsAndListeners();
}
