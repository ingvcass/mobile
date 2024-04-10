package com.example.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Bitmap bmp1;
    Bitmap bmp2;
    int w, h;
    ImageView img1;
    ImageView img2;
    int brightnessValue = 0;
    SeekBar brightness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*AssetManager asset = getAssets();
        InputStream stream = null;

        try {
            stream = asset.open("lenna.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        bmp1 = BitmapFactory.decodeStream(stream);*/
        bmp1 = BitmapFactory.decodeResource(getResources(), R.drawable.picture);
        w = bmp1.getWidth();
        h = bmp1.getHeight();

        bmp2 = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        img1 = findViewById(R.id.im1);
        img2 = findViewById(R.id.im2);

        img1.setImageBitmap(bmp1);
        img2.setImageBitmap(bmp2);

        brightness = findViewById(R.id.seekBar);
        /*brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                progress = seekBar.getProgress() - 255;
                for(int x = 0; x < w; x++)
                    for (int y = 0; y < h; y++) {
                        int pixelColor = bmp1.getPixel(x, y);
                        int pixelAlpha = Color.alpha(pixelColor);

                        int pixelRed = Color.red(pixelColor) + progress;
                        int pixelGreen = Color.green(pixelColor) + progress;
                        int pixelBlue = Color.blue(pixelColor) + progress;

                        if (pixelRed > 255) {
                            pixelRed = 255;
                        } else if (pixelRed < 0) {
                            pixelRed = 0;
                        }

                        if (pixelGreen > 255) {
                            pixelGreen = 255;
                        } else if (pixelGreen < 0) {
                            pixelGreen = 0;
                        }

                        if (pixelBlue > 255) {
                            pixelBlue = 255;
                        } else if (pixelBlue < 0) {
                            pixelBlue = 0;
                        }

                        int newPixel = Color.argb(
                                pixelAlpha, pixelRed, pixelGreen, pixelBlue);

                        bmp2.setPixel(x, y, newPixel);
                    } return;)*/
            }

    public void copy(View v)
    {
        for (int y = 0; y < h; y++)
        {
            for (int x = 0; x < w; x++)
            {
                bmp2.setPixel(x,y, bmp1.getPixel(x,y));
            }
        }
    }

    public void invert(View v)
    {
        for (int y = 0; y < h; y++)
        {
            for (int x = 0; x < w; x++)
            {
                int c0 = bmp1.getPixel(x,y);
                int r = 255 - Color.red(c0);
                int g = 255 - Color.green(c0);
                int b = 255 - Color.blue(c0);
                int c1 = Color.argb(255,r,g,b);
                bmp2.setPixel(x,y,c1);
            }
        }
    }

    public void grey(View v)
    {
        Canvas canvas = new Canvas(bmp2);
        ColorMatrix ma = new ColorMatrix();
        ma.setSaturation(0);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(ma));
        canvas.drawBitmap(bmp1, 0, 0, paint);
    }

    public void bw(View v)
    {
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int pixel = bmp1.getPixel(x, y);
                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = (pixel >> 0) & 0xff;
                double Y = 0.2126 * r + 0.7152 * g + 0.0722 * b;
                if (Y < 128) {
                    bmp2.setPixel(x, y, Color.BLACK);
                } else {
                    bmp2.setPixel(x, y, Color.WHITE);
                }
            }
        }
    }
}