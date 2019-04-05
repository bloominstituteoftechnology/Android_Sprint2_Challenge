package com.lambdaschool.sprint2_challenge;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class ImageListView extends AppCompatActivity {
    private ItemsList itemsList;
    private RecyclerView entryRecyclerView;
    private ImageListAdapter ilaAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_image_list_view );

        context = getApplicationContext();
        ReceiveData();

        ilaAdapter=new ImageListAdapter( itemsList );


        entryRecyclerView = findViewById(R.id.recycler_view);
        entryRecyclerView.setAdapter( ilaAdapter );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation( LinearLayoutManager.HORIZONTAL );
        entryRecyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sendData();

    }

    private void sendData() {

        Context context= getApplicationContext();

        Intent intent = new Intent(context, ImageListView.class);
        intent.putExtra("DATA_I_NEED",itemsList);

        startActivity(intent);

    }
    public void ReceiveData(){
        itemsList=(ItemsList)getIntent().getSerializableExtra("DATA_I_NEED");
        ImageListAdapter ila=new ImageListAdapter( itemsList );

    }


}
