package com.edocent.surveyapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.edocent.surveyapp.database.SurveyDBHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Sample Code to query the database
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

            cursor.close();
            db.close();
        }catch (SQLiteException e){
            Log.v(TAG, "Exception "+e.getMessage());
        }
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
}
