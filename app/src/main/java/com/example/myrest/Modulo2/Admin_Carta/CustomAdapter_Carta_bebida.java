package com.example.myrest.Modulo2.Admin_Carta;

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

public class CustomAdapter_Carta_bebida
        extends RecyclerView.Adapter<CustomAdapter_Carta_bebida.Carta_bebida_MyViewHolder> {
    private Context context;

    Activity activity;
    private ArrayList Aval_bebidaid;
    private ArrayList Aguar_bebidaid;
    private ArrayList Arow_carta_bebida_id , Arow_carta_bebida_bebida,
            Arow_carta_bebida_categoria,Arow_carta_bebida_descripcion ,
            Arow_carta_bebida_precio ;
    private CustomAdapter_Carta_bebida.Carta_bebida_MyViewHolder holder;
    private int position;
    public CustomAdapter_Carta_bebida(Activity activity, Context context,
                                        ArrayList row_carta_bebida_id,
                                        ArrayList row_carta_bebida_bebida,
                                        ArrayList row_carta_bebida_categoria,
                                        ArrayList row_carta_bebida_descripcion,
                                        ArrayList row_carta_bebida_precio,
                                        ArrayList Aval_bebidaid
    ){
        this.activity = activity;
        this.context= context;
        this.Arow_carta_bebida_id = row_carta_bebida_id;
        this.Arow_carta_bebida_bebida = row_carta_bebida_bebida;
        this.Arow_carta_bebida_categoria= row_carta_bebida_categoria;
        this.Arow_carta_bebida_descripcion = row_carta_bebida_descripcion;
        this.Arow_carta_bebida_precio = row_carta_bebida_precio;
        this.Aguar_bebidaid = Aval_bebidaid;
    }

    @NonNull
    @Override
    public CustomAdapter_Carta_bebida.Carta_bebida_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_carta_bebidaselect,parent,false);
        return new CustomAdapter_Carta_bebida.Carta_bebida_MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter_Carta_bebida.Carta_bebida_MyViewHolder
                                         holder,
                                 @SuppressLint("RecyclerView") int position) {


        this.position =position;
        holder.row_carta_bebida_id.setText(String.valueOf(Arow_carta_bebida_id.get(position)));
        holder.row_carta_bebida_bebida.setText(String.valueOf(Arow_carta_bebida_bebida.get(position)));
        holder.row_carta_bebida_categoria.setText(String.valueOf(Arow_carta_bebida_categoria.get(position)));
        holder.row_carta_bebida_descripcion.setText(String.valueOf(Arow_carta_bebida_descripcion.get(position)));
        holder.row_carta_bebida_precio.setText(String.valueOf(Arow_carta_bebida_precio.get(position)));

        holder.row_check3.setOnClickListener(view -> {

            if (((CheckBox) view).isChecked())
            {
                Aguar_bebidaid.add(String.valueOf(Arow_carta_bebida_id.get(position)));
            }else
            {
                for (int ic = 0;ic<Aguar_bebidaid.size();ic++){
                    if (Aguar_bebidaid.get(ic) == Arow_carta_bebida_id.get(position) ) {
                        Aguar_bebidaid.remove(ic);
                    }
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return Arow_carta_bebida_id.size();
    }

    public class Carta_bebida_MyViewHolder extends RecyclerView.ViewHolder {

        TextView row_carta_bebida_id, row_carta_bebida_bebida,row_carta_bebida_categoria,
                row_carta_bebida_descripcion, row_carta_bebida_precio;
        CheckBox row_check3;
        LinearLayout mainLayout;
        public Carta_bebida_MyViewHolder(@NonNull View itemView) {
            super(itemView);
            row_carta_bebida_id = itemView.findViewById(R.id.row_carta_bebidaselect_id);
            row_carta_bebida_bebida = itemView.findViewById(R.id.row_carta_bebidaselect_bebida);
            row_carta_bebida_categoria = itemView.findViewById(R.id.row_carta_bebidaselect_categoria);
            row_carta_bebida_descripcion = itemView.findViewById(R.id.row_carta_bebidaselect_descripcion);
            row_carta_bebida_precio = itemView.findViewById(R.id.row_carta_bebidaselect_precio);
            row_check3 = itemView.findViewById(R.id.carta_bebidaselect_checkbox_1);
            mainLayout= itemView.findViewById(R.id.row_carta_bebidaselect_mainLayout);
        }
    }
}
