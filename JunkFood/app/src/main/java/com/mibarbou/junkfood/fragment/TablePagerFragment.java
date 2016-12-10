package com.mibarbou.junkfood.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mibarbou.junkfood.R;
import com.mibarbou.junkfood.model.Table;
import com.mibarbou.junkfood.model.Tables;

/**
 * A simple {@link Fragment} subclass.
 */
public class TablePagerFragment extends Fragment {

    private static final String ARG_TABLE_INDEX = "ARG_TABLE_INDEX";

    private Tables mTables;
    private ViewPager mPager;
    private int mInitialTableIndex;

    public static TablePagerFragment newInstance(int tableIndex) {
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_TABLE_INDEX, tableIndex);

        TablePagerFragment tablePagerFragment = new TablePagerFragment();
        tablePagerFragment.setArguments(arguments);

        return tablePagerFragment;
    }

    public TablePagerFragment() {
        // Required empty public constructor
        mTables = new Tables();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mInitialTableIndex = getArguments().getInt(ARG_TABLE_INDEX, 0);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_table_pager, container, false);
        mPager = (ViewPager) root.findViewById(R.id.view_pager);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateTableInfo(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mPager.setAdapter(new TablePagerAdapter(getFragmentManager(), mTables));

        mPager.setCurrentItem(mInitialTableIndex);
        updateTableInfo(mInitialTableIndex);

        return root;
    }

    public void showTable(int position) { mPager.setCurrentItem(position); }

    private void updateTableInfo(int position) {
        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            ActionBar actionBar = activity.getSupportActionBar();

            if (actionBar != null) {
                actionBar.setTitle(mTables.getTable(position).getName());
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_pager, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superValue = super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.previous) {
            // Retrocedemos una página
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);

            return true;
        }
        else if (item.getItemId() == R.id.next) {
            // Avanzamos una página
            mPager.setCurrentItem(mPager.getCurrentItem() + 1);

            return true;
        }

        return superValue;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem menuPrev = menu.findItem(R.id.previous);
        MenuItem menuNext = menu.findItem(R.id.next);

        menuPrev.setEnabled(mPager.getCurrentItem() > 0);
        menuNext.setEnabled(mPager.getCurrentItem() < mTables.getCount() - 1);
    }

}

class TablePagerAdapter extends FragmentPagerAdapter {

    private Tables mTables;

    public TablePagerAdapter(FragmentManager fm, Tables tables){
        super(fm);
        mTables = tables;
    }

    @Override
    public Fragment getItem(int position) {
        Table table = mTables.getTables().get(position);
        OrdersFragment fragment = OrdersFragment.newInstance(table);

        return fragment;
    }

    @Override
    public int getCount() {
        return mTables.getCount();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);

        Table table = mTables.getTable(position);
        return table.getName();
    }

}
