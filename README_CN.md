# DataBinding RecyclerViewAdapter

从写 adapter 解放出来！ **不需要任何多余的类**

使用 Data Binding 技术的超级简单的 RecyclerView adapter，再也不需要写什么adapter了！
你也无须为此额外创建 ViewHolder 或者 ItemView 这种类。

又多了一个理由使用 Data Binding！

# Get Started

```groovy
dependencies {
    compile 'com.github.markzhai:databinding-rv-adapter:1.0.0'
}
```

该库提供了两种 RecyclerView.Adapter.

`SingleTypeAdapter`:

```Java
SingleTypeAdapter<EmployeeViewModel> adapter = new SingleTypeAdapter<>(this, R.layout.item_single_type);
```

`MultiTypeAdapter`:

```Java
MultiTypeAdapter adapter = new MultiTypeAdapter(this);
adapter.addViewTypeToLayoutMap(VIEW_TYPE_HEADER, R.layout.item_header);
adapter.addViewTypeToLayoutMap(VIEW_TYPE_CODER, R.layout.item_coder);
adapter.addViewTypeToLayoutMap(VIEW_TYPE_BOSS, R.layout.item_boss);
```

唯一的限制是命名规范：你的 ViewModel 在 xml 中的明明必须为 `item`，而你的 事件监听对象 必须被命名为 `presenter`，我认为这对 Data Binding 来说是一种最佳实践。

# 高级用法

## 操作

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

## 事件监听

这个库也提供了一种简单的方式来增加事件监听，并在 `SingleTypeAdapter` 中默认提供了一个整个 view 的对应 ViewModel 点击事件。

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

## 装饰器

有时候，我们想在 `onBindViewHolder` 做些额外的事（比如根据 position 隐藏显示一些东西），所以额外提供了一个 `Decorator` 来让你实现并 set 进去。

```java
public class DemoAdapterDecorator implements BaseViewAdapter.Decorator {

    @Override
    public void decorator(BindingViewHolder holder, int position, int viewType) {
        // you may do something according to position or view type
    }
}

adapter.setDecorator(new DemoAdapterDecorator());
```

# 贡献者

- [markzhai](https://github.com/markzhai)
- [nimengbo](https://github.com/nimengbo)

欢迎提 issues 和 PR 来满足你们的需求。

# 其他使用了 Data Binding 的 adapter 库

- [evant / binding-collection-adapter](https://github.com/evant/binding-collection-adapter)
- [radzio / android-data-binding-recyclerview](https://github.com/radzio/android-data-binding-recyclerview)

他们在某种程度上十分相似且十分强大，可以让你省下不少代码。
然后，两者都存在丧失灵活性的问题，并强制你创建一个莫名其妙的 wrapper。

# 协议

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