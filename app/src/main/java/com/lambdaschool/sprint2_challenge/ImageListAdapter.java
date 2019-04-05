package com.lambdaschool.sprint2_challenge;


import android.content.Intent;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import  android.support.v7.widget.CardView;

import java.util.ArrayList;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder>{

    private ViewHolder viewHolder;



    public static final int EDIT_ENTRY_REQUEST_CODE = 2;


    private Context context;
    private ItemsList itemsList;


    public ImageListAdapter(ItemsList itemsList) {

        this.itemsList=itemsList;

    }



    @NonNull

    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context=viewGroup.getContext();


        View entryView = LayoutInflater.from(context).inflate(R.layout.image_list_view, viewGroup, false);


        return new ViewHolder(entryView);

    }



    @Override

    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final Item it = this.itemsList.get(i);

        this.viewHolder=viewHolder;

        viewHolder.tvName.setText(it.getStrName());
        viewHolder.ivImage.setImageDrawable( context.getResources() .getDrawable( it.getiIcon() ));

        viewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeBackGroundColorAndCheckData( view);




          //      Intent detailIntent = new Intent(v.getContext(), ImageListView.class);

            //    detailIntent.putExtra("ItemsList", itemsList);

              //  ((Activity) v.getContext())

                      //  .startActivityForResult(detailIntent, EDIT_ENTRY_REQUEST_CODE);

            }

        });



    }

    private void changeBackGroundColorAndCheckData(View view){

        String str=viewHolder.tvName.getText().toString();
        Item item=itemsList.findItemByName( str );


        if(item!=null){
            if(item.isbToShop()){
                view.setBackgroundColor(Color.WHITE);
                viewHolder.tvName.setTextColor( Color.BLACK );
                item.setbToShop( false );
            }else{
                view.setBackgroundColor(Color.RED);
                viewHolder.tvName.setTextColor( Color.WHITE );
                item.setbToShop( true );
            }
        }else {
        }
    }



    @Override

    public int getItemCount() {

        return this.itemsList.size();

    }




    class ViewHolder extends RecyclerView.ViewHolder{

        private CardView parent;


        private ImageView ivImage;
        private TextView tvName;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            this.parent = itemView.findViewById(R.id.element_parent);

            this.ivImage = itemView.findViewById(R.id.image_icon_to_choose);

            this.tvName= itemView.findViewById(R.id.text_name_to_choose);


        }

        public void changeColor(View itemView){
            this.parent.setCardBackgroundColor( 3 );
        }

    }

}