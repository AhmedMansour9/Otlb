package com.otlb.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.otlb.Activites.Navigation;
import com.otlb.Adapter.Cart_Adapter;
import com.otlb.Language;
import com.otlb.Model.Cart;
import com.otlb.Model.CartList;
import com.otlb.Model.Cart_Details;
import com.otlb.Presenter.AddToCart_Presenter;
import com.otlb.Presenter.ShowCart_Presenter;
import com.otlb.R;
import com.otlb.View.AddToCart_View;
import com.otlb.View.Count_View;
import com.otlb.View.ShowCart_View;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCart extends Fragment implements ShowCart_View,AddToCart_View , Count_View,SwipeRefreshLayout.OnRefreshListener{


    public MyCart() {
        // Required empty public constructor
    }
    View view;
    ShowCart_Presenter showCart_presenter;
    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    Cart_Adapter cart_adapter;
    SharedPreferences Shared;
    String user;
    //    AddCart_Presenter addCart;
    TextView T_Price,TotalPrice;
    Button order;
    Button requestorder;
    String Price;
    String id;
    RelativeLayout relatwo,rel;
    FrameLayout cartframe;
//    NetworikConntection networikConntection;
    List<Cart> listss;
    TextView NoProducts;
    AddToCart_Presenter addCart;
    Boolean cart=false;
    String Id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_my_cart, container, false);
        showCart_presenter=new ShowCart_Presenter(getContext(),this);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        init();
        Navigation();
        Recyclview();
        SwipRefresh();
        RequestOrder();


        return view;
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

    public void init(){
        listss=new ArrayList<>();
        cartframe=view.findViewById(R.id.cartframe);
        NoProducts=view.findViewById(R.id.noproduct);
        requestorder=view.findViewById(R.id.requestorder);
        relatwo=view.findViewById(R.id.relatwo);
        user=Shared.getString("logggin",null);
        T_Price=view.findViewById(R.id.T_Price);
//        TotalPrice=view.findViewById(R.id.Price);
        addCart=new AddToCart_Presenter(getContext(),this);
//        order=view.findViewById(R.id.servicerequest);

    }
    public void RequestOrder(){
        requestorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    if (listss != null) {
//                        OrderLocation fragmen = new OrderLocation();
//                        Bundle args = new Bundle();
//                        args.putString("price", Price);
//
//                        fragmen.setArguments(args);
//                        getFragmentManager().beginTransaction()
//                                .replace(R.id.cartframe, fragmen)
//                                .addToBackStack(null)
//                                .commitAllowingStateLoss();
//                    } else {
//                        Toast.makeText(getContext(),getResources().getString(R.string.noproducts), Toast.LENGTH_SHORT).show();
//                    }

            }
        });
    }
    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_Cart);
        recyclerView.setHasFixedSize(true);
    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_Cart);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                cart=true;

                    if (user != null) {
                        if (Language.isRTL()) {
                            mSwipeRefreshLayout.setRefreshing(true);
                            showCart_presenter.ShowCart("ar", user);
                        } else {
                            mSwipeRefreshLayout.setRefreshing(true);
                            showCart_presenter.ShowCart("en", user);
                        }

                }
            }

        });
    }

    @Override
    public void ShowCart(List<CartList> list) {
//            id=String.valueOf(list.get(0).getId());
//        listss=list;
        if(cart) {
            if (list != null) {
                cart_adapter = new Cart_Adapter(list, getContext());
                cart_adapter.count(this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(cart_adapter);
                mSwipeRefreshLayout.setRefreshing(false);

            }
            mSwipeRefreshLayout.setRefreshing(false);
            relatwo.setVisibility(View.VISIBLE);

        }
    }


    @Override
    public void ShowTotalprice(String price) {
//        Price = price;
//        T_Price.setText(price + "LE");
//        relatwo.setVisibility(View.VISIBLE);
//        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void Result(String Message) {

    }

    @Override
    public void Updated(String Message) {

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void NoProduct() {
//        NoProducts.setVisibility(View.VISIBLE);
//        rel.setVisibility(View.GONE);
//        relatwo.setVisibility(View.GONE);
//        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        cart=true;
            if (user != null) {
                if (Language.isRTL()) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    showCart_presenter.ShowCart("ar", user);
                } else {
                    mSwipeRefreshLayout.setRefreshing(true);
                    showCart_presenter.ShowCart("en", user);
                }
            }

    }
    @Override
    public void count_plus(String id) {
        cart=false;
//            mSwipeRefreshLayout.setRefreshing(true);
            if (Language.isRTL()) {
                addCart.UpdateCart("ar", user, id);
            } else {
                addCart.UpdateCart("en", user, id);
            }

    }

    @Override
    public void count_minus(String id) {
        cart=false;
//            mSwipeRefreshLayout.setRefreshing(true);
            if (Language.isRTL()) {
                addCart.MinusCart("ar", user, id);
            } else {
                addCart.MinusCart("en", user, id);
            }


    }


    @Override
    public void delete(String id,String poistion) {
        Id=poistion;
            cart=true;
            mSwipeRefreshLayout.setRefreshing(true);
            if (Language.isRTL()) {
                addCart.Delete_toCart("ar", user, id);
            } else {
                addCart.Delete_toCart("en", user, id);
            }

    }

    @Override
    public void Success() {
        if (Language.isRTL()) {
//                mSwipeRefreshLayout.setRefreshing(true);
            showCart_presenter.ShowCart("ar", user);
        } else {
//                mSwipeRefreshLayout.setRefreshing(true);
            showCart_presenter.ShowCart("en", user);
        }

    }

    @Override
    public void Failed() {

    }


    @Override
    public void DeleteCart(String countity) {

            if (Language.isRTL()) {
                mSwipeRefreshLayout.setRefreshing(true);
                showCart_presenter.ShowCart("ar", user);
            } else {
                mSwipeRefreshLayout.setRefreshing(true);
                showCart_presenter.ShowCart("en", user);
            }
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
