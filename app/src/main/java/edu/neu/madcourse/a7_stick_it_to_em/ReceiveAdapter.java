package edu.neu.madcourse.a7_stick_it_to_em;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReceiveAdapter extends RecyclerView.Adapter<ReceiveAdapter.MyViewHolder> {

    ArrayList<ReceiveHistoryModel> historyList;
    Context context;

    public ReceiveAdapter(Context context, ArrayList<ReceiveHistoryModel>historyList){
        this.historyList=historyList;
        this.context=context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.history_card, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ReceiveHistoryModel model = historyList.get(position);
        switch (model.getSticker()){
            case "Sticker1":
                holder.sticker.setImageResource(R.drawable.sticker1);
                break;
            case "Sticker2":
                holder.sticker.setImageResource(R.drawable.sticker2);
                break;
            case "Sticker3":
                holder.sticker.setImageResource(R.drawable.sticker3);
                break;
            case "Sticker4":
                holder.sticker.setImageResource(R.drawable.sticker4);
                break;
        }

        holder.sender.setText(model.getSender());
        holder.time.setText(model.getTime());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView sticker;
        TextView sender;
        TextView time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sticker = itemView.findViewById(R.id.receivedSticker);
            sender = itemView.findViewById(R.id.sender);
            time = itemView.findViewById(R.id.receiveTime);
        }
    }
}
