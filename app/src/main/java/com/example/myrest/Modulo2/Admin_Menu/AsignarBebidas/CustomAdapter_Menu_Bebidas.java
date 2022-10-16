package com.example.myrest.Modulo2.Admin_Menu.AsignarBebidas;

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

public class CustomAdapter_Menu_Bebidas
        extends RecyclerView.Adapter<CustomAdapter_Menu_Bebidas.menu_Bebidas_MyViewHolder>

{



private Context context;
        Activity activity;
    private ArrayList Aval_bebidaid;
    private ArrayList Aguar_bebidaid;
private ArrayList Arow_Menu_bebidas_id , Arow_Menu_bebidas_bebidas,
        Arow_Menu_bebidas_categoria,Arow_Menu_bebidas_descripcion ,Arow_Menu_bebidas_precio ;
private CustomAdapter_Menu_Bebidas.menu_Bebidas_MyViewHolder holder;
private int position;
public CustomAdapter_Menu_Bebidas(Activity activity, Context context,
        ArrayList row_Menu_bebidas_id,
        ArrayList row_Menu_bebidas_bebidas,
        ArrayList row_Menu_bebidas_categoria,
        ArrayList row_Menu_bebidas_descripcion,
        ArrayList row_Menu_bebidas_precio,
        ArrayList Aval_bebidaid

){
        this.activity = activity;
        this.context= context;
        this.Arow_Menu_bebidas_id = row_Menu_bebidas_id;
        this.Arow_Menu_bebidas_bebidas = row_Menu_bebidas_bebidas;
        this.Arow_Menu_bebidas_categoria= row_Menu_bebidas_categoria;
        this.Arow_Menu_bebidas_descripcion = row_Menu_bebidas_descripcion;
        this.Arow_Menu_bebidas_precio = row_Menu_bebidas_precio;
        this.Aguar_bebidaid = Aval_bebidaid;
        }
@NonNull
@Override
public CustomAdapter_Menu_Bebidas.menu_Bebidas_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_menu_bebidas,parent,false);
        return new CustomAdapter_Menu_Bebidas.menu_Bebidas_MyViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull CustomAdapter_Menu_Bebidas.menu_Bebidas_MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


    this.position = position;
    holder.row_Menu_bebidas_id.setText(String.valueOf(Arow_Menu_bebidas_id.get(position)));
    holder.row_Menu_bebidas_bebidas.setText(String.valueOf(Arow_Menu_bebidas_bebidas.get(position)));
    holder.row_Menu_bebidas_categoria.setText(String.valueOf(Arow_Menu_bebidas_categoria.get(position)));
    holder.row_Menu_bebidas_descripcion.setText(String.valueOf(Arow_Menu_bebidas_descripcion.get(position)));
    holder.row_Menu_bebidas_precio.setText(String.valueOf(Arow_Menu_bebidas_precio.get(position)));

    holder.row_check2.setOnClickListener(view -> {
        if (((CheckBox) view).isChecked())
        {
            Aguar_bebidaid.add(String.valueOf(Arow_Menu_bebidas_id.get(position)));
        }else
        {
            for (int i = 0;i<Aguar_bebidaid.size();i++){
                if (Aguar_bebidaid.get(i) == Arow_Menu_bebidas_id.get(position) ) {
                    Aguar_bebidaid.remove(i);
                }
            }
        }
    });


}

@Override
public int getItemCount() {
        return Arow_Menu_bebidas_id.size();
        }

public class menu_Bebidas_MyViewHolder extends RecyclerView.ViewHolder {

    TextView row_Menu_bebidas_id, row_Menu_bebidas_bebidas,row_Menu_bebidas_categoria,
            row_Menu_bebidas_descripcion, row_Menu_bebidas_precio;
    CheckBox row_check2;
    LinearLayout mainLayout;
    public menu_Bebidas_MyViewHolder(@NonNull View itemView) {
        super(itemView);
        row_Menu_bebidas_id = itemView.findViewById(R.id.row_menu_bebidas_id);
        row_Menu_bebidas_bebidas = itemView.findViewById(R.id.row_menu_bebidas_bebidas);
        row_Menu_bebidas_categoria = itemView.findViewById(R.id.row_menu_bebidas_categoria);
        row_Menu_bebidas_descripcion = itemView.findViewById(R.id.row_menu_bebidas_descripcion);
        row_Menu_bebidas_precio = itemView.findViewById(R.id.row_menu_bebidas_precio);
        row_check2 = itemView.findViewById(R.id.menu_bebidas_checkbox_1);
        mainLayout= itemView.findViewById(R.id.row_menu_bebidas_mainLayout);
    }
}
}

