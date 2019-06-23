package com.mandarin.bcu.androidutil;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mandarin.bcu.R;

import common.system.MultiLangCont;
import common.util.unit.Unit;

public class DynamicExplanation extends Fragment {
    View view;

    public static DynamicExplanation newInstance(int val, int id,String [] titles) {
        DynamicExplanation explanation = new DynamicExplanation();
        Bundle bundle = new Bundle();
        bundle.putInt("Number",val);
        bundle.putInt("ID",id);
        bundle.putStringArray("Title",titles);
        explanation.setArguments(bundle);
        return explanation;
    }

    int val;
    int id;
    String[] titles;
    TextView unitname;
    TextView[] explains = new TextView[3];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        view = inflater.inflate(R.layout.unit_info_tab,container,false);
        val = getArguments().getInt("Number",0);
        id = getArguments().getInt("ID",0);
        titles = getArguments().getStringArray("Title");
        String[] explanation = MultiLangCont.FEXP.getCont(StaticStore.units.get(id).forms[val]);

        unitname = view.findViewById(R.id.unitexname);
        int[] lineid = {R.id.unitex0,R.id.unitex1,R.id.unitex2};
        for(int i=0;i<lineid.length;i++)
            explains[i] = view.findViewById(lineid[i]);

        unitname.setText(StaticStore.units.get(id).forms[val].name);

        for(int i=0;i < explains.length;i++) {
            if(i >= explanation.length) {
                explains[i].setVisibility(View.GONE);
            } else {
                if(explanation[i] != null)
                    explains[i].setText(explanation[i]);
            }
        }

        return view;
    }
}
