package com.example.anmol.efarming;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by harshul on 12/03/18.
 */

public class farmingListAdapter extends RecyclerView.Adapter<farmingListAdapter.farmingListViewHolder> {

    private String[] data;
    private String[] info;
    public farmingListAdapter(String[] data,String[] info)
    {
    this.data = data;
    this.info = info;
    }
    @Override
    public farmingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.list_item_layout,parent,false);
        return new farmingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(farmingListViewHolder holder, int position) {
        String title= data[position];
        holder.txtTitle.setText(title);
        String price=info[position];
        holder.txtinfo.setText(price);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class farmingListViewHolder extends RecyclerView.ViewHolder{
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtinfo;
        public farmingListViewHolder(View itemView) {
            super(itemView);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
            txtTitle= (TextView) itemView.findViewById(R.id.txtTitle);
            txtinfo =(TextView) itemView.findViewById(R.id.txtinfo);
        }
    }
}
