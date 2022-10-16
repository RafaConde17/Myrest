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

public class CustomAdapter_Menu_Menudia_platillos extends
        RecyclerView.Adapter<CustomAdapter_Menu_Menudia_platillos.Menu_menudia_MyViewHolder> {

    private Context context;

    Activity activity;

    private ArrayList Arow_menu_menudia_id , Arow_menu_menudia_platillo,
            Arow_menu_menudia_categoria,Arow_menu_menudia_descripcion ;
    private CustomAdapter_Menu_Menudia_platillos.Menu_menudia_MyViewHolder holder;
    private int position;
    public CustomAdapter_Menu_Menudia_platillos(Activity activity, Context context,
                                                ArrayList row_menu_menudia_id,
                                                ArrayList row_menu_menudia_platillo,
                                                ArrayList row_menu_menudia_categoria,
                                                ArrayList row_menu_menudia_descripcion


    ){
        this.activity = activity;
        this.context= context;
        this.Arow_menu_menudia_id = row_menu_menudia_id;
        this.Arow_menu_menudia_platillo = row_menu_menudia_platillo;
        this.Arow_menu_menudia_categoria= row_menu_menudia_categoria;
        this.Arow_menu_menudia_descripcion = row_menu_menudia_descripcion;
    }

    @NonNull
    @Override
    public CustomAdapter_Menu_Menudia_platillos.Menu_menudia_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_menu_menudia_platillo,parent,false);
        return new CustomAdapter_Menu_Menudia_platillos.Menu_menudia_MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter_Menu_Menudia_platillos.Menu_menudia_MyViewHolder
                                         holder,
                                 @SuppressLint("RecyclerView") int position) {


        this.position =position;
       holder.row_menu_menudia_id.setText(String.valueOf(Arow_menu_menudia_id.get(position)));
       holder.row_menu_menudia_platillo.setText(String.valueOf(Arow_menu_menudia_platillo.get(position)));
       holder.row_menu_menudia_categoria.setText(String.valueOf(Arow_menu_menudia_categoria.get(position)));
        holder.row_menu_menudia_descripcion.setText(String.valueOf(Arow_menu_menudia_descripcion.get(position)));
    }


    @Override
    public int getItemCount() {
        return Arow_menu_menudia_id.size();
    }

    public class Menu_menudia_MyViewHolder extends RecyclerView.ViewHolder {

        TextView row_menu_menudia_id, row_menu_menudia_platillo,row_menu_menudia_categoria,
                row_menu_menudia_descripcion;
        LinearLayout mainLayout;
        public Menu_menudia_MyViewHolder(@NonNull View itemView) {
            super(itemView);
            row_menu_menudia_id = itemView.findViewById(R.id.row_menu_menudia_id);
            row_menu_menudia_platillo = itemView.findViewById(R.id.row_menu_menudia_platillo);
            row_menu_menudia_categoria = itemView.findViewById(R.id.row_menu_menudia_categoria);
            row_menu_menudia_descripcion = itemView.findViewById(R.id.row_menu_menudia_descripcion);
            mainLayout= itemView.findViewById(R.id.row_menu_menudia_platillos_mainLayout);
        }
    }
}

