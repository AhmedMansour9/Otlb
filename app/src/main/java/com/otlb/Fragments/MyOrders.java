package com.otlb.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.otlb.Activites.Navigation;
import com.otlb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrders extends Fragment {


    public MyOrders() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_orders, container, false);
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
