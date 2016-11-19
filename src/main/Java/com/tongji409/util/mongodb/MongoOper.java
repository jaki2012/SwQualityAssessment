package com.tongji409.util.mongodb;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tongji409.domain.SysLogModel;
import com.tongji409.util.log.CE;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Iterator;


public class MongoOper {
    private static MongoDatabase db = null;

    /**
     * 添加文档
     * @Title: addToMongo
     * @Description:
     * @param
     * @return void
     * @throws
     * @author Cogent Cui(brilliantree@126.com)
     * @date 2015年10月12日 上午8:52:02
     */
    public static boolean addToMongo(String collectionname, Document doc){
        boolean tag = true;
        try {
            db = MongoUtil.getDB();

            db.getCollection(collectionname).insertOne(doc);
        } catch (Exception e) {
            tag = false;
            CE.lgErr("执行MongoDB插入", e);
        }
        return tag;
    }


    /**
     * 查询集合所有数据
     * @author Cogent Cui
     * 2015年6月2日 上午9:57:04
     */
    private void findAllFromCollection(){
        db = MongoUtil.getDB();
        FindIterable<Document> iterable = db.getCollection(SysLogModel.LOG_TBNAME_INFO).find();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
        CE.sp("Done");
    }



    /**
     * 筛选集合数据
     * @author Cogent Cui
     * 2015年6月2日 上午9:57:21
     */
    public static JSONArray FilterCollection(Document doc, int pageindex, int pagesize){

        CE.sp("DOC:"+doc);

        final JSONArray ja = new JSONArray();

        db = MongoUtil.getDB();
        FindIterable<Document> iterable = db.getCollection(SysLogModel.LOG_TBNAME_INFO)
                .find(doc).sort(new Document("LOG_TIME", -1)).skip(pagesize*pageindex).limit(pagesize);
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document doc) {
                Iterator<String> keys = doc.keySet().iterator();
                JSONObject jo = new JSONObject();
                while (keys.hasNext()) {
                    String key = keys.next();
                    jo.put(key, doc.get(key));
                }
                ja.add(jo);
            }
        });
        return ja;
    }


    /**
     * 获取唯一文档
     * @param filter
     * @return
     */
    public static JSONObject FilterUniqueDoc(Document filter){

        CE.sp("DOC:" + filter);
        final JSONObject jo = new JSONObject();

        db = MongoUtil.getDB();
        FindIterable<Document> iterable = db.getCollection(SysLogModel.LOG_TBNAME_INFO).find(filter);

//		MongoCursor<Document> mongoCursor = iterable.iterator();
//		System.out.println(mongoCursor.hasNext());
//		while(mongoCursor.hasNext()){
//			System.out.println(1);
//			System.out.println(mongoCursor.next());
//		}

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document doc) {
                Iterator<String> keys = doc.keySet().iterator();

                while (keys.hasNext()) {
                    String key = keys.next();
                    jo.put(key, doc.get(key));
                }
            }
        });
        return jo;
    }



    /**
     * 过滤条目数量
     * @Title: FilterCollectionCount
     * @Description:
     * @param @return
     * @return long
     * @throws
     * @author Cogent Cui(brilliantree@126.com)
     * @date 2015年10月16日 下午4:04:09
     */
    public static long FilterCollectionCount(Document doc){
        db = MongoUtil.getDB();
        return db.getCollection(SysLogModel.LOG_TBNAME_INFO).count(doc);
    }


    /**
     * 删除日志
     * @Title: removeFromCollection
     * @Description:
     * @param
     * @return void
     * @throws
     * @author Cogent Cui(brilliantree@126.com)
     * @date 2015年10月8日 下午3:47:40
     */
    private void removeFromCollection(){
        db = MongoUtil.getDB();
        db.getCollection(SysLogModel.LOG_TBNAME_INFO).deleteMany(new Document());
        CE.sp("Done");
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        MongoOper mg = new MongoOper();
//		mg.FilterCollection();
        CE.sp("Done, time cost "+(System.currentTimeMillis()-start)+"ms");
    }

}
