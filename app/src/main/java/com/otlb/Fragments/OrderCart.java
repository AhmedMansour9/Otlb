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
public class OrderCart extends Fragment {


    public OrderCart() {
        // Required empty public constructor
    }
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_order_cart, container, false);



        return view;
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
