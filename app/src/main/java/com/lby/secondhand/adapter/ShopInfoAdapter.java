package com.lby.secondhand.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lby.secondhand.R;
import com.lby.secondhand.dao.bean.Goods;

import java.util.ArrayList;
import java.util.List;


public class ShopInfoAdapter extends RecyclerView.Adapter<ShopInfoAdapter.ViewHolder> {

    private int p[] = new int[]{
            R.drawable.i1,
            R.drawable.i2,
            R.drawable.i3,
            R.drawable.i4,
            R.drawable.i5,
            R.drawable.i6,
            R.drawable.i7,
    };

    private List<Goods> goodsList = new ArrayList<>();

    private Context mContext;

    public ShopInfoAdapter(List<Goods> goodsList, Context context) {
        this.goodsList = goodsList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(mContext, R.layout.shop_item, null);
        ViewHolder holder = new ViewHolder(v);
        holder.Icon = v.findViewById(R.id.img_shop_item);
        holder.name = v.findViewById(R.id.tv_shop_item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Icon.setImageResource(p[position % 7]);
        holder.name.setText(position + "");
    }


    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView Icon;

        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
