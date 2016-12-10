package com.mibarbou.junkfood.activity;

import android.app.FragmentManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mibarbou.junkfood.R;
import com.mibarbou.junkfood.fragment.TablePagerFragment;

public class TablePagerActivity extends AppCompatActivity {

    public static final String EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX";

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

            TablePagerFragment tablePagerFragment = TablePagerFragment.newInstance(tableIndex);

            fm.beginTransaction()
                    .add(R.id.fragment_table_pager, tablePagerFragment)
                    .commit();

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superValue = super.onOptionsItemSelected(item);

        if (item.getItemId() == android.R.id.home) {
            // Han pulsado la flecha de back de la Actionbar, finalizamos la actividad
            finish();
            return true;
        }

        return superValue;
    }
}
