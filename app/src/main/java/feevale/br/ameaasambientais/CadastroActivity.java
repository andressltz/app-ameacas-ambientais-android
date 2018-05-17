package feevale.br.ameaasambientais;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastroActivity extends Activity {

    EditText txtDescription;

    EditText txtAddress;

    EditText txtDistrict;

    EditText txtImpact;

    Integer idAmeacaAmbiental;

    AmeacasAmbientaisDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtDistrict = (EditText) findViewById(R.id.txtDistrict);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtImpact = (EditText) findViewById(R.id.txtImpact);
        txtDescription = (EditText) findViewById(R.id.txtDescription);

        dao = new AmeacasAmbientaisDAO(getBaseContext());
    }

    public void salvar(View view) {
        if (idAmeacaAmbiental == null) {
            AmeacaAmbiental ameaca = new AmeacaAmbiental();
            ameaca.setAmeaca(txtDescription.getText().toString());
            ameaca.setBairro(txtDistrict.getText().toString());
            ameaca.setEndereco(txtAddress.getText().toString());
            ameaca.setImpacto(Integer.valueOf(txtImpact.getText().toString()));

            dao.save(ameaca);

            txtAddress.setText(null);
            txtDistrict.setText(null);
            txtImpact.setText(null);
            txtDescription.setText(null);

            // TODO: atualizar tela
        } else {
            AmeacaAmbiental ameaca = new AmeacaAmbiental();
            ameaca.setAmeaca(txtDescription.getText().toString());
            ameaca.setBairro(txtDistrict.getText().toString());
            ameaca.setEndereco(txtAddress.getText().toString());
            ameaca.setImpacto(Integer.valueOf(txtImpact.getText().toString()));

            dao.update(ameaca);

            txtAddress.setText(null);
            txtDistrict.setText(null);
            txtImpact.setText(null);
            txtDescription.setText(null);

            // TODO: atualizar tela
        }
        // FIXME: refactor - jogar lógica do if pra dentro do adapter.save()
    }

}
