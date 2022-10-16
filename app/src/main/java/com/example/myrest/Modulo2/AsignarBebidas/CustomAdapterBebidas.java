package com.example.myrest.Modulo2.AsignarBebidas;

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

public class CustomAdapterBebidas extends RecyclerView.Adapter<CustomAdapterBebidas.Bebidas_MyViewHolder>{

    private Context context;
    Activity activity;
    private ArrayList Arow_bebidas_id , Arow_bebidas_bebidas,
            Arow_bebidas_categoria,Arow_bebidas_descripcion ,Arow_bebidas_precio ;
    private CustomAdapterBebidas.Bebidas_MyViewHolder holder;
    private int position;
    public CustomAdapterBebidas(Activity activity, Context context,
                                 ArrayList row_bebidas_id,
                                 ArrayList row_bebidas_bebidas,
                                 ArrayList row_bebidas_categoria,
                                 ArrayList row_bebidas_descripcion,
                                 ArrayList row_bebidas_precio){
        this.activity = activity;
        this.context= context;
        this.Arow_bebidas_id = row_bebidas_id;
        this.Arow_bebidas_bebidas = row_bebidas_bebidas;
        this.Arow_bebidas_categoria= row_bebidas_categoria;
        this.Arow_bebidas_descripcion = row_bebidas_descripcion;
        this.Arow_bebidas_precio = row_bebidas_precio;
    }
    @NonNull
    @Override
    public CustomAdapterBebidas.Bebidas_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_bebidas,parent,false);
        return new CustomAdapterBebidas.Bebidas_MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterBebidas.Bebidas_MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        this.position =position;
        holder.row_bebidas_id.setText(String.valueOf(Arow_bebidas_id.get(position)));
        holder.row_bebidas_bebidas.setText(String.valueOf(Arow_bebidas_bebidas.get(position)));
        holder.row_bebidas_categoria.setText(String.valueOf(Arow_bebidas_categoria.get(position)));
        holder.row_bebidas_descripcion.setText(String.valueOf(Arow_bebidas_descripcion.get(position)));
        holder.row_bebidas_precio.setText(String.valueOf(Arow_bebidas_precio.get(position)));


        holder.mainLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Update_delete_bebidas.class);
                intent.putExtra("id",String.valueOf(Arow_bebidas_id.get(position)));
                intent.putExtra("bebidas",String.valueOf(Arow_bebidas_bebidas.get(position)));
                intent.putExtra("categoria",String.valueOf(Arow_bebidas_categoria.get(position)));
                intent.putExtra("descripcion",String.valueOf(Arow_bebidas_descripcion.get(position)));
                intent.putExtra("precio",String.valueOf(Arow_bebidas_precio.get(position)));
                activity.startActivityForResult(intent,1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return Arow_bebidas_id.size();
    }

    public class Bebidas_MyViewHolder extends RecyclerView.ViewHolder {

        TextView row_bebidas_id, row_bebidas_bebidas,row_bebidas_categoria, row_bebidas_descripcion, row_bebidas_precio;
        LinearLayout mainLayout;
        public Bebidas_MyViewHolder(@NonNull View itemView) {
            super(itemView);
            row_bebidas_id = itemView.findViewById(R.id.row_bebidas_id);
            row_bebidas_bebidas = itemView.findViewById(R.id.row_bebidas_bebidas);
            row_bebidas_categoria = itemView.findViewById(R.id.row_bebidas_categoria);
            row_bebidas_descripcion = itemView.findViewById(R.id.row_bebidas_descripcion);
            row_bebidas_precio = itemView.findViewById(R.id.row_bebidas_precio);
            mainLayout= itemView.findViewById(R.id.row_bebidas_mainLayout);
        }
    }
}

