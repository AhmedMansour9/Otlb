package com.otlb.Fragments;


import android.content.Context;
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
import com.otlb.Adapter.Packages_Adapter;
import com.otlb.Adapter.Restaurants_Adapter;
import com.otlb.Language;
import com.otlb.Model.Packages_Details;
import com.otlb.Presenter.GetPackages_Presenter;
import com.otlb.Presenter.GetRestaurants;
import com.otlb.R;
import com.otlb.View.GetPackages_View;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalletPackges extends Fragment implements GetPackages_View, SwipeRefreshLayout.OnRefreshListener {


    public WalletPackges() {
        // Required empty public constructor
    }
    View view;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;
    Packages_Adapter restaurants_adapter;
    GetPackages_Presenter getPackages_presenter;
    String Lang;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_wallet_packges, container, false);
       init();
        Navigation();
       Language();
        SwipRefresh();


    return view;
    }
    public void init(){
        recyclerView=view.findViewById(R.id.recycler_Packges);
        getPackages_presenter=new GetPackages_Presenter(getContext(),this);
    }
    @Override
    public void listpackages(List<Packages_Details> list) {

        mSwipeRefreshLayout.setRefreshing(false);
        restaurants_adapter=new Packages_Adapter(list,getContext());
//        restaurants_adapter.setClickListener(this);
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

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        getPackages_presenter.GetMenuDetails(Lang);
    }
    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_Packges);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getPackages_presenter.GetMenuDetails(Lang);

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
    public void onAttach(Context context) {
        super.onAttach(context);
        Navigation.Visablty=false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Navigation.Visablty=true;
    }
}
