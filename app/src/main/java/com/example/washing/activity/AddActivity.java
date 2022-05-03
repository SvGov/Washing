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

        mTakePhoto = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        binding.ivClothes.setImageURI(result);
                    }

                }
        );

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


    public void choose_img(View view) {
        // проверить разрешение во время выполнения
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {
                // разрешение не предоставлено, запросить его
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                // показать всплывающее окно для получения разрешения
                requestPermissions(permissions, PERMISSION_CODE);
            }
            else {
                // разрешение уже предоставлено
                pickImageFromGallery();
            }
        }
        else {
            // system os меньше, чем marshmallow
            pickImageFromGallery();
        }
    }

    private void pickImageFromGallery() {
//        // intent для загрузки изображения
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent, IMAGE_PICK_CODE);
        mTakePhoto.launch("image/*");
    }

    // обрабатывать результат разрешения во время выполнения

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    // разрешение было получено
                    pickImageFromGallery();
                } else {
                    // в разрешении было отказано
                    Toast.makeText(this, "В разрешении отказано!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
//            // установить изображение image view
//            binding.ivClothes.setImageURI(data.getData());
//        }
//    }
}
