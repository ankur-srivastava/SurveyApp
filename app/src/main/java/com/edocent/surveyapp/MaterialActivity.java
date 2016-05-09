package com.edocent.surveyapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MaterialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_material);

        String[] inputData = {"Hello Sam", "Hello John", "Hello Riki"};

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sample_recycler);

        SampleAdapter adapter = new SampleAdapter(inputData);
        adapter.setListener(new SampleAdapter.Listener() {
            @Override
            public void onClick(int position) {
                //Add code here
            }
        });
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }

}
