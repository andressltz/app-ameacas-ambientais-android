package feevale.br.ameaasambientais;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class CadastroActivity extends Activity {

    EditText txtDescription;

    EditText txtAddress;

    EditText txtDistrict;

    Integer txtImpact;

    Spinner impactSpinner;

    Integer idAmeacaAmbiental;

    AmeacasAmbientaisDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtDistrict = (EditText) findViewById(R.id.txtDistrict);
        txtAddress = (EditText) findViewById(R.id.txtListAddress);
        impactSpinner = (Spinner) findViewById(R.id.txtImpact);
        txtDescription = (EditText) findViewById(R.id.txtDescription);
        txtImpact = getDefaultValueImpact();

        dao = new AmeacasAmbientaisDAO(getBaseContext());

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getListImpact());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        impactSpinner.setAdapter(adapter);

        Intent intent = getIntent();
        idAmeacaAmbiental = intent.getIntExtra("ID", -1);
        if (idAmeacaAmbiental != null && idAmeacaAmbiental != -1) {
            AmeacaAmbiental ameaca = dao.load(idAmeacaAmbiental);
            if (ameaca != null) {
                txtDescription.setText(ameaca.getAmeaca());
                txtImpact = ameaca.getImpacto();
                impactSpinner.setSelection(getPositionImpact(txtImpact),true);
                txtAddress.setText(ameaca.getEndereco());
                txtDistrict.setText(ameaca.getBairro());
            }
        }

        impactSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txtImpact = Integer.valueOf(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txtImpact = getDefaultValueImpact();
            }
        });

    }

    public void salvar(View view) {
        if (idAmeacaAmbiental == null || idAmeacaAmbiental == -1) {
            AmeacaAmbiental ameaca = new AmeacaAmbiental();
            ameaca.setAmeaca(txtDescription.getText().toString());
            ameaca.setBairro(txtDistrict.getText().toString());
            ameaca.setEndereco(txtAddress.getText().toString());
            ameaca.setImpacto(txtImpact);

            dao.save(ameaca);

            txtAddress.setText(null);
            txtDistrict.setText(null);
            txtImpact = getDefaultValueImpact();
            txtDescription.setText(null);
            impactSpinner.setSelection(0,true);
        } else {
            AmeacaAmbiental ameaca = new AmeacaAmbiental();
            ameaca.setAmeaca(txtDescription.getText().toString());
            ameaca.setBairro(txtDistrict.getText().toString());
            ameaca.setEndereco(txtAddress.getText().toString());
            ameaca.setImpacto(txtImpact);
            ameaca.setId(idAmeacaAmbiental);

            dao.update(ameaca);

            txtAddress.setText(null);
            txtDistrict.setText(null);
            txtImpact = getDefaultValueImpact();
            txtDescription.setText(null);
            impactSpinner.setSelection(0,true);

            setResult(RESULT_OK);
            finish();
        }
        // FIXME: refactor - jogar lógica do if pra dentro do adapter.save()
    }

    private Integer getDefaultValueImpact() {
        return getListImpact().get(0);
    }

    private List<Integer> getListImpact() {
        ArrayList<Integer> listImpact = new ArrayList<>();
        listImpact.add(1);
        listImpact.add(2);
        listImpact.add(3);
        listImpact.add(4);
        listImpact.add(5);
        listImpact.add(6);
        listImpact.add(7);
        listImpact.add(8);
        listImpact.add(9);
        listImpact.add(10);
        return listImpact;
    }

    public int getPositionImpact(Integer impact) {
        List<Integer> list = getListImpact();
        for (int i = 0; i < list.size(); i ++) {
            if (list.get(i) == impact) {
                return i;
            }
        }
        return 0;
    }

}
