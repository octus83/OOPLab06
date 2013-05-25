import java.awt.Image;
import java.awt.Toolkit;

import MiniGamePackage.MiniGame;
import MiniGamePackage.Sprite;

@SuppressWarnings("serial")
public class MyMiniGame extends MiniGame
{
    final int NR_OF_FIELDS = 12;
    private int[] fieldStatus = new int[NR_OF_FIELDS]; // 0: not set, 1: computer , 2:player

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
		fieldSprites[0].paintImage("huhn.jpg");
		//fieldSprites[0].paintEllipse(5, 5, 22, 22, -1, 0, 0, 0);
		playerStoneSprites[0].paintEllipse(5, 5, 22, 22, -1, 255, 128, 0);
		computerStoneSprites[0].paintEllipse(5, 5, 22, 22, -1, 0, 0, 255);	
		computerSprite.paintRectangle(5, 0, 22, 32, -1, 255, 0, 255);		
    }
   
    public void startGame()
    {
    	StartDialog entryDialog = new StartDialog();
    	
    	if(entryDialog.showDialog())
    	{
    		level = entryDialog.selectedDifficulty; 
    		newGame(entryDialog.selectedDifficulty);
    		playerSprite.paintRectangle(5, 0, 22, 32, -1, entryDialog.playerColorR, entryDialog.playerColorG, entryDialog.playerColorB);
    		//playerSprite.paintImage("Pistole.png");
    	}
    }
     
    @Override
    protected void initGame()
    {	
		/*for (int i = 0; i < NR_OF_FIELDS; i++)
		{
		    fieldSprites[i].setPosition(304, i * 40 + 100);
		    playerStoneSprites[i].setPosition(304, i * 40 + 100);
		    computerStoneSprites[i].setPosition(304, i * 40 + 100);
		    playerStoneSprites[i].dontShow();
		    computerStoneSprites[i].dontShow();
	
		    fieldStatus[i] = 0;
		}*/

		playerPosition = 0;
		computerPosition = 0;
		updatePositions();
    }

    @Override
    protected void gameHasStarted()
    {
    	System.out.println("JETZT");
    }
    
    protected void showRandomFieldSprites(int amount)
    {
    	for(int i = 0; i <= amount; i++)
    	{
    		fieldSprites[getRandomNr(0, NR_OF_FIELDS)].setPosition(304, getRandomNr(0, NR_OF_FIELDS) * 40 + 100);      	
    	}
    	//updatePositions();
    }
    
    @Override
    protected void gameHasFinished()
    {
    	hideAllSprites();
    	EndDialog endDialog = new EndDialog();
    	endDialog.setScoresandLevel(playerScore, computerScore, level);
    	if(endDialog.showDialog())
    	{
    		newGame(endDialog.selectedDifficulty);
    	} 
    }
    
    private void hideAllSprites()
    {
    	computerSprite.dontShow();
    	playerSprite.dontShow();
    	
    	for (int i = 0; i < NR_OF_FIELDS; i++)
		{
		    fieldSprites[i].dontShow();
		    playerStoneSprites[i].dontShow();
		    computerStoneSprites[i].dontShow();
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
				if(fieldStatus[computerPosition] == 2)
			    {
					fieldStatus[computerPosition] = 0;
					fieldSprites[computerPosition].setPosition(fieldSprites[computerPosition].getXPosition() + 20, fieldSprites[computerPosition].getYPosition());
					fieldSprites[computerPosition].show();
			    }
				else
				{
					fieldStatus[computerPosition] = 1;
					fieldSprites[computerPosition].setPosition(fieldSprites[computerPosition].getXPosition() + 20, fieldSprites[computerPosition].getYPosition());
					//computerStoneSprites[computerPosition].show();
				}
			    playerStoneSprites[computerPosition].dontShow();		
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
				/*if(fieldStatus[playerPosition] == 1)
			    {
					fieldStatus[playerPosition] = 0;
					fieldSprites[playerPosition].setPosition(fieldSprites[playerPosition].getXPosition() - 20, fieldSprites[playerPosition].getYPosition());
					fieldSprites[playerPosition].show();
			    }
				else
				{
					fieldStatus[playerPosition] = 2;
					fieldSprites[playerPosition].setPosition(fieldSprites[playerPosition].getXPosition() - 20, fieldSprites[playerPosition].getYPosition());
					//playerStoneSprites[playerPosition].show();
				}*/
			    computerStoneSprites[playerPosition].dontShow();
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
		return cs * 100;
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
		return ps * 100;
    }

    @Override
    public String getName()
    {
    	return "MMG - MarcusMiniGame";
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
