package com.example.myrest.Modulo2.Admin_Menu;

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

public class CustomAdapter_Menu_Menudia_entradas extends
        RecyclerView.Adapter<CustomAdapter_Menu_Menudia_entradas.Menu_menudia_entradas_MyViewHolder> {

    private Context context;

    Activity activity;

    private ArrayList Arow_menu_menudia_entradas_id , Arow_menu_menudia_entradas_entrada,
            Arow_menu_menudia_entradas_categoria,Arow_menu_menudia_entradas_descripcion ;
    private CustomAdapter_Menu_Menudia_entradas.Menu_menudia_entradas_MyViewHolder holder;
    private int position;
    public CustomAdapter_Menu_Menudia_entradas(Activity activity, Context context,
                                                ArrayList row_menu_menudia_entradas_id,
                                                ArrayList row_menu_menudia_entradas_entrada,
                                                ArrayList row_menu_menudia_entradas_categoria,
                                                ArrayList row_menu_menudia_entradas_descripcion


    ){
        this.activity = activity;
        this.context= context;
        this.Arow_menu_menudia_entradas_id = row_menu_menudia_entradas_id;
        this.Arow_menu_menudia_entradas_entrada = row_menu_menudia_entradas_entrada;
        this.Arow_menu_menudia_entradas_categoria= row_menu_menudia_entradas_categoria;
        this.Arow_menu_menudia_entradas_descripcion = row_menu_menudia_entradas_descripcion;
    }

    @NonNull
    @Override
    public CustomAdapter_Menu_Menudia_entradas.Menu_menudia_entradas_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_menu_menudia_entradas,parent,false);
        return new CustomAdapter_Menu_Menudia_entradas.Menu_menudia_entradas_MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter_Menu_Menudia_entradas.Menu_menudia_entradas_MyViewHolder
                                         holder,
                                 @SuppressLint("RecyclerView") int position) {


        this.position =position;
        holder.row_menu_menudia_entradas_id.setText(String.valueOf(Arow_menu_menudia_entradas_id.get(position)));
        holder.row_menu_menudia_entradas_entrada.setText(String.valueOf(Arow_menu_menudia_entradas_entrada.get(position)));
        holder.row_menu_menudia_entradas_categoria.setText(String.valueOf(Arow_menu_menudia_entradas_categoria.get(position)));
        holder.row_menu_menudia_entradas_descripcion.setText(String.valueOf(Arow_menu_menudia_entradas_descripcion.get(position)));
    }


    @Override
    public int getItemCount() {
        return Arow_menu_menudia_entradas_id.size();
    }

    public class Menu_menudia_entradas_MyViewHolder extends RecyclerView.ViewHolder {

        TextView row_menu_menudia_entradas_id, row_menu_menudia_entradas_entrada,row_menu_menudia_entradas_categoria,
                row_menu_menudia_entradas_descripcion;
        LinearLayout mainLayout;
        public Menu_menudia_entradas_MyViewHolder(@NonNull View itemView) {
            super(itemView);
            row_menu_menudia_entradas_id = itemView.findViewById(R.id.row_menu_menudia_entradas_id);
            row_menu_menudia_entradas_entrada = itemView.findViewById(R.id.row_menu_menudia_entradas_entrada);
            row_menu_menudia_entradas_categoria = itemView.findViewById(R.id.row_menu_menudia_entradas_categoria);
            row_menu_menudia_entradas_descripcion = itemView.findViewById(R.id.row_menu_menudia_entradas_descripcion);
            mainLayout= itemView.findViewById(R.id.row_menu_menudia_entradas_mainLayout);
        }
    }
}

