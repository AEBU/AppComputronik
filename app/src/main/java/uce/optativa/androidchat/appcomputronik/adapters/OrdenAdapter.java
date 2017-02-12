package uce.optativa.androidchat.appcomputronik.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import uce.optativa.androidchat.appcomputronik.R;
import uce.optativa.androidchat.appcomputronik.model.Orden;


/**
 * Created by Alexis on 11/02/2017.
 */

public class OrdenAdapter extends RecyclerView.Adapter<OrdenAdapter.OrdenViewHolder> {
    private List<Orden> ordenes;



    public static class OrdenViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView idOrden;
        public TextView ordFechaEmision;
        public TextView ordProblema;
        public TextView fkEstCodigo;

        public OrdenViewHolder(View v) {
            super(v);
            idOrden = (TextView) v.findViewById(R.id.ordNumero);
            ordFechaEmision = (TextView) v.findViewById(R.id.ordFechaEmision);
            ordProblema = (TextView) v.findViewById(R.id.ordProblema);
            fkEstCodigo = (TextView) v.findViewById(R.id.fkEstCodigo);
        }
    }
    public OrdenAdapter(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }

    @Override
    public int getItemCount() {
        return ordenes.size();
    }

    @Override
    public OrdenViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.orden_card, viewGroup, false);
        return new OrdenViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OrdenViewHolder viewHolder, int i) {
        viewHolder.idOrden.setText("Orden # "+String.valueOf(ordenes.get(i).getOrdNumero()));
        viewHolder.ordFechaEmision.setText(ordenes.get(i).getOrdFechaEmision());
        viewHolder.ordProblema.setText(ordenes.get(i).getOrdProblema());
        viewHolder.fkEstCodigo.setText(ordenes.get(i).getFkEstCodigo());
    }
}
