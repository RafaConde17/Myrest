package com.example.myrest.Modulo2.Admin_Menu.AsignarEntrada;

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

public class CustomAdapter_Menu_menu_entradaCant
        extends RecyclerView.Adapter<CustomAdapter_Menu_menu_entradaCant.Menu_menu_entrada_MyViewHolder> {
    private Context context;
    // Llamado al CustomAdapter

    Activity activity;
    private ArrayList Arow_menu_menu_entrada_id , Arow_menu_entrada_valfecha, Arow_menu_entrada_codigo,
           Arow_menu_cantentradas  ;
    private CustomAdapter_Menu_menu_entradaCant.Menu_menu_entrada_MyViewHolder holder;
    private int position;
    public CustomAdapter_Menu_menu_entradaCant(Activity activity, Context context,
                                               ArrayList row_menu_menu_entrada_id,
                                               ArrayList row_menu_entrada_valfecha,
                                               ArrayList row_menu_entrada_codigo,
                                               ArrayList row_menu_entrada_cantentradas

    ){
        this.activity = activity;
        this.context= context;
        this.Arow_menu_menu_entrada_id = row_menu_menu_entrada_id;
        this.Arow_menu_entrada_valfecha = row_menu_entrada_valfecha;
        this.Arow_menu_entrada_codigo = row_menu_entrada_codigo;
        this.Arow_menu_cantentradas= row_menu_entrada_cantentradas;
    }
    @NonNull
    @Override
    public CustomAdapter_Menu_menu_entradaCant.Menu_menu_entrada_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_menu_menu_entrada,parent,false);
        return new CustomAdapter_Menu_menu_entradaCant.Menu_menu_entrada_MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter_Menu_menu_entradaCant.Menu_menu_entrada_MyViewHolder
                                         holder,
                                 @SuppressLint("RecyclerView") int position) {
        this.position =position;
        holder.row_menu_menu_entrada_id.setText(String.valueOf(Arow_menu_menu_entrada_id.get(position)));
        holder.row_menu_entrada_valfecha.setText(String.valueOf(Arow_menu_entrada_valfecha.get(position)));
        holder.row_menu_entrada_codigo.setText(String.valueOf(Arow_menu_entrada_codigo.get(position)));
        holder.row_menu_entrada_cantentradas.setText(String.valueOf(Arow_menu_cantentradas.get(position)));

        //      for (int ic = 0;ic<Aguar_platilloid.size();ic++) {

        //    }
        // holder.row_menu_cantplatillo.setText(String.valueOf(Arow_menu_cantplatill.get(position)));
        //    holder.row_menu_cantentradas.setText(String.valueOf(Arow_menu_cantentradas.get(position)));
        //    holder.row_menu_cantbebidas.setText(String.valueOf(Arow_menu_cantbebidas.get(position)));
    }


    @Override
    public int getItemCount() {
        return Arow_menu_menu_entrada_id.size();
    }

    public class Menu_menu_entrada_MyViewHolder extends RecyclerView.ViewHolder {
        TextView row_menu_menu_entrada_id,
                row_menu_entrada_valfecha, row_menu_entrada_codigo,row_menu_entrada_cantentradas;
        LinearLayout mainLayout;
        public Menu_menu_entrada_MyViewHolder(@NonNull View itemView) {
            super(itemView);
            row_menu_menu_entrada_id = itemView.findViewById(R.id.row_menu_menu_entrada_id);
            row_menu_entrada_valfecha = itemView.findViewById(R.id.row_menu_entrada_fecha);
            row_menu_entrada_codigo = itemView.findViewById(R.id.row_menu_entrada_codigo);
            row_menu_entrada_cantentradas = itemView.findViewById(R.id.row_menu_entrada_cantentrada );
            mainLayout= itemView.findViewById(R.id.row_menu_menu_entrada_mainLayout);
        }
    }


}

