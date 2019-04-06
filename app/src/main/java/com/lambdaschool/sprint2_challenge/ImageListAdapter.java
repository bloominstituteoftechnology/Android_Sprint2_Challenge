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
import android.widget.Toast;

import java.util.ArrayList;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder>{

    private ViewHolder viewHolder;



    public static final int EDIT_ENTRY_REQUEST_CODE = 2;


    private Context context;
    private ItemsList itemsList;


    public ImageListAdapter(ItemsList itemsList) {

        this.itemsList=itemsList;

    }
    public void set(ItemsList itemsList){
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
        if(it.isbToShop()){
            viewHolder.parent.setBackgroundColor( Color.RED );
          //  viewHolder.tvName.setTextColor( Color.WHITE );
        }else{
            viewHolder.parent.setBackgroundColor( Color.WHITE );
        }
        viewHolder.ivImage.setImageDrawable( context.getResources() .getDrawable( it.getiIcon() ));

    }

    public ItemsList getItemList(){
        return this.itemsList;
    }

    private void changeBackGroundColorAndCheckData(ViewHolder vh){

        String str=vh.tvName.getText().toString();
        Item item=itemsList.findItemByName( str );


        if(item!=null){
            if(item.isbToShop()){
             //   vh.tvName.setBackgroundColor(Color.WHITE);
                vh.parent.setBackgroundColor(Color.WHITE);
         //       vh.tvName.setTextColor( Color.BLACK );
              //  vh.tvName.append( item.getStrName() );//debug
                item.setbToShop( false );
            }else{
             //   vh.tvName.setBackgroundColor(Color.RED);
                vh.parent.setBackgroundColor(Color.RED);
     //           vh.tvName.setTextColor( Color.WHITE );
           //     vh.tvName.append( item.getStrName() );//debug

                item.setbToShop( true );
            }
        }else {
        }


    }



    @Override

    public int getItemCount() {

        return this.itemsList.size();

    }


    class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        private CardView parent;


        private ImageView ivImage;
        private TextView tvName;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            this.parent = itemView.findViewById(R.id.element_parent);

            this.ivImage = itemView.findViewById(R.id.image_icon_to_choose);

            this.tvName= itemView.findViewById(R.id.text_name_to_choose);
            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);

        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it

             //   Toast.makeText(context, tvName.getText(), Toast.LENGTH_SHORT).show();
           //     tvName.setText(tvName.getText()+Integer.toString( position ));
                changeBackGroundColorAndCheckData(this);
            }
        }


    }

}