package com.example.androidshaper.doodleinctestapplication.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidshaper.doodleinctestapplication.R;
import com.example.androidshaper.doodleinctestapplication.model.Category;
import com.example.androidshaper.doodleinctestapplication.model.CategoryName;
import com.example.androidshaper.doodleinctestapplication.model.NameList;
import com.example.androidshaper.doodleinctestapplication.model.Subcatg;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>
{
    Context context;
    List<Category> categoryList;
    List<CategoryName> categoryNameList = new ArrayList<>();
    List<CategoryName> categoryNameListShared = new ArrayList<>();
    MySubCategoryAdapter mySubCategoryAdapter;
    NameList nameList = new NameList();

    public MyRecyclerViewAdapter(
            Context context,
            List<Category> categoryList)
    {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_child_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull MyViewHolder holder,
            int position)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("categoryname", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        Category category = categoryList.get(position);
        List<Subcatg> subcatgList = category.getSubcatg();
        holder.textViewCategoryName.setText(category.getCategoryName());


        mySubCategoryAdapter = new MySubCategoryAdapter(subcatgList, context);
        holder.recyclerViewSubCategory.setAdapter(mySubCategoryAdapter);

        //        categoryNameListShared = new Gson().fromJson(sharedPreferences.getString("category", null), new TypeToken<List<CategoryName>>()
        //        {}.getType());

        String name=category.getCategoryName();
        if (sharedPreferences.contains("category"))
        {
            categoryNameList.clear();
            categoryNameList = new Gson().fromJson(sharedPreferences.getString("category", null), new TypeToken<List<CategoryName>>() {}.getType());

            for (CategoryName categoryName : new ArrayList<>(categoryNameList))
            {
                if (categoryName.getCategoryName().toLowerCase().trim().equals(name.toLowerCase().trim()))
                {
                    //categoryNameList.add(new CategoryName(category.getCategoryName()));
                    holder.checkBoxCategory.setChecked(true);
                    holder.checkBoxCategory.setButtonDrawable(R.drawable.ic_baseline_check_24);

                }
            }        }


        holder.checkBoxCategory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(
                    CompoundButton buttonView,
                    boolean isChecked)
            {

                if (isChecked)
                {
                    //Toast.makeText(context,category.getCategoryName(),Toast.LENGTH_LONG).show();
                    holder.checkBoxCategory.setButtonDrawable(R.drawable.ic_baseline_check_24);
                    categoryNameList.add(new CategoryName(category.getCategoryName()));

                    //                    nameList.setCategoryNameList(categoryNameList);

                }
                if (!isChecked)
                {
                    holder.checkBoxCategory.setButtonDrawable(R.drawable.ic_baseline_add_24);
//                    categoryNameList.clear();
//                    categoryNameList.addAll(new Gson().fromJson(sharedPreferences.getString("category", null),
//                            new TypeToken<List<CategoryName>>() {}.getType()));

                    //                    Toast.makeText(context,category.getCategoryName(),Toast.LENGTH_LONG).show();

                    for (CategoryName categoryName : new ArrayList<>(categoryNameList))
                    {

                        if (categoryName.getCategoryName().equals(category.getCategoryName()))
                        {
                            categoryNameList.remove(categoryName);
                        }
                    }

                }

                editor.putString("category", new Gson().toJson(categoryNameList)).commit();
//                categoryNameList.addAll(new Gson().fromJson(sharedPreferences.getString("category", null),
//                        new TypeToken<List<CategoryName>>() {}.getType()));
                //                Toast.makeText(context,String.valueOf(categoryNameList.size()),Toast.LENGTH_LONG).show();

            }
        });

        holder.textViewCategoryName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (holder.relativeLayoutExpandable.getVisibility() == View.GONE)
                {
                    holder.imageViewExpandable.setImageResource(R.drawable.ic_baseline_expand_more_24);
                    holder.relativeLayoutExpandable.setVisibility(View.VISIBLE);
                }
                else
                {
                    holder.relativeLayoutExpandable.setVisibility(View.GONE);
                    holder.imageViewExpandable.setImageResource(R.drawable.ic_baseline_arrow_forward_ios_24);

                }

            }
        });

    }

    @Override
    public int getItemCount()
    {
        return categoryList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout relativeLayoutExpandable;
        TextView textViewCategoryName;
        CheckBox checkBoxCategory;
        RecyclerView recyclerViewSubCategory;
        ImageView imageViewExpandable;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            relativeLayoutExpandable = itemView.findViewById(R.id.expandable_child);
            textViewCategoryName = itemView.findViewById(R.id.categoryName);
            checkBoxCategory = itemView.findViewById(R.id.categoryCheckbox);
            relativeLayoutExpandable = itemView.findViewById(R.id.expandable_child);
            imageViewExpandable = itemView.findViewById(R.id.expandableImageView);
            recyclerViewSubCategory = itemView.findViewById(R.id.recyclerViewSubCategory);
            recyclerViewSubCategory.setLayoutManager(new LinearLayoutManager(context));
        }
    }
}
