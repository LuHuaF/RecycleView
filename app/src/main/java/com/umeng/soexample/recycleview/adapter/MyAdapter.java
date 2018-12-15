package com.umeng.soexample.recycleview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.recycleview.R;
import com.umeng.soexample.recycleview.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：
 * 作者：鲁华丰
 * 创建时间：2018/12/15
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<User.DataBean> list = new ArrayList<>();

    public MyAdapter(Context context, List<User.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.item_news,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(holder.imglogo);
        holder.txtTitle.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imglogo;
        private TextView txtTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            imglogo = itemView.findViewById(R.id.img_logo);
            txtTitle = itemView.findViewById(R.id.txt_title);
        }
    }
}
