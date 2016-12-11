package com.mibarbou.junkfood.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mibarbou.junkfood.R;
import com.mibarbou.junkfood.model.Table;
import com.mibarbou.junkfood.model.Tables;

/**
 * A simple {@link Fragment} subclass.
 */
public class TableListFragment extends Fragment {

    private OnTableSelectedListener mOnTableSelectedListener;
    public static final String EXTRA_TABLES = "EXTRA_TABLES";
    private Tables mTables;

    public static TableListFragment newInstance(Tables tables) {
        TableListFragment fragment = new TableListFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_TABLES, tables);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTables = (Tables) getArguments().getSerializable(EXTRA_TABLES);
        }

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_table_list, container, false);

        ListView list = (ListView) root.findViewById(android.R.id.list);


        ArrayAdapter<Table> adapter = new ArrayAdapter<Table>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mTables.getTables()
        );

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (mOnTableSelectedListener != null) {
                    mOnTableSelectedListener.onTableSelected(mTables.getTable(position), position);
                }
            }
        });

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof  OnTableSelectedListener) {
            mOnTableSelectedListener = (OnTableSelectedListener) getActivity();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (getActivity() instanceof  OnTableSelectedListener) {
            mOnTableSelectedListener = (OnTableSelectedListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mOnTableSelectedListener = null;
    }


    public interface OnTableSelectedListener {
        void onTableSelected(Table table, int position);
    }

}
