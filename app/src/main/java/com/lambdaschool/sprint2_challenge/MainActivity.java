package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private static ItemsList itemsList;
    private Item item;
    private RecyclerView recyclerViewChoosen;
    private ImageListAdapter ilaAdapter;


    private LinearLayout llScroll;
    private static SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=getApplicationContext();
        ReceiveData();
        if(this.preferences==null) {
            this.preferences = getApplicationContext().getSharedPreferences( "ShoppingListRecord", MODE_PRIVATE );
            String strRetrieved = preferences.getString( "IDS_FOR_SHOPPING", "" );
            SharedPreferences.Editor editor = preferences.edit();
        //   editor.clear(); //to erase preference
        //     editor.commit();//to erase preference
            if (strRetrieved.equals( "" )) {

                String strIDs = "";

                ShoppingItemConstants sic = new ShoppingItemConstants();
                Item newItem = new Item( 0, sic.ITEM_NAMES_RAW[0], false, sic.ICON_IDS[0] );
                itemsList = new ItemsList( newItem );
                strIDs += "0,";
                editor.putString( "ITEM_FOR_SHOPPING" + "0", newItem.toCSV() );
                editor.apply();
                for (int i = 1; i < sic.ITEM_NAMES_RAW.length; i++) {
                    newItem = new Item( i, sic.ITEM_NAMES_RAW[i], false, sic.ICON_IDS[i] );
                    itemsList.add( newItem );
                    if (i == sic.ITEM_NAMES_RAW.length - 1) {
                        strIDs += Integer.toString( i );
                    } else {
                        strIDs += Integer.toString( i ) + ",";
                    }
                    editor.putString( "ITEM_FOR_SHOPPING" + Integer.toString( i ), newItem.toCSV() );
                    editor.apply();
                }

                editor.putString( "IDS_FOR_SHOPPING", strIDs );
                editor.apply();

            } else {
                String[] strarIndex = strRetrieved.split( "," );
                for (int i = 0; i < strarIndex.length; i++) {
                    strRetrieved = preferences.getString( "ITEM_FOR_SHOPPING" + strarIndex[i], "" );

                    if (strRetrieved.equals( "" )) {
                    } else {
                        Item item = new Item( strRetrieved );
                        if (itemsList == null) {
                            itemsList = new ItemsList( item );
                        } else {
                            itemsList.add( item );
                        }
                    }
                }
            }
        }

        if(itemsList.getChoosen() !=null){
            ilaAdapter=new ImageListAdapter( itemsList.getChoosen() );
            recyclerViewChoosen=findViewById( R.id.recycler_view_choosen);
            recyclerViewChoosen.setAdapter( ilaAdapter );
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
            recyclerViewChoosen.setLayoutManager(linearLayoutManager);
        }

        findViewById(R.id.button_recyclerview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }

        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        writePreferance();
        finish();


    }

    public void writePreferance(){


        String strIDs="";
        preferences = getApplicationContext().getSharedPreferences("ShoppingListRecord", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        if (itemsList.size()>263){
            strIDs=""; //debug
        }

        for(int i=0;i<itemsList.size();i++) {
            if(i==itemsList.size()-1){
                strIDs+=itemsList.get( i ).getiID();
            }else{
                strIDs+=itemsList.get( i ).getiID()+",";
            }
            editor.putString("ITEM_FOR_SHOPPING"+Integer.toString( i ), itemsList.get( i ).toCSV());
            editor.apply();
        }

        editor.putString("IDS_FOR_SHOPPING", strIDs);
        editor.commit();
    }

    private void sendData() {

        Context context= getApplicationContext();

        Intent intent = new Intent(context, ImageListView.class);
        intent.putExtra("DATA_I_NEED",itemsList);
        //       fromOtherScreen=true;
        startActivityForResult(intent,1);

    }
    public void ReceiveData(){
        itemsList=(ItemsList)getIntent().getSerializableExtra("DATA_I_NEED");
        ImageListAdapter ila=new ImageListAdapter( itemsList );

    }


}
