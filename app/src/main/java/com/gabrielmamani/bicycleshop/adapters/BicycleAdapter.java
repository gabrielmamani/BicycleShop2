package com.gabrielmamani.bicycleshop.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gabrielmamani.bicycleshop.BicycleActivity;
import com.gabrielmamani.bicycleshop.R;
import com.gabrielmamani.bicycleshop.entities.bicicleta;

import java.util.List;

/**
 * Created by gabrielmamani on 1/6/18.
 */

public class BicycleAdapter extends  RecyclerView.Adapter<BicycleAdapter.BicycleViewHolder> {

    public List<bicicleta> bicicletaList;
    public Context context;

    public BicycleAdapter(List<bicicleta> bicicletaList, Context context) {
        this.bicicletaList = bicicletaList;
        this.context = context;
    }

    @Override
    public BicycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_bicycle, parent,false);
        BicycleViewHolder mholder= new BicycleViewHolder(view);
        return mholder;
    }

    @Override
    public void onBindViewHolder(BicycleViewHolder holder, int position) {
        bicicleta mbicicleta = bicicletaList.get(position);
        holder.nombrebici.setText("Nombre: " + mbicicleta.getNombre_bici());
        holder.marca.setText("Marca: " + mbicicleta.getMarca());
        holder.tipo.setText("Tipo: " + mbicicleta.getTipo());
        holder.precio.setText("Precio: " + mbicicleta.getPrecio());
        holder.cantidad.setText("Cantidad: " + mbicicleta.getCantidad());

        holder.setItemClickListener(new ItemClickListener(){

            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(context,"LongClick: " + bicicletaList.get(position).getNombre_bici(), Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(context," " + bicicletaList.get(position).getNombre_bici(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bicicletaList.size();
    }

    public static class BicycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView nombrebici, marca, tipo, precio, cantidad;
        private ItemClickListener itemClickListener;


        public BicycleViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nombrebici = (TextView) itemView.findViewById(R.id.textnombrebici);
            marca = (TextView) itemView.findViewById(R.id.textmarca);
            tipo = (TextView) itemView.findViewById(R.id.texttipo);
            precio = (TextView) itemView.findViewById(R.id.textprecio);
            cantidad = (TextView) itemView.findViewById(R.id.textcantidad);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }

        public void setItemClickListener(ItemClickListener item){
            this.itemClickListener = item;
        }

    }
}
