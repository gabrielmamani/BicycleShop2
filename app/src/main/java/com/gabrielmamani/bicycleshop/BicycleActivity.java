package com.gabrielmamani.bicycleshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gabrielmamani.bicycleshop.adapters.BicycleAdapter;
import com.gabrielmamani.bicycleshop.adapters.SucursalAdapter;
import com.gabrielmamani.bicycleshop.entities.bicicleta;
import com.gabrielmamani.bicycleshop.entities.sucursal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BicycleActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    static public List<bicicleta> bicicletas = null;
    BicycleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicycle);

        sucursal objeto = (sucursal) getIntent().getExtras().getSerializable("sucursal");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_bicycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bicicletas = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference(bicicleta.BICYCLE_REFERENCE);
        Log.d("bicycle ref", myRef.toString() +"");


        //Collections.sort(bicicletas);

        adapter = new BicycleAdapter(bicicletas,this);

        recyclerView.setAdapter(adapter);

        myRef.child(objeto.getKey()).addValueEventListener(new ValueEventListener() {
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
                    Log.e("bicicleta key", "" + _bicicleta.getKey());

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

    }
}
