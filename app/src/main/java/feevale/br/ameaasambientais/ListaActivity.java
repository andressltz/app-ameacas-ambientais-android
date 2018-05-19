package feevale.br.ameaasambientais;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ListaActivity extends Activity {

    private ListView listAmeacas;

    private AmeacaAmbientalAdapter adapter;

    private AmeacasAmbientaisDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        listAmeacas = (ListView) findViewById(R.id.listRegisters);

        dao = new AmeacasAmbientaisDAO(getBaseContext());

        adapter = new AmeacaAmbientalAdapter(dao, getBaseContext());

        listAmeacas.setAdapter(adapter);
    }

}
