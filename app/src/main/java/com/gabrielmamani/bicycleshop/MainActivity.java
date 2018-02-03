package com.gabrielmamani.bicycleshop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gabrielmamani.bicycleshop.entities.sucursal;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mdrawerLayout;
    private TabLayout tabs;
    private int[] tabIcons = {R.drawable.ic_favorite, R.drawable.ic_add, R.drawable.ic_share};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //agregando Toolbar a screen principal
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Configurar ViewPager para cada Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        //Modificar los tabs dentro del toolbar
        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        //crear NavigationDrawer and InflateLayout
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        //Agregando iconnos del menu al Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            VectorDrawableCompat indicador = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicador.setTint(ResourcesCompat.getColor(getResources(), R.color.white, getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicador);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        //establecer el compartamiento del panel de navegacion
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);

                Toast.makeText(getApplicationContext(), "" + item.getTitle(), Toast.LENGTH_SHORT).show();
                //Fragment fragment = new BlankFragment();
                //getSupportFragmentManager().beginTransaction().replace(R.id.frame_sample, fragment).addToBackStack(null).commit();

                //cerrando el panel itemclick
                mdrawerLayout.closeDrawers();
                return true;
            }
        });

    }


    //Agregamos Fragments a Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new BicycleContentFragment(), "Bicicletas");
        adapter.addFragment(new SpareContentFragment(), "Repuestos");
        adapter.addFragment(new SpareContentFragment(), "Accesorios");

        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mfragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mfragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mfragmentList.size();
        }

        public void addFragment(Fragment fragment, String title){
            mfragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position){ return mFragmentTitleList.get(position); }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }else{
            mdrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
