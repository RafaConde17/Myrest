package com.example.myrest.Modulo2.AsignarPlatillo;

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

public class CustomAdapterPlatillos
        extends RecyclerView.Adapter<CustomAdapterPlatillos.Platillo_MyViewHolder> {



    private Context context;

    Activity activity;
    private ArrayList Arow_platillo_id , Arow_platillo_platillo,
            Arow_platillo_categoria,Arow_platillo_descripcion ,Arow_platillo_precio ;
    private Platillo_MyViewHolder holder;
    private int position;
    public CustomAdapterPlatillos(Activity activity, Context context,
                            ArrayList row_platillo_id,
                            ArrayList row_platillo_platillo,
                            ArrayList row_platillo_categoria,
                            ArrayList row_platillo_descripcion,
                            ArrayList row_platillo_precio){
        this.activity = activity;
        this.context= context;
        this.Arow_platillo_id = row_platillo_id;
        this.Arow_platillo_platillo = row_platillo_platillo;
        this.Arow_platillo_categoria= row_platillo_categoria;
        this.Arow_platillo_descripcion = row_platillo_descripcion;
        this.Arow_platillo_precio = row_platillo_precio;

    }

    @NonNull
    @Override
    public Platillo_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_platillo,parent,false);
        return new Platillo_MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull Platillo_MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        this.position =position;
        holder.row_platillo_id.setText(String.valueOf(Arow_platillo_id.get(position)));
        holder.row_platillo_platillo.setText(String.valueOf(Arow_platillo_platillo.get(position)));
        holder.row_platillo_categoria.setText(String.valueOf(Arow_platillo_categoria.get(position)));
        holder.row_platillo_descripcion.setText(String.valueOf(Arow_platillo_descripcion.get(position)));
        holder.row_platillo_precio.setText(String.valueOf(Arow_platillo_precio.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_delete_platillo.class);
                intent.putExtra("id",String.valueOf(Arow_platillo_id.get(position)));
                intent.putExtra("platillo",String.valueOf(Arow_platillo_platillo.get(position)));
                intent.putExtra("categoria",String.valueOf(Arow_platillo_categoria.get(position)));
                intent.putExtra("descripcion",String.valueOf(Arow_platillo_descripcion.get(position)));
                intent.putExtra("precio",String.valueOf(Arow_platillo_precio.get(position)));
                activity.startActivityForResult(intent,1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return Arow_platillo_id.size();
    }

    public class Platillo_MyViewHolder extends RecyclerView.ViewHolder {

        TextView row_platillo_id, row_platillo_platillo,row_platillo_categoria, row_platillo_descripcion, row_platillo_precio;
        LinearLayout mainLayout;
        public Platillo_MyViewHolder(@NonNull View itemView) {
            super(itemView);
            row_platillo_id = itemView.findViewById(R.id.row_platillo_id);
            row_platillo_platillo = itemView.findViewById(R.id.row_platillo_platillo);
            row_platillo_categoria = itemView.findViewById(R.id.row_platillo_categoria);
            row_platillo_descripcion = itemView.findViewById(R.id.row_platillo_descripcion);
            row_platillo_precio = itemView.findViewById(R.id.row_platillo_precio);
            mainLayout= itemView.findViewById(R.id.row_platillo_mainLayoutUsu);
        }
    }


}
