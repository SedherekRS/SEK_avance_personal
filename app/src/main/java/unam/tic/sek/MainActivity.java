package unam.tic.sek;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import unam.tic.sek.Model.Empresa;
import unam.tic.sek.Model.Factura;

public class MainActivity extends AppCompatActivity {
    ArrayList<Factura> datos = new ArrayList<>();
    ListView lv;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=findViewById(R.id.lv);
        progressBar=findViewById(R.id.progressBar);

        if(savedInstanceState==null){
            new ConexionHttp().execute("");
        }
    }
    public class ConexionHttp extends AsyncTask<String, Float,String> {

        boolean sinError = true;

        @Override
        protected String doInBackground(String... strings) {
            try {
                InputStream is = getAssets().open("facturas.json");
                StringBuilder result = new StringBuilder();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                rd.close();
                JSONArray myresponse = new JSONArray(result.toString());
                for (int i = 0; i < myresponse.length(); ++i) {
                    JSONObject rec = myresponse.getJSONObject(i);
                    String numeroFactura = rec.getString("numeroFactura");
                    String fechaFactura = rec.getString("fechaFactura");
                    ///Date Converter
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaFacturaDate = sdf.parse(fechaFactura);
                    Double monto = rec.getDouble("monto");
                    String moneda = rec.getString("moneda");
                    String ruta = rec.getString("rutaFacturaPdf");
                    Factura factura = new Factura();
                    factura.setNumeroFactura(numeroFactura);
                    factura.setFechaFactura(fechaFacturaDate);
                    factura.setMonto(monto);
                    factura.setMoneda(moneda);
                    factura.setRutaFacturaPDF(ruta);
                    //Adicion extra de datos
                    JSONObject empresaO = rec.getJSONObject("idEmpresa");
                    String nombreEmpresa = empresaO.getString("nombre");
                    String rfcEmpresa = empresaO.getString("rfc");
                    int periodoPago = empresaO.getInt("periodoPago");
                    Empresa empresaResult = new Empresa();
                    empresaResult.setNombre(nombreEmpresa);
                    empresaResult.setRfc(rfcEmpresa);
                    empresaResult.setPeriodoPago(periodoPago);
                    factura.setEmpresa(empresaResult);
                    //
                    datos.add(factura);
                    //System.out.println(numeroFactura+"-"+fechaStr+"-"+montoStr+"-"+moneda);
                }


            }catch(Exception e){
                e.printStackTrace();
                sinError = false;
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            if(sinError){
                progressBar.setVisibility(View.GONE);
                lv.setAdapter(new Adaptador(MainActivity.this,datos));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("ruta",datos.get(position).getRutaFacturaPDF());
                        bundle.putString("empresa",datos.get(position).getEmpresa().getNombre());
                        bundle.putString("rfc",datos.get(position).getEmpresa().getRfc());
                        bundle.putInt("periodoPago",datos.get(position).getEmpresa().getPeriodoPago());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });


            }else{
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Aviso")
                        .setMessage("Servicio no disponible en estos momentos.")
                        .setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                new ConexionHttp().execute("");
                            }
                        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        new ConexionHttp().execute("");
                    }
                }).setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                        .show();
            }

        }
    }
}
