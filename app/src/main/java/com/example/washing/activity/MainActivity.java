package com.example.washing.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.washing.clothes.ClothesAdapter;
import com.example.washing.R;
import com.example.washing.clothes.ClothesDao;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ClothesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ClothesAdapter(this);

        RecyclerView recycler = findViewById(R.id.rv_cards_clothes);
        recycler.setHasFixedSize(true);
        // устанавливаем recycler отображение сеткой по 2 элемента
        recycler.setLayoutManager(new GridLayoutManager(this, 2));
        // устанавливаем что и как отображать
        recycler.setAdapter(adapter);

         // 1 способ назначить обработчик кнопке
//        Button b_add = findViewById(R.id.b_add);
//        b_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, AddActivity.class);
//                startActivity(intent);
//            }
//        });


        // 2 способ - имплементировать View.OnClickListener и переопределить onClick
        findViewById(R.id.b_add).setOnClickListener(this);
    }

    // 3 способ - указать кнопке в xml android:onClick="NAME_FUNCTION"
//    public void add(View view) {
//        Intent intent = new Intent(this.getApplicationContext(), AddActivity.class);
//        startActivity(intent);
//    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.b_add) {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        }

//        switch (view.getId()) {
//            case R.id.b_add:
//                Intent intent = new Intent(MainActivity.this, AddActivity.class);
//                startActivity(intent);
//                break;
//        }
    }
}