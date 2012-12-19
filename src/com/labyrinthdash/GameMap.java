package com.labyrinthdash;

import android.util.Log;

/**
 * Creates and holds all the cells that make up the map
 * 
 * @author Isaac Doub
 */
public class GameMap {
	/** 2D array defining the cells in the map */
	public static GameCell[][] Map;
	/** Cell the player starts in */
	public static GameCell startCell;
	/** How many cells horizontally */
	private static int columns = 20;
	/** How many cells vertically */
	private static int rows = 20;
	
	/**
	 * Creates the game map. Goes through the 2D array filling it with blank
	 * cells, then replaces certain blank cells with specified platforms.
	 */
	public GameMap() {
		Map = new GameCell[columns][rows];

		for(int i=0; i<columns; i++) {
			for(int j=0; j<rows; j++) {
				Map[i][j] = new EmptyCell(i,j);
			}
		}

		Map[2][1] = new Platform(2,1);
		Map[2][2] = new Platform(2,2);
		Map[2][3] = new Platform(2,3);
		Map[2][4] = new Platform(2,4);
		Map[2][6] = new Platform(2,6);
		Map[2][7] = new Platform(2,7);
		Map[2][8] = new Platform(2,8);
		Map[2][9] = new Platform(2,9);
		Map[2][10] = new Platform(2,10);
			
		Map[1][4] = new Platform(1,4);
		Map[1][5] = new Platform(1,5);
		Map[1][6] = new Platform(1,6);		
	
		Map[3][5] = new Platform(3,5);
		Map[4][5] = new Platform(4,5);
		Map[5][5] = new Platform(5,5);
		Map[6][5] = new Platform(6,5);
	
		Map[3][4] = new Platform(3,4);
		Map[3][6] = new Platform(3,6);
		
		startCell = Map[2][1];
		
		Log.i("INFO", "New map created");
	}
	
	/**
	 * Finds and returns a cell in which the provided point lies.
	 * @param position screen position a cell will cover
	 * @return GameCell in which the point provided lies
	 */
	public static GameCell getCellContaining(Vector2D position) {
		GameCell tmpCell = Map[0][0];
		int x = (int)position.x/tmpCell.width;
		int y = (int)position.y/tmpCell.height;
		return Map[x][y];
	}
	
	/**
	 * Finds and returns an array of cells containing and surrounding the
	 * position provided. This is used to limit the number of cells the player
	 * calls collision on.
	 * @param position
	 * @return Array of GameCell surrounding the position provided
	 */
	public static GameCell[] getCellsInProximity(Vector2D position) {
		GameCell tmpCell = Map[0][0];
		int x = (int)position.x/tmpCell.width;
		int y = (int)position.y/tmpCell.height;
		GameCell[] returnable = null;
		if(x == 0) {
			if(y == 0) {
				returnable = new GameCell[]{Map[x][y], Map[x][y+1], Map[x+1][y], Map[x+1][y+1]};
			} else if(y == rows){
				returnable = new GameCell[]{Map[x][y-1], Map[x][y], Map[x+1][y-1], Map[x+1][y]};
			} else {
				returnable = new GameCell[]{Map[x][y-1], Map[x][y], Map[x][y+1], Map[x+1][y-1], Map[x+1][y], Map[x+1][y+1]};
			}
		} else if(x == columns){
			if(y == 0) {
				returnable = new GameCell[]{Map[x-1][y], Map[x-1][y+1], Map[x][y], Map[x][y+1]};
			} else if(y == rows){
				returnable = new GameCell[]{Map[x-1][y-1], Map[x-1][y], Map[x][y-1], Map[x][y]};
			} else {
				returnable = new GameCell[]{Map[x-1][y-1], Map[x-1][y], Map[x-1][y+1], Map[x][y-1], Map[x][y], Map[x][y+1]};
			}
		} else {
			if(y == 0) {
				returnable = new GameCell[]{Map[x-1][y], Map[x-1][y+1], Map[x][y], Map[x][y+1], Map[x+1][y], Map[x+1][y+1]};
			} else if(y == rows){
				returnable = new GameCell[]{Map[x-1][y-1], Map[x-1][y], Map[x][y-1], Map[x][y],  Map[x+1][y-1], Map[x+1][y]};
			} else {
				returnable = new GameCell[]{Map[x-1][y-1], Map[x-1][y], Map[x-1][y+1], Map[x][y-1], Map[x][y], Map[x][y+1], Map[x+1][y-1], Map[x+1][y], Map[x+1][y+1]};
			}
		}
		return returnable;
	}
}
