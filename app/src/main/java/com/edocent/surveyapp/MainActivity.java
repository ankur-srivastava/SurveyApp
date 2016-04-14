package com.edocent.surveyapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.edocent.surveyapp.database.SurveyDBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    EditText userNameId;
    EditText userEmailId;
    EditText userAgeId;
    Button submitDataId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameId = (EditText)findViewById(R.id.userNameId);
        userEmailId = (EditText)findViewById(R.id.userEmailId);
        userAgeId = (EditText)findViewById(R.id.userAgeId);
        submitDataId = (Button)findViewById(R.id.submitDataId);

        submitDataId.setOnClickListener(this);

        //Sample Code to query the database
        /*
        try {
            SurveyDBHelper surveyDBHelper = new SurveyDBHelper(this);
            SQLiteDatabase db = surveyDBHelper.getReadableDatabase();

            Cursor cursor = surveyDBHelper.getSurveyData(db);

            if (cursor.moveToFirst()) {
                //Get the data
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                Log.v(TAG, "Name "+name);
            }

            CursorAdapter ca = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{SurveyDBHelper.SURVEY_TABLE_NAME_COLUMN},
                    new int[]{android.R.id.text1},
                    0);

        }catch (SQLiteException e){
            Log.v(TAG, "Exception "+e.getMessage());
        }
        */
        //End


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //Close the Cursor here
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Log.v(TAG, "Submit Button Clicked");
        if(v.getId() == R.id.submitDataId){
            Log.v(TAG, "On Submit Data Id");

            //Insert Logic moved to AsyncTask

            new SurveyDBAsyncTask().execute("");
        }
    }

    @Override
    public void onRestart(){
        super.onRestart();

        Log.v(TAG, "Check for data refresh");
        /*
        try {
            SurveyDBHelper surveyDBHelper = new SurveyDBHelper(this);
            SQLiteDatabase db = surveyDBHelper.getReadableDatabase();

            Cursor cursor = surveyDBHelper.getSurveyData(db);

            ListView lv = (ListView) findViewById(R.id.displaySurveyDataId);

            CursorAdapter ca = (CursorAdapter)lv.getAdapter();
            ca.changeCursor(cursor);

        }catch (SQLiteException e){
            Log.v(TAG, "Exception "+e.getMessage());
        }
        */
    }

    //Create a new AsyncTask
    private class SurveyDBAsyncTask extends AsyncTask<String, Void, Long>{

        ContentValues cv;

        @Override
        protected void onPreExecute() {
            Log.v(TAG, "Data entered by the user is "+userNameId.getText()+" "+userAgeId.getText()+" "+userEmailId);

            cv = new ContentValues();
            cv.put(SurveyDBHelper.SURVEY_TABLE_NAME_COLUMN, userNameId.getText().toString());
            cv.put(SurveyDBHelper.SURVEY_TABLE_EMAIL_COLUMN, userEmailId.getText().toString());
            cv.put(SurveyDBHelper.SURVEY_TABLE_AGE_COLUMN, userAgeId.getText().toString());

            super.onPreExecute();
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p/>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Long doInBackground(String... params) {
            long id = 0;
            Log.v(TAG, "In doInBackground");
            try {
                SurveyDBHelper surveyDBHelper = new SurveyDBHelper(MainActivity.this);
                SQLiteDatabase db = surveyDBHelper.getReadableDatabase();

                id = db.insert(SurveyDBHelper.SURVEY_TABLE, null, cv);

                db.close();
            }catch (SQLiteException e){
                Log.v(TAG, "Exception "+e.getMessage());
            }
            return id;
        }

        @Override
        protected void onPostExecute(Long id) {
            super.onPostExecute(id);
            Log.v(TAG, "In onPostExecute and id is "+id);
            //Toast.makeText(getApplicationContext(), "Insert Success", Toast.LENGTH_SHORT).show();
        }
    }
}
