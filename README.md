# DataBinding RecyclerViewAdapter

[中文版 README](https://github.com/markzhai/DataBindingAdapter/blob/master/README_CN.md)

Free from writing adapters! **NO MORE CLASSES!**

Super simple RecyclerView adapter using Data Binding Technology, no longer need to write any adapter!
You don't need to write any extra class like ViewHolder or ItemView.

# Get Started

```groovy
dependencies {
    compile 'com.github.markzhai:databinding-rv-adapter:1.0.0'
}
```

This library provide two types of RecyclerView.Adapter.

SingleTypeAdapter:
```Java
SingleTypeAdapter<EmployeeViewModel> adapter = new SingleTypeAdapter<>(this, R.layout.item_single_type);
```

MultiTypeAdapter:
```Java
MultiTypeAdapter adapter = new MultiTypeAdapter(this);
adapter.addViewTypeToLayoutMap(VIEW_TYPE_HEADER, R.layout.item_header);
adapter.addViewTypeToLayoutMap(VIEW_TYPE_CODER, R.layout.item_coder);
adapter.addViewTypeToLayoutMap(VIEW_TYPE_BOSS, R.layout.item_boss);
```

The only limitation is naming convention: your view model in xml should be named `item`, and your presenter should be named `presenter`, which I do think is a good practice.

# Advanced Usage

## Manipulation

```java
singleTypeAdapter.addAll(EMPLOYEE_LIST);
// clear and addAll
singleTypeAdapter.set(EMPLOYEE_LIST);
singleTypeAdapter.add(employee);
// add with specific position
singleTypeAdapter.add(2, employee);

multiTypeAdapter.add(null, VIEW_TYPE_HEADER);
multiTypeAdapter.addAll(EMPLOYEE_LIST, VIEW_TYPE_EMPLOYEE);
multiTypeAdapter.addAll(EMPLOYER_LIST, VIEW_TYPE_EMPLOYER);
multiTypeAdapter.add(2, employee, VIEW_TYPE_EMPLOYEE);

// Like clear + addAll
multiTypeAdapter.set(EMPLOYEE_LIST, VIEW_TYPE_EMPLOYEE);

// both have these methods
adapter.clear();
adapter.remove(2);
```

## Listener Binding

The library also provide an easy way to add listener binding, and by default provide a whole item click listener.

```java

public class DemoAdapterPresenter implements BaseViewAdapter.Presenter {
    public void onItemClick(EmployeeViewModel model) {
        Toast.makeText(MainActivity.this, "employee " + model.name, Toast.LENGTH_SHORT).show();

    }
    public void onItemClick(EmployerViewModel model) {
        Toast.makeText(MainActivity.this, "employer " + model.name, Toast.LENGTH_SHORT).show();
    }
}

multiTypeAdapter.setPresenter(new DemoAdapterPresenter());

singleTypeAdapter.setPresenter(new SingleTypeAdapter.Presenter<EmployeeViewModel>() {

    @Override
    public void onItemClick(EmployeeViewModel model) {
        Toast.makeText(MainActivity.this, model.name, Toast.LENGTH_SHORT).show();
    }
});
```

## Decorator

Sometimes, we may want to do some extra works in `onBindViewHolder`, thus we provide a `Decorator` to let user implement and set it in.

```java
public class DemoAdapterDecorator implements BaseViewAdapter.Decorator {

    @Override
    public void decorator(BindingViewHolder holder, int position, int viewType) {
        // you may do something according to position or view type
    }
}

adapter.setDecorator(new DemoAdapterDecorator());
```

# Contributors

- [markzhai](https://github.com/markzhai)
- [nimengbo](https://github.com/nimengbo)

Welcome for issues and PR to fulfill your own features.

# You may have interest in

- [evant / binding-collection-adapter](https://github.com/evant/binding-collection-adapter)
- [radzio / android-data-binding-recyclerview](https://github.com/radzio/android-data-binding-recyclerview)

They are somehow likely and powerful, enable you to save codes.
However, both of them have the problems that lose flexibility and force you to create something like a wrapper which is annoyed.

# License

    Copyright (C) 2016 MarkZhai (http://zhaiyifan.cn).

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.