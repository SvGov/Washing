package com.example.washing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.washing.R;
import com.example.washing.clothes.Clothes;
import com.example.washing.clothes.ClothesDao;
import com.example.washing.databinding.ActivityViewCardBinding;

public class EditActivity extends AppCompatActivity {
    ActivityViewCardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int index = getIntent().getIntExtra("index", 0);
        Clothes clothes = ClothesDao.get(index);
        binding.etViewTitle.setText(clothes.getTitle());
        String filenameImg = getResources().getResourceEntryName(clothes.getIdClothes());
        if (filenameImg.equals("hanger")) {
            binding.etViewNameImg.setText("");
        }
        else {
            binding.etViewNameImg.setText(filenameImg);
        }
        binding.ivClothes.setImageResource(clothes.getIdClothes());

        binding.bViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clothes.setTitle(binding.etViewTitle.getText().toString());
                String filenameImg = binding.etViewNameImg.getText().toString();

                int id_img;
                if (filenameImg.isEmpty()) {
                    id_img = R.drawable.hanger;
                } else {
                    id_img = getResources().getIdentifier(filenameImg, "drawable", getPackageName());
                }
                clothes.setIdClothes(id_img);

                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
