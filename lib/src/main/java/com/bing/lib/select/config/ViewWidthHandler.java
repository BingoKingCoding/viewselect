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

import android.view.View;
import android.view.ViewGroup;

class ViewWidthHandler extends ViewPropertyHandler<Integer>
{
    public ViewWidthHandler(View view, OnValueChangedCallback onValueChangedCallback)
    {
        super(view, onValueChangedCallback);
    }

    @Override
    protected void onViewSelectedChanged(boolean selected, Integer value, View view)
    {
        if (value == null)
        {
            return;
        }

        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null && params.width != value)
        {
            params.width = value;
            view.setLayoutParams(params);
        }
    }
}
