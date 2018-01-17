/*
 * Copyright (C) 2017 zhengjun, fanwe (http://www.fanwe.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bing.lib.select.config;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * View的参数配置
 */
public class FViewSelectConfig implements ViewPropertyHandler.OnValueChangedCallback
{
    private Context mContext;
    private WeakReference<View> mView;

    private List<ViewPropertyHandler> mListHandler = new ArrayList<>();

    private ViewBackgroundHandler mBackgroundHandler;
    private ViewAlphaHandler mAlphaHandler;
    private ViewWidthHandler mWidthHandler;
    private ViewHeightHandler mHeightHandler;
    private ViewVisibilityHandler mVisibilityHandler;

    protected FViewSelectConfig(View view)
    {
        setView(view);
    }

    public static FViewSelectConfig config(View view)
    {
        FViewSelectConfig config = new FViewSelectConfig(view);
        return config;
    }

    public static FImageViewSelectConfig configImage(ImageView view)
    {
        FImageViewSelectConfig config = new FImageViewSelectConfig(view);
        return config;
    }

    public static FTextViewSelectConfig configText(TextView view)
    {
        FTextViewSelectConfig config = new FTextViewSelectConfig(view);
        return config;
    }

    /**
     * 设置View是否选中
     *
     * @param selected
     */
    public void setSelected(boolean selected)
    {
        setSelected(selected, true);
    }

    /**
     * 设置View是否选中
     *
     * @param selected
     * @param invokeViewSelected 是否触发View的setSelected方法
     */
    public void setSelected(boolean selected, boolean invokeViewSelected)
    {
        final View view = getView();
        if (view == null)
        {
            return;
        }

        for (ViewPropertyHandler item : mListHandler)
        {
            item.setSelected(selected);
        }

        if (invokeViewSelected)
        {
            view.setSelected(selected);
        }
    }

    public FViewSelectConfig setBackgroundResIdNormal(Integer value)
    {
        setBackgroundNormal(value == null ? null : getContext().getResources().getDrawable(value));
        return this;
    }

    public FViewSelectConfig setBackgroundResIdSelected(Integer value)
    {
        setBackgroundSelected(value == null ? null : getContext().getResources().getDrawable(value));
        return this;
    }

    public FViewSelectConfig setBackgroundColorNormal(Integer value)
    {
        setBackgroundNormal(value == null ? null : new ColorDrawable(value));
        return this;
    }

    public FViewSelectConfig setBackgroundColorSelected(Integer value)
    {
        setBackgroundSelected(value == null ? null : new ColorDrawable(value));
        return this;
    }

    public FViewSelectConfig setBackgroundColorResIdNormal(Integer value)
    {
        setBackgroundColorNormal(value == null ? null : getContext().getResources().getColor(value));
        return this;
    }

    public FViewSelectConfig setBackgroundColorResIdSelected(Integer value)
    {
        setBackgroundColorSelected(value == null ? null : getContext().getResources().getColor(value));
        return this;
    }

    //---------- property start ----------

    public FViewSelectConfig setBackgroundNormal(Drawable value)
    {
        getBackgroundHandler().setValueNormal(value);
        return this;
    }

    public FViewSelectConfig setBackgroundSelected(Drawable value)
    {
        getBackgroundHandler().setValueSelected(value);
        return this;
    }

    public FViewSelectConfig setAlphaNormal(Float value)
    {
        getAlphaHandler().setValueNormal(value);
        return this;
    }

    public FViewSelectConfig setAlphaSelected(Float value)
    {
        getAlphaHandler().setValueSelected(value);
        return this;
    }

    public FViewSelectConfig setWidthNormal(Integer value)
    {
        getWidthHandler().setValueNormal(value);
        return this;
    }

    public FViewSelectConfig setWidthSelected(Integer value)
    {
        getWidthHandler().setValueSelected(value);
        return this;
    }

    public FViewSelectConfig setHeightNormal(Integer value)
    {
        getHeightHandler().setValueNormal(value);
        return this;
    }

    public FViewSelectConfig setHeightSelected(Integer value)
    {
        getHeightHandler().setValueSelected(value);
        return this;
    }

    public FViewSelectConfig setVisibilityNormal(Integer value)
    {
        getVisibilityHandler().setValueNormal(value);
        return this;
    }

    public FViewSelectConfig setVisibilitySelected(Integer value)
    {
        getVisibilityHandler().setValueSelected(value);
        return this;
    }

    //---------- properties end ----------

    protected final Context getContext()
    {
        return mContext;
    }

    public final View getView()
    {
        if (mView != null)
        {
            return mView.get();
        } else
        {
            return null;
        }
    }

    private FViewSelectConfig setView(View view)
    {
        final View oldView = getView();
        if (oldView != view)
        {
            if (view != null)
            {
                mView = new WeakReference<>(view);
                mContext = view.getContext().getApplicationContext();
            } else
            {
                mView = null;
            }
        }
        return this;
    }

    private ViewBackgroundHandler getBackgroundHandler()
    {
        if (mBackgroundHandler == null)
        {
            mBackgroundHandler = new ViewBackgroundHandler(getView(), this);
        }
        return mBackgroundHandler;
    }

    private ViewAlphaHandler getAlphaHandler()
    {
        if (mAlphaHandler == null)
        {
            mAlphaHandler = new ViewAlphaHandler(getView(), this);
        }
        return mAlphaHandler;
    }

    private ViewWidthHandler getWidthHandler()
    {
        if (mWidthHandler == null)
        {
            mWidthHandler = new ViewWidthHandler(getView(), this);
        }
        return mWidthHandler;
    }

    private ViewHeightHandler getHeightHandler()
    {
        if (mHeightHandler == null)
        {
            mHeightHandler = new ViewHeightHandler(getView(), this);
        }
        return mHeightHandler;
    }

    private ViewVisibilityHandler getVisibilityHandler()
    {
        if (mVisibilityHandler == null)
        {
            mVisibilityHandler = new ViewVisibilityHandler(getView(), this);
        }
        return mVisibilityHandler;
    }

    @Override
    public final void onValueChanged(boolean selectedValue, Object value, ViewPropertyHandler handler)
    {
        if (handler.isEmpty())
        {
            mListHandler.remove(handler);
            onReleaseHandler(handler);
        } else
        {
            if (!mListHandler.contains(handler))
            {
                mListHandler.add(handler);
            }
        }
    }

    protected void onReleaseHandler(ViewPropertyHandler handler)
    {
        if (mAlphaHandler == handler)
        {
            mAlphaHandler = null;
        } else if (mBackgroundHandler == handler)
        {
            mBackgroundHandler = null;
        } else if (mWidthHandler == handler)
        {
            mWidthHandler = null;
        } else if (mHeightHandler == handler)
        {
            mHeightHandler = null;
        } else if (mVisibilityHandler == handler)
        {
            mVisibilityHandler = null;
        }
    }
}
