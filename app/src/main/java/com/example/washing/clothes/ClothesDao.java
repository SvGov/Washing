package com.example.washing.clothes;

import com.example.washing.R;

import java.util.ArrayList;
import java.util.List;

public class ClothesDao {
    private static final List<Clothes> lst_clothes = new ArrayList<>();

    static {
        lst_clothes.add(new Clothes("Голубая кот", R.drawable.img2));
        lst_clothes.add(new Clothes("Зебра", R.drawable.img10));
        lst_clothes.add(new Clothes("толстовка красная", R.drawable.img6));
        lst_clothes.add(new Clothes("слон", R.drawable.img5));
        lst_clothes.add(new Clothes("Время Приключений", R.drawable.img3));
        lst_clothes.add(new Clothes("индеец", R.drawable.img1));
        lst_clothes.add(new Clothes("фиолетовая футболка кот", R.drawable.img4));
        lst_clothes.add(new Clothes("счастье в горах", R.drawable.img8));
        lst_clothes.add(new Clothes("осьминог", R.drawable.img7));
//        lst_clothes.add(new Clothes("Голубая цветастая", R.drawable.img9));
//        lst_clothes.add(new Clothes("Череп с цветочками", R.drawable.img11));
    }

    public static void add(Clothes clths) {
        lst_clothes.add(clths);
    }

    public static List<Clothes> getAll() {
        return lst_clothes;
    }

    public static Clothes get(int index) {
        return lst_clothes.get(index);
    }

    public static int size() {
        return lst_clothes.size();
    }
}
