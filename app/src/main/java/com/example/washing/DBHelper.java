package com.example.washing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.washing.clothes.Clothes;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "washingDb";
    public static final String TABLE_CLOTHES = "clothes";

    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_ID_CLOTHES = "id_clothes";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CLOTHES + " ( " +
                KEY_ID +         " INTEGER PRIMARY KEY," +
                KEY_TITLE +      " TEXT," +
                KEY_ID_CLOTHES + " INTEGER NOT NULL" + " )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE if exists " + TABLE_CLOTHES);

        onCreate(db);
    }

    public Boolean insert(Clothes clths) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_TITLE, clths.getTitle());
        contentValues.put(DBHelper.KEY_ID_CLOTHES, clths.getIdClothes());

        long res = database.insert(DBHelper.TABLE_CLOTHES, null, contentValues);
        return res != -1;
    }

    public ArrayList<Clothes> getListData() {
        ArrayList<Clothes> clothes = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CLOTHES, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int titleIndex = cursor.getColumnIndex(KEY_TITLE);
            int idClothesIndex = cursor.getColumnIndex(KEY_ID_CLOTHES);

            do {
                clothes.add(new Clothes(cursor.getInt(idIndex),
                        cursor.getString(titleIndex),
                        cursor.getInt(idClothesIndex))
                );
            } while (cursor.moveToNext());
        }

        cursor.close();
        return clothes;
    }

    public Clothes getClothesById(int index) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "_id = ?";
        String[] selectionArgs = new String[] {String.valueOf(index)};
        Cursor cursor = db.query(TABLE_CLOTHES,
                null,selection, selectionArgs, null, null, null);
        if(cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(KEY_ID);
            int titleIndex = cursor.getColumnIndex(KEY_TITLE);
            int idClothesIndex = cursor.getColumnIndex(KEY_ID_CLOTHES);

            return new Clothes(cursor.getInt(idIndex),
                    cursor.getString(titleIndex),
                    cursor.getInt(idClothesIndex));
        }
        cursor.close();
        return null;
    }

    public boolean update(Clothes clothes) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_TITLE, clothes.getTitle());
        contentValues.put(DBHelper.KEY_ID_CLOTHES, clothes.getIdClothes());

        int res = db.update(TABLE_CLOTHES, contentValues, "_id = ?",
                new String[] { String.valueOf(clothes.getIdDB())});
        return res != -1;
    }

}
