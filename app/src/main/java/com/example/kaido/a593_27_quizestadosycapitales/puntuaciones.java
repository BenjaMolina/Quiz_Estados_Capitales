package com.example.kaido.a593_27_quizestadosycapitales;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class puntuaciones extends AppCompatActivity {
    private TextView txvAciertos, txvErrores,txvPorcentaje;
    private Button btnComenzar, btnTerminar;
    private int aciertos=0;
    private int errores=0;
    private int porcentaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuaciones);

        txvAciertos = (TextView) findViewById(R.id.txvAciertos2);
        txvErrores = (TextView) findViewById(R.id.txvErrores2);
        txvPorcentaje = (TextView)findViewById(R.id.txvPorcentaje);
        btnComenzar = (Button) findViewById(R.id.btnComenzar);
        btnTerminar = (Button) findViewById(R.id.btnTerminar);

        Bundle parame = getIntent().getExtras();
        aciertos = parame.getInt("aciertos");
        errores = parame.getInt("errores");

        txvAciertos.setText(aciertos + "");
        txvErrores.setText(errores + "");

        porcentaje = (aciertos*100) / 5;
        txvPorcentaje.setText("Porcentaje: " + porcentaje + "%");

        btnComenzar.setOnClickListener(new listenerComenzar());
        btnTerminar.setOnClickListener(new listenerTerminar());
    }

    class listenerComenzar implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intento = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intento);
            finish();
        }
    }

    class listenerTerminar implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            finish();
        }
    }
}
