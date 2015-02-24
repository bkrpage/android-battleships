package uk.co.bkrpage.battleshipsi7709331;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HighScoreDB extends SQLiteOpenHelper{ 
    
    static final String dbName = "HighScores";
    static final String scoreTable = "Scores";
    static final String attID = "ScoreID";
    static final String attName = "ScoreName";
    static final String attScore = "Score";
    
    static final String viewScore = "ViewScore";

    public HighScoreDB(Context context) {
    	  super(context, dbName, null,1); 
    	  }
    
    public void onCreate(SQLiteDatabase db) {
    	  
    	// This creates score table
    	db.execSQL("CREATE TABLE " + scoreTable + " ("
    			+ attID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
    			+ attName + " TEXT, "
    			+ attScore +" INTEGER NOT NULL);"
    	);
    	  
    }
    
    public SQLiteDatabase openDB(SQLiteDatabase db) {
        db = this.getWritableDatabase();
        return db;
    }
    
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	  
    	  db.execSQL("DROP TABLE IF EXISTS " + scoreTable);
    	  
    	  onCreate(db);
    }
    
	public void addScore(String player, int score) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(attName, player);
		cv.put(attScore, score);
		db.insert(scoreTable, null, cv);

		db.close();
	}
	
	public int getHighScore(){
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + scoreTable
				+ " ORDER BY " + attScore + " DESC;", null);

		int score = 0;

		if (cursor.moveToFirst()) {
			score = Integer.parseInt(cursor.getString(2));
		}

		cursor.close();
		
		return score;
	}
	
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
	
	public void resetScores(){
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		db.execSQL("DROP TABLE IF EXISTS " + scoreTable);
		
    	db.execSQL("CREATE TABLE " + scoreTable + " ("
    			+ attID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
    			+ attName + " TEXT, "
    			+ attScore +" INTEGER NOT NULL);"
    	);

		db.close();
	}
}
