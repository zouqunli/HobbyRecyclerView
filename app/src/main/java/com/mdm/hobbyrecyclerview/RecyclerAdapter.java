package com.mdm.myhobbyrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{


    private Context mContext;
    private List<String> mList;
    public RecyclerAdapter(Context context, List<String> list){
        this.mContext = context;
        this.mList = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext,R.layout.item_tt,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textView.setText(mList.get(i));
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            textView = itemView.findViewById(R.id.itemOne);
        }
    }

    public void setList(List<String> list){
        mList = list;
        notifyDataSetChanged();
    }
}
