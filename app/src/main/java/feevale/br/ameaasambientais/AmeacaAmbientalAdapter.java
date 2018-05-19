package feevale.br.ameaasambientais;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AmeacaAmbientalAdapter extends BaseAdapter {

    private List<AmeacaAmbiental> ameacaAmbientalList;

    private Context context;

    private AmeacasAmbientaisDAO dao;

    private LayoutInflater inflater;

    public AmeacaAmbientalAdapter(AmeacasAmbientaisDAO dao, Context context) {
        this.context = context;
        this.dao = dao;
        inflater = LayoutInflater.from(context);
        ameacaAmbientalList = dao.findAll();
    }

    @Override
    public int getCount() {
        ameacaAmbientalList = dao.findAll();
        return ameacaAmbientalList.size();
    }

    @Override
    public Object getItem(int position) {
        ameacaAmbientalList = dao.findAll();
        return ameacaAmbientalList.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (ameacaAmbientalList.size() > 0) {
            return ameacaAmbientalList.get(position).getId();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ameacaAmbientalList = dao.findAll();
        View row = inflater.inflate(R.layout.list_register_item, parent, false);

        TextView txtEndereco = (TextView) row.findViewById(R.id.txtAddress);
        TextView txtImpacto= (TextView) row.findViewById(R.id.lblImpact);
        TextView txtDescricao = (TextView) row.findViewById(R.id.lblDescription);
        txtEndereco.setText(ameacaAmbientalList.get(position).getEndereco());
        txtImpacto.setText(ameacaAmbientalList.get(position).getImpacto());
        txtDescricao.setText(ameacaAmbientalList.get(position).getAmeaca());

        return row;
    }

}
