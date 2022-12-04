package com.example.dogapp.data.TrainingAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.R;
import com.example.dogapp.model.TrainingModel;

import java.util.ArrayList;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder> {

    private final TrainingInterface trainingInterface;
    Context context;
    ArrayList<TrainingModel> trainingModels;


    public TrainingAdapter(Context context, ArrayList<TrainingModel> trainingModels, TrainingInterface trainingInterface) {
        this.trainingInterface = trainingInterface;
        this.context = context;
        this.trainingModels = trainingModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Giving a look to our recycler view rows
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_view_row, parent, false);
        return new TrainingAdapter.ViewHolder(view, trainingInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingAdapter.ViewHolder holder, int position) {
        //assigning values we created in the layout file based on a position of the recycler view
        holder.textView.setText(trainingModels.get(position).getName());
        holder.imageView.setImageResource(trainingModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        //telling recycler view the number of items I want to display which is the whole list
        return trainingModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView, TrainingInterface trainingInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView2);
            textView = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (trainingInterface != null)
                    {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION)
                        {
                            trainingInterface.onItemClick(pos);
                        }


                    }
                }
            });
        }
    }

}
