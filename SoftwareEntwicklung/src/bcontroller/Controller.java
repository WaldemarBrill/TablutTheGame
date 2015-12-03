package bcontroller;


import cmodel.*;
public class Controller{
	
	private Gamefield gamefield;
	private Rules rule;
	private HitRule hitrule;
	private boolean playerTurn = true;
	private boolean winGameAttack = false;
	private HitRuleKing hrk;
	
	public Controller(int sizeOfMatrix){
		gamefield = new Gamefield(sizeOfMatrix);
		rule = new Rules();
		hitrule = new HitRule();
		hrk = new HitRuleKing();
	}
		
	public void printField(){
		for (int i = 0; i < gamefield.getSizeOfGameField(); i++){
			for (int j = 0; j< gamefield.getSizeOfGameField(); j++){
				if((j % gamefield.getSizeOfGameField()) == 0)
					System.out.println("\n");
				System.out.print(gamefield.getField(j, i));
			}
		}
	}
	
	public void move(int xStart, int yStart, int xZiel, int yZiel){
		Stone drawStone = gamefield.getField(xStart, yStart).getCharakter();
		Stone changeStone = gamefield.getField(xZiel, yZiel).getCharakter();
		if(rule.yourTurn(playerTurn, gamefield, xStart, yStart)
				&& rule.drawRules(gamefield, drawStone, changeStone, xStart, xZiel, yStart, yZiel)){
			gamefield.getField(xStart, yStart).setCharakter(changeStone);
			gamefield.getField(xZiel, yZiel).setCharakter(drawStone);
			gamefield = hitrule.hit(gamefield, xZiel, yZiel);
//			winGameAttack = hrk;
			playerTurn = !playerTurn;
		}
	}
	
	
	public boolean winGame(){
		// Siegbedingung f�r den Verteidiger
		if((gamefield.getField(gamefield.getSizeOfGameField()-1, gamefield.getSizeOfGameField()-1).getCharakter().getIsKing())
				|| (gamefield.getField(0, gamefield.getSizeOfGameField()-1).getCharakter().getIsKing())
				|| (gamefield.getField(gamefield.getSizeOfGameField()-1, 0).getCharakter().getIsKing())
				|| (gamefield.getField(0, 0).getCharakter().getIsKing())){
			System.out.println("\nVerteidiger hat gewonnen!\n");
			return false;
			
		} else
		return true;
	}
	
	public boolean winGameAttack(){
		if(winGameAttack){
			return false;
		}
		return true;
	}
	
	public boolean getWinGameAttack(){
		return winGameAttack;
	}
	
	public void setWinGameAttack(boolean winGameAttack){
		this.winGameAttack = winGameAttack;
	}
}