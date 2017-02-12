package uce.optativa.androidchat.appcomputronik;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uce.optativa.androidchat.appcomputronik.adapters.OrdenAdapter;
import uce.optativa.androidchat.appcomputronik.model.Orden;


public class HomeFragment extends Fragment {
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_home, container, false);
        List<Orden> ordens = new ArrayList<>();


        ordens.add(new Orden(1,"2017/23/3","Tenemos un problema bien descartado","Pendiente"));
        ordens.add(new Orden(2,"2017/23/3","Tenemos un problema bien descartado","Listo"));
        ordens.add(new Orden(3,"2017/23/3","Tenemos un problema bien descartado","Pendiente"));

        // Obtener el Recycler
        recycler = (RecyclerView) root.findViewById(R.id.recyclerOrdenes);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new OrdenAdapter(ordens);
        recycler.setAdapter(adapter);
        return root;
    }

}
