package com.divinevine.moonwallpaper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shriji on 11/3/18.
 */

public class SampleDataItem {

    public static List<ImageModel> dataItemList;
    public static Map<String, ImageModel> dataItemMap;

    static {
        dataItemList = new ArrayList<>();
        dataItemMap = new HashMap<>();

        addItem(new ImageModel(null,"moon1.jpg"));
        addItem(new ImageModel(null,"moon2.jpg"));
        addItem(new ImageModel(null,"moon3.jpg"));
        addItem(new ImageModel(null,"moon4.jpg"));
        addItem(new ImageModel(null,"moon5.jpg"));
        addItem(new ImageModel(null,"moon6.jpg"));
        addItem(new ImageModel(null,"moon7.jpg"));
        addItem(new ImageModel(null,"moon8.jpg"));
        addItem(new ImageModel(null,"moon9.jpg"));
        addItem(new ImageModel(null,"moon10.jpg"));
        addItem(new ImageModel(null,"moon11.jpg"));
        addItem(new ImageModel(null,"moon12.jpg"));
        addItem(new ImageModel(null,"moon13.jpg"));
        addItem(new ImageModel(null,"moon14.jpg"));
        addItem(new ImageModel(null,"moon15.jpg"));
        addItem(new ImageModel(null,"moon16.jpg"));
        addItem(new ImageModel(null,"moon17.jpg"));
        addItem(new ImageModel(null,"moon18.jpg"));
        addItem(new ImageModel(null,"moon19.jpg"));
        addItem(new ImageModel(null,"moon20.jpg"));
        addItem(new ImageModel(null,"moon21.jpg"));
        addItem(new ImageModel(null,"moon22.jpg"));
        addItem(new ImageModel(null,"moon23.jpg"));
        addItem(new ImageModel(null,"moon24.jpg"));
        addItem(new ImageModel(null,"moon25.jpg"));
        addItem(new ImageModel(null,"moon26.jpg"));
        addItem(new ImageModel(null,"moon27.jpg"));
        addItem(new ImageModel(null,"moon28.jpg"));
        addItem(new ImageModel(null,"moon29.jpg"));
        addItem(new ImageModel(null,"moon30.jpg"));
        addItem(new ImageModel(null,"moon31.jpg"));
        addItem(new ImageModel(null,"moon32.jpg"));
        addItem(new ImageModel(null,"moon33.jpg"));
        addItem(new ImageModel(null,"moon34.jpg"));
        addItem(new ImageModel(null,"moon35.jpg"));
        addItem(new ImageModel(null,"moon36.jpg"));
        addItem(new ImageModel(null,"moon37.jpg"));
        addItem(new ImageModel(null,"moon38.jpg"));
        addItem(new ImageModel(null,"moon39.jpg"));
        addItem(new ImageModel(null,"moon40.jpg"));
        addItem(new ImageModel(null,"moon41.jpg"));
        addItem(new ImageModel(null,"moon42.jpg"));
        addItem(new ImageModel(null,"moon43.jpg"));
        addItem(new ImageModel(null,"moon44.jpg"));
        addItem(new ImageModel(null,"moon45.jpg"));
        addItem(new ImageModel(null,"moon46.jpg"));
        addItem(new ImageModel(null,"moon47.jpg"));
        addItem(new ImageModel(null,"moon48.jpg"));
        addItem(new ImageModel(null,"moon49.jpg"));
        addItem(new ImageModel(null,"moon50.jpg"));
        addItem(new ImageModel(null,"moon51.jpg"));
    }
    private static void addItem(ImageModel item){
        dataItemList.add(item);
        dataItemMap.put(item.getItemId(),item);
    }
}
