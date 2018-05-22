package feevale.br.ameaasambientais;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    // TODO create the recent register in home activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void verTodos(View view) {
        Intent intentList = new Intent(getBaseContext(), ListaActivity.class);

        startActivityForResult(intentList, RequestCode.VER_TODOS.code);
    }

    public void cadastrar(View view) {
        Intent intentCadastro = new Intent(getBaseContext(), CadastroActivity.class);

        startActivityForResult(intentCadastro, RequestCode.CADASTRAR.code);
    }
}
