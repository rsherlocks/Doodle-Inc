package com.example.androidshaper.doodleinctestapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidshaper.doodleinctestapplication.R;
import com.example.androidshaper.doodleinctestapplication.adapter.MyItemShowAdapter;
import com.example.androidshaper.doodleinctestapplication.adapter.MyRecyclerViewAdapter;
import com.example.androidshaper.doodleinctestapplication.model.CategoryName;
import com.example.androidshaper.doodleinctestapplication.model.NameList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewActivity extends AppCompatActivity
{
    NameList nameList;
    List<CategoryName> categoryNameList=new ArrayList<>();
    RecyclerView recyclerViewCategoryName;
    MyItemShowAdapter myItemShowAdapter;
    SharedPreferences sharedPreferences;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);
        recyclerViewCategoryName=findViewById(R.id.addItemRecyclearView);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(CategoryViewActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewCategoryName.setLayoutManager(linearLayoutManager);
        sharedPreferences = getSharedPreferences("categoryname", Context.MODE_PRIVATE);


        //        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.contains("category"))
        {

            loadData();
//            Toast.makeText(this,String.valueOf(MyRecyclerViewAdapter.categoryNameList.size()),Toast.LENGTH_LONG).show();
        }

    }

    private void loadData()
    {
        categoryNameList=new Gson().fromJson(sharedPreferences.getString("category", null), new TypeToken<List<CategoryName>>(){}.getType());

        myItemShowAdapter=new MyItemShowAdapter(categoryNameList,this);
        recyclerViewCategoryName.setAdapter(myItemShowAdapter);
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        loadData();
    }
}