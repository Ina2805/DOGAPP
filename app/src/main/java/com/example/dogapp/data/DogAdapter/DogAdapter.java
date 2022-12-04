package com.example.dogapp.data.DogAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.data.DAO.FavDB;
import com.example.dogapp.R;
import com.example.dogapp.model.DogItem;

import java.util.ArrayList;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {
    private ArrayList<DogItem> dogItems;
    private Context context;
    private FavDB favDB;

    public DogAdapter(ArrayList<DogItem> dogItems, Context context) {
        this.dogItems = dogItems;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favDB = new FavDB(context);
        //create table on first
        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            createTableOnFirstStart();
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull DogAdapter.ViewHolder holder, int position) {
        final DogItem dogItem = dogItems.get(position);

        readCursorData(dogItem, holder);
        holder.imageView.setImageResource(dogItem.getImageResource());
        holder.textView.setText(dogItem.getTitle());
    }



    @Override
    public int getItemCount() {
        return dogItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        Button favButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            favButton = itemView.findViewById(R.id.favButton);

            //add to fav button
            favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    DogItem dogItem = dogItems.get(position);

                    if (dogItem.getFavStatus().equals("0"))
                    {
                        dogItem.setFavStatus("1");
                        favDB.insertIntoDatabase(dogItem.getTitle(), dogItem.getImageResource(),dogItem.getKey_id(), dogItem.getFavStatus());
                        favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                    } else {
                        dogItem.setFavStatus("0");
                        favDB.remove_fav(dogItem.getKey_id());
                        favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24);

                    }
                }
            });
        }
    }
    private void createTableOnFirstStart() {
        favDB.insertEmpty();

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }


    private void readCursorData(DogItem dogItem, ViewHolder holder) {
        Cursor cursor = favDB.read_all_data(dogItem.getKey_id());
        SQLiteDatabase db = favDB.getReadableDatabase();
        try {
            while (cursor.moveToNext()) {
                int m=cursor.getColumnIndex(FavDB.FAVOURITE_STATUS);
                String item_fav_status = cursor.getString(m);

                //check fav status
                if (item_fav_status != null && item_fav_status.equals("1")) {
                    holder.favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                } else if (item_fav_status != null && item_fav_status.equals("0")) {
                    holder.favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }
    }
}
