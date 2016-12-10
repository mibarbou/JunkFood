package com.mibarbou.junkfood.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewSwitcher;

import com.mibarbou.junkfood.R;
import com.mibarbou.junkfood.adapter.OrdersRecycleViewAdapter;
import com.mibarbou.junkfood.model.Order;
import com.mibarbou.junkfood.model.Table;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends Fragment {

    private static final String ARG_TABLE = "table";

    private ViewSwitcher mViewSwitcher;
    private RecyclerView mList;

    private LinkedList<Order> mOrders;

    private Table mTable;

    public static OrdersFragment newInstance(Table table) {
        OrdersFragment fragment = new OrdersFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_TABLE, table);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mTable = (Table) getArguments().getSerializable(ARG_TABLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_orders, container, false);

        mViewSwitcher = (ViewSwitcher) root.findViewById(R.id.view_switcher);
        mViewSwitcher.setInAnimation(getActivity(), android.R.anim.fade_in);
        mViewSwitcher.setOutAnimation(getActivity(), android.R.anim.fade_out);

        mList = (RecyclerView) root.findViewById(android.R.id.list);
        mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mList.setItemAnimator(new DefaultItemAnimator());

        mList.setAdapter(new OrdersRecycleViewAdapter(new LinkedList<Order>(), getActivity()));

        setOrders(mTable.getOrders());

        return root;

    }

    private void setOrders(LinkedList<Order> orders) {
        if (orders == null) {

        } else {

            mList.setAdapter(new OrdersRecycleViewAdapter(orders, getActivity()));

            mOrders = orders;
        }
    }
}
