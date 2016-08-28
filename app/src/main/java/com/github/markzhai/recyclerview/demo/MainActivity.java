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
import android.widget.Toast;

import com.github.markzhai.recyclerview.BaseViewAdapter;
import com.github.markzhai.recyclerview.BindingViewHolder;
import com.github.markzhai.recyclerview.MultiTypeAdapter;
import com.github.markzhai.recyclerview.SingleTypeAdapter;
import com.github.markzhai.recyclerview.demo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private SingleTypeAdapter<EmployeeViewModel> mSingleTypeAdapter;
    private MultiTypeAdapter mMultiTypeAdapter;

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_EMPLOYEE = 1;
    private static final int VIEW_TYPE_EMPLOYER = 2;

    private static final ArrayList<EmployeeViewModel> EMPLOYEE_LIST = new ArrayList<>();
    private static final ArrayList<EmployerViewModel> EMPLOYER_LIST = new ArrayList<>();

    static {
        EmployeeViewModel model1 = new EmployeeViewModel("markzhai", 26);
        EmployeeViewModel model2 = new EmployeeViewModel("dim", 25);
        EmployeeViewModel model3 = new EmployeeViewModel("abner", 25);
        EmployeeViewModel model4 = new EmployeeViewModel("cjj", 26);

        EMPLOYEE_LIST.add(model1);
        EMPLOYEE_LIST.add(model2);
        EMPLOYEE_LIST.add(model3);
        EMPLOYEE_LIST.add(model4);

        EmployerViewModel model5 = new EmployerViewModel("boss1", 30,
                "https://avatars2.githubusercontent.com/u/1106500?v=3&s=150", "CEO");

        EmployerViewModel model6 = new EmployerViewModel("boss2", 31,
                "https://avatars3.githubusercontent.com/u/11629640?v=3&s=150", "CTO");

        EmployerViewModel model7 = new EmployerViewModel("boss3", 38,
                "https://avatars2.githubusercontent.com/u/1468623?v=3&s=150", "CAO");

        EMPLOYER_LIST.add(model5);
        EMPLOYER_LIST.add(model6);
        EMPLOYER_LIST.add(model7);
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

    public class DemoAdapterPresenter implements BaseViewAdapter.Presenter {
        public void onItemClick(EmployeeViewModel model) {
            Toast.makeText(MainActivity.this, "employee " + model.name, Toast.LENGTH_SHORT).show();

        }

        public void onItemClick(EmployerViewModel model) {
            Toast.makeText(MainActivity.this, "employer " + model.name, Toast.LENGTH_SHORT).show();
        }
    }

    public class DemoAdapterDecorator implements BaseViewAdapter.Decorator {

        @Override
        public void decorator(BindingViewHolder holder, int position, int viewType) {
            // you may do something according to position or view type
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setPresenter(new Presenter());

        mSingleTypeAdapter = new SingleTypeAdapter<>(this, R.layout.item_employee);

        mSingleTypeAdapter.setPresenter(new DemoAdapterPresenter());

        // you can use the built-in presenter
//        mSingleTypeAdapter.setPresenter(new SingleTypeAdapter.Presenter<EmployeeViewModel>() {
//
//            @Override
//            public void onItemClick(EmployeeViewModel model) {
//                Toast.makeText(MainActivity.this, model.name, Toast.LENGTH_SHORT).show();
//            }
//        });

        mSingleTypeAdapter.setDecorator(new DemoAdapterDecorator());

        mMultiTypeAdapter = new MultiTypeAdapter(this);
        mMultiTypeAdapter.setPresenter(new DemoAdapterPresenter());

        mMultiTypeAdapter.addViewTypeToLayoutMap(VIEW_TYPE_HEADER, R.layout.item_header);
        mMultiTypeAdapter.addViewTypeToLayoutMap(VIEW_TYPE_EMPLOYEE, R.layout.item_employee);
        mMultiTypeAdapter.addViewTypeToLayoutMap(VIEW_TYPE_EMPLOYER, R.layout.item_employer);

        mBinding.setLayoutManager(new LinearLayoutManager(this));
        mBinding.setAdapter(mSingleTypeAdapter);

        mSingleTypeAdapter.addAll(EMPLOYEE_LIST);
        mMultiTypeAdapter.add(null, VIEW_TYPE_HEADER);
        mMultiTypeAdapter.addAll(EMPLOYEE_LIST, VIEW_TYPE_EMPLOYEE);
        mMultiTypeAdapter.addAll(EMPLOYER_LIST, VIEW_TYPE_EMPLOYER);

        final List<Object> data = new ArrayList<Object>();
        data.addAll(EMPLOYEE_LIST);
        data.addAll(EMPLOYER_LIST);
        Collections.shuffle(data);
        mMultiTypeAdapter.addAll(data, new MultiTypeAdapter.MultiViewTyper() {
            @Override
            public int getViewType(Object item) {
                if (item instanceof EmployerViewModel) {
                    return VIEW_TYPE_EMPLOYER;
                }

                if (item instanceof EmployeeViewModel) {
                    return VIEW_TYPE_EMPLOYEE;
                }

                return 0;
            }
        });
    }
}
