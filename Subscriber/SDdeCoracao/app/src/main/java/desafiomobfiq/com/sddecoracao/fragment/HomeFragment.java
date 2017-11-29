package desafiomobfiq.com.sddecoracao.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import desafiomobfiq.com.sddecoracao.R;
import desafiomobfiq.com.sddecoracao.interfaces.ComunicacaoFragmentActivity;
import desafiomobfiq.com.sddecoracao.model.Dados;


public class HomeFragment extends Fragment {

    private int [] tags;
    private String ip;
    private String mensagemEntrada;
    private String mensagemSaida;

    private ComunicacaoFragmentActivity comunicacaoFragmentActivity;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        comunicacaoFragmentActivity = (ComunicacaoFragmentActivity) context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText edtIP = (EditText) view.findViewById(R.id.edt_ip);
        final CheckBox cbTag1 = (CheckBox) view.findViewById(R.id.cb_tag1);
        final CheckBox cbTag2 = (CheckBox) view.findViewById(R.id.cb_tag2);
        final CheckBox cbTag3 = (CheckBox) view.findViewById(R.id.cb_tag3);

        tags = new int [3];

        Button btnCadastrar = (Button) view.findViewById(R.id.btn_cadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip = edtIP.getText().toString();

                tags[0] = (cbTag1.isChecked()?1:0);
                tags[1] = (cbTag2.isChecked()?1:0);
                tags[2] = (cbTag3.isChecked()?1:0);

                inicializarMensagemEntrada();

                new TratarSocket().execute();
            }
        });
    }

    private void inicializarMensagemEntrada(){

        mensagemEntrada = "-n,";
        for (int tag : tags){
            mensagemEntrada += tag + ",";
        }
    }

    class TratarSocket extends AsyncTask<Void, Void, Dados> {

        Socket socket = null;

        @Override
        protected Dados doInBackground(Void... params) {
            Socket socket = null;
            ObjectOutputStream canalSaida = null;
            Scanner canalEntrada = null;
            try {
                Log.d("debug", "entrei 1");
                socket = new Socket(ip, 5003);
                socket.setSoTimeout(2000);
                Log.d("debug", "entrei 2");

                canalSaida = new ObjectOutputStream(socket.getOutputStream());
                Log.d("debug", "entrei 3");
                canalSaida.writeBytes(mensagemEntrada);
                Log.d("debug", "entrei 4");

//                canalEntrada = new Scanner(socket.getInputStream());
//
//                if(canalEntrada.hasNextLine()){
//                    mensagemSaida += canalEntrada.nextLine();
//                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(socket!= null) {
                        socket.close();
                        canalSaida.close();
//                        canalEntrada.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return new Dados(ip,tags,mensagemSaida);

        }
        @Override
        protected void onPostExecute(Dados dados) {
            super.onPostExecute(dados);
            comunicacaoFragmentActivity.enviarDados(dados.getIp(), dados.getTags(),dados.getMensagemSaida());
        }
    }
}
