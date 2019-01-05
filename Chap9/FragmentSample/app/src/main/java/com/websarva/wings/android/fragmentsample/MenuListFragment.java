package com.websarva.wings.android.fragmentsample;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuListFragment extends Fragment {
    /**
     * このフラグメントが所属するアクティビティオブジェクト
     */
    private Activity _parentActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // 親クラスのonCreate()の呼び出し。
        super.onCreate(savedInstanceState);
        // 所属するアクティビティオブジェクトの取得
        _parentActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // フラグメントで表示する画面をXMLファイルからインフレートする。
        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);
        // 画面部品ListViewを取得。
        ListView lvMenu = view.findViewById(R.id.lvMenu);
        // メニューリストを作成。
        List<Map<String, Object>> menuList = createTeishokuList();
        // SimpleAdapterの第4引数fromに使用する定数フィールド。
        String[] from = {"name", "price"};
        // SimpleAdapterの第5引数toに使用する定数フィールド。
        int[] to = {android.R.id.text1, android.R.id.text2};
        // SimpleAdapterを生成。
        SimpleAdapter adapter = new SimpleAdapter(_parentActivity, menuList,
                android.R.layout.simple_list_item_2, from, to);
        // アダプタの登録
        lvMenu.setAdapter(adapter);
        // リスナの登録。
        lvMenu.setOnItemClickListener(new ListItemClickListener());
        // インフレートされた画面を戻り値として返す。
        return view;
    }

    private List<Map<String, Object>> createTeishokuList() {
        // 定食メニューリスト用のListオブジェクトを用意。
        List<Map<String, Object>> menuList = new ArrayList<>();
        // 「から揚げ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "から揚げ定食");
        menu.put("price", "800円");
        menu.put("desc", "若鳥のから揚げにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);
        // 「ハンバーグ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", "850円");
        menu.put("desc", "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);
        // 「焼き魚定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = new HashMap<>();
        menu.put("name", "焼き魚定食");
        menu.put("price", "850円");
        menu.put("desc", "鮭の塩焼きにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        return menuList;
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // タップされた行のデータを取得。SimpleAdapterでは1行分のデータはMap型!
            Map<String, String> item = (Map<String, String>) parent.getItemAtPosition(position);
            // 定食名と金額を取得。
            String menuName = item.get("name");
            String menuPrice = item.get("price");
            // インテントオブジェクトを生成。
            Intent intent = new Intent(_parentActivity, MenuThanksActivity.class);
            // 第2画面に送るデータを格納
            intent.putExtra("menuName", menuName);
            intent.putExtra("menuPrice", menuPrice);
            // 第2画面の起動。
            startActivity(intent);
        }
    }

}
