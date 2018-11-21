package com.ebatista.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Switch switchButton;
    TextView textView, rcTextView, ixmTextView, rxmTextView;

    Button b, buttonTen, buttonPot, buttonCorr;

    String ensaioCC = "Ensaio CC";
    String ensaioCA = "Ensaio CA";

    Double RC = 1.0;
    Double RXM = 1.0;
    Double IXM = 1.0;

//    Boolean showRC = false;
//    Boolean showRXM = false;
//    Boolean showIXM = false;

    Double tensao, potencia, corrente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch_button);

        // For the switch button
        switchButton = (Switch) findViewById(R.id.switchButton);
        textView = (TextView) findViewById(R.id.textView);

        switchButton.setChecked(true);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (!bChecked) {
                    textView.setText(ensaioCC);
//                    showIXM = true;
                } else {
                    textView.setText(ensaioCA);
//                    showRC = true;
//                    showRXM = true;
                }
            }
        });

        b = (Button) findViewById(R.id.buttonSend);
        b.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText tensaoText = (EditText) findViewById(R.id.Tensao);
                EditText potenciaText = (EditText) findViewById(R.id.Potencia);
                EditText correnteText = (EditText) findViewById(R.id.Corrente);

                if(!tensaoText.equals(null))
                    tensao = Double.parseDouble(tensaoText.getText().toString());
                if(!potenciaText.equals(null))
                    potencia = Double.parseDouble(potenciaText.getText().toString());
                if(!correnteText.equals(null))
                    corrente = Double.parseDouble(correnteText.getText().toString());

                if (!switchButton.isChecked()) {//Modo de ensaio CC
                    textView.setText(ensaioCC);
                    RC = potencia / Math.pow(corrente,2);
                    RXM = Math.sqrt((Math.pow((tensao/corrente),2) - Math.pow(RC,2)));

                } else { //Modo de ensaio CA
                    textView.setText(ensaioCA);
                    RC = Math.pow(tensao,2)/potencia;
                    IXM = Math.sqrt( Math.pow(corrente,2) - Math.pow((tensao/RC),2));
                    RXM = (tensao/ IXM)*(-1);
                }

                String rcResult = RC.toString();
                String ixmResult = IXM.toString();
                String rxmResult = RXM.toString();

                TextView resultadosView = findViewById(R.id.Resultados);
                resultadosView.setText("    RESULTADOS");
                resultadosView.setVisibility(View.VISIBLE);

                try{
                    TextView rcView = findViewById(R.id.rcTextView);
                    if (!switchButton.isChecked()) {//Modo de ensaio CC
                        rcView.setText("Req: " + rcResult);
                    } else { //Modo de ensaio CA
                        rcView.setText("Rc: " + rcResult);
                    }
                    rcView.setVisibility(View.VISIBLE);

                } catch (Exception newException){
//                    resultadosView.setText("ERROR");
//                    resultadosView.setVisibility(View.VISIBLE);
                }

                //ignorar, nao precisa fazer o display na tela
                //TODO: Remover essa visualização do IXM
                try {
                    TextView ixmView = findViewById(R.id.ixmTextView);
                    ixmView.setText(ixmResult);
                    ixmView.setVisibility(View.INVISIBLE);
                } catch (Exception newException){
//                    resultadosView.setText("ERROR");
//                    resultadosView.setVisibility(View.VISIBLE);
                }

                try {
                    TextView rxmView = findViewById(R.id.rxmTextView);
                    if (!switchButton.isChecked()) {//Modo de ensaio CC
                        rxmView.setText("Xeq: " + rxmResult);
                    } else { //Modo de ensaio CA
                        rxmView.setText("Xm: " + rxmResult);
                    }

                    rxmView.setVisibility(View.VISIBLE);
                } catch (Exception newException){
//                    resultadosView.setText("ERROR");
//                    resultadosView.setVisibility(View.VISIBLE);
                }
            }
        });

//        if(tensaoText.getText().length()!=0)
//            tensao = Double.parseDouble(tensaoText.getText().toString());
//        if(potenciaText.getText().length()!=0)
//            potencia = Double.parseDouble(potenciaText.getText().toString());
//        if(correnteText.getText().length()!=0)
//            corrente = Double.parseDouble(correnteText.getText().toString());

//        if (!switchButton.isChecked()) {//Modo de ensaio CC
//            textView.setText(ensaioCC);
//
//            RC = potencia / Math.pow(corrente,2);
//            RXM = Math.sqrt((Math.pow((tensao/corrente),2) - Math.pow(RC,2)));
//
//        } else { //Modo de ensaio CA
//            textView.setText(ensaioCA);
//
//            RC = Math.pow(tensao,2)/potencia;
//            IXM = Math.sqrt( Math.pow(corrente,2) - Math.pow((tensao/RC),2));
//            RXM = ((tensao*(-1))/ IXM);
//
//        }
//
//        String rcResult = RC.toString();
//        String ixmResult = IXM.toString();
//        String rxmResult = RXM.toString();
//
//
//        TextView rcView = findViewById(R.id.rcTextView);
//        rcView.setText(rcResult);
//
//        TextView ixmView = findViewById(R.id.ixmTextView);
//        ixmView.setText(ixmResult);
//
//        TextView rxmView = findViewById(R.id.rxmTextView);
//        rxmView.setText(rxmResult);

//        if(showRC)
//            rcView.setVisibility(View.VISIBLE);
//        if(showRXM)
//            rxmView.setVisibility(View.VISIBLE);
//        if(!showIXM)
//            ixmView.setVisibility(View.INVISIBLE);
    }

//
//    public void sendMessage(View view) {
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }

}