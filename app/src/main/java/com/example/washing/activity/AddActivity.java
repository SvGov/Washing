package com.example.washing.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.washing.R;
import com.example.washing.clothes.Clothes;
import com.example.washing.clothes.ClothesDao;
import com.example.washing.databinding.ActivityAddBinding;

public class AddActivity extends AppCompatActivity {
    ActivityAddBinding binding;
    ActivityResultLauncher<String> mTakePhoto;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
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
