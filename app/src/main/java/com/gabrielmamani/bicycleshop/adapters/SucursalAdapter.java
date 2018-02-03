package com.gabrielmamani.bicycleshop.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gabrielmamani.bicycleshop.BicycleActivity;
import com.gabrielmamani.bicycleshop.R;
import com.gabrielmamani.bicycleshop.entities.sucursal;

import java.util.List;

/**
 * Created by gabrielmamani on 1/6/18.
 */

public class SucursalAdapter extends  RecyclerView.Adapter<SucursalAdapter.SucursalViewHolder> {

    public List<sucursal> sucursalList;
    public Context context;

    public SucursalAdapter(List<sucursal> sucursalList, Context context) {
        this.sucursalList = sucursalList;
        this.context = context;
    }

    @Override
    public SucursalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_sucursal, parent,false);
        SucursalViewHolder mholder= new SucursalViewHolder(view);
        return mholder;
    }

    @Override
    public void onBindViewHolder(SucursalViewHolder holder, int position) {
        sucursal msucursal = sucursalList.get(position);
        holder.nit.setText(msucursal.getNit());
        holder.empresa.setText(msucursal.getEmpresa());
        holder.direccion.setText(msucursal.getDireccion());

        holder.setItemClickListener(new ItemClickListener(){

            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(context,"LongClick: " + sucursalList.get(position).getEmpresa(), Toast.LENGTH_SHORT).show();
                }else{
                    sucursal sucursalput = sucursalList.get(position);
                    Intent intent = new Intent(context, BicycleActivity.class);
                    intent.putExtra("sucursal", sucursalput);
                    context.startActivity(intent);
                    //Toast.makeText(context," " + sucursalList.get(position).getEmpresa(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return sucursalList.size();
    }

    public static class SucursalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView nit, empresa, direccion;
        private ItemClickListener itemClickListener;


        public SucursalViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nit = (TextView) itemView.findViewById(R.id.textnit);
            empresa = (TextView) itemView.findViewById(R.id.textempresa);
            direccion = (TextView) itemView.findViewById(R.id.textdireccion);
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
