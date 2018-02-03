package com.gabrielmamani.bicycleshop;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gabrielmamani.bicycleshop.adapters.SucursalAdapter;
import com.gabrielmamani.bicycleshop.entities.sucursal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.gabrielmamani.bicycleshop.entities.*;

public class SucursalActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    static public List<sucursal> sucursals = null;
    SucursalAdapter adapter;
    //private static HashMap<String, sucursal> _sucursalHash = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursal);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_sucursal);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sucursals = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        
        final DatabaseReference myRef = database.getReference(sucursal.SUCURSAL_REFERENCE);
        Log.d("sucursal ref", myRef.toString() +"");


        Collections.sort(sucursals);

        adapter = new SucursalAdapter(sucursals,this);

        recyclerView.setAdapter(adapter);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sucursals.removeAll(sucursals);
                Log.d("sucursal firebase", dataSnapshot.toString() +"");

                for (DataSnapshot item : dataSnapshot.getChildren()){
                    Log.e("snapshot item ----> ", "" + item.toString());

                    Map<String, Object> sucursalMap = (Map<String, Object>) item.getValue();
                    Log.e("snapshot item MAp--> ", "" + sucursalMap.toString());

                    sucursal _sucursal = new sucursal();
                    _sucursal.key = item.getKey();
                    _sucursal.nit = sucursalMap.get("nit").toString();
                    _sucursal.empresa = sucursalMap.get("empresa").toString();
                    _sucursal.direccion = sucursalMap.get("direccion").toString();
                    Log.e("sucursal nit", "" + _sucursal.getKey());

                    sucursals.add(_sucursal);

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
