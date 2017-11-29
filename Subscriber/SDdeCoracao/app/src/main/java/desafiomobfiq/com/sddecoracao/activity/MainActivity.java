package desafiomobfiq.com.sddecoracao.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import desafiomobfiq.com.sddecoracao.fragment.HomeFragment;
import desafiomobfiq.com.sddecoracao.R;
import desafiomobfiq.com.sddecoracao.interfaces.ComunicacaoFragmentActivity;

public class MainActivity extends AppCompatActivity implements ComunicacaoFragmentActivity{

    public static int id;
    public static String ip;
    public static int [] tags;
    private FragmentManager fragmentManager;
    private TextView tvId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvId = (TextView) findViewById(R.id.tv_id);
        HomeFragment homeFragment = new HomeFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, homeFragment)
                .commit();
    }

    @Override
    public void enviarDados(String ip, int[] tags, String mensagemSaida) {
        this.ip = ip;
        this.tags = tags;
        if(mensagemSaida!=null) {
            id = Integer.parseInt(mensagemSaida);
        }

        tvId.setText(id + "");

        Intent intent = new Intent(MainActivity.this, ServerActivity.class);
        startActivity(intent);
    }
}
