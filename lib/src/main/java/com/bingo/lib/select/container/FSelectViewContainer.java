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
package com.bingo.lib.select.container;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bingo.lib.select.config.FImageViewSelectConfig;
import com.bingo.lib.select.config.FTextViewSelectConfig;
import com.bingo.lib.select.config.FViewSelectConfig;

import java.util.Map;
import java.util.WeakHashMap;

public class FSelectViewContainer implements FISelectViewContainer
{
    private WeakHashMap<View, FViewSelectConfig> mMapViewConfig = new WeakHashMap<>();

    private boolean mInvokeViewSelected = false;

    @Override
    public void setInvokeViewSelected(boolean invokeViewSelected)
    {
        mInvokeViewSelected = invokeViewSelected;
    }

    @Override
    public FViewSelectConfig config(View view)
    {
        if (view == null)
        {
            return null;
        }
        FViewSelectConfig config = mMapViewConfig.get(view);
        if (config == null)
        {
            config = FViewSelectConfig.config(view);
            mMapViewConfig.put(view, config);
        }
        return config;
    }

    @Override
    public FImageViewSelectConfig configImage(ImageView view)
    {
        if (view == null)
        {
            return null;
        }
        FImageViewSelectConfig config = (FImageViewSelectConfig) mMapViewConfig.get(view);
        if (config == null)
        {
            config = FViewSelectConfig.configImage(view);
            mMapViewConfig.put(view, config);
        }
        return config;
    }

    @Override
    public FTextViewSelectConfig configText(TextView view)
    {
        if (view == null)
        {
            return null;
        }
        FTextViewSelectConfig config = (FTextViewSelectConfig) mMapViewConfig.get(view);
        if (config == null)
        {
            config = FViewSelectConfig.configText(view);
            mMapViewConfig.put(view, config);
        }
        return config;
    }

    @Override
    public void removeConfig(View view)
    {
        mMapViewConfig.remove(view);
    }

    @Override
    public void clearConfig()
    {
        mMapViewConfig.clear();
    }

    @Override
    public void setSelected(boolean selected)
    {
        if (mMapViewConfig.isEmpty())
        {
            return;
        }
        for (Map.Entry<View, FViewSelectConfig> item : mMapViewConfig.entrySet())
        {
            item.getValue().setSelected(selected, mInvokeViewSelected);
        }
    }
}
