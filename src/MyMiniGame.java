import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

import MiniGamePackage.MiniGame;
import MiniGamePackage.Sprite;

@SuppressWarnings("serial")
public class MyMiniGame extends MiniGame
{
    private final int 	NR_OF_FIELDS 		= 12;
    private int[] 		fieldStatus 		= new int[NR_OF_FIELDS]; // 0: not set, 1: computer , 2:player
    private boolean[] 	fieldVisibleStatus 	= new boolean[NR_OF_FIELDS]; // true=visible, false=hidden

    private Sprite[] 	birds 				= getSprites(0);
    private Sprite 		computerGun 		= getSprite(1, 0);
    private Sprite 		playerGun 			= getSprite(2, 0);

    private int 		playerPosition = 0;
    private int 		computerPosition = 0;   
    protected int 		level;
    
    private Sequencer backgroundSound;
    InputStream midiFile = MyMiniGame.class.getResourceAsStream("rmb.mid"); 

 
    public MyMiniGame()
    { 
    	getBackgroundPicture().paintImage("MoorhuhnfeldMH1.jpg"); 	
		birds[0].paintImage("huhn.png");	
		computerGun.paintRectangle(5, 0, 22, 32, -1, 255, 0, 255);	
		
		try
		{
			backgroundSound = MidiSystem.getSequencer();
			backgroundSound.setSequence( MidiSystem.getSequence(midiFile) );
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
   
    public void startGame()
    {
    	StartDialog entryDialog = new StartDialog();
    	
    	if(entryDialog.showDialog())
    	{
    		playerGun.paintRectangle(5, 0, 22, 32, -1, entryDialog.playerColorR, entryDialog.playerColorG, entryDialog.playerColorB);
    		level = entryDialog.selectedDifficulty; 
    		newGame(level);   		
    	}
    }
     
    @Override
    protected void initGame()
    {		
		try
		{
			backgroundSound.open();
		}
		catch (MidiUnavailableException e)
		{
				e.printStackTrace();
		}

    	hideFieldSprites();
		for (int i = 0; i < NR_OF_FIELDS; i++)
		{			
		    birds[i].setPosition(304 + getRandomNr(-30, 30), i * 40 + 100);	
		    birds[i].dontShow();
		    fieldVisibleStatus[i] = false;
		    fieldStatus[i] = 0;
		}

		playerPosition = 0;
		computerPosition = 0;
		updatePositions();
    }

    @Override
    protected void gameHasStarted()
    {
		backgroundSound.start(); 
    	hideFieldSprites();
    }
    
    @Override
    protected void gameHasFinished()
    {
    	hideAllSprites();
    	EndDialog endDialog = new EndDialog();
    	endDialog.setScoresandLevel(getCurrentPlayerScore(), getCurrentComputerScore(), level);
    	
    	if(endDialog.showDialog())
    	{
    		hideFieldSprites();
    		level = endDialog.finishedLevel + 1;
    		newGame(level);
    		backgroundSound.stop();
    	} 
    }
    
    protected void showRandomFieldSprites(int amount)
    {   	
    	hideFieldSprites();
    	
    	for(int i = 0; i <= amount; i++)
    	{
    		int rdNo = getRandomNr(0, NR_OF_FIELDS-1);
    		
    		if(fieldStatus[rdNo] == 0)
    		{
    			birds[rdNo].show();
        		fieldVisibleStatus[rdNo] = true;
    		}  		
    	}
    }
    
    private void hideAllSprites()
    {
    	computerGun.dontShow();
    	playerGun.dontShow();   	
    	hideFieldSprites();
    }
    
    private void hideFieldSprites()
    {
    	for (int i = 0; i < NR_OF_FIELDS; i++)
		{
		    birds[i].dontShow();
		    fieldVisibleStatus[i] = false;
		}
    }
    
    @Override
    protected void computerMove(Action action)
    {
		switch (action)
		{
			case DOWN:
			    computerPosition = Math.min(computerPosition + 1, NR_OF_FIELDS - 1);
			    updatePositions();
			    break;
			case UP:
			    computerPosition = Math.max(computerPosition - 1, 0);
			    updatePositions();
			    break;
			case GO:
				if(fieldVisibleStatus[computerPosition])
				{
					fieldStatus[computerPosition] = 1;
					birds[computerPosition].dontShow();
					fieldVisibleStatus[computerPosition] =  false;
				}
			    break;
			default:
			    break;
		}
    }

    @Override
    protected void playerMove(Action action)
    {
		switch (action)
		{
			case DOWN:
			    playerPosition = Math.min(playerPosition + 1, NR_OF_FIELDS - 1);
			    updatePositions();
			    break;
			case UP:
			    playerPosition = Math.max(playerPosition - 1, 0);
			    updatePositions();
			    break;
			case GO:							
				if(fieldVisibleStatus[playerPosition])
				{
					fieldStatus[playerPosition] = 2;
					birds[playerPosition].dontShow();
					fieldVisibleStatus[playerPosition] = false;
				}

			    break;
			default:
			    break;
		}
    }

    @Override
    public int getCurrentComputerScore()
    {
		int cs = 0;
		for (int i = 0; i < fieldStatus.length; i++)
		{
		    if (fieldStatus[i] == 1)
		    {
		    	++cs;
		    }
		}
		return cs;
    }

    @Override
    public int getCurrentPlayerScore()
    {
		int ps = 0;
		for (int i = 0; i < fieldStatus.length; i++)
		{
		    if (fieldStatus[i] == 2)
		    {
		    	++ps;
		    }
		}
		return ps;
    }

    @Override
    public String getName()
    {
    	return "MarcusMoorhuhnGame";
    }

    @Override
    public int getNrOfPlayerGoActionsMax(int difficulty)
    {
    	return 10;
    }

    @Override
    public int getNrofComputerGoActions(int difficulty)
    {
    	return difficulty * 10;
    }

    private void updatePositions()
    {
    	computerGun.setPosition(50, computerPosition * 40 + 100);
    	playerGun.setPosition(550, playerPosition * 40 + 100);
    }
}
