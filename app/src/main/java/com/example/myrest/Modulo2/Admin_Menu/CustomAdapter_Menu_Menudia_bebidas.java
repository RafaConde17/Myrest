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

public class CustomAdapter_Menu_Menudia_bebidas extends
        RecyclerView.Adapter<CustomAdapter_Menu_Menudia_bebidas.Menu_menudia_bebidas_MyViewHolder> {

    private Context context;

    Activity activity;

    private ArrayList Arow_menu_menudia_bebidas_id , Arow_menu_menudia_bebidas_bebida,
            Arow_menu_menudia_bebidas_categoria,Arow_menu_menudia_bebidas_descripcion ;
    private CustomAdapter_Menu_Menudia_bebidas.Menu_menudia_bebidas_MyViewHolder holder;
    private int position;
    public CustomAdapter_Menu_Menudia_bebidas(Activity activity, Context context,
                                               ArrayList row_menu_menudia_bebidas_id,
                                               ArrayList arow_menu_menudia_bebidas_bebida,
                                               ArrayList row_menu_menudia_bebidas_categoria,
                                               ArrayList row_menu_menudia_bebidas_descripcion


    ){
        this.activity = activity;
        this.context= context;
        this.Arow_menu_menudia_bebidas_id = row_menu_menudia_bebidas_id;
        this.Arow_menu_menudia_bebidas_bebida = arow_menu_menudia_bebidas_bebida;
        this.Arow_menu_menudia_bebidas_categoria= row_menu_menudia_bebidas_categoria;
        this.Arow_menu_menudia_bebidas_descripcion = row_menu_menudia_bebidas_descripcion;
    }

    @NonNull
    @Override
    public CustomAdapter_Menu_Menudia_bebidas.Menu_menudia_bebidas_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_menu_menudia_bebidas,parent,false);
        return new CustomAdapter_Menu_Menudia_bebidas.Menu_menudia_bebidas_MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter_Menu_Menudia_bebidas.Menu_menudia_bebidas_MyViewHolder
                                         holder,
                                 @SuppressLint("RecyclerView") int position) {


        this.position =position;
        holder.row_menu_menudia_bebidas_id.setText(String.valueOf(Arow_menu_menudia_bebidas_id.get(position)));
        holder.row_menu_menudia_bebidas_bebida.setText(String.valueOf(Arow_menu_menudia_bebidas_bebida.get(position)));
        holder.row_menu_menudia_bebidas_categoria.setText(String.valueOf(Arow_menu_menudia_bebidas_categoria.get(position)));
        holder.row_menu_menudia_bebidas_descripcion.setText(String.valueOf(Arow_menu_menudia_bebidas_descripcion.get(position)));
    }


    @Override
    public int getItemCount() {
        return Arow_menu_menudia_bebidas_id.size();
    }

    public class Menu_menudia_bebidas_MyViewHolder extends RecyclerView.ViewHolder {

        TextView row_menu_menudia_bebidas_id, row_menu_menudia_bebidas_bebida
                ,row_menu_menudia_bebidas_categoria,
                row_menu_menudia_bebidas_descripcion;
        LinearLayout mainLayout;
        public Menu_menudia_bebidas_MyViewHolder(@NonNull View itemView) {
            super(itemView);
            row_menu_menudia_bebidas_id = itemView.findViewById(R.id.row_menu_menudia_bebidas_id);
            row_menu_menudia_bebidas_bebida= itemView.findViewById(R.id.row_menu_menudia_bebidas_bebida);
            row_menu_menudia_bebidas_categoria = itemView.findViewById(R.id.row_menu_menudia_bebidas_categoria);
            row_menu_menudia_bebidas_descripcion = itemView.findViewById(R.id.row_menu_menudia_bebidas_descripcion);
            mainLayout= itemView.findViewById(R.id.row_menu_menudia_bebidas_mainLayout);
        }
    }
}
