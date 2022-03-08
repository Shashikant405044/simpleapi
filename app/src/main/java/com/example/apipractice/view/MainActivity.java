package com.example.apipractice.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.apipractice.adapter.RecyclerAdapter;
import com.example.apipractice.databinding.ActivityMainBinding;
import com.example.apipractice.interfaace.ApiName;
import com.example.apipractice.model.PostResponse;
import com.example.apipractice.repository.RetofitApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Page Loading !!");
        progressDialog.setCancelable(false);
        progressDialog.show();
        PostApiData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);


    }

    private void PostApiData() {


          ApiName apiName = RetofitApi.getInstance(ApiName.class);
          Call<List<PostResponse>> call = apiName.getPostData();
          call.enqueue(new Callback<List<PostResponse>>() {
              @Override
              public void onResponse(Call<List<PostResponse>> call, Response<List<PostResponse>> response) {
                  progressDialog.hide();

                  if (response.isSuccessful() && response.code()==200)
                  {
                       List<PostResponse> responseList = response.body();

                         for ( PostResponse postData:   responseList){

                            // Log.d("MyData",postData.getBody());

                             RecyclerAdapter recyclerAdapter = new RecyclerAdapter(MainActivity.this,responseList);
                             binding.recyclerView.setAdapter(recyclerAdapter);

                         }

                  }

              }

              @Override
              public void onFailure(Call<List<PostResponse>> call, Throwable t) {

                  Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
              }
          });



    }
}