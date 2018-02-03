package com.gabrielmamani.bicycleshop;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.gabrielmamani.bicycleshop.entities.bicicleta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gabrielmamani on 1/24/18.
 */

public class BicycleContentFragment extends Fragment{

    public static List<bicicleta> bicicletas = new ArrayList<>();
    ContentAdapter adapter;
    //Uri filepathuri;
    //public StorageReference pathReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);


        bicicletas = new ArrayList<>();
        //base datos instancia
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference(bicicleta.BICYCLE_REFERENCE);
        Log.d("bicycle ref", myRef.toString() +"");


        adapter = new ContentAdapter(bicicletas, recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        myRef.child("monaco").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bicicletas.removeAll(bicicletas);
                Log.d("bicycle firebase", dataSnapshot.toString() +"");

                for (DataSnapshot item : dataSnapshot.getChildren()){
                    Log.e("snapshot item ----> ", "" + item.toString());

                    Map<String, Object> bicycleMap = (Map<String, Object>) item.getValue();
                    Log.e("snapshot item MAp--> ", "" + bicycleMap.toString());

                    bicicleta _bicicleta = new bicicleta();
                    _bicicleta.key = item.getKey();
                    _bicicleta.nombre_bici = bicycleMap.get("nombre_bici").toString();
                    _bicicleta.marca = bicycleMap.get("marca").toString();
                    _bicicleta.tipo = bicycleMap.get("tipo").toString();
                    _bicicleta.precio = Double.parseDouble(bicycleMap.get("precio").toString());
                    _bicicleta.cantidad = Integer.parseInt(bicycleMap.get("cantidad").toString());
                    _bicicleta.detalle = bicycleMap.get("detalle").toString();

                    _bicicleta.image = bicycleMap.get("imagen").toString();

                    Log.e("bicicleta key", "" + _bicicleta.getImage());

                    bicicletas.add(_bicicleta);

                    //_sucursalHash.put(item.getKey(), _sucursal);
                }

                //System.out.println(Arrays.asList(_sucursalHash));


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return recyclerView;

    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView picture;
        public TextView name;
        public TextView descripcion;

        public ViewHolder(final LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_card, parent, false));

            picture = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.card_title);
            descripcion = (TextView) itemView.findViewById(R.id.card_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());

                    context.startActivity(intent);

                }
            });

            //agregando snackbar al boton accion dentro del card
            Button button = (Button)  itemView.findViewById(R.id.action_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed", Snackbar.LENGTH_SHORT).show();
                }
            });

            ImageButton favoriteImageButton = (ImageButton) itemView.findViewById(R.id.favorite_button);
            favoriteImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Agregar a favorito", Snackbar.LENGTH_SHORT).show();
                }
            });

            ImageButton shareImageButton = (ImageButton) itemView.findViewById(R.id.share_button);
            shareImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Agregar a favorito", Snackbar.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        //Modifica el numero de card en el RecyclerView
        //private static final int LENGTH = 18;

        public List<bicicleta> bicicletaList = new ArrayList<>();
        public Context context;

        public ContentAdapter(List<bicicleta> bicicletaList1, Context context) {
            this.context = context;
            this.bicicletaList = bicicletaList1;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Log.e("AQUIII ADAPTER", "" + position);
            bicicleta _bicicletaAdapter = bicicletaList.get(position);
            Log.e("AQUIII ADAPTER bici", "" + _bicicletaAdapter.getDetalle());

            //Picasso.with(context).load(_bicicletaAdapter.getImage()).into(holder.picture);
            //Glide.with(context).load(_bicicletaAdapter.getImage()).into(holder.picture);
            Glide.with(context)
                    .load(_bicicletaAdapter.getImage())
                    .into(holder.picture);

            holder.name.setText(_bicicletaAdapter.getNombre_bici());
            holder.descripcion.setText(_bicicletaAdapter.getDetalle());

        }

        //GABRIEl v2

        @Override
        public int getItemCount() {
            return bicicletaList.size();
        }
    }
}
