package com.mibarbou.junkfood.activity;

import android.app.FragmentManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mibarbou.junkfood.R;
import com.mibarbou.junkfood.fragment.TablePagerFragment;
import com.mibarbou.junkfood.model.Tables;

public class TablePagerActivity extends AppCompatActivity {

    public static final String EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX";
    public static final String EXTRA_TABLES = "EXTRA_TABLES";

    private Tables mTables;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_pager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_table_pager) == null){

            int tableIndex = getIntent().getIntExtra(EXTRA_TABLE_INDEX, 0);
            mTables = (Tables) getIntent().getSerializableExtra(EXTRA_TABLES);

            TablePagerFragment tablePagerFragment = TablePagerFragment.newInstance(tableIndex, mTables);

            fm.beginTransaction()
                    .add(R.id.fragment_table_pager, tablePagerFragment)
                    .commit();

        }

    }


}
