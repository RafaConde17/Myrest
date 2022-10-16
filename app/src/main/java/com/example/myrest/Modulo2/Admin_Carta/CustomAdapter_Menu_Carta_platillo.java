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

public class CustomAdapter_Menu_Carta_platillo
        extends
        RecyclerView.Adapter<CustomAdapter_Menu_Carta_platillo.Menu_carta_MyViewHolder> {

private Context context;

        Activity activity;

private ArrayList Arow_menu_carta_id , Arow_menu_carta_platillo,
        Arow_menu_carta_categoria,Arow_menu_carta_descripcion , Arow_menu_carta_precio;
private CustomAdapter_Menu_Carta_platillo.Menu_carta_MyViewHolder holder;
private int position;
public CustomAdapter_Menu_Carta_platillo(Activity activity, Context context,
                                         ArrayList row_menu_carta_id,
                                         ArrayList row_menu_carta_platillo,
                                         ArrayList row_menu_carta_categoria,
                                         ArrayList row_menu_carta_descripcion,
                                         ArrayList row_menu_carta_precio

        ){
        this.activity = activity;
        this.context= context;
        this.Arow_menu_carta_id = row_menu_carta_id;
        this.Arow_menu_carta_platillo = row_menu_carta_platillo;
        this.Arow_menu_carta_categoria= row_menu_carta_categoria;
        this.Arow_menu_carta_descripcion = row_menu_carta_descripcion;
        this.Arow_menu_carta_precio = row_menu_carta_precio;
        }

@NonNull
@Override
public CustomAdapter_Menu_Carta_platillo.Menu_carta_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_menu_carta_platillo,parent,false);
        return new CustomAdapter_Menu_Carta_platillo.Menu_carta_MyViewHolder(view);


        }

@Override
public void onBindViewHolder(@NonNull CustomAdapter_Menu_Carta_platillo.Menu_carta_MyViewHolder
        holder,
@SuppressLint("RecyclerView") int position) {


        this.position =position;
        holder.row_menu_carta_id.setText(String.valueOf(Arow_menu_carta_id.get(position)));
        holder.row_menu_carta_platillo.setText(String.valueOf(Arow_menu_carta_platillo.get(position)));
        holder.row_menu_carta_categoria.setText(String.valueOf(Arow_menu_carta_categoria.get(position)));
        holder.row_menu_carta_descripcion.setText(String.valueOf(Arow_menu_carta_descripcion.get(position)));
        holder.row_menu_carta_precio.setText(String.valueOf(Arow_menu_carta_precio.get(position)));
        }


@Override
public int getItemCount() {
        return Arow_menu_carta_id.size();
        }

public class Menu_carta_MyViewHolder extends RecyclerView.ViewHolder {

    TextView row_menu_carta_id, row_menu_carta_platillo,row_menu_carta_categoria,
            row_menu_carta_descripcion,row_menu_carta_precio ;
    LinearLayout mainLayout;
    public Menu_carta_MyViewHolder(@NonNull View itemView) {
        super(itemView);
        row_menu_carta_id = itemView.findViewById(R.id.row_menu_carta_id);
        row_menu_carta_platillo = itemView.findViewById(R.id.row_menu_carta_platillo);
        row_menu_carta_categoria = itemView.findViewById(R.id.row_menu_carta_categoria);
        row_menu_carta_descripcion = itemView.findViewById(R.id.row_menu_carta_descripcion);
        row_menu_carta_precio = itemView.findViewById(R.id.row_menu_carta_precio);
        mainLayout= itemView.findViewById(R.id.row_menu_carta_platillos_mainLayout);
    }
}
}

