package yoshiyukiohara.com.github.original15puzzle;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 表示する画像の名前（拡張子無し）
    private String tiles[] = {
            "icon_number02_black24_00",
            "icon_number02_green24_01",
            "icon_number02_green24_02",
            "icon_number02_green24_03",
            "icon_number02_green24_04",
            "icon_number02_green24_05",
            "icon_number02_green24_06",
            "icon_number02_green24_07",
            "icon_number02_green24_08"
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
        final GridAdapter adapter = new GridAdapter(this.getApplicationContext(), R.layout.grid_items, imgList, tiles);

        // gridViewにadapterをセット
        gridView.setAdapter(adapter);


        // 空タイルの値を取得
        String emptyTile = tiles[0];
        Log.d("DEBUG_TEST", "emptyTile is " + String.valueOf(emptyTile));
        final int emptyTileId = getResources().getIdentifier(emptyTile, "drawable", getPackageName());
        Log.d("DEBUG_TEST", "emptyTileId is " + String.valueOf(emptyTileId));


        // ピースを移動
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("DEBUG_TEST", String.valueOf(position));

                // タップした行を取得
                int row = 0;
                if (position < 3) {
                    row = 1;
                } else if (position < 6) {
                    row = 2;
                } else if (position < 9) {
                    row = 3;
                }
                Log.d("DEBUG_TEST", "row is " + String.valueOf(row));

                // タップした列を取得
                int column = 0;
                if (position % 3 == 0) {
                    column = 3;
                } else if (position % 3 == 1) {
                    column = 2;
                } else if (position % 3 == 2) {
                    column = 1;
                }
                Log.d("DEBUG_TEST", "column is " + String.valueOf(column));

                // 上に移動
                if (row != 1) {
                    if (emptyTileId == imgList.get(position - 3)) {
                        Log.d("DEBUG_TEST", "上に移動");
                        int num1 = imgList.get(position - 3);
                        Log.d("DEBUG_TEST", "num1 is " + String.valueOf(num1));
                        int num2 = imgList.get(position);
                        Log.d("DEBUG_TEST", "num2 is " + String.valueOf(num2));
                        int temp = num1;
                        Log.d("DEBUG_TEST", "temp is " + String.valueOf(temp));

                        imgList.set(position - 3, num2);
                        imgList.set(position, temp);

                        adapter.notifyDataSetChanged();
                    }
                }

                // 下に移動
                if (row != 3) {
                    if (emptyTileId == imgList.get(position + 3)) {
                        Log.d("DEBUG_TEST", "下に移動");
                        int num1 = imgList.get(position + 3);
                        Log.d("DEBUG_TEST", "num1 is " + String.valueOf(num1));
                        int num2 = imgList.get(position);
                        Log.d("DEBUG_TEST", "num2 is " + String.valueOf(num2));
                        int temp = num1;
                        Log.d("DEBUG_TEST", "temp is " + String.valueOf(temp));

                        imgList.set(position + 3, num2);
                        imgList.set(position, temp);

                        adapter.notifyDataSetChanged();
                    }
                }

                // 左に移動
                if (column != 3) {
                    if (emptyTileId == imgList.get(position - 1)) {
                        Log.d("DEBUG_TEST", "左に移動");
                        int num1 = imgList.get(position - 1);
                        Log.d("DEBUG_TEST", "num1 is " + String.valueOf(num1));
                        int num2 = imgList.get(position);
                        Log.d("DEBUG_TEST", "num2 is " + String.valueOf(num2));
                        int temp = num1;
                        Log.d("DEBUG_TEST", "temp is " + String.valueOf(temp));

                        imgList.set(position - 1, num2);
                        imgList.set(position, temp);

                        adapter.notifyDataSetChanged();
                    }
                }

                // 右に移動
                if (column != 1) {
                    if (emptyTileId == imgList.get(position + 1)) {
                        Log.d("DEBUG_TEST", "右に移動");
                        int num1 = imgList.get(position + 1);
                        Log.d("DEBUG_TEST", "num1 is " + String.valueOf(num1));
                        int num2 = imgList.get(position);
                        Log.d("DEBUG_TEST", "num2 is " + String.valueOf(num2));
                        int temp = num1;
                        Log.d("DEBUG_TEST", "temp is " + String.valueOf(temp));

                        imgList.set(position + 1, num2);
                        imgList.set(position, temp);

                        adapter.notifyDataSetChanged();
                    }
                }

                /*
                int num1 = imgList.get(0);
                int num2 = imgList.get(1);
                int temp = num1;

                imgList.set(0, num2);
                imgList.set(1, temp);

                adapter.notifyDataSetChanged();
                */


                for (int i = 0; i < imgList.size(); ++i)
                {
                    Log.d("DEBUG_TEST", "imageListItem is " + String.valueOf(imgList.get(i)));
                }

            }
        });

    }
}
