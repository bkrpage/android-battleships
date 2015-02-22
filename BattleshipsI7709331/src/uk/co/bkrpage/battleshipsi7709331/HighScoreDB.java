package uk.co.bkrpage.battleshipsi7709331;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class HighScoreDB extends SQLiteOpenHelper{ 
    
    public HighScoreDB(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	private static final int DATABASE_VERSION = 1;
 
    private static final String DATABASE_NAME = "highScores";

    private static final String TABLE_HIGHSCORES = "highScores"; 

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SCORE = "score";
     
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HIGHSCORES_TABLE = "CREATE TABLE " + TABLE_HIGHSCORES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SCORE + " TEXT" + ")";
        db.execSQL(CREATE_HIGHSCORES_TABLE);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGHSCORES);
 
        onCreate(db);
    }

    public void addScore(String name, int score) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); 
        values.put(KEY_SCORE, score); // Contact Phone Number
     
        // Inserting Row
        db.insert(TABLE_HIGHSCORES, null, values);
        db.close(); // Closing database connection
    }
    
    public int getScoresCount(){
    	String countQuery = "SELECT  * FROM " + TABLE_HIGHSCORES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
     
  
    
}
