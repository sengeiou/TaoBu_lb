package com.lianbei.taobu.views;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BitmapUtil {
    /**
     * 获得图片的像素方法
     *
     * @param bitmap
     */

    public static ArrayList<Integer> getPicturePixel(Bitmap bitmap) {

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // 保存所有的像素的数组，图片宽×高
        int[] pixels = new int[width * height];

        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        ArrayList<Integer> rgb=new ArrayList<>();
        for (int i = 0; i < pixels.length; i++) {
            int clr = pixels[i];
            int red = (clr & 0x00ff0000) >> 16; // 取高两位
            int green = (clr & 0x0000ff00) >> 8; // 取中两位
            int blue = clr & 0x000000ff; // 取低两位
//            Log.d("tag", "r=" + red + ",g=" + green + ",b=" + blue);
            int color = Color.rgb(red, green, blue);
            //除去白色和黑色
            if (color!=Color.WHITE && color!=Color.BLACK){
                rgb.add(color);
            }
        }
        return rgb;
    }

    public static int getImageViewColor(Bitmap bitmap){
        ArrayList<Integer> picturePixel = BitmapUtil.getPicturePixel(bitmap);
        //计数相同颜色数量并保存
        HashMap<Integer,Integer> color2=new HashMap<>();
        for (Integer color:picturePixel){
            if (color2.containsKey(color)){
                Integer integer = color2.get(color);
                integer++;
                color2.remove(color);
                color2.put(color,integer);

            }else{
                color2.put(color,1);
            }
        }
        //挑选数量最多的颜色
        Iterator iter = color2.entrySet().iterator();
        int count=0;
        int color=0;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            int value = (int) entry.getValue();
            if (count<value){
                count=value;
                color= (int) entry.getKey();
            }
        }
        if (color==0){
            return 000000;
        }
        return color;
    }


//删选出色值并赋值给imageview
         public static void getImageViewbg(ImageView imageView,Bitmap bitmap){

            ArrayList<Integer> picturePixel = BitmapUtil.getPicturePixel(bitmap);
             //计数相同颜色数量并保存
             HashMap<Integer,Integer> color2=new HashMap<>();
             for (Integer color:picturePixel){
                 if (color2.containsKey(color)){
                     Integer integer = color2.get(color);
                     integer++;
                     color2.remove(color);
                     color2.put(color,integer);

                 }else{
                     color2.put(color,1);
                 }
             }
             //挑选数量最多的颜色
             Iterator iter = color2.entrySet().iterator();
             int count=0;
             int color=0;
             while (iter.hasNext()) {
                 Map.Entry entry = (Map.Entry) iter.next();
                 int value = (int) entry.getValue();
                 if (count<value){
                     count=value;
                     color= (int) entry.getKey();
                 }

             }
             if (color==0){
                 return;
             }
             imageView.setBackgroundColor(color);

//             Bitmap bg = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(),
//                     Bitmap.Config.ARGB_8888);
//             bg.eraseColor(color);//填充颜色
//             imageView.setImageBitmap(bg);
         }


}
