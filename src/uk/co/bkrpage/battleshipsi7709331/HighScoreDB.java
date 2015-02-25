package uk.co.bkrpage.battleshipsi7709331;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HighScoreDB extends SQLiteOpenHelper{ 
    
    static final String dbName = "HighScores", scoreTable = "Scores", attID = "ScoreID",
    		attName = "ScoreName", attScore = "Score";
    
    static final String viewScore = "ViewScore";

    public HighScoreDB(Context context) {
    	  super(context, dbName, null,1); 
    	  }
    
    @Override
	public void onCreate(SQLiteDatabase db) {
    	// This creates score table
    	db.execSQL("CREATE TABLE " + scoreTable + " ("
    			+ attID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
    			+ attName + " TEXT, "
    			+ attScore +" INTEGER NOT NULL);"
    	);
    	  
    }
    
    // Executed if I upgrade the database above version 1.
    @Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	  db.execSQL("DROP TABLE IF EXISTS " + scoreTable);
    	  onCreate(db);
    }
    
    /**
     * Adds the score to the database
     * @param player Players to be named in the score
     * @param score Score to be entered into the database.
     */
	public void addScore(String player, int score) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(attName, player);
		cv.put(attScore, score);
		db.insert(scoreTable, null, cv);

		db.close();
	}
	
	/**
	 * @return An array list containing cores in Descending order.
	 */
	public ArrayList<Integer> getScores() {

		ArrayList<Integer> highScore = new ArrayList<Integer>();

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + scoreTable
				+ " ORDER BY " + attScore + " DESC;", null);

		int score = 0;

		if (cursor.moveToFirst()) {
			do {
				score = Integer.parseInt(cursor.getString(2));
				highScore.add(score);
			} while (cursor.moveToNext());
		}

		cursor.close();

		return highScore;

	}
	
	/**
	 * 
	 * @return Array List of score names ordered by scores in descending order.
	 */
	public ArrayList<String> getScoreNames(){
		
		ArrayList<String> name = new ArrayList<String>();
		
	   SQLiteDatabase db = this.getReadableDatabase();
	   Cursor cursor = db.rawQuery("SELECT * FROM " + scoreTable + " ORDER BY " + attScore + " DESC", null);
	   
	   String scoreName;
	   
	   if(cursor.moveToFirst()){
		   do {
			   scoreName = cursor.getString(1);
			   name.add(scoreName);
		   } while (cursor.moveToNext());
	   }
	   
	   cursor.close();
	   
	   return name;
	   
	}
	
	/**
	 * Deletes the score table and re-creates an empty one.
	 */
	public void resetScores(){
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		db.execSQL("DROP TABLE IF EXISTS " + scoreTable);
		// re-creates the table to prevent errors.
    	db.execSQL("CREATE TABLE " + scoreTable + " ("
    			+ attID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
    			+ attName + " TEXT, "
    			+ attScore +" INTEGER NOT NULL);"
    	);

		db.close();
	}
}
