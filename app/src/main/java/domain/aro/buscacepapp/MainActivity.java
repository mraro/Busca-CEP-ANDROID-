package domain.aro.buscacepapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public Button btnBuscaCep;
    public EditText cep;
    public TextView resposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cep = findViewById(R.id.etMain_cep);
        resposta = findViewById(R.id.etMain_resposta);

        btnBuscaCep = (Button) findViewById(R.id.btnMain_buscarCep);

        btnBuscaCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CEP retorno = new httpService(cep.getText().toString()).execute().get();
                    resposta.setText(retorno.toString());
                }catch (ExecutionException e){
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
