package com.websarva.wings.android.fragmentsample;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuThanksFragment extends Fragment {
    /**
     * このフラグメントが所属するアクティビティオブジェクト。
     */
    private Activity _parentActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // 親クラスのonCreate()の呼び出し。
        super.onCreate(savedInstanceState);
        // 所属するアクティビティオブジェクトを取得。
        _parentActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // フラグメントで表示する画面をXMLファイルからインフレートする。
        View view = inflater.inflate(R.layout.fragment_menu_thanks, container, false);

        // 所属アクティビティからインテントを取得。
        Intent intent = _parentActivity.getIntent();
        // インテントから引き継ぎデータをまとめたもの(Bundleオブジェクト)を取得。
        Bundle extras = intent.getExtras();

        // 注文した定食名と金額変数を用意。引き継ぎデータがうまく取得できなかったときのために""で初期化。
        String menuName = "";
        String menuPrice = "";
        // 引き継ぎデータ(Bundleオブジェクト)が存在すれば...
        if (extras != null) {
            // 定食名と金額を取得
            menuName = extras.getString("menuName");
            menuPrice = extras.getString("menuPrice");
        }
        // 定食名と金額を表示させるTextViewを取得。
        TextView tvMenuName = view.findViewById(R.id.tvMenuName);
        TextView tvMenuPrice = view.findViewById(R.id.tvMenuPrice);
        // TextViewに定食名と金額を表示。
        tvMenuName.setText(menuName);
        tvMenuPrice.setText(menuPrice);

        // 戻るボタンを取得。
        Button bnBackButton = view.findViewById(R.id.btBackButton);
        // 戻るボタンにリスナを登録。
        bnBackButton.setOnClickListener(new ButtonClickListener());

        // インフレートされた画面を戻り値として返す。
        return view;
    }

    /**
     * ボタンが押された時の処理が記述されたメンバクラス。
     */
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            _parentActivity.finish();
        }
    }
}
