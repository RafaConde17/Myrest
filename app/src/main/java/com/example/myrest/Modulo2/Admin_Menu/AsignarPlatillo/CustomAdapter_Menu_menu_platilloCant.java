package com.example.myrest.Modulo2.Admin_Menu.AsignarPlatillo;

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

import com.example.myrest.Modulo2.Admin_Menu.DAO_Menu_Detalles;
import com.example.myrest.R;

import java.util.ArrayList;

public class CustomAdapter_Menu_menu_platilloCant
        extends RecyclerView.Adapter<CustomAdapter_Menu_menu_platilloCant.Menu_menu_MyViewHolder> {
    private Context context;
    // Llamado al CustomAdapter
    DAO_Menu_Detalles myDB;
    Activity activity;
    private ArrayList Arow_menu_menu_id , Arow_menu_valfecha, Arow_menu_codigo,
            Arow_menu_cantplatill;
    private CustomAdapter_Menu_menu_platilloCant.Menu_menu_MyViewHolder holder;
    private int position;
    public CustomAdapter_Menu_menu_platilloCant(Activity activity, Context context,
                                                ArrayList row_menu_menu_id,
                                                ArrayList row_menu_valfecha,
                                                ArrayList row_menu_codigo,
                                                ArrayList row_menu_cantplatillo

    ){
        this.activity = activity;
        this.context= context;
        this.Arow_menu_menu_id = row_menu_menu_id;
        this.Arow_menu_valfecha = row_menu_valfecha;
        this.Arow_menu_codigo = row_menu_codigo;
        this.Arow_menu_cantplatill= row_menu_cantplatillo;

    }

    @NonNull
    @Override
    public CustomAdapter_Menu_menu_platilloCant.Menu_menu_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_menu_menu_platillo,parent,false);
        return new CustomAdapter_Menu_menu_platilloCant.Menu_menu_MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter_Menu_menu_platilloCant.Menu_menu_MyViewHolder
                                         holder,
                                 @SuppressLint("RecyclerView") int position) {
        this.position =position;
        holder.row_menu_menu_id.setText(String.valueOf(Arow_menu_menu_id.get(position)));
        holder.row_menu_valfecha.setText(String.valueOf(Arow_menu_valfecha.get(position)));
        holder.row_menu_codigo.setText(String.valueOf(Arow_menu_codigo.get(position)));
        holder.row_menu_cantplatillo.setText(String.valueOf(Arow_menu_cantplatill.get(position)));
    }


    @Override
    public int getItemCount() {
        return Arow_menu_menu_id.size();
    }

    public class Menu_menu_MyViewHolder extends RecyclerView.ViewHolder {
        TextView row_menu_cantplatillo,
                row_menu_valfecha, row_menu_codigo,row_menu_menu_id;
        LinearLayout mainLayout;
        public Menu_menu_MyViewHolder(@NonNull View itemView) {
            super(itemView);
            row_menu_menu_id = itemView.findViewById(R.id.row_menu_menu_id);
            row_menu_valfecha = itemView.findViewById(R.id.row_menu_fecha);
            row_menu_codigo = itemView.findViewById(R.id.row_menu_codigo);
            row_menu_cantplatillo = itemView.findViewById(R.id.row_menu_cantplatillo);
            mainLayout= itemView.findViewById(R.id.row_menu_menu_mainLayout);
        }
    }


}

