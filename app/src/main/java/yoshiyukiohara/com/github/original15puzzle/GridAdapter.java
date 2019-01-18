package yoshiyukiohara.com.github.original15puzzle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter {

    class ViewHolder {
        ImageView imageView;
    }

    private List<Integer> imageList = new ArrayList<>();
    private LayoutInflater inflater;
    private int layoutId;


    /*
    ImageView.setLayoutParams(new LinearLayout.LayoutParams(
    ViewGroup.LayoutParams.MATCH_PARENT,
    ViewGroup.LayoutParams.MATCH_PARENT,
    weight));

    private final int WC = ViewGroup.LayoutParams.;

    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            2f
    );
    myView.setLayoutParams(param)

    int width = getWindowManager().getDefaultDisplay().getWidth() / 3;

    */

    // 引数がMainActivityからの設定と合わせる
    GridAdapter(Context context, int layoutId, List<Integer> iList, String[] tiles) {
        super();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutId = layoutId;
        imageList = iList;

        //Log.d("DEBUG_TEST", String.valueOf(tiles));
    }

    @Override
    public int getCount() {
        // List<String> imgList の全要素数を返す
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        //Log.d("DEBUG_TEST", String.valueOf(position));
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            // main.xml の <GridView .../> に grid_items.xml を inflate して convertView とする
            convertView = inflater.inflate(layoutId, parent, false);
            // ViewHolder を生成
            holder = new ViewHolder();

            holder.imageView = convertView.findViewById(R.id.imageView);


            ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();

            Param param = new Param();
            param.getImageWidth();
            Log.d("DEBUG_TEST", "imageWidth is " + String.valueOf(param.getImageWidth()));

            //  横幅のみ画面サイズに変更
            params.width = param.getImageWidth();
            params.height = param.getImageWidth();
            holder.imageView.setLayoutParams(params);



            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //Log.d("DEBUG_TEST", String.valueOf(position));
        holder.imageView.setImageResource(imageList.get(position));



        /*
        // 隣が空ならピースを移動
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEBUG_TEST", String.valueOf(imageList.get(0)));
                Log.d("DEBUG_TEST", String.valueOf(imageList.get(1)));
                //Log.d("DEBUG_TEST", String.valueOf(position));

                //Log.d("DEBUG_TEST", String.valueOf(imageList.get(0)));

                int num1 = imageList.get(0);
                int num2 = imageList.get(1);
                int temp = num1;

                imageList.set(0, num2);
                imageList.set(1, temp);
            }
        });
        */

        return convertView;
    }
}
