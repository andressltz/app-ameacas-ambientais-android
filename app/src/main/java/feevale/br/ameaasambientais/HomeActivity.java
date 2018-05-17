package feevale.br.ameaasambientais;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    private static final int VER_TODOS = 1000;

    private static final int CADASTRAR = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void verTodos(View view) {
        Intent intentList = new Intent(getBaseContext(), ListaActivity.class);

        startActivityForResult(intentList, VER_TODOS);
    }

    public void cadastrar(View view) {
        Intent intentCadastro = new Intent(getBaseContext(), CadastroActivity.class);

        startActivityForResult(intentCadastro, CADASTRAR);
    }
}
