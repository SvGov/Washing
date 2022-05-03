package com.example.washing.clothes;

import java.util.Objects;

public class Clothes {
    private String title;
    private int IdClothes;
//    private String IdLabel;

    public Clothes(String title, int idClothes) {
        this.title = title;
        IdClothes = idClothes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIdClothes() {
        return IdClothes;
    }

    public void setIdClothes(int idClothes) {
        this.IdClothes = idClothes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clothes clothes = (Clothes) o;
        return Objects.equals(title, clothes.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
