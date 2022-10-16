package com.example.myrest.Modulo2.Admin_Carta;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myrest.R;

import java.util.ArrayList;

public class CustomAdapter_Carta_platillo

        extends RecyclerView.Adapter<CustomAdapter_Carta_platillo.Carta_Platillo_MyViewHolder> {

    private Context context;

    Activity activity;
    private ArrayList Aval_platilloid;
    private ArrayList Aguar_platilloid;
    private ArrayList Arow_carta_platillo_id , Arow_carta_platillo_platillo,
            Arow_carta_platillo_categoria,Arow_carta_platillo_descripcion ,
            Arow_carta_platillo_precio ;
    private CustomAdapter_Carta_platillo.Carta_Platillo_MyViewHolder holder;
    private int position;
    public CustomAdapter_Carta_platillo(Activity activity, Context context,
                                       ArrayList row_carta_platillo_id,
                                       ArrayList row_carta_platillo_platillo,
                                       ArrayList row_carta_platillo_categoria,
                                       ArrayList row_carta_platillo_descripcion,
                                       ArrayList row_carta_platillo_precio,
                                       ArrayList Aval_platilloid
    ){
        this.activity = activity;
        this.context= context;
        this.Arow_carta_platillo_id = row_carta_platillo_id;
        this.Arow_carta_platillo_platillo = row_carta_platillo_platillo;
        this.Arow_carta_platillo_categoria= row_carta_platillo_categoria;
        this.Arow_carta_platillo_descripcion = row_carta_platillo_descripcion;
        this.Arow_carta_platillo_precio = row_carta_platillo_precio;
        this.Aguar_platilloid = Aval_platilloid;
    }

    @NonNull
    @Override
    public CustomAdapter_Carta_platillo.Carta_Platillo_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_carta_platilloselect,parent,false);
        return new CustomAdapter_Carta_platillo.Carta_Platillo_MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter_Carta_platillo.Carta_Platillo_MyViewHolder
                                         holder,
                                 @SuppressLint("RecyclerView") int position) {


        this.position =position;
        holder.row_carta_platillo_id.setText(String.valueOf(Arow_carta_platillo_id.get(position)));
        holder.row_carta_platillo_platillo.setText(String.valueOf(Arow_carta_platillo_platillo.get(position)));
        holder.row_carta_platillo_categoria.setText(String.valueOf(Arow_carta_platillo_categoria.get(position)));
        holder.row_carta_platillo_descripcion.setText(String.valueOf(Arow_carta_platillo_descripcion.get(position)));
        holder.row_carta_platillo_precio.setText(String.valueOf(Arow_carta_platillo_precio.get(position)));

        holder.row_check3.setOnClickListener(view -> {

            if (((CheckBox) view).isChecked())
            {
                Aguar_platilloid.add(String.valueOf(Arow_carta_platillo_id.get(position)));
            }else
            {
                for (int ic = 0;ic<Aguar_platilloid.size();ic++){
                    if (Aguar_platilloid.get(ic) == Arow_carta_platillo_id.get(position) ) {
                        Aguar_platilloid.remove(ic);
                    }
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return Arow_carta_platillo_id.size();
    }

    public class Carta_Platillo_MyViewHolder extends RecyclerView.ViewHolder {

        TextView row_carta_platillo_id, row_carta_platillo_platillo,row_carta_platillo_categoria,
                row_carta_platillo_descripcion, row_carta_platillo_precio;
        CheckBox row_check3;
        LinearLayout mainLayout;
        public Carta_Platillo_MyViewHolder(@NonNull View itemView) {
            super(itemView);
            row_carta_platillo_id = itemView.findViewById(R.id.row_carta_platilloselect_id);
            row_carta_platillo_platillo = itemView.findViewById(R.id.row_carta_platilloselect_platillo);
            row_carta_platillo_categoria = itemView.findViewById(R.id.row_carta_platilloselect_categoria);
            row_carta_platillo_descripcion = itemView.findViewById(R.id.row_carta_platilloselect_descripcion);
            row_carta_platillo_precio = itemView.findViewById(R.id.row_carta_platilloselect_precio);
            row_check3 = itemView.findViewById(R.id.carta_platilloselect_checkbox_1);
            mainLayout= itemView.findViewById(R.id.row_carta_platilloselect_mainLayout);
        }
    }
}
