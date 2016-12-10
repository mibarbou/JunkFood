package com.mibarbou.junkfood.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mibarbou.junkfood.R;
import com.mibarbou.junkfood.fragment.TableListFragment;
import com.mibarbou.junkfood.fragment.TablePagerFragment;
import com.mibarbou.junkfood.model.Table;

public class TableActivity extends AppCompatActivity implements TableListFragment.OnTableSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        FragmentManager fm = getFragmentManager();

        if (findViewById(R.id.fragment_table_pager) != null) {

            if (fm.findFragmentById(R.id.fragment_table_pager) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_table_pager, new TablePagerFragment())
                        .commit();
            }
        }

        if (findViewById(R.id.fragment_table_list) != null) {

            if (fm.findFragmentById(R.id.fragment_table_list) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_table_list, new TableListFragment())
                        .commit();
            }
        }

    }

    @Override
    public void onTableSelected(Table table, int position) {

        FragmentManager fm = getFragmentManager();
        TablePagerFragment tablePagerFragment = (TablePagerFragment) fm.findFragmentById(R.id.fragment_table_pager);

        if (tablePagerFragment != null) {

            tablePagerFragment.showTable(position);

        } else {

            Intent intent = new Intent(this, TablePagerActivity.class);
            intent.putExtra(TablePagerActivity.EXTRA_TABLE_INDEX, position);

            startActivity(intent);
        }
    }
}
