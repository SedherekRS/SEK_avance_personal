package unam.tic.sek;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private static final String LOGTAG="PARAMETROS";

    TextView tvEmpresa;
    TextView tvRFC;
    TextView tvPayment;
    ImageView ivPdf;

    String nombreEmpresa="";
    String rfc="";
    String url="";
    int periodoPago=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvEmpresa = findViewById(R.id.tvEmpresa);
        tvRFC = findViewById(R.id.tvRFC);
        tvPayment = findViewById(R.id.tvPayment);
        ivPdf = findViewById(R.id.ivPdf);

        Bundle parametros = getIntent().getExtras();

        if(parametros!=null){
            nombreEmpresa = parametros.getString("empresa","Empresa");
            rfc = parametros.getString("rfc","RFC");
            url= parametros.getString("ruta","");
            periodoPago = parametros.getInt("periodoPago",0);

            Log.d(LOGTAG, "nombre Recibido: "+nombreEmpresa+"<->"+rfc+"<->"+periodoPago+"<->"+url);

            tvEmpresa.setText(nombreEmpresa);
            tvRFC.setText(rfc);
            tvPayment.setText(Integer.toString(periodoPago));

            ivPdf.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {
                    System.out.println("Ruta:"+url);
                    String urlPdf = "https://docs.google.com/file/d/"+url+"/view?usp=sharing";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(urlPdf));
                    startActivity(i);

                }

            });
        }
    }
}
