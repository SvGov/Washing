package com.example.washing.clothes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.washing.R;

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ClothesViewHolder> {
//    private final List<Clothes> cards;

//    public ClothesAdapter(List<Clothes> cards) {
//        this.cards = cards;
//    }

    private final ClothesDao dao;

    public ClothesAdapter(ClothesDao dao) {
        this.dao = dao;
    }

    // Создает необходимое число холдеров ("карточек")
    @NonNull
    @Override
    public ClothesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        ClothesViewHolder cvh = new ClothesViewHolder(view);
        return cvh;
    }

    // Отображает на холдерах нужную нам информацию
    @Override
    public void onBindViewHolder(@NonNull ClothesViewHolder holder, int index) {
        holder.title.setText(dao.get(index).getTitle());
        holder.photoClothes.setImageResource(dao.get(index).getIdClothes());
    }

    @Override
    public int getItemCount() {
        return dao.size();
    }

    public static class ClothesViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView photoClothes;
//        ImageView photoLabel;

        public ClothesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_clothes);
            photoClothes = itemView.findViewById(R.id.iv_clothes_mini);
        }
    }


}