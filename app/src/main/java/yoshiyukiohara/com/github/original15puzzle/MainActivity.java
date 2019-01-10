package yoshiyukiohara.com.github.original15puzzle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 表示する画像の名前（拡張子無し）
    private String tiles[] = {
            "icon_number02_black24_01", "icon_number02_black24_02"
    };

    // Resource IDを格納するarray
    private List<Integer> imgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // for-each tile名をR.drawable.名前としてintに変換してarrayに登録
        for (String tile: tiles) {
            int imageId = getResources().getIdentifier(tile, "drawable", getPackageName());
            imgList.add(imageId);
        }

        // GridViewのインスタンスを生成
        GridView gridView = findViewById(R.id.gridView);
        // BaseAdapter を継承したGridAdapterのインスタンスを生成
        // 子要素のレイアウトファイル grid_items.xml を
        // activity_main.xml に inflate するためにGridAdapterに引数として渡す
        GridAdapter adapter = new GridAdapter(this.getApplicationContext(), R.layout.grid_items, imgList, tiles);

        // gridViewにadapterをセット
        gridView.setAdapter(adapter);
    }
}
