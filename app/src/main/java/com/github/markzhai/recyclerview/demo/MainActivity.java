/*
 * Copyright (C) 2016 MarkZhai (http://zhaiyifan.cn).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.markzhai.recyclerview.demo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.markzhai.recyclerview.MultiTypeAdapter;
import com.github.markzhai.recyclerview.SingleTypeAdapter;
import com.github.markzhai.recyclerview.demo.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private SingleTypeAdapter<EmployeeViewModel> mSingleTypeAdapter;
    private MultiTypeAdapter mMultiTypeAdapter;

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_CODER = 1;
    private static final int VIEW_TYPE_BOSS = 2;

    private static final ArrayList<EmployeeViewModel> EMPLOYEE_LIST = new ArrayList<>();
    private static final ArrayList<EmployerViewModel> EMPLOYER_LIST = new ArrayList<>();

    static {

    }

    public class Presenter {
        public void onToggleClick(View view) {
            if (mBinding.getAdapter() == mMultiTypeAdapter) {
                mBinding.setAdapter(mSingleTypeAdapter);
            } else {
                mBinding.setAdapter(mMultiTypeAdapter);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setPresenter(new Presenter());

        mSingleTypeAdapter = new SingleTypeAdapter<>(this, R.layout.item_single_type);

        mMultiTypeAdapter = new MultiTypeAdapter(this);
        mMultiTypeAdapter.addViewTypeToLayoutMap(VIEW_TYPE_HEADER, R.layout.item_header);
        mMultiTypeAdapter.addViewTypeToLayoutMap(VIEW_TYPE_CODER, R.layout.item_coder);
        mMultiTypeAdapter.addViewTypeToLayoutMap(VIEW_TYPE_BOSS, R.layout.item_boss);

        mBinding.setLayoutManager(new LinearLayoutManager(this));
        mBinding.setAdapter(mSingleTypeAdapter);
    }
}
