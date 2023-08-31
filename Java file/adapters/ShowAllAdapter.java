package com.thushan.ecommerce.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thushan.ecommerce.R;
import com.thushan.ecommerce.activities.DetailedActivity;
import com.thushan.ecommerce.models.ShowAllModel;

import java.util.List;

public class ShowAllAdapter extends RecyclerView.Adapter<ShowAllAdapter.ViewHolder> {

    private Context context;
    private List<ShowAllModel>list;

    public ShowAllAdapter(Context context, List<ShowAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.aNewImg);
        holder.aCost.setText("$"+list.get(position).getPrice());
        holder.aName.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,DetailedActivity.class);
                intent.putExtra("detailed",list.get(position));
                intent.putExtra("quantity",list.get(position).getQuantity());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView aNewImg;
        private TextView aCost;
        private TextView aName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            aNewImg=itemView.findViewById(R.id.item_image);
            aCost=itemView.findViewById(R.id.item_cost);
            aName=itemView.findViewById(R.id.item_name);
        }
    }
}
