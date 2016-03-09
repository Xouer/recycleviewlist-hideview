package com.wangg.recycleiew_list_hidedisplay;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/7.
 */
public class TimeListViewAdapter extends RecyclerView.Adapter<TimeListViewAdapter.MyViewHolder>{

    private  List<String> dataList;//数据源
    private ArrayList<Integer> selectedList = new ArrayList<>();  //编号
    private  boolean signDelete = false;
    private  OnRecyclerItemClickListener mOnItemClickListener = null;


    public void setmOnItemClickListener(OnRecyclerItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public boolean getSignDelete() {
        return signDelete;
    }


    public void setSignDelete(boolean signDelete) {
        this.signDelete = signDelete;
    }


    public TimeListViewAdapter(List<String> dataList){
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item, parent, false);
        MyViewHolder holder = new MyViewHolder(view, mOnItemClickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.starTimeText.setText(dataList.get(position));
//        holder.itemView.setTag(position);
        holder.deleteImageView.setVisibility(signDelete ? View.VISIBLE : View.INVISIBLE);
    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements
            View.OnClickListener, View.OnLongClickListener{
        private  OnRecyclerItemClickListener mClickListener;
        private  TextView starTimeText;
        private  ImageView deleteImageView;

        public MyViewHolder(View itemView,OnRecyclerItemClickListener listener) {
            super(itemView);
            starTimeText = (TextView) itemView.findViewById(R.id.text_stratime);
            deleteImageView =  (ImageView)itemView.findViewById(R.id.delete_imageview);
            this.mClickListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mClickListener != null){
                mClickListener.onItemClick(v, getAdapterPosition());
                Log.e("TimeClass",""+ getAdapterPosition());
                Log.e("TimeClass", "signDelete" + signDelete);
                if(signDelete){
                    dataList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            }
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }

    }

    interface OnRecyclerItemClickListener{
        void onItemClick(View view, int position);
    }
}
