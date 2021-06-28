package com.example.androidshaper.doodleinctestapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.androidshaper.doodleinctestapplication.R;
import com.example.androidshaper.doodleinctestapplication.adapter.MyRecyclerViewAdapter;
import com.example.androidshaper.doodleinctestapplication.model.Category;
import com.example.androidshaper.doodleinctestapplication.model.MainDataModel;
import com.example.androidshaper.doodleinctestapplication.service.OurRetrofitClient;
import com.example.androidshaper.doodleinctestapplication.service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    OurRetrofitClient ourRetrofitClient;
    RecyclerView recyclerViewCategory;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    ProgressDialog progressDialog;
    private String TAG = "MainActivity";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewCategory = findViewById(R.id.recyclerViewCategory);
        toolbar=findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this));
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        loadData();
    }

    private void loadData()
    {
        ourRetrofitClient = RetrofitInstance.getService();
        Call<MainDataModel> call = ourRetrofitClient.getObject();
        call.enqueue(new Callback<MainDataModel>()
        {
            @Override
            public void onResponse(
                    Call<MainDataModel> call,
                    Response<MainDataModel> response)
            {

                List<Category> categoryList = response.body().getCategories();
                Log.d(TAG, "onResponse: " + categoryList.size());
                myRecyclerViewAdapter = new MyRecyclerViewAdapter(MainActivity.this, categoryList);
                recyclerViewCategory.setAdapter(myRecyclerViewAdapter);
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(
                    Call<MainDataModel> call,
                    Throwable t)
            {
                Log.d(TAG, "onResponse: " + t.getMessage());

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId()==R.id.menuSave)
        {
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
}