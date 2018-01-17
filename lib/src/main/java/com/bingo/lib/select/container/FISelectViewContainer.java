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


public interface FISelectViewContainer
{
    /**
     * 调用setSelected的时候是否也触发View的setSelected方法，默认false
     *
     * @param invokeViewSelected
     */
    void setInvokeViewSelected(boolean invokeViewSelected);

    /**
     * 把View添加到容器中，并返回该View对应的Config
     *
     * @param view
     * @return
     */
    FViewSelectConfig config(View view);

    /**
     * 把View添加到容器中，并返回该View对应的Config
     *
     * @param view
     * @return
     */
    FImageViewSelectConfig configImage(ImageView view);

    /**
     * 把View添加到容器中，并返回该View对应的Config
     *
     * @param view
     * @return
     */
    FTextViewSelectConfig configText(TextView view);

    /**
     * 把View和对应的Config移除
     *
     * @param view
     */
    void removeConfig(View view);

    /**
     * 清空容器中的所有View和对应的Config
     */
    void clearConfig();

    /**
     * 设置容器中的所有View是否选中
     *
     * @param selected
     */
    void setSelected(boolean selected);
}
