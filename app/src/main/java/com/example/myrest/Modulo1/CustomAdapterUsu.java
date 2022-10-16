package com.example.myrest.Modulo1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrest.R;

import java.util.ArrayList;

public class CustomAdapterUsu extends RecyclerView.Adapter<CustomAdapterUsu.MyViewHolder> {


    private Context context;

    Activity activity;
    private ArrayList Atxtid , Atxtusuario,Atxttipousuario ,Atxtdatos ;
    private MyViewHolder holder;
    private int position;
    public CustomAdapterUsu(Activity activity, Context context,
                            ArrayList id,
                            ArrayList usuario,

                            ArrayList tipousuario,
                            ArrayList datos){
        this.activity = activity;
        this.context= context;
        this.Atxtid = id;
        this.Atxtusuario = usuario;

        this.Atxttipousuario= tipousuario;
        this.Atxtdatos = datos;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_usu,parent,false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        this.position =position;
        holder.txtid.setText(String.valueOf(Atxtid.get(position)));
        holder.txtusuario.setText(String.valueOf(Atxtusuario.get(position)));
        holder.txttipousuario.setText(String.valueOf(Atxttipousuario.get(position)));
        holder.txtdato.setText(String.valueOf(Atxtdatos.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_delete_usu.class);
                        intent.putExtra("id",String.valueOf(Atxtid.get(position)));
                        intent.putExtra("usuario",String.valueOf(Atxtusuario.get(position)));
                        intent.putExtra("tipousuario",String.valueOf(Atxttipousuario.get(position)));
                        intent.putExtra("datos",String.valueOf(Atxtdatos.get(position)));
                        activity.startActivityForResult(intent,1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return Atxtid.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtid, txtusuario, txttipousuario, txtdato;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtid = itemView.findViewById(R.id.txtid);
            txtusuario = itemView.findViewById(R.id.txtusuario);
            txttipousuario = itemView.findViewById(R.id.txttipousuario);
            txtdato = itemView.findViewById(R.id.txtdato);
             mainLayout= itemView.findViewById(R.id.mainLayoutUsu);
        }
    }
}
