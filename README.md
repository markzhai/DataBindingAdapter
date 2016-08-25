# DataBinding RecyclerViewAdapter

Super simple RecyclerView adapter using Data Binding Technology.

# Get Started

This library provide two types of RecyclerView.Adapter.

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

Both have common manipulation methods, like `add`, `addAll`, `remove`, `clear`, `set`, etc.

# Contributors

- [markzhai](https://github.com/markzhai)
- [nimengbo](https://github.com/nimengbo)

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