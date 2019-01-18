package yoshiyukiohara.com.github.original15puzzle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

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

    // ピースの数変更。設定で変更
    int width = 3;
    int hight = 3;

    //int imageWidth = 100;
    //int imageHeight = 100;

    GridAdapter adapter;


    // ViewSize取得
    public static class DisplaySizeCheck {
        public static Point getViewSize(View View){
            Point point = new Point(0, 0);
            point.set(View.getWidth(), View.getHeight());

            return point;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        RelativeLayout r = (RelativeLayout)findViewById(R.id.relativeLayout);
        Point view = DisplaySizeCheck.getViewSize(r);

        //ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Param param = new Param();
        int imageWidth = view.x / width;
        param.setImageWidth(imageWidth);
        Log.d("DEBUG_TEST", "imageWidthM is " + String.valueOf(imageWidth));

        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        //  横幅のみ画面サイズに変更
        params.width = imageWidth;
        params.height = imageWidth;
        imageView.setLayoutParams(params);

        GridView gridView = findViewById(R.id.gridView);
        gridView.invalidate();
        adapter.notifyDataSetChanged();

    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);


        //ImageView imageView = (ImageView) findViewById(R.id.imageView);



        /*
        // 画像を読みこみ
        Resources resources = getResources();
        Bitmap image = BitmapFactory.decodeResource(resources, R.drawable.icon_number02_black24_00);

        // 画像のサイズを取得
        // 縦
        int imageHeight = image.getHeight();
        // 横
        int imageWidth = image.getWidth();

        // 想定サイズ
        int bestSize = 210;

        // 画像が想定サイズより大きいか、小さい場合想定サイズに伸縮
        if( imageHeight < bestSize || imageHeight > bestSize) {
            image = Bitmap.createScaledBitmap( image, bestSize, bestSize, false );
        }
        */



        // for-each tile名をR.drawable.名前としてintに変換してarrayに登録
        for (String tile: tiles) {
            int imageId = getResources().getIdentifier(tile, "drawable", getPackageName());
            imgList.add(imageId);
        }

        // GridViewのインスタンスを生成
        final GridView gridView = findViewById(R.id.gridView);
        // BaseAdapter を継承したGridAdapterのインスタンスを生成
        // 子要素のレイアウトファイル grid_items.xml を
        // activity_main.xml に inflate するためにGridAdapterに引数として渡す
        adapter = new GridAdapter(this.getApplicationContext(), R.layout.grid_items, imgList, tiles);

        // gridViewにadapterをセット
        gridView.setAdapter(adapter);


        // 空タイルの値を取得
        String emptyTile = tiles[0];
        Log.d("DEBUG_TEST", "emptyTile is " + String.valueOf(emptyTile));
        final int emptyTileId = getResources().getIdentifier(emptyTile, "drawable", getPackageName());
        Log.d("DEBUG_TEST", "emptyTileId is " + String.valueOf(emptyTileId));


        // 左端ピースを移動
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if (emptyTileId == imgList.get(position - width))
                    Log.d("DEBUG_TEST", "上に移動");
                int num1 = imgList.get(position - width);
                Log.d("DEBUG_TEST", "num1 is " + String.valueOf(num1));
                int num2 = imgList.get(position);
                Log.d("DEBUG_TEST", "num2 is " + String.valueOf(num2));
                int temp = num1;
                Log.d("DEBUG_TEST", "temp is " + String.valueOf(temp));

                imgList.set(position - width, num2);
                imgList.set(position, temp);
　               */
            }
        });

        // ピースを移動
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("DEBUG_TEST", String.valueOf(position));

                // タップした行を取得
                int row = position / width;
                Log.d("DEBUG_TEST", "row is " + String.valueOf(row));

                // タップした列を取得
                int column = position % width;
                Log.d("DEBUG_TEST", "column is " + String.valueOf(column));

                // 上に移動
                if (row != 0) {
                    if (emptyTileId == imgList.get(position - width)) {
                        Log.d("DEBUG_TEST", "上に移動");
                        int num1 = imgList.get(position - width);
                        Log.d("DEBUG_TEST", "num1 is " + String.valueOf(num1));
                        int num2 = imgList.get(position);
                        Log.d("DEBUG_TEST", "num2 is " + String.valueOf(num2));
                        int temp = num1;
                        Log.d("DEBUG_TEST", "temp is " + String.valueOf(temp));

                        imgList.set(position - width, num2);
                        imgList.set(position, temp);

                        adapter.notifyDataSetChanged();
                    }
                }

                // 下に移動
                if (row != hight - 1) {
                    if (emptyTileId == imgList.get(position + width)) {
                        Log.d("DEBUG_TEST", "下に移動");
                        int num1 = imgList.get(position + width);
                        Log.d("DEBUG_TEST", "num1 is " + String.valueOf(num1));
                        int num2 = imgList.get(position);
                        Log.d("DEBUG_TEST", "num2 is " + String.valueOf(num2));
                        int temp = num1;
                        Log.d("DEBUG_TEST", "temp is " + String.valueOf(temp));

                        imgList.set(position + width, num2);
                        imgList.set(position, temp);

                        adapter.notifyDataSetChanged();
                    }
                }

                // 左に移動
                if (column != 0) {
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
                if (column != width -1) {
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
