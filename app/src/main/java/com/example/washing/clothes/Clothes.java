package com.example.washing.clothes;

import java.util.Objects;

public class Clothes {
    private int idDB;
    private String title;
    private int IdClothes;
//    private String IdLabel;

    public Clothes(String title, int idClothes) {
        this.title = title;
        this.IdClothes = idClothes;
    }

    public Clothes(int id, String title, int idClothes) {
        this.idDB = id;
        this.title = title;
        this.IdClothes = idClothes;
    }

    public int getIdDB() {
        return idDB;
    }

    public void setIdDB(int id) {
        this.idDB = id;
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
