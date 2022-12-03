package com.example.cavaleiro_da_lua;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList _id, _nome, _email, _mensagem;

    CustomAdapter(Activity activity, Context context, ArrayList _id, ArrayList _nome, ArrayList _email, ArrayList _mensagem){
        this.activity = activity;
        this.context = context;
        this._id = _id;
        this._nome = _nome;
        this._email = _email;
        this._mensagem = _mensagem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_review, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder._id.setText(String.valueOf(_id.get(position)));
        holder._nome.setText(String.valueOf(_nome.get(position)));
        holder._email.setText(String.valueOf(_email.get(position)));
        holder._mensagem.setText(String.valueOf(_mensagem.get(position)));

        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", String.valueOf(_id.get(position)));
            intent.putExtra("nome", String.valueOf(_nome.get(position)));
            intent.putExtra("email", String.valueOf(_email.get(position)));
            intent.putExtra("mensagem", String.valueOf(_mensagem.get(position)));
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return _id.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView _id, _nome, _email, _mensagem;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            _id = itemView.findViewById(R.id.item_review_id);
            _nome = itemView.findViewById(R.id.item_review_nome);
            _email = itemView.findViewById(R.id.item_review_email);
            _mensagem = itemView.findViewById(R.id.item_review_mensagem);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}