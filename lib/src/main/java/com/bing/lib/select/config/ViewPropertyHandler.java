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

import java.lang.ref.WeakReference;

abstract class ViewPropertyHandler<T>
{
    private WeakReference<View> mView;

    private T mValueNormal;
    private T mValueSelected;

    private OnValueChangedCallback mOnValueChangedCallback;

    public ViewPropertyHandler(View view, OnValueChangedCallback onValueChangedCallback)
    {
        mView = new WeakReference<>(view);
        mOnValueChangedCallback = onValueChangedCallback;
    }

    private View getView()
    {
        if (mView != null)
        {
            return mView.get();
        } else
        {
            return null;
        }
    }

    public final void setValueNormal(T valueNormal)
    {
        mValueNormal = valueNormal;
        if (mOnValueChangedCallback != null)
        {
            mOnValueChangedCallback.onValueChanged(false, valueNormal, this);
        }
    }

    public final void setValueSelected(T valueSelected)
    {
        mValueSelected = valueSelected;
        if (mOnValueChangedCallback != null)
        {
            mOnValueChangedCallback.onValueChanged(true, valueSelected, this);
        }
    }

    public final void setSelected(boolean selected)
    {
        final View view = getView();
        if (view != null)
        {
            T value = selected ? mValueSelected : mValueNormal;
            onViewSelectedChanged(selected, value, view);
        }
    }

    protected abstract void onViewSelectedChanged(boolean selected, T value, View view);

    public final boolean isEmpty()
    {
        return mValueNormal == null && mValueSelected == null;
    }

    public interface OnValueChangedCallback<T>
    {
        void onValueChanged(boolean selectedValue, T value, ViewPropertyHandler<T> handler);
    }
}
