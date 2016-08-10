package com.example.administrator.yicheng.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Jensen on 2016/8/10.
 */
public class NetWorkUtils {
    /**
      * 网络是否可用
        *
        * @param activity
       * @return
        */
    public static boolean isNetworkAvailable(Context context) {
               ConnectivityManager connectivity = (ConnectivityManager) context
                     .getSystemService(Context.CONNECTIVITY_SERVICE);
               if (connectivity == null) {

               } else {
                 NetworkInfo[] info = connectivity.getAllNetworkInfo();
                  if (info != null) {
                            for (int i = 0; i < info.length; i++) {
                                 if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                                          return true;
                                     }
                             }
                     }
               }
              return false;
           }

    }

