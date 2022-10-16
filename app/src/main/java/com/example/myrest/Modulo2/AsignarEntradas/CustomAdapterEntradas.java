package com.example.myrest.Modulo2.AsignarEntradas;

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

public class CustomAdapterEntradas extends RecyclerView.Adapter<CustomAdapterEntradas.Entradas_MyViewHolder>{




    private Context context;

    Activity activity;
    private ArrayList Arow_entradas_id , Arow_entradas_entrada,
            Arow_entradas_categoria,Arow_entradas_descripcion ,Arow_entradas_precio ;
    private CustomAdapterEntradas.Entradas_MyViewHolder holder;
    private int position;
    public CustomAdapterEntradas(Activity activity, Context context,
                                  ArrayList row_entradas_id,
                                  ArrayList row_entradas_entrada,
                                  ArrayList row_entradas_categoria,
                                  ArrayList row_entradas_descripcion,
                                  ArrayList row_entradas_precio){
        this.activity = activity;
        this.context= context;
        this.Arow_entradas_id = row_entradas_id;
        this.Arow_entradas_entrada = row_entradas_entrada;
        this.Arow_entradas_categoria= row_entradas_categoria;
        this.Arow_entradas_descripcion = row_entradas_descripcion;
        this.Arow_entradas_precio = row_entradas_precio;

    }

    @NonNull
    @Override
    public Entradas_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_entradas,parent,false);
        return new Entradas_MyViewHolder(view);


    }





    @Override
    public void onBindViewHolder(@NonNull Entradas_MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        this.position =position;
        holder.row_entradas_id.setText(String.valueOf(Arow_entradas_id.get(position)));
        holder.row_entradas_entrada.setText(String.valueOf(Arow_entradas_entrada.get(position)));
        holder.row_entradas_categoria.setText(String.valueOf(Arow_entradas_categoria.get(position)));
        holder.row_entradas_descripcion.setText(String.valueOf(Arow_entradas_descripcion.get(position)));
        holder.row_entradas_precio.setText(String.valueOf(Arow_entradas_precio.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Update_delete_entradas.class);
                intent.putExtra("id",String.valueOf(Arow_entradas_id.get(position)));
                intent.putExtra("entrada",String.valueOf(Arow_entradas_entrada.get(position)));
                intent.putExtra("categoria",String.valueOf(Arow_entradas_categoria.get(position)));
                intent.putExtra("descripcion",String.valueOf(Arow_entradas_descripcion.get(position)));
                intent.putExtra("precio",String.valueOf(Arow_entradas_precio.get(position)));
                activity.startActivityForResult(intent,1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return Arow_entradas_id.size();
    }

    public class Entradas_MyViewHolder extends RecyclerView.ViewHolder {

        TextView row_entradas_id, row_entradas_entrada,row_entradas_categoria, row_entradas_descripcion, row_entradas_precio;
        LinearLayout mainLayout;
        public Entradas_MyViewHolder(@NonNull View itemView) {
            super(itemView);
            row_entradas_id = itemView.findViewById(R.id.row_Entradas_id);
            row_entradas_entrada = itemView.findViewById(R.id.row_Entradas_Entrada);
            row_entradas_categoria = itemView.findViewById(R.id.row_Entradas_categoria);
            row_entradas_descripcion = itemView.findViewById(R.id.row_Entradas_descripcion);
            row_entradas_precio = itemView.findViewById(R.id.row_Entradas_precio);
            mainLayout= itemView.findViewById(R.id.row_Entradas_mainLayout);
        }
    }


}

