package com.example.androidshaper.doodleinctestapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidshaper.doodleinctestapplication.R;

import com.example.androidshaper.doodleinctestapplication.model.NameList;
import com.example.androidshaper.doodleinctestapplication.model.SubCategoryName;
import com.example.androidshaper.doodleinctestapplication.model.Subcatg;

import java.util.ArrayList;
import java.util.List;

public class MySubCategoryAdapter extends RecyclerView.Adapter<MySubCategoryAdapter.SubCategoryViewHolder>
{
    List<Subcatg> subcatgList;
    Context context;
    NameList nameList=new NameList();
    List<SubCategoryName> subCategoryNameList=new ArrayList<>();

    public MySubCategoryAdapter(
            List<Subcatg> subcatgList,
            Context context)
    {
        this.subcatgList = subcatgList;
        this.context = context;
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(
            @NonNull  ViewGroup parent,
            int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sub_category, parent, false);
        return new SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull  MySubCategoryAdapter.SubCategoryViewHolder holder,
            int position)
    {
        Subcatg subcatg=subcatgList.get(position);
        holder.textViewSubCategoryName.setText(subcatg.getSubCategoryName());
        holder.checkBoxSubCategory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(
                    CompoundButton buttonView,
                    boolean isChecked)
            {
                if (holder.checkBoxSubCategory.isChecked())
                {
                    Toast.makeText(context,subcatg.getSubCategoryName(),Toast.LENGTH_LONG).show();
                    subCategoryNameList.add(new SubCategoryName(subcatg.getSubCategoryName()));
                    nameList.setSubCategoryNameList(subCategoryNameList);
                }
                else
                {
                    subCategoryNameList.remove(new SubCategoryName(subcatg.getSubCategoryName()));
                    nameList.setSubCategoryNameList(subCategoryNameList);

                }
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return subcatgList.size();
    }

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSubCategoryName;
        CheckBox checkBoxSubCategory;
        public SubCategoryViewHolder(@NonNull  View itemView)
        {
            super(itemView);
            textViewSubCategoryName=itemView.findViewById(R.id.subCategoryName);
            checkBoxSubCategory=itemView.findViewById(R.id.subCategoryCheckbox);
        }
    }
}
