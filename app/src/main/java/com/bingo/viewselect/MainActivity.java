package com.bingo.viewselect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bingo.viewselect.view.TabUnderline;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private TabUnderline tab1;
    private TabUnderline tab2;
    private TabUnderline tab3;

    private FSelectViewManager<TabUnderline> mSelectManager = new FSelectViewManager<>();

    private void init()
    {
        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);

        changeLiveTabUnderline(tab1, "全部");
        changeLiveTabUnderline(tab2, "推荐");
        changeLiveTabUnderline(tab3, "段子");

        TabUnderline[] items = new TabUnderline[]{tab1, tab2, tab3};

        mSelectManager.addSelectCallback(new FSelectManager.SelectCallback<TabUnderline>()
        {
            @Override
            public void onNormal(int index, TabUnderline item)
            {

            }

            @Override
            public void onSelected(int index, TabUnderline item)
            {
                Toast.makeText(MainActivity.this, item.getTextViewTitle().getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        mSelectManager.setItems(items);
        mSelectManager.performClick(0);
    }

    private void changeLiveTabUnderline(TabUnderline tabUnderline, String title)
    {
        tabUnderline.getTextViewTitle().setText(title);
        tabUnderline.configViewUnderline().setWidthNormal(dp2px(50)).setWidthSelected(dp2px(50));
//        tabUnderline.configTextViewTitle().setTextSizeNormal(getDimensionPixelSize(R.dimen.base_textsize_12)).setTextSizeSelected(getDimensionPixelSize(R.dimen.base_textsize_12));
    }

    public int getDimensionPixelSize(int resId)
    {
        return getResources().getDimensionPixelSize(resId);
    }

    public int dp2px(float dp)
    {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
