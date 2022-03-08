package com.example.apipractice.adapter;

import android.content.Context;
import android.hardware.lights.LightState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apipractice.R;
import com.example.apipractice.model.PostResponse;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

     private Context context;
     List<PostResponse> postResponseList;

    public RecyclerAdapter(Context context, List<PostResponse> postResponseList) {
        this.context = context;
        this.postResponseList = postResponseList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_data,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        PostResponse postResponse = postResponseList.get(position);
        //holder.news.setText(String.valueof(c.getnewss()));

        holder.id.setText(String.valueOf(postResponse.getId()));
        holder.userId.setText(String.valueOf(postResponse.getUserId()));
        holder.title.setText(postResponse.getTitle());
        holder.body.setText(postResponse.getBody());

    }

    @Override
    public int getItemCount() {
        return postResponseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userId, id,title,body;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            userId = itemView.findViewById(R.id.userId);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }
}
