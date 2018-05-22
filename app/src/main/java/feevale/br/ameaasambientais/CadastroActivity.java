package feevale.br.ameaasambientais;

import android.app.Activity;
import android.content.Intent;
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
        txtAddress = (EditText) findViewById(R.id.txtListAddress);
        txtImpact = (EditText) findViewById(R.id.txtImpact);
        txtDescription = (EditText) findViewById(R.id.txtDescription);

        dao = new AmeacasAmbientaisDAO(getBaseContext());

        Intent intent = getIntent();
        idAmeacaAmbiental = intent.getIntExtra("ID", -1);
        if (idAmeacaAmbiental != null && idAmeacaAmbiental != -1) {
            AmeacaAmbiental ameaca = dao.load(idAmeacaAmbiental);
            if (ameaca != null) {
                txtDescription.setText(ameaca.getAmeaca());
                txtImpact.setText(ameaca.getImpacto());
                txtAddress.setText(ameaca.getEndereco());
                txtDistrict.setText(ameaca.getBairro());
            }
        }

    }

    public void salvar(View view) {
        if (idAmeacaAmbiental == null || idAmeacaAmbiental == -1) {
            AmeacaAmbiental ameaca = new AmeacaAmbiental();
            ameaca.setAmeaca(txtDescription.getText().toString());
            ameaca.setBairro(txtDistrict.getText().toString());
            ameaca.setEndereco(txtAddress.getText().toString());
            ameaca.setImpacto(txtImpact.getText().toString());

            dao.save(ameaca);

            txtAddress.setText(null);
            txtDistrict.setText(null);
            txtImpact.setText(null);
            txtDescription.setText(null);
        } else {
            AmeacaAmbiental ameaca = new AmeacaAmbiental();
            ameaca.setAmeaca(txtDescription.getText().toString());
            ameaca.setBairro(txtDistrict.getText().toString());
            ameaca.setEndereco(txtAddress.getText().toString());
            ameaca.setImpacto(txtImpact.getText().toString());
            ameaca.setId(idAmeacaAmbiental);

            dao.update(ameaca);

            txtAddress.setText(null);
            txtDistrict.setText(null);
            txtImpact.setText(null);
            txtDescription.setText(null);

            setResult(RESULT_OK);
            finish();
        }
        // FIXME: refactor - jogar lógica do if pra dentro do adapter.save()
    }



}
