package com.example.anmol.efarming;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MiLaN on 17-03-2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<productDetail> productlist = new ArrayList<>();
    Context context;
    productDetail productDetail;

    public RecyclerViewAdapter(Context context, List<productDetail> templist)
    {
        this.context = context;
        this.productlist = templist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
            productDetail = productlist.get(position);

        Glide.with(context.getApplicationContext()).load(productDetail.getImageurl()).into(holder.imagedisplay);

        holder.hcropgroup.setText("CROP GROUP :"+productDetail.getCropgroup().toString());
        holder.hcropname.setText("CROP NAME :"+productDetail.getCropname().toString());
        holder.hfarmername.setText(productDetail.getFarmername().toString());
        holder.hprice.setText("PRICE :"+productDetail.getPrice().toString());
        holder.hcropgroup.setText("CROP GROUP :"+productDetail.getCropgroup().toString());
        holder.hqty.setText("QUANTITY : "+productDetail.getQuantity());


    }

    @Override
    public int getItemCount() {
        return productlist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagedisplay;
        public TextView hfarmername,hprice,hdesc,hurl,hcropgroup,hcropname,hqty ;

        public ViewHolder(View itemView) {

            super(itemView);
            hfarmername = (TextView)itemView.findViewById(R.id.fname);
            hprice = (TextView)itemView.findViewById(R.id.fname);
            imagedisplay = (ImageView)itemView.findViewById(R.id.image1);
            hprice = (TextView)itemView.findViewById(R.id.prc);
            hcropname = (TextView)itemView.findViewById(R.id.crname);
            hcropgroup = (TextView)itemView.findViewById(R.id.crgroup);
            hqty = (TextView)itemView.findViewById(R.id.qty);

//            recyclerView=(RecyclerView)itemView.findViewById(R.id.newRecycle);
//            spinner = (Spinner)itemView.findViewById(R.id.plus);


        }
    }
    public void clear()
    {
        int size = this.productlist.size();
        if (size > 0)
        {
            for (int i = 0;i<size;i++)
                delete(i);
            this.notifyItemRangeRemoved(0,size);
        }
    }
    private void delete(int i) {
        productlist.remove(i);
        notifyItemRemoved(i);
    }
}
