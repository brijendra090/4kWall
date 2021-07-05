package com.example.a4kwall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.a4kwall.adapter.Adapter;
import com.example.a4kwall.api.ApiUtilities;
import com.example.a4kwall.model.ImageModel;
import com.example.a4kwall.model.SearchModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageModel> modelClassList;
    private RecyclerView recyclerView;
    Adapter adapter;
    CardView mnature, mcar, mtech, mdog, mtrending, mlove;
    EditText editText;
    ImageButton search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recyclerview);
        mtech = findViewById(R.id.tech);
        mnature = findViewById(R.id.nature);
        mcar = findViewById(R.id.car);
        mlove = findViewById(R.id.love);
        mdog = findViewById(R.id.dog);
        mtrending = findViewById(R.id.trending);
        editText = findViewById(R.id.editText);
        search = findViewById(R.id.searchBtn);

        modelClassList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(getApplicationContext(), modelClassList);
        recyclerView.setAdapter(adapter);

        findPhotos();

        mnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "nature";
                getSearchImage(query);
            }
        });

        mlove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "love";
                getSearchImage(query);
            }
        });

        mtech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "technology";
                getSearchImage(query);
            }
        });

        mcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "car";
                getSearchImage(query);
            }
        });

        mdog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "dog";
                getSearchImage(query);
            }
        });

        mtrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "trending";
                getSearchImage(query);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = editText.getText().toString().trim().toLowerCase();
                if (query.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Something", Toast.LENGTH_SHORT).show();
                }
                else {
                    getSearchImage(query);
                }
            }
        });

    }

    private void getSearchImage(String query) {

        ApiUtilities.getApiInterface().getSearchImage(query, 1, 80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClassList.clear();
                if (response.isSuccessful()){
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "Not able to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }

    private void findPhotos() {
        ApiUtilities.getApiInterface().getImage(1, 80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if (response.isSuccessful()){
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "Not able to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }
}














