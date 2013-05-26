import java.util.Timer;

import MiniGamePackage.MiniGame;
import MiniGamePackage.Sprite;

@SuppressWarnings("serial")
public class MyMiniGame extends MiniGame
{
    final int NR_OF_FIELDS = 12;
    private int[] fieldStatus = new int[NR_OF_FIELDS]; // 0: not set, 1: computer , 2:player
    private boolean[] fieldVisibleStatus = new boolean[NR_OF_FIELDS]; // true=visible, false=hidden

    private Sprite[] fieldSprites = getSprites(0);
    private Sprite[] computerStoneSprites = getSprites(3);
    private Sprite[] playerStoneSprites = getSprites(4);

    private Sprite computerSprite = getSprite(1, 0);
    private Sprite playerSprite = getSprite(2, 0);

    private int playerPosition = 0;
    private int computerPosition = 0;   
    protected int level;
    protected int playerScore;
    protected int computerScore;
 
    public MyMiniGame()
    { 
    	getBackgroundPicture().paintImage("MoorhuhnfeldMH1.jpg"); 	
		fieldSprites[0].paintImage("huhn.png");
		//fieldSprites[0].paintEllipse(5, 5, 22, 22, -1, 0, 0, 0);
		//playerStoneSprites[0].paintEllipse(5, 5, 22, 22, -1, 255, 128, 0);
		//computerStoneSprites[0].paintEllipse(5, 5, 22, 22, -1, 0, 0, 255);	
		computerSprite.paintRectangle(5, 0, 22, 32, -1, 255, 0, 255);		
    }
   
    public void startGame()
    {
    	StartDialog entryDialog = new StartDialog();
    	
    	if(entryDialog.showDialog())
    	{
    		level = entryDialog.selectedDifficulty; 
    		newGame(level);
    		playerSprite.paintRectangle(5, 0, 22, 32, -1, entryDialog.playerColorR, entryDialog.playerColorG, entryDialog.playerColorB);
    		//playerSprite.paintImage("Pistole.png");
    	}
    }
     
    @Override
    protected void initGame()
    {	
		for (int i = 0; i < NR_OF_FIELDS; i++)
		{
		    fieldSprites[i].setPosition(304, i * 40 + 100);
		    //playerStoneSprites[i].setPosition(304, i * 40 + 100);
		    //computerStoneSprites[i].setPosition(304, i * 40 + 100);
		    //playerStoneSprites[i].dontShow();
		    //computerStoneSprites[i].dontShow();
	
		    fieldStatus[i] = 0;
		}

		playerPosition = 0;
		computerPosition = 0;
		updatePositions();
    }

    @Override
    protected void gameHasStarted()
    {
    	hideFieldSprites();
    }
    
    protected void showRandomFieldSprites(int amount)
    {   	
    	hideFieldSprites();
    	
    	for(int i = 0; i <= amount; i++)
    	{
    		int rdNo = getRandomNr(0, NR_OF_FIELDS-1);
    		
    		if(fieldStatus[rdNo] == 0)
    		{
    			fieldSprites[rdNo].show();
        		fieldVisibleStatus[rdNo] = true;
    		}  		
    	}
    }
    
    private void hideAllSprites()
    {
    	computerSprite.dontShow();
    	playerSprite.dontShow();   	
    	hideFieldSprites();
    }
    
    private void hideFieldSprites()
    {
    	for (int i = 0; i < NR_OF_FIELDS; i++)
		{
		    fieldSprites[i].dontShow();
		    fieldVisibleStatus[i] = false;
		}
    }
    
    @Override
    protected void gameHasFinished()
    {
    	hideAllSprites();
    	EndDialog endDialog = new EndDialog();
    	endDialog.setScoresandLevel(getCurrentPlayerScore(), getCurrentComputerScore(), level);
    	
    	if(endDialog.showDialog())
    	{
    		newGame(endDialog.selectedDifficulty);
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
					fieldSprites[computerPosition].dontShow();
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
					fieldSprites[playerPosition].dontShow();
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
    	computerSprite.setPosition(50, computerPosition * 40 + 100);
    	playerSprite.setPosition(550, playerPosition * 40 + 100);
    }
}
