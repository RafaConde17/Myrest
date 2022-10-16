package com.example.myrest.Modulo2.Admin_Carta;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrest.R;

import java.util.ArrayList;

public class CustomAdapter_Menu_Carta_bebida
        extends
        RecyclerView.Adapter<CustomAdapter_Menu_Carta_bebida.Menu_carta_bebida_yViewHolder> {

    private Context context;

    Activity activity;

    private ArrayList Arow_menu_carta_bebida_id , Arow_menu_carta_bebida_bebida,
            Arow_menu_carta_bebida_categoria,Arow_menu_carta_bebida_descripcion , Arow_menu_carta_bebida_precio;
    private CustomAdapter_Menu_Carta_bebida.Menu_carta_bebida_yViewHolder holder;
    private int position;
    public CustomAdapter_Menu_Carta_bebida(Activity activity, Context context,
                                             ArrayList row_menu_carta_bebida_id,
                                             ArrayList arow_menu_carta_bebida_bebida,
                                             ArrayList row_menu_carta_bebida_categoria,
                                             ArrayList row_menu_carta_bebida_descripcion,
                                             ArrayList row_menu_carta_bebida_precio

    ){
        this.activity = activity;
        this.context= context;
        this.Arow_menu_carta_bebida_id = row_menu_carta_bebida_id;
        this.Arow_menu_carta_bebida_bebida   = arow_menu_carta_bebida_bebida;
        this.Arow_menu_carta_bebida_categoria= row_menu_carta_bebida_categoria;
        this.Arow_menu_carta_bebida_descripcion = row_menu_carta_bebida_descripcion;
        this.Arow_menu_carta_bebida_precio = row_menu_carta_bebida_precio;
    }

    @NonNull
    @Override
    public CustomAdapter_Menu_Carta_bebida.Menu_carta_bebida_yViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_menu_carta_bebida  ,parent,false);
        return new CustomAdapter_Menu_Carta_bebida.Menu_carta_bebida_yViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter_Menu_Carta_bebida.Menu_carta_bebida_yViewHolder
                                         holder,
                                 @SuppressLint("RecyclerView") int position) {


        this.position =position;
        holder.row_menu_carta_bebida_id.setText(String.valueOf(Arow_menu_carta_bebida_id.get(position)));
        holder.row_menu_carta_bebida_bebida.setText(String.valueOf(Arow_menu_carta_bebida_bebida.get(position)));
        holder.row_menu_carta_bebida_categoria.setText(String.valueOf(Arow_menu_carta_bebida_categoria.get(position)));
        holder.row_menu_carta_bebida_descripcion.setText(String.valueOf(Arow_menu_carta_bebida_descripcion.get(position)));
        holder.row_menu_carta_bebida_precio.setText(String.valueOf(Arow_menu_carta_bebida_precio.get(position)));
    }


    @Override
    public int getItemCount() {
        return Arow_menu_carta_bebida_id.size();
    }

    public class Menu_carta_bebida_yViewHolder extends RecyclerView.ViewHolder {

        TextView row_menu_carta_bebida_id, row_menu_carta_bebida_bebida,row_menu_carta_bebida_categoria,
                row_menu_carta_bebida_descripcion,row_menu_carta_bebida_precio ;
        LinearLayout mainLayout;
        public Menu_carta_bebida_yViewHolder(@NonNull View itemView) {
            super(itemView);
            row_menu_carta_bebida_id = itemView.findViewById(R.id.row_menu_carta_bebida_id);
            row_menu_carta_bebida_bebida = itemView.findViewById(R.id.row_menu_carta_bebida);
            row_menu_carta_bebida_categoria = itemView.findViewById(R.id.row_menu_carta_bebida_categoria);
            row_menu_carta_bebida_descripcion = itemView.findViewById(R.id.row_menu_carta_bebida_descripcion);
            row_menu_carta_bebida_precio = itemView.findViewById(R.id.row_menu_carta_bebida_precio);
            mainLayout= itemView.findViewById(R.id.row_menu_carta_bebidas_mainLayout);
        }
    }
}

