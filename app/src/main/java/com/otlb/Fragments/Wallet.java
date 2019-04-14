package com.otlb.Fragments;


import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.otlb.Activites.Navigation;
import com.otlb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Wallet extends Fragment {


    public Wallet() {
        // Required empty public constructor
    }
   View view;
    ImageView arrow_packages,img_addpackge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_wallet, container, false);
        arrow_packages=view.findViewById(R.id.arrow_packages);
        img_addpackge=view.findViewById(R.id.img_addpackge);
        Navigation();
        OpenPackageDetails();
        arrow_packages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayForRestaurant detailsHomeProductFragment=new PayForRestaurant();
                Bundle bundle=new Bundle();
                detailsHomeProductFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.Rela_Wallet,detailsHomeProductFragment)
                        .addToBackStack(null).commit();

            }
        });

        return view;
    }

    public void OpenPackageDetails(){
        img_addpackge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WalletPackges detailsHomeProductFragment=new WalletPackges();
                Bundle bundle=new Bundle();
                detailsHomeProductFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.Rela_Wallet,detailsHomeProductFragment)
                        .addToBackStack(null).commit();

            }
        });
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
        Navigation.Visablty=true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Navigation.Visablty=false;
    }
}
