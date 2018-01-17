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

import android.graphics.drawable.Drawable;
import android.view.View;

class ViewBackgroundHandler extends ViewPropertyHandler<Drawable>
{
    public ViewBackgroundHandler(View view, OnValueChangedCallback onValueChangedCallback)
    {
        super(view, onValueChangedCallback);
    }

    @Override
    protected void onViewSelectedChanged(boolean selected, Drawable value, View view)
    {
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop();
        int paddingRight = view.getPaddingRight();
        int paddingBottom = view.getPaddingBottom();

        view.setBackgroundDrawable(value);

        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }
}
