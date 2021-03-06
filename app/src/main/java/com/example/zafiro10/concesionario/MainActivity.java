/*
* Realizado por: Samuel Bautista Sanchez
* DNI: 20227866X
* Asignatura: Desarrollo de Aplicaciones Multiplataforma
* */



package com.example.zafiro10.concesionario;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    public static ListView listView;


    public static adaptadorCochesNuevos adapter;
    public static adaptadorExtrasNuevos adapterExtras;

    public static ArrayList<Vehiculos> arrayVehiculos = new ArrayList();

    public static  Vehiculos VehiculosDetalles;

    public static Vehiculos VehiculosOcasionDetalles;

    public static ArrayList<Vehiculos> arrayVehiculosOcasion = new ArrayList();

    public static ArrayList<Extras> arrayExtras = new ArrayList<Extras>();
    public static ArrayList<Extras> arrayExtrasOcasion = new ArrayList<Extras>();

    public  static ConectorBaseDatos databaseAccess;


    boolean click =false;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                click = !click;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                    Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                            android.R.interpolator.fast_out_slow_in);

                    view.animate()
                            .rotation(click ? 90f : 0)
                            .setInterpolator(interpolador)
                            .start();
                }




                    Intent intent2 = new Intent(getApplicationContext(), nuevo_coche.class);
                    startActivity(intent2);


                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuConocenos) {

            Intent intent = new Intent(this, conocenos.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1) {



                listView = (ListView) rootView.findViewById(R.id.listacoches);

                databaseAccess = ConectorBaseDatos.getInstance(getActivity());
                databaseAccess.AbrirConexion();
                arrayVehiculos = databaseAccess.todos_los_coches();
                databaseAccess.CerrarConexcion();

                adapter = new adaptadorCochesNuevos(getActivity(), arrayVehiculos);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View v, int position,
                                            long arg3)
                    {
                        VehiculosDetalles = arrayVehiculos.get(position);
                        Intent intent = new Intent(getActivity(), Detalles.class);
                        startActivity(intent);
                    }
                });

            }
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                //textView.setText("adios");



                listView = (ListView) rootView.findViewById(R.id.listacoches);

                ConectorBaseDatos databaseAccess = ConectorBaseDatos.getInstance(getActivity());
                databaseAccess.AbrirConexion();
                arrayVehiculosOcasion = databaseAccess.todos_los_coches_ocasion();
                databaseAccess.CerrarConexcion();

                adapter = new adaptadorCochesNuevos(getActivity(), arrayVehiculosOcasion);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View v, int position,
                                            long arg3)
                    {
                        VehiculosOcasionDetalles = arrayVehiculosOcasion.get(position);
                        Intent intent = new Intent(getActivity(), ScrollingActivity.class);
                        startActivity(intent);
                    }
                });
            }
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                //textView.setText("buenos dias");

                listView = (ListView) rootView.findViewById(R.id.listacoches);

                ConectorBaseDatos databaseAccess = ConectorBaseDatos.getInstance(getActivity());
                databaseAccess.AbrirConexion();
                arrayExtras = databaseAccess.todos_los_extras();
                databaseAccess.CerrarConexcion();

                adapterExtras = new adaptadorExtrasNuevos(getActivity(), arrayExtras);
                listView.setAdapter(adapterExtras);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View v, int position,
                                            long arg3)
                    {

                    }
                });
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        MainActivity.databaseAccess.AbrirConexion();
        arrayVehiculos=databaseAccess.todos_los_coches();
        MainActivity.databaseAccess.CerrarConexcion();
        adapter = new adaptadorCochesNuevos(this, arrayVehiculos);
        listView.setAdapter(adapter);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }
}
