package com.bingo.viewselect.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.bingo.lib.select.config.FTextViewSelectConfig;
import com.bingo.lib.select.config.FViewSelectConfig;
import com.bingo.lib.select.view.FSelectView;
import com.bingo.viewselect.R;

/**
 * <请描述这个类是干什么的>
 */

public class TabUnderline extends FSelectView
{
    public TabUnderline(Context context)
    {
        super(context);
        init();
    }

    public TabUnderline(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public TabUnderline(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private TextView tv_title;
    private View view_underline;

    private void init()
    {
        setContentView(R.layout.tab_underline);
        tv_title = findViewById(R.id.tv_title);
        view_underline = findViewById(R.id.iv_underline);

        configTextViewTitle()
                .setTextColorResIdNormal(R.color.text_gray_s)
                .setTextColorResIdSelected(R.color.colorPrimary)
                .setSelected(false);

        configViewUnderline()
                .setVisibilityNormal(View.INVISIBLE)
                .setVisibilitySelected(View.VISIBLE)
                .setSelected(false);
    }

    public FTextViewSelectConfig configTextViewTitle()
    {
        return configText(getTextViewTitle());
    }

    public FViewSelectConfig configViewUnderline()
    {
        return config(getViewUnderline());
    }

    public TextView getTextViewTitle()
    {
        return tv_title;
    }

    public View getViewUnderline()
    {
        return view_underline;
    }

}
