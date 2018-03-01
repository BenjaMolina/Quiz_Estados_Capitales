package com.example.kaido.a593_27_quizestadosycapitales;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView txvNumPregunta,txvEstado,txvAciertos,txvErrores;
    private EditText edtCapital;
    private Button btnCancelar,btnOk;
    int aciertos,errores;
    int rand;
    int numPreguntas;
    String [] estados;
    String [] capitales;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvNumPregunta = (TextView)findViewById(R.id.txvNumPregunta);
        txvEstado = (TextView)findViewById(R.id.txvEstado);
        edtCapital = (EditText) findViewById(R.id.edtCapital);
        txvAciertos = (TextView)findViewById(R.id.txvAciertos);
        txvErrores = (TextView)findViewById(R.id.txvErrores);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnOk = (Button)findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new listenerOk());
        btnCancelar.setOnClickListener(new listenerCancelar());

        Resources res = this.getResources();
        estados = res.getStringArray(R.array.estados);
        capitales = res.getStringArray(R.array.capitales);

        txvAciertos.setText("Aciertos: "+aciertos);
        txvErrores.setText("Errores: "+errores);


        generarPregunta();

    }

    private void generarPregunta() {
        numPreguntas++;

        rand = (int) (Math.random()*((estados.length)-1)); // nos genera numeros de 0 al tamaño del array
        txvNumPregunta.setText("Pregunta #: "+numPreguntas);
        edtCapital.setText(null);
        txvEstado.setText(estados[rand].toString());
    }

    private void cambiarActividad(){
        Intent intento = new Intent(getApplicationContext(),puntuaciones.class);
        intento.putExtra("aciertos", aciertos);
        intento.putExtra("errores",errores);
        startActivity(intento);
        finish();
    }

    class listenerOk implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(edtCapital.getText().toString().length() > 0){

                if(numPreguntas<=5){

                    if(capitales[rand].compareToIgnoreCase(edtCapital.getText().toString()) == 0){
                        Toast.makeText(getApplicationContext(),"Correcto",Toast.LENGTH_SHORT).show();
                        aciertos++;
                        txvAciertos.setText("Aciertos: "+aciertos);

                        if(numPreguntas != 5)
                            generarPregunta();
                        else
                            cambiarActividad();
                    }

                    else{
                        Toast.makeText(getApplicationContext(),"Incorrecto \n Respuesta Correcta: "+ capitales[rand],Toast.LENGTH_SHORT).show();
                        errores++;
                        txvErrores.setText("Errores: "+errores);
                        if(numPreguntas != 5)
                            generarPregunta();
                        else
                            cambiarActividad();
                    }
                }
            }
            else
                Toast.makeText(getApplicationContext(),"Escriba la Capital",Toast.LENGTH_SHORT).show();


        }
    }

    class listenerCancelar implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            cambiarActividad();
        }
    }
}
