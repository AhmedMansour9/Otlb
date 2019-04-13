package com.otlb.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.otlb.Model.Restaurants;
import com.otlb.R;
import com.otlb.View.RestaurantDetails_View;

import java.util.ArrayList;
import java.util.List;

public class Restaurants_Adapter extends RecyclerView.Adapter<Restaurants_Adapter.MyViewHolder>{

    private List<Restaurants> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    RestaurantDetails_View restaurantDetails_view;
//    phone_view phoneView;
    String Price;
//    ListUnitDetails_View listUnitDetails_view;
    List<Restaurants> list=new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Name;
        private Button Callnow,Details;
        private ImageView Image_Unit,arrow;
        private ProgressBar ProgrossSpare;
        private ImageView person_image,Starone,Startwo,StarThree,StarFour,StarFive;
        RelativeLayout relaa;

        public MyViewHolder(View view) {
            super(view);
            Name=view.findViewById(R.id.Name);
            Image_Unit=view.findViewById(R.id.img);
            arrow=view.findViewById(R.id.arrow);
        }
    }

    public Restaurants_Adapter(List<Restaurants> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
//    public Restaurants_Adapter(List<Units_Detail> list){
//        this.filteredList=list;
//
//    }
    public void setClickListener(RestaurantDetails_View restaurantDetails_view) {
        this.restaurantDetails_view = restaurantDetails_view;

    }

    @Override
    public Restaurants_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_restaurant, parent, false);
        return new Restaurants_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Restaurants_Adapter.MyViewHolder holder, final int position) {


        holder.Name.setText(filteredList.get(position).getName());
        String i = filteredList.get(position).getLogo();
        if(i!=null) {
            Uri u = Uri.parse(i);
//            holder.ProgrossSpare.setVisibility(View.VISIBLE);
            Glide.with(con)
                    .load("http://raaleat.com/site/"+ u)
                    .apply(new RequestOptions().override(500, 500))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            holder.ProgrossSpare.setVisibility(View.GONE);
                            return false;
                        }
                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            holder.ProgrossSpare.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.Image_Unit);

        }

        holder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                restaurantDetails_view.id(filteredList.get(position).getId()
                        ,filteredList.get(position).getImage(),filteredList.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}

