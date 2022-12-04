package com.example.dogapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.data.DogAdapter.DogAdapter;
import com.example.dogapp.model.DogItem;
import com.example.dogapp.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<DogItem> dogItems = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(new DogAdapter(dogItems, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        dogItems.add(new DogItem(R.drawable.australian_shepherd2, "Australian Shepherd", "0", "0"));
        dogItems.add(new DogItem(R.drawable.chiuauau2, "Chihuahua", "1", "0"));
        dogItems.add(new DogItem(R.drawable.german2, "German Shepherd", "2", "0"));
        dogItems.add(new DogItem(R.drawable.golden2, "Golden Retriever", "3", "0"));
        dogItems.add(new DogItem(R.drawable.labrador2, "Labrador Retriever", "4", "0"));
        dogItems.add(new DogItem(R.drawable.newfoundland2, "Newfoundland", "5", "0"));
        dogItems.add(new DogItem(R.drawable.poodle2, "Poodle", "0", "6"));
        dogItems.add(new DogItem(R.drawable.rottweiler2, "Rottweiler", "7", "0"));
        dogItems.add(new DogItem(R.drawable.shih_tzu2, "Shih Tzu", "8", "0"));



        return root;
    }
}