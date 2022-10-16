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

import com.example.myrest.R;

import java.util.ArrayList;

public class CustomAdapter_Menu_platilloselect
    extends RecyclerView.Adapter<CustomAdapter_Menu_platilloselect.Menu_Platilloselect_MyViewHolder> {

    private Context context;

    Activity activity;

    private ArrayList Arow_menu_platillo_id , Arow_menu_platillo_platillo,
            Arow_menu_platillo_categoria,Arow_menu_platillo_descripcion ,Arow_menu_platillo_fecha ;
    private CustomAdapter_Menu_platilloselect.Menu_Platilloselect_MyViewHolder holder;
    private int position;
    public CustomAdapter_Menu_platilloselect(Activity activity, Context context,
                                       ArrayList row_menu_platillo_id,
                                       ArrayList row_menu_platillo_platillo,
                                       ArrayList row_menu_platillo_categoria,
                                       ArrayList row_menu_platillo_descripcion,
                                       ArrayList row_menu_platillo_fecha

    ){
        this.activity = activity;
        this.context= context;
        this.Arow_menu_platillo_id = row_menu_platillo_id;
        this.Arow_menu_platillo_platillo = row_menu_platillo_platillo;
        this.Arow_menu_platillo_categoria= row_menu_platillo_categoria;
        this.Arow_menu_platillo_descripcion = row_menu_platillo_descripcion;
        this.Arow_menu_platillo_fecha = row_menu_platillo_fecha;
    }

    @NonNull
    @Override
    public CustomAdapter_Menu_platilloselect.Menu_Platilloselect_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_menu_platillo_select,parent,false);
        return new CustomAdapter_Menu_platilloselect.Menu_Platilloselect_MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull
    CustomAdapter_Menu_platilloselect.Menu_Platilloselect_MyViewHolder
                                         holder,
                                 @SuppressLint("RecyclerView") int position) {


        this.position =position;
        holder.row_menu_platillo_id.setText(String.valueOf(Arow_menu_platillo_id.get(position)));
        holder.row_menu_platillo_platillo.setText(String.valueOf(Arow_menu_platillo_platillo.get(position)));
        holder.row_menu_platillo_categoria.setText(String.valueOf(Arow_menu_platillo_categoria.get(position)));
        holder.row_menu_platillo_descripcion.setText(String.valueOf(Arow_menu_platillo_descripcion.get(position)));
        holder.row_menu_platillo_fecha.setText(String.valueOf(Arow_menu_platillo_fecha.get(position)));



    }


    @Override
    public int getItemCount() {
        return Arow_menu_platillo_id.size();
    }

    public class Menu_Platilloselect_MyViewHolder extends RecyclerView.ViewHolder {

        TextView row_menu_platillo_id, row_menu_platillo_platillo,row_menu_platillo_categoria,
                row_menu_platillo_descripcion, row_menu_platillo_fecha;

        LinearLayout mainLayout;
        public Menu_Platilloselect_MyViewHolder(@NonNull View itemView) {
            super(itemView);
            row_menu_platillo_id = itemView.findViewById(R.id.row_menu_platillo_select_id);
            row_menu_platillo_platillo = itemView.findViewById(R.id.row_menu_platillo_select_platillo);
            row_menu_platillo_categoria = itemView.findViewById(R.id.row_menu_platillo_select_categoria);
            row_menu_platillo_descripcion = itemView.findViewById(R.id.row_menu_platillo_select_descripcion);
            row_menu_platillo_fecha = itemView.findViewById(R.id.row_menu_platillo_select_fecha);

            mainLayout= itemView.findViewById(R.id.row_menu_platillo_select_mainLayout);
        }
    }


}

