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

public class CustomAdapter_Carta_entrada
        extends RecyclerView.Adapter<CustomAdapter_Carta_entrada.Carta_entrada_MyViewHolder> {

    private Context context;

    Activity activity;
    private ArrayList Aval_entradaid;
    private ArrayList Aguar_entradaid;
    private ArrayList Arow_carta_entrada_id , Arow_carta_entrada_entrada,
            Arow_carta_entrada_categoria,Arow_carta_entrada_descripcion ,
            Arow_carta_entrada_precio ;
    private CustomAdapter_Carta_entrada.Carta_entrada_MyViewHolder holder;
    private int position;
    public CustomAdapter_Carta_entrada(Activity activity, Context context,
                                        ArrayList row_carta_entrada_id,
                                        ArrayList row_carta_entrada_entrada,
                                        ArrayList row_carta_entrada_categoria,
                                        ArrayList row_carta_entrada_descripcion,
                                        ArrayList row_carta_entrada_precio,
                                        ArrayList Aval_entradaid
    ){
        this.activity = activity;
        this.context= context;
        this.Arow_carta_entrada_id = row_carta_entrada_id;
        this.Arow_carta_entrada_entrada = row_carta_entrada_entrada;
        this.Arow_carta_entrada_categoria= row_carta_entrada_categoria;
        this.Arow_carta_entrada_descripcion = row_carta_entrada_descripcion;
        this.Arow_carta_entrada_precio = row_carta_entrada_precio;
        this.Aguar_entradaid = Aval_entradaid;
    }

    @NonNull
    @Override
    public CustomAdapter_Carta_entrada.Carta_entrada_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_carta_entradaselect,parent,false);
        return new CustomAdapter_Carta_entrada.Carta_entrada_MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter_Carta_entrada.Carta_entrada_MyViewHolder
                                         holder,
                                 @SuppressLint("RecyclerView") int position) {


        this.position =position;
        holder.row_carta_entrada_id.setText(String.valueOf(Arow_carta_entrada_id.get(position)));
        holder.row_carta_entrada_entrada.setText(String.valueOf(Arow_carta_entrada_entrada.get(position)));
        holder.row_carta_entrada_categoria.setText(String.valueOf(Arow_carta_entrada_categoria.get(position)));
        holder.row_carta_entrada_descripcion.setText(String.valueOf(Arow_carta_entrada_descripcion.get(position)));
        holder.row_carta_entrada_precio.setText(String.valueOf(Arow_carta_entrada_precio.get(position)));

        holder.row_check3.setOnClickListener(view -> {

            if (((CheckBox) view).isChecked())
            {
                Aguar_entradaid.add(String.valueOf(Arow_carta_entrada_id.get(position)));
            }else
            {
                for (int ic = 0;ic<Aguar_entradaid.size();ic++){
                    if (Aguar_entradaid.get(ic) == Arow_carta_entrada_id.get(position) ) {
                        Aguar_entradaid.remove(ic);
                    }
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return Arow_carta_entrada_id.size();
    }

    public class Carta_entrada_MyViewHolder extends RecyclerView.ViewHolder {

        TextView row_carta_entrada_id, row_carta_entrada_entrada,row_carta_entrada_categoria,
                row_carta_entrada_descripcion, row_carta_entrada_precio;
        CheckBox row_check3;
        LinearLayout mainLayout;
        public Carta_entrada_MyViewHolder(@NonNull View itemView) {
            super(itemView);
            row_carta_entrada_id = itemView.findViewById(R.id.row_carta_entradaselect_id);
            row_carta_entrada_entrada = itemView.findViewById(R.id.row_carta_entradaselect_entrada);
            row_carta_entrada_categoria = itemView.findViewById(R.id.row_carta_entradaselect_categoria);
            row_carta_entrada_descripcion = itemView.findViewById(R.id.row_carta_entradaselect_descripcion);
            row_carta_entrada_precio = itemView.findViewById(R.id.row_carta_entradaselect_precio);
            row_check3 = itemView.findViewById(R.id.carta_entradaselect_checkbox_1);
            mainLayout= itemView.findViewById(R.id.row_carta_entradaselect_mainLayout);
        }
    }
}
