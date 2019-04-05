package unam.tic.sek;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import unam.tic.sek.Model.Factura;

public class Adaptador extends BaseAdapter {
    private Context contexto;
    private ArrayList<Factura> datos;
    private static LayoutInflater inflater = null;

    public Adaptador(Context contexto, ArrayList<Factura> datos) {
        this.contexto = contexto;
        this.datos = datos;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position).getId();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.elemento_lista,null);
        TextView tvFactura = vista.findViewById(R.id.tvFactura);
        TextView tvFecha = vista.findViewById(R.id.tvFecha);
        TextView tvMonto = vista.findViewById(R.id.tvMonto);
        TextView tvMoneda = vista.findViewById(R.id.tvMoneda);
        ////////Currency Converter
        String montoStr = String.format("%,.2f", datos.get(position).getMonto());
        montoStr="$ "+montoStr;
        ////////Date Converter
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        String fechaStr = dt1.format(datos.get(position).getFechaFactura());
        ///////Values asignation
        tvFactura.setText(datos.get(position).getNumeroFactura());
        tvFecha.setText(fechaStr);
        tvMonto.setText(montoStr);
        tvMoneda.setText(datos.get(position).getMoneda());

        return vista;
    }
}
