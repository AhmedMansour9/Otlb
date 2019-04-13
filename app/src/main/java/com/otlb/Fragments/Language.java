package com.otlb.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.otlb.Activites.Navigation;
import com.otlb.MainActivity;
import com.otlb.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Language extends Fragment {


    public Language() {
        // Required empty public constructor
    }
    RelativeLayout btn_Arabic,Btn_English;
    SharedPreferences.Editor share;
     View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_language, container, false);
        btn_Arabic=view.findViewById(R.id.Rela_Arabic);
        Btn_English=view.findViewById(R.id.Rela_English);
       Lan_Arabic();
       Lan_English();

        return view;
    }
    public void Lan_Arabic(){
        btn_Arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share=getActivity().getSharedPreferences("Language",MODE_PRIVATE).edit();
                share.putString("Lann","ar");
                share.commit();
                startActivity(new Intent(getContext(), Navigation.class));
                getActivity().finish();


            }
        });
    }
    public void Lan_English(){
        Btn_English.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share.putString("Lann","en");
                share.commit();
                startActivity(new Intent(getContext(), Navigation.class));
                getActivity().finish();


            }
        });

    }
}
