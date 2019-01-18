package yoshiyukiohara.com.github.original15puzzle;

import android.util.Log;

public class Param {
    private static int imageWidth = 100;

    public void setImageWidth(int imageWidth) {
        this.imageWidth = Param.imageWidth;
        Log.d("DEBUG_TEST", "imageWidthP is " + String.valueOf(imageWidth));
    }

    public int getImageWidth() {
        return this.imageWidth;
    }
}
