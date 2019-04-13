package com.otlb.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.otlb.Activites.Navigation;
import com.otlb.Adapter.Restaurants_Adapter;
import com.otlb.Language;
import com.otlb.Presenter.GetRestaurants;
import com.otlb.R;
import com.otlb.View.RestaurantDetails_View;
import com.otlb.View.Restaurants_View;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Restaurants extends Fragment implements RestaurantDetails_View,Restaurants_View,SwipeRefreshLayout.OnRefreshListener{


    public Restaurants() {
        // Required empty public constructor
    }
    View view;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;
    Restaurants_Adapter restaurants_adapter;
    GetRestaurants getUnits_presenter;
    String Typeid,Cityid,Stateid;
    String Lang;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_restaurants, container, false);
        Navigation();
       init();
       Language();
       SwipRefresh();

        return view;
    }

    public void init(){
        recyclerView=view.findViewById(R.id.recycler_Restaurants);
        getUnits_presenter=new GetRestaurants(getContext(),this);
        Bundle bundle=getArguments();
        if(bundle!=null){
            Cityid=bundle.getString("cityid");
            Typeid=bundle.getString("typeid");
            Stateid=bundle.getString("stateid");
        }
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        getUnits_presenter.GetRestaurants(Lang,Stateid,Cityid,Typeid);
    }

    @Override
    public void listRestaurants(List<com.otlb.Model.Restaurants> list) {
        mSwipeRefreshLayout.setRefreshing(false);
        restaurants_adapter=new Restaurants_Adapter(list,getContext());
        restaurants_adapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(restaurants_adapter);

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_Restaurants);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
           getUnits_presenter.GetRestaurants(Lang,Stateid,Cityid,Typeid);

            }
        });
    }
    public void Language(){
        if(Language.isRTL()){
            Lang="ar";
        }
        else {
            Lang="en";
        }
    }
    public void Navigation(){
        Toolbar toolbar =view.findViewById(R.id.toolbarrestaurants);

        Navigation.toggle = new ActionBarDrawerToggle(
                getActivity(), Navigation.drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        Navigation.drawer.addDrawerListener(Navigation.toggle);
        Navigation.toggle.syncState();

        Navigation.toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationIcon(R.drawable. navigation);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Navigation.drawer.isDrawerOpen(GravityCompat.START)) {
                    Navigation.drawer.closeDrawer(GravityCompat.START);
                } else {
                    Navigation.drawer.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    @Override
    public void id(String id, String Image,String Name) {
        Menus detailsHomeProductFragment=new Menus();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        bundle.putString("img",Image);
        bundle.putString("name",Name);
        detailsHomeProductFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.Home_Frame,detailsHomeProductFragment)
                .addToBackStack(null).commit();

    }

    @Override
    public void OpenDetails(String title, String Image, String id, String Image_meal, String Name,String aa) {

    }

    @Override
    public void AddtoCart(String id) {

    }
}
