package domain.aro.buscacepapp;
import android.os.AsyncTask;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by alessandro.roliveir4 on 02/04/2018.
 */

public class httpService extends AsyncTask<Void, Void, CEP> {
    private final String cep;

    public httpService(String cep){this.cep = cep;}

    @Override
    protected CEP doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        if (this.cep != null && this.cep.length() == 8){
            try {
                URL url = new URL("http://ws.matheuscastiglioni.com.br/ws/cep/find/" + this.cep + "/json/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type","application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()){
                    resposta.append(scanner.next());

                }
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (ProtocolException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return new Gson().fromJson(resposta.toString(),CEP.class);
    }
}
