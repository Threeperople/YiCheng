package com.example.administrator.yicheng.utils;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.litesuits.orm.db.model.ConflictAlgorithm;


import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public class LiteOrmUtils {

    private static LiteOrm liteOrm;

    public static void creatLiteOrm(Context context, String liteormName){
        String DB_NAME=liteormName+".db";
        if(liteOrm==null){
            liteOrm = LiteOrm.newSingleInstance(context, DB_NAME);
        }
    }
    public static LiteOrm getLiteOrm(){
        return liteOrm;
    }
    /**
     * 保存一条记录/更新
     * @param t
     */
    public static <T> void save(T t){
        liteOrm.save(t);
    }


    /**
     * 插入一条记录
     * @param t
     */
    public static <T> void insert(T t){
        liteOrm.insert(t);
    }
    /**
     * 仅在以存在时更新
     * @param t
     */
    public static <T> void update(T t){
        liteOrm.update(t, ConflictAlgorithm.Replace);
    }

    /**
     * 插入所有记录
     * @param list
     */
    public static <T> void insertAll(List<T> list){
        liteOrm.save(list);
    }

    /**
     * 查询所有
     * @param cla
     * @return
     */
    public static <T> List<T> getQueryAll(Class<T> cla){
        return liteOrm.query(cla);
    }

    /**
     * 查询  某字段 等于 Value的值
     * @param cla
     * @param field
     * @return
     */
    public static <T> List<T> getQueryByWhere(Class<T> cla,String field,String[] args){
        return liteOrm.<T>query(new QueryBuilder(cla).where(field + "=?",args));
    }

    public static <T> void deleteWhere(Class<T> cla,String field,String [] value){
        liteOrm.delete(new WhereBuilder(cla).where(field + "=?", value));
    }





    /**
     * 删除所有
     * @param cla
     */
    public static <T> void deleteAll(Class<T> cla){
        liteOrm.deleteAll(cla);
    }




    public static <T> void updateALL(List<T> list){
        liteOrm.update(list);
    }



}
