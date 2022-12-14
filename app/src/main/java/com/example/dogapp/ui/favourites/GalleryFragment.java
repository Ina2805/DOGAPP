package com.example.dogapp.ui.favourites;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.data.FavAdapter.FavAdapter;
import com.example.dogapp.data.DAO.FavDB;
import com.example.dogapp.model.FavItem;
import com.example.dogapp.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavDB favDB;
    private List<FavItem> favItemList = new ArrayList<>();
    private FavAdapter favAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        favDB = new FavDB(getActivity());
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadData();

        return root;
    }

    private void loadData() {

        if (favItemList != null){
            favItemList.clear();
        }
        SQLiteDatabase db = favDB.getReadableDatabase();
        Cursor cursor = favDB.select_all_fav_list();
        try {
            while(cursor.moveToNext()){
                String title = cursor.getString(cursor.getColumnIndexOrThrow(FavDB.ITEM_TITLE));
                String id = cursor.getString(cursor.getColumnIndexOrThrow(FavDB.KEY_ID));
                int image = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(FavDB.ITEM_IMAGE)));
                FavItem favItem = new FavItem(title, id, image);
                favItemList.add(favItem);

            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }
        favAdapter = new FavAdapter(getActivity(), favItemList);

        recyclerView.setAdapter(favAdapter);
    }
}
