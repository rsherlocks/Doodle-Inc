package com.example.androidshaper.doodleinctestapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidshaper.doodleinctestapplication.R;
import com.example.androidshaper.doodleinctestapplication.model.CategoryName;

import java.util.List;

public class MyItemShowAdapter extends RecyclerView.Adapter<MyItemShowAdapter.NameViewHolder>
{
    List<CategoryName> categoryNameList;
    Context context;

    public MyItemShowAdapter(
            List<CategoryName> categoryNameList,
            Context context)
    {
        this.categoryNameList = categoryNameList;
        this.context = context;
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(
            @NonNull  ViewGroup parent,
            int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category, parent, false);

        return new NameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull  MyItemShowAdapter.NameViewHolder holder,
            int position)
    {
        CategoryName categoryName=categoryNameList.get(position);
        holder.textViewName.setText(categoryName.getCategoryName());

    }

    @Override
    public int getItemCount()
    {
        return categoryNameList.size();
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        public NameViewHolder(@NonNull  View itemView)
        {
            super(itemView);
            textViewName=itemView.findViewById(R.id.textViewName);
        }
    }
}
