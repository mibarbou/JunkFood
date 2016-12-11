package com.mibarbou.junkfood.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ViewSwitcher;

import com.mibarbou.junkfood.R;
import com.mibarbou.junkfood.adapter.MenuRecycleViewAdapter;
import com.mibarbou.junkfood.model.Food;
import com.mibarbou.junkfood.model.Foods;
import com.mibarbou.junkfood.model.Table;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

public class MenuActivity extends AppCompatActivity implements MenuRecycleViewAdapter.OnItemClickListener {

    public static final String EXTRA_TABLE = "table";

    public static final String EXTRA_FOOD = "food";

    private static final int LOADING_VIEW_INDEX = 0;
    private static final int MENU_VIEW_INDEX = 1;

    private LinkedList<Food> mFoods;
    //private  Table mTable;

    private ViewSwitcher mViewSwitcher;
    private RecyclerView mList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        mViewSwitcher = (ViewSwitcher) findViewById(R.id.view_switcher);
        mViewSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mViewSwitcher.setOutAnimation(this, android.R.anim.fade_out);

        mList = (RecyclerView) findViewById(android.R.id.list);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setItemAnimator(new DefaultItemAnimator());
        mList.setAdapter(new MenuRecycleViewAdapter(new LinkedList<Food>(), this));

        setMenu(mFoods);
    }

    private void setMenu(LinkedList<Food> foods) {
        if (foods == null) {
            downloadMenu();
        }
        else {
            mViewSwitcher.setDisplayedChild(MENU_VIEW_INDEX);

            mList.setAdapter(new MenuRecycleViewAdapter(foods, this));

            mFoods = foods;
        }
    }


    private void downloadMenu() {
        AsyncTask<Table, Integer, LinkedList<Food>> menuDownloader = new AsyncTask<Table, Integer, LinkedList<Food>>() {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                mViewSwitcher.setDisplayedChild(LOADING_VIEW_INDEX);
            }

            @Override
            protected LinkedList<Food> doInBackground(Table... tables) {
                URL url = null;
                InputStream input = null;

                try {
                    // Nos bajamos los datos
                    url = new URL("http://www.mocky.io/v2/584d469a0f00005310d40f4a");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.connect();
                    int responseLength = con.getContentLength();
                    byte data[] = new byte[1024];
                    long currentBytes = 0;
                    int downloadedBytes;
                    input = con.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    while ((downloadedBytes = input.read(data)) != -1 && !isCancelled()) {
                        sb.append(new String(data, 0, downloadedBytes));

                        publishProgress((int)(currentBytes * 100) / responseLength);
                    }

                    if (isCancelled()) {
                        return null;
                    }

                    JSONObject jsonRoot = new JSONObject(sb.toString());
                    JSONArray menu = jsonRoot.getJSONArray("menu");

                    LinkedList<Food> foods = new LinkedList<>();

                    for (int i = 0; i < menu.length(); i++) {
                        JSONObject food = menu.getJSONObject(i);
                        String name = food.getJSONObject("food").getString("name");
                        float price = (float) food.getJSONObject("food").getDouble("price");
                        int id = (int) food.getJSONObject("food").getDouble("id");
                        String allergens = food.getJSONObject("food").getString("allergens");


                        int iconInt =  id;
                        int iconResource = R.drawable.pizza;
                        switch (iconInt) {
                            case 1:
                                iconResource = R.drawable.pizza;
                                break;
                            case 2:
                                iconResource = R.drawable.burger;
                                break;
                            case 3:
                                iconResource = R.drawable.burrito;
                                break;
                            case 4:
                                iconResource = R.drawable.wings;
                                break;
                            case 5:
                                iconResource = R.drawable.hotdog;
                                break;
                            case 6:
                                iconResource = R.drawable.fries;
                                break;
                            case 7:
                                iconResource = R.drawable.rings;
                                break;
                            case 8:
                                iconResource = R.drawable.nachos;
                                break;
                            case 9:
                                iconResource = R.drawable.brownie;
                                break;
                            case 10:
                                iconResource = R.drawable.donut;
                                break;
                        }

                        foods.add(new Food(name, iconResource, allergens, price, id));
                    }

                    return foods;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(LinkedList<Food> foods) {
                super.onPostExecute(foods);

                if (foods != null) {
                    // Actualizo la interfaz
                    setMenu(foods);
                }
                else {
                    // Ha habido algún error, se lo notificamos al usuario
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuActivity.this);
                    alertDialog.setTitle(R.string.error);
                    alertDialog.setMessage(R.string.menu_download_error_message);
                    alertDialog.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            downloadMenu();
                        }
                    });

                    alertDialog.show();
                }
            }
        };


        menuDownloader.execute();
    }

    @Override
    public void onFoodSelected(Food food) {
        // se ha hecho click en un plato
        Intent returnIntent = new Intent();

        returnIntent.putExtra(EXTRA_FOOD, food);

        setResult(RESULT_OK, returnIntent);

        finish();


    }
}
