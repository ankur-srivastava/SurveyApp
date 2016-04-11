package com.edocent.surveyapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ankursrivastava on 4/2/16.
 */
public class SurveyDBHelper extends SQLiteOpenHelper {

    static final String DB_NAME="surveydb";
    static final int DB_VERSION=2;

    /*Database table and column names*/
    public static final String SURVEY_TABLE="survey";
    public static final String SURVEY_TABLE_NAME_COLUMN="name";
    public static final String SURVEY_TABLE_EMAIL_COLUMN="email";
    public static final String SURVEY_TABLE_AGE_COLUMN="age";
    public static final String SURVEY_TABLE_LICENSE_COLUMN="age";

    public SurveyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        onUpgrade(db, 0, DB_VERSION);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion < 1){

            db.execSQL("CREATE TABLE "+SURVEY_TABLE+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            +SURVEY_TABLE_NAME_COLUMN+" TEXT, "
                            +SURVEY_TABLE_EMAIL_COLUMN+" TEXT, "
                            +SURVEY_TABLE_AGE_COLUMN+" INTEGER);"
            );

        }
        if(oldVersion < 2){
            //Perform Updates
            db.execSQL("ALTER TABLE "+SURVEY_TABLE+" ADD COLUMN "+SURVEY_TABLE_LICENSE_COLUMN+" TEXT");
        }
    }

    /*
    * query(String table,
	  String[] columns,
	  String selection,
	  String[] selectionArgs,
	  String groupBy,
	  String having,
	  String orderBy,
	  String limit)
    * */
    public Cursor getSurveyData(SQLiteDatabase db){

        // Define a projection that specifies which columns from the database you will actually use after this query.

        String[] projection = {
                "_id",
                SURVEY_TABLE_NAME_COLUMN,
                SURVEY_TABLE_EMAIL_COLUMN,
                SURVEY_TABLE_AGE_COLUMN
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = "_id" + " DESC";

        return db.query(
                SURVEY_TABLE,
                projection,
                null, null, null, null,
                sortOrder
        );
    }
}
