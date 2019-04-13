package com.otlb.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.otlb.Activites.Navigation;
import com.otlb.Language;
import com.otlb.Model.Cities;
import com.otlb.Model.CityDetails_Spinner;
import com.otlb.Model.States;
import com.otlb.Model.TypesFood;
import com.otlb.Presenter.GetCities_Presenter;
import com.otlb.Presenter.GetTypes_Presenter;
import com.otlb.R;
import com.otlb.View.GetCities_View;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.otlb.Activites.Navigation.toolbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment implements GetCities_View {


    public Home() {
        // Required empty public constructor
    }
   Spinner Cities_Spinner,States_Spinner,Type_Spinner;
   List<CityDetails_Spinner> list_Cities=new ArrayList<>();
   ArrayAdapter<CityDetails_Spinner> list_Cities_Adapter;
    List<CityDetails_Spinner> list_States=new ArrayList<>();
    ArrayAdapter<CityDetails_Spinner> list_States_Adapter;
    GetTypes_Presenter getTypes_presenter;
   String City,City_Id;
    String State,State_Id;
    List<CityDetails_Spinner> list_Types=new ArrayList<>();
    ArrayAdapter<CityDetails_Spinner> list_Types_Adapter;
    String Type,Type_Id;
    View view;
   GetCities_Presenter getCities_presenter;
   String Lang;
   Button btn_Search;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        Navigation();
        Language();
        init();

        return view;
    }

    public void init() {
        btn_Search=view.findViewById(R.id.btn_Search);
        Cities_Spinner=view.findViewById(R.id.Cities_Spinner);
        States_Spinner=view.findViewById(R.id.States_Spinner);
        Type_Spinner=view.findViewById(R.id.Type_Spinner);
        getCities_presenter=new GetCities_Presenter(getContext(),this);
        getTypes_presenter=new GetTypes_Presenter(getContext(),this);
        getCities_presenter.GetCities(Lang);
        getTypes_presenter.GetTypes(Lang);


        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(City_Id!=null && State_Id!=null && Type_Id!=null){
                    Restaurants detailsHomeProductFragment=new Restaurants();
                    Bundle bundle=new Bundle();
                    bundle.putString("cityid",City_Id);
                    bundle.putString("typeid",Type_Id);
                    bundle.putString("stateid",State_Id);
                    detailsHomeProductFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().add(R.id.Home_Frame,detailsHomeProductFragment)
                            .addToBackStack(null).commit();
                }
            }
        });
    }

    public void Navigation(){
        Toolbar toolbar =view.findViewById(R.id.toolbarhome);

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
    public void listCities(List<Cities> list) {
        list_Cities.clear();
        CityDetails_Spinner car_detail=new CityDetails_Spinner();
        car_detail.setId("0");
        car_detail.setName(view.getResources().getString(R.string.selectcity));
        list_Cities.add(car_detail);
        for(int i=0;i<list.size();i++){
            CityDetails_Spinner car_details=new CityDetails_Spinner();
            car_details.setId(String.valueOf(list.get(i).getId()));
            car_details.setName(String.valueOf(list.get(i).getName()));
            list_Cities.add(car_details);
        }
        list_Cities_Adapter = new ArrayAdapter<CityDetails_Spinner>(getApplicationContext(), R.layout.textcolorspinner,list_Cities) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.BLACK);
                return textView;
            }
        };
        list_Cities_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Cities_Spinner.setAdapter(list_Cities_Adapter);
        Cities_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                City= Cities_Spinner.getSelectedItem().toString();


                if(!City.equals("Select City")&&!City.equals("ختار المدينة")) {
                    for(i = 0; i<list_Cities.size(); i++){
                        if(list_Cities.get(i).getName().equals(City)){
                            City_Id=String.valueOf(list_Cities.get(i).getId());
                        }
                    }
                    getCities_presenter.GetStates(Lang,City_Id);
                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void listStates(List<States> list) {
        list_States.clear();
        CityDetails_Spinner car_detail=new CityDetails_Spinner();
        car_detail.setId("0");
        car_detail.setName(view.getResources().getString(R.string.selectstate));
        list_States.add(car_detail);
        for(int i=0;i<list.size();i++){
            CityDetails_Spinner car_details=new CityDetails_Spinner();
            car_details.setId(String.valueOf(list.get(i).getId()));
            car_details.setName(String.valueOf(list.get(i).getName()));
            list_States.add(car_details);
        }
        list_States_Adapter = new ArrayAdapter<CityDetails_Spinner>(getApplicationContext(), R.layout.textcolorspinner,list_States) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.BLACK);
                return textView;
            }
        };
        list_States_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        States_Spinner.setAdapter(list_States_Adapter);
        States_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                State= States_Spinner.getSelectedItem().toString();


                if(!City.equals("Select State")&&!City.equals("اختار الحي")) {
                    for(i = 0; i<list_States.size(); i++){
                        if(list_States.get(i).getName().equals(State)){
                            State_Id=String.valueOf(list_States.get(i).getId());
                        }
                    }
                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void listTypes(List<TypesFood> list) {
        list_Types.clear();
        CityDetails_Spinner car_detail=new CityDetails_Spinner();
        car_detail.setId("0");
        car_detail.setName(view.getResources().getString(R.string.selecttype));
        list_Types.add(car_detail);
        for(int i=0;i<list.size();i++){
            CityDetails_Spinner car_details=new CityDetails_Spinner();
            car_details.setId(String.valueOf(list.get(i).getId()));
            car_details.setName(String.valueOf(list.get(i).getName()));
            list_Types.add(car_details);
        }
        list_Types_Adapter = new ArrayAdapter<CityDetails_Spinner>(getApplicationContext(), R.layout.textcolorspinner,list_Types) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
                textView.setTextColor(Color.BLACK);
                return textView;
            }
        };
        list_Types_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Type_Spinner.setAdapter(list_Types_Adapter);
        Type_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Type= Type_Spinner.getSelectedItem().toString();


                if(!Type.equals("Select Type Food")&&!Type.equals("اختار نوع الاكل")) {
                    for(i = 0; i<list_Types.size(); i++){
                        if(list_Types.get(i).getName().equals(Type)){
                            Type_Id=String.valueOf(list_Types.get(i).getId());
                        }
                    }
                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void Error(String a) {

    }

    public void Language (){
        if(Language.isRTL()){
            Lang="ar";
        }else {
            Lang="en";
        }

    }
}
