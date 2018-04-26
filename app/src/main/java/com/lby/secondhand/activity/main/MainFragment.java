package com.lby.secondhand.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lby.secondhand.R;
import com.lby.secondhand.activity.login.LoginActivity;
import com.lby.secondhand.activity.shop_content.ShopContentActivity;
import com.lby.secondhand.adapter.ShopInfoAdapter;
import com.lby.secondhand.dao.bean.Goods;
import com.lby.secondhand.module.LBYApiProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainFragment extends Fragment implements MainContract.View, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MainFragment";
    @BindView(R.id.nv_main)
    NavigationView navigationView;
    @BindView(R.id.rv_main)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView titleText;
    @BindView(R.id.srl_main)
    SwipeRefreshLayout mSwipeRefreshLayout;

    ShopInfoAdapter adapter;
    private MainContract.Presenter mPresenter;
    private boolean isPageAll = true;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container);
        ButterKnife.bind(this, v);
        initialView();
        initialListener();
        return v;
    }

    private void initialListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_goods:
                        getContext().startActivity(new Intent(getContext(), ShopContentActivity.class));
                        break;
                    case R.id.exit_login:
                        LBYApiProvider.getInstance().setUser(null);
                        if (getActivity() != null) {
                            getActivity().finish();
                        }
                        getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                        break;
                }
                return false;
            }
        });
    }

    private void initialView() {

        View view = navigationView.inflateHeaderView(R.layout.drawer_head);
        ImageView imageView = view.findViewById(R.id.fuck_img);
        Glide.with(getContext()).load(R.drawable.fuck_loading).into(imageView);

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.drawer_user_name).setTitle(LBYApiProvider.getInstance().getUser().getName());

        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @OnClick(R.id.img_change)
    public void change() {
        String title = isPageAll ? "我的商品" : "所有商品";
        titleText.setText(title);
        mPresenter.changeRecycleViewData(isPageAll);
        isPageAll = !isPageAll;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void refreshRecycleView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initialRecycler(List<Goods> goodsList) {
        adapter = new ShopInfoAdapter(goodsList, getContext());
        recyclerView.setAdapter(adapter);
        StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void stopRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        if (isPageAll) {
            mPresenter.initialGoods();
        } else {
            mPresenter.loadPersonalGoods();
        }
    }
}
