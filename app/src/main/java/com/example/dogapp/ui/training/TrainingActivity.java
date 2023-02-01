package com.example.dogapp.ui.training;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.dogapp.R;
import com.example.dogapp.data.TrainingAdapter.TrainingInterface;
import com.example.dogapp.data.TrainingAdapter.TrainingAdapter;
import com.example.dogapp.model.TrainingModel;
import com.example.dogapp.ui.description.DescriptionActivity;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity implements TrainingInterface {

    ArrayList<TrainingModel> trainingModels = new ArrayList<>();

    int[] images = {R.drawable.alpha_training,
            R.drawable.clicker_training,
            R.drawable.electronic_training,
            R.drawable.mirror_training,
            R.drawable.positive_reinforcement,
            R.drawable.relationship_training,
            R.drawable.scientific_training,
            R.drawable.scientific_training,
            R.drawable.scientific_training,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        setUpTrainingModels();

        TrainingAdapter adapter = new TrainingAdapter(this, trainingModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpTrainingModels()
    {
        String[] trainingNames = getResources().getStringArray(R.array.training_array);
        String[] desc = getResources().getStringArray(R.array.descriptions_array);

        for (int i = 0; i <trainingNames.length; i++)
        {
            trainingModels.add(new TrainingModel(trainingNames[i],
                    images[i], desc[i]));
        }
    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(TrainingActivity.this, DescriptionActivity.class);

        intent.putExtra("IMAGE", trainingModels.get(position).getImage());
        intent.putExtra("NAME", trainingModels.get(position).getName());
        intent.putExtra("DESCRIPTION", trainingModels.get(position).getDescription());
        startActivity(intent);

    }
}