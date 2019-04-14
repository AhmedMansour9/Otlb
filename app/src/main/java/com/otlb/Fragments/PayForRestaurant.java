package com.otlb.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.otlb.Activites.Navigation;
import com.otlb.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayForRestaurant extends Fragment {


    public PayForRestaurant() {
        // Required empty public constructor
    }
    View view;
   EditText E_Email;
   Button btn_Share,btn_Pay;
   ImageView PlusEmail;
   LinearLayout linearLayout;
    EditText e;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_pay_for_restaurant, container, false);
        init();
        Navigation();
        CheckEmail();
        PlusEditText();

    btn_Pay.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            View childView = getLayoutInflater().inflate(R.layout.custom_layout, null);
             e = childView.findViewById(R.id.custom_email);
             LinearLayout linearr=childView.findViewById(R.id.linearr);
//            ArrayList<String> edittextArray = new ArrayList<String>();
//            for (int i = 0; i < linearLayout.getChildCount(); i++) {
//
//               EditText es = (EditText) linearr.getChildAt(i);
//                edittextArray.add(es.getText().toString());
//            }
            ArrayList<String> edittextArray = new ArrayList<String>();
            for(int i = 0; i < linearLayout.getChildCount(); i++){
                View newLayout = linearLayout.getChildAt(i);
                EditText editText =
                        newLayout.findViewById(R.id. custom_email);
                edittextArray.add(editText.getText().toString());
            }

//             for (int i=0;i<edittextArray.size();i++){
//                 Toast.makeText(getActivity(), ""+edittextArray.get(i), Toast.LENGTH_SHORT).show();
//             }

        }
    });

        return view;
    }

    private void PlusEditText() {
        PlusEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.setVisibility(View.VISIBLE);
                View childView = getLayoutInflater().inflate(R.layout.custom_layout, null);
                linearLayout.addView(childView);
            }
        });

    }

    public void init(){
        E_Email=view.findViewById(R.id.login_email);
        btn_Share=view.findViewById(R.id.btn_Share);
        btn_Pay=view.findViewById(R.id.btn_Pay);
        PlusEmail=view.findViewById(R.id.PlusEmail);
        linearLayout=view.findViewById(R.id.child_linear);
    }

    public void CheckEmail() {
        E_Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
             if(s.toString().length()!=0){
                 btn_Share.setEnabled(true);
                 PlusEmail.setVisibility(View.VISIBLE);
             }else {
                 btn_Share.setEnabled(false);
                 PlusEmail.setVisibility(View.GONE);
             }

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
        Navigation.Visablty=false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Navigation.Visablty=true;
    }
}
