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
import com.mibarbou.junkfood.model.Tables;

public class TableActivity extends AppCompatActivity implements TableListFragment.OnTableSelectedListener {

    private int TABLES_CODE = 0;

    private Tables mTables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        mTables = new Tables();

        FragmentManager fm = getFragmentManager();

        TableListFragment tableListFragment = TableListFragment.newInstance(mTables);

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
                        .add(R.id.fragment_table_list, tableListFragment)
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
            intent.putExtra(TablePagerActivity.EXTRA_TABLES, mTables);

            startActivityForResult(intent, TABLES_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TABLES_CODE && resultCode == RESULT_OK) {
            mTables = (Tables) data.getSerializableExtra(TablePagerFragment.EXTRA_TABLES);

            FragmentManager fm = getFragmentManager();

            TableListFragment tableListFragment = TableListFragment.newInstance(mTables);

            if (findViewById(R.id.fragment_table_list) != null) {

                if (fm.findFragmentById(R.id.fragment_table_list) == null) {
                    fm.beginTransaction()
                            .add(R.id.fragment_table_list, tableListFragment)
                            .commit();
                }
            }
        }
    }
}
