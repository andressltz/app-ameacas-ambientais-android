package feevale.br.ameaasambientais;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
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

        listAmeacas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), CadastroActivity.class);
                intent.putExtra("ID", (int) id);
                startActivityForResult(intent, RequestCode.ATUALIZAR.code);
            }
        });

        listAmeacas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dao.delete((int) id);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCode.ATUALIZAR.code && resultCode == RESULT_OK) {
            adapter.notifyDataSetChanged();
        }
    }
}
