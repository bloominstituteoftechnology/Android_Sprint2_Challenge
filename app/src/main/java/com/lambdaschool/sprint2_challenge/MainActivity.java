package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private ItemsList itemsList;
    private Item item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=getApplicationContext();
        LinearLayout ll = findViewById(R.id.scrolling_view);
        Item test=new Item( 0,"almond",false,R.drawable.almond );
        TextView tv=findViewById( R.id.text_test );
        tv.setText( test.getStrName());
        ImageView iv=findViewById( R.id.image_view );
        iv.setImageDrawable( getResources().getDrawable( test.getiIcon() ));
        itemsList=new ItemsList( test );


        findViewById(R.id.button_recyclerview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }

        });

    }

    private void sendData() {

        Context context= getApplicationContext();

        Intent intent = new Intent(context, ImageListView.class);
        intent.putExtra("DATA_I_NEED",itemsList);
        //       fromOtherScreen=true;
        startActivityForResult(intent,1);

    }


}
