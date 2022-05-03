package com.example.washing.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.washing.R;
import com.example.washing.clothes.Clothes;
import com.example.washing.clothes.ClothesDao;
import com.example.washing.databinding.ActivityViewCardBinding;

public class AddActivity extends AppCompatActivity {
    ActivityViewCardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }


    public void save(View view) {
        String title = binding.etViewTitle.getText().toString();
        String filenameImg = binding.etViewNameImg.getText().toString();

        int id_img;
        if (filenameImg.isEmpty()) {
            id_img = R.drawable.hanger;
        } else {
            id_img = getResources().getIdentifier(filenameImg, "drawable", getPackageName());
        }

        ClothesDao.add(new Clothes(title, id_img));

        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}
