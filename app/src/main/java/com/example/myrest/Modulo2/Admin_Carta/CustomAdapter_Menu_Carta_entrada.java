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

public class CustomAdapter_Menu_Carta_entrada
        extends
        RecyclerView.Adapter<CustomAdapter_Menu_Carta_entrada.Menu_carta_entrada_yViewHolder> {

    private Context context;

    Activity activity;

    private ArrayList Arow_menu_carta_entrada_id , Arow_menu_carta_entrada_entrada,
            Arow_menu_carta_entrada_categoria,Arow_menu_carta_entrada_descripcion , Arow_menu_carta_entrada_precio;
    private CustomAdapter_Menu_Carta_entrada.Menu_carta_entrada_yViewHolder holder;
    private int position;
    public CustomAdapter_Menu_Carta_entrada(Activity activity, Context context,
                                           ArrayList row_menu_carta_entrada_id,
                                           ArrayList arow_menu_carta_entrada_entrada,
                                           ArrayList row_menu_carta_entrada_categoria,
                                           ArrayList row_menu_carta_entrada_descripcion,
                                           ArrayList row_menu_carta_entrada_precio

    ){
        this.activity = activity;
        this.context= context;
        this.Arow_menu_carta_entrada_id = row_menu_carta_entrada_id;
        this.Arow_menu_carta_entrada_entrada   = arow_menu_carta_entrada_entrada;
        this.Arow_menu_carta_entrada_categoria= row_menu_carta_entrada_categoria;
        this.Arow_menu_carta_entrada_descripcion = row_menu_carta_entrada_descripcion;
        this.Arow_menu_carta_entrada_precio = row_menu_carta_entrada_precio;
    }

    @NonNull
    @Override
    public CustomAdapter_Menu_Carta_entrada.Menu_carta_entrada_yViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_menu_carta_entrada  ,parent,false);
        return new CustomAdapter_Menu_Carta_entrada.Menu_carta_entrada_yViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter_Menu_Carta_entrada.Menu_carta_entrada_yViewHolder
                                         holder,
                                 @SuppressLint("RecyclerView") int position) {


        this.position =position;
        holder.row_menu_carta_entrada_id.setText(String.valueOf(Arow_menu_carta_entrada_id.get(position)));
        holder.row_menu_carta_entrada_entrada.setText(String.valueOf(Arow_menu_carta_entrada_entrada.get(position)));
        holder.row_menu_carta_entrada_categoria.setText(String.valueOf(Arow_menu_carta_entrada_categoria.get(position)));
        holder.row_menu_carta_entrada_descripcion.setText(String.valueOf(Arow_menu_carta_entrada_descripcion.get(position)));
        holder.row_menu_carta_entrada_precio.setText(String.valueOf(Arow_menu_carta_entrada_precio.get(position)));
    }


    @Override
    public int getItemCount() {
        return Arow_menu_carta_entrada_id.size();
    }

    public class Menu_carta_entrada_yViewHolder extends RecyclerView.ViewHolder {

        TextView row_menu_carta_entrada_id,
                row_menu_carta_entrada_entrada,
                row_menu_carta_entrada_categoria,
                row_menu_carta_entrada_descripcion,
                row_menu_carta_entrada_precio ;
        LinearLayout mainLayout;
        public Menu_carta_entrada_yViewHolder(@NonNull View itemView) {
            super(itemView);
            row_menu_carta_entrada_id = itemView.findViewById(R.id.row_menu_carta_entrada_id);
            row_menu_carta_entrada_entrada = itemView.findViewById(R.id.row_menu_carta_entrada);
            row_menu_carta_entrada_categoria = itemView.findViewById(R.id.row_menu_carta_entrada_categoria);
            row_menu_carta_entrada_descripcion = itemView.findViewById(R.id.row_menu_carta_entrada_descripcion);
            row_menu_carta_entrada_precio = itemView.findViewById(R.id.row_menu_carta_entrada_precio);
            mainLayout= itemView.findViewById(R.id.row_menu_carta_entrada_mainLayout);
        }
    }
}

