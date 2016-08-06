package com.example.administrator.yicheng.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jensen on 2016/8/6.
 */
public class CityContentBean {

    /**
     * result : true
     * data : [{"msgid":"41754","docid":"e751fa4553c15cfcb69cf06be0d8e3fc","channelid":"1250101","title":"薯片吃腻了，来点玉米片怎么样？","description":"虽然在中国，玉米片还算是小众零食，但在北美地区，它的流行程度简直可与薯片相匹敌。美国最流行的咸味小零食中，薄脆饼干和薯片分居第一第二，而玉米片则紧随其后，位于第三。","author":"企鹅吃喝指南","images":"https://www.cityfun.me/Public/img/yunying/shenhe/20160729/449dc3f22cfa53149cb7983fd1be9cbe.jpg","tags":"企鹅吃喝指南","mtime":"1469799834","sendtime":"1470204000","st":"1","stype":0,"summary":"https://www.cityfun.me/show/dailybj?&docid=e751fa4553c15cfcb69cf06be0d8e3fc&pf=9&channelid=1250101","top_type":"125","top_name":"寻味","code":"0","number":581},{"msgid":"41186","docid":"de3b715ea91cda790d729b0cf46d6594","channelid":"1250101","title":"蟹锡兰｜这里的蟹每天从印度洋飞来，别处都吃不到！","description":"这家店里藏着5253公里外印度洋国度的两样珍宝","author":"二黑 / 北京吃货小分队","images":"https://www.cityfun.me/Public/img/yunying/shenhe/20160720/dc35018868f58a5e6bd42dc58fa97385.jpg","tags":"二黑 / 北京吃货小分队","mtime":"1468976014","sendtime":"1469160000","st":"1","stype":0,"summary":"https://www.cityfun.me/show/dailybj?&docid=de3b715ea91cda790d729b0cf46d6594&pf=9&channelid=1250101","top_type":"125","top_name":"寻味","code":"010","number":413},{"msgid":"41127","docid":"be78747e034f8d82ad2d9f46e85a8a3d","channelid":"1250101","title":"Casa Talia｜这里的菜一直被认为缺斤短两，一言不合老板就亮斧子","description":"这家店从开业到现在一直被客人吐槽菜量太小了，小鸟胃都纷纷表示有点吃不饱了","author":"大钢蹦子 / 北京吃货小分队","images":"https://www.cityfun.me/Public/img/yunying/shenhe/20160720/1608c1555dcbd42ac6bd14dbfe4f77c9.jpg","tags":"大钢蹦子 / 北京吃货小分队","mtime":"1468975519","sendtime":"1469073600","st":"1","stype":0,"summary":"https://www.cityfun.me/show/dailybj?&docid=be78747e034f8d82ad2d9f46e85a8a3d&pf=9&channelid=1250101","top_type":"125","top_name":"寻味","code":"010","number":290},{"msgid":"41074","docid":"fa6e17c7b8e5699323fda183527ba278","channelid":"1250101","title":"合集｜干了这杯，就是辣么爽！","description":"在开着空调的凉爽酒吧里，来杯带着火辣滋味的饮品，也是非常过瘾的事。","author":" TimeOut北京","images":"https://www.cityfun.me/Public/img/yunying/shenhe/20160717/78c48b49c8aa2434e546b3821dbacd52.jpg","tags":" TimeOut北京","mtime":"1468734451","sendtime":"1468987200","st":"1","stype":0,"summary":"https://www.cityfun.me/show/dailybj?&docid=fa6e17c7b8e5699323fda183527ba278&pf=9&channelid=1250101","top_type":"125","top_name":"寻味","code":"010","number":320},{"msgid":"41002","docid":"2978de7ceb0ea82687e4a671e005af22","channelid":"1250101","title":"宇宙卷饼｜一不小心成了宇宙中心首席管饱担当！","description":"五道口众多神店的代表之一！宇宙中心人民心中的管饱担当！","author":"大钢蹦子 / 北京吃货小分队","images":"https://www.cityfun.me/Public/img/yunying/shenhe/20160717/e2fa48a0153fb3861c511ae2d62066ed.jpg","tags":"大钢蹦子 / 北京吃货小分队","mtime":"1468726595","sendtime":"1468900800","st":"1","stype":0,"summary":"https://www.cityfun.me/show/dailybj?&docid=2978de7ceb0ea82687e4a671e005af22&pf=9&channelid=1250101","top_type":"125","top_name":"寻味","code":"010","number":221},{"msgid":"40928","docid":"1ddde170d314cef325b2f63add36e719","channelid":"1250101","title":"NUAGE CAFÉ观云｜去这家店，就是浪费时间！","description":"一进门就要待够一下午，还能吃到米其林餐厅主厨的手艺哦","author":" 二黑 / 北京吃货小分队","images":"https://www.cityfun.me/Public/img/yunying/shenhe/20160715/b59687f7bd51b55d22b9d41876b2521c.jpg","tags":" 二黑 / 北京吃货小分队","mtime":"1468554713","sendtime":"1468814400","st":"1","stype":0,"summary":"https://www.cityfun.me/show/dailybj?&docid=1ddde170d314cef325b2f63add36e719&pf=9&channelid=1250101","top_type":"125","top_name":"寻味","code":"010","number":236},{"msgid":"40872","docid":"1976303b682734aff2363f567742504a","channelid":"1250101","title":"合集｜我们替你吃了京城10家海南鸡饭","description":"我们评测了北京十家餐厅的海南鸡饭，让你多了一种\u201c一人食\u201d的最佳选择。","author":"Siukay / 豆瓣一刻","images":"https://www.cityfun.me/Public/img/yunying/shenhe/20160714/654801c7078d4e928224a64c99c63515.jpg","tags":"Siukay / 豆瓣一刻","mtime":"1468486930","sendtime":"1468728000","st":"1","stype":0,"summary":"https://www.cityfun.me/show/dailybj?&docid=1976303b682734aff2363f567742504a&pf=9&channelid=1250101","top_type":"125","top_name":"寻味","code":"010","number":304},{"msgid":"40803","docid":"26c309d9cb27e2f27244bef4bde75b80","channelid":"1250101","title":"幸菓｜南锣深处好评如潮の日式甜品店","description":"一家小巧精致但好评如潮の日式甜品店\u2014\u2014幸菓","author":"LingKK / 遇见美食遇见你","images":"https://www.cityfun.me/Public/img/yunying/shenhe/20160714/f39df6ca96997638458b93e8045e2b3a.jpg","tags":"LingKK / 遇见美食遇见","mtime":"1468482624","sendtime":"1468641600","st":"1","stype":0,"summary":"https://www.cityfun.me/show/dailybj?&docid=26c309d9cb27e2f27244bef4bde75b80&pf=9&channelid=1250101","top_type":"125","top_name":"寻味","code":"010","number":345},{"msgid":"40722","docid":"78a8810cff6f4af1c8423e45995a6c97","channelid":"1250101","title":"合集｜吃了北京这8家，告诉你哪一碗最台湾！","description":"我们为你吃了北京这8碗卤肉饭，告诉你哪碗最台湾。","author":"Siukay /  什么值得吃","images":"https://www.cityfun.me/Public/img/yunying/shenhe/20160714/df072b6a75eacae763297a6510f2b3a5.jpg","tags":"Siukay /  什么值得吃","mtime":"1468464763","sendtime":"1468555200","st":"1","stype":0,"summary":"https://www.cityfun.me/show/dailybj?&docid=78a8810cff6f4af1c8423e45995a6c97&pf=9&channelid=1250101","top_type":"125","top_name":"寻味","code":"010","number":427},{"msgid":"40635","docid":"9cbca6504f1efa795155d9febc2ea4b9","channelid":"1250101","title":"一家人｜京城最美火锅店！没有之一！","description":"颜值与实力担当","author":"遇见美食遇见你","images":"https://www.cityfun.me/Public/img/yunying/shenhe/20160711/a6eb213f14da13731b5e074454182062.jpg","tags":"遇见美食遇见你","mtime":"1468227397","sendtime":"1468468800","st":"1","stype":0,"summary":"https://www.cityfun.me/show/dailybj?&docid=9cbca6504f1efa795155d9febc2ea4b9&pf=9&channelid=1250101","top_type":"125","top_name":"寻味","code":"010","number":385},{"msgid":"40548","docid":"117816e04edb25554eace0a2bf7f8690","channelid":"1250101","title":"合集｜一条给杂食动物者的推送","description":"这绝对是一张很繁杂的推荐单，无法分类的好吃的，适合从周一刷到周五。","author":"邢娜 / 大城小店","images":"https://www.cityfun.me/Public/img/yunying/shenhe/20160711/a6cf981f69c5c16b1e09107848d2ff55.jpg","tags":"邢娜 / 大城小店","mtime":"1468223033","sendtime":"1468382400","st":"1","stype":0,"summary":"https://www.cityfun.me/show/dailybj?&docid=117816e04edb25554eace0a2bf7f8690&pf=9&channelid=1250101","top_type":"125","top_name":"寻味","code":"010","number":340},{"msgid":"40467","docid":"9b2b20fa4a5553809e2e030bcffd2537","channelid":"1250101","title":"蘭棠｜品性如蘭，朴实如棠","description":"蘭 棠，揖让而升，宴于蘭棠，相约在美丽湖畔的蘭棠，邂逅美丽的她","author":"晓松 / 遇见美食遇见你","images":"https://www.cityfun.me/Public/img/yunying/shenhe/20160711/55c8315615a631df9798989af01b7a22.jpg","tags":"晓松 / 遇见美食遇见你","mtime":"1468219006","sendtime":"1468296000","st":"1","stype":0,"summary":"https://www.cityfun.me/show/dailybj?&docid=9b2b20fa4a5553809e2e030bcffd2537&pf=9&channelid=1250101","top_type":"125","top_name":"寻味","code":"010","number":333},{"msgid":"40383","docid":"dd778d38d5246f01c92c9eb99019c91e","channelid":"1250101","title":"合集｜谁说减肥就该节食？不吃饱哪有力气减肥！","description":"俗话说得好，这月不减肥，月月徒伤悲...","author":"北京探店","images":"https://www.cityfun.me/Public/img/yunying/shenhe/20160708/37b4adc2c441c569b917542be7142152.jpg","tags":"北京探店","mtime":"1467973137","sendtime":"1468209600","st":"1","stype":0,"summary":"https://www.cityfun.me/show/dailybj?&docid=dd778d38d5246f01c92c9eb99019c91e&pf=9&channelid=1250101","top_type":"125","top_name":"寻味","code":"010","number":471},{"msgid":"40316","docid":"98a35827d448941f69d9d1818b7dbf2e","channelid":"1250101","title":"合集｜前方几家火爆三里屯的\u201c人气店\u201d，请提前做好排队准备","description":"为了满足友人胃口，小北特意搜罗了几家有特色的馆子，据说吃过终身难忘\u2026\u2026","author":"北京探店","images":"https://www.cityfun.me/Public/img/yunying/shenhe/20160708/d855008eaded1ad30ad7a835de48e1f0.jpg","tags":"北京探店","mtime":"1467972493","sendtime":"1468123200","st":"1","stype":0,"summary":"https://www.cityfun.me/show/dailybj?&docid=98a35827d448941f69d9d1818b7dbf2e&pf=9&channelid=1250101","top_type":"125","top_name":"寻味","code":"010","number":404},{"msgid":"40250","docid":"a5622c6dfdadb83b1463be9c6932eb32","channelid":"1250101","title":"成吉思汗｜让不一样的蒙餐点燃你的味蕾","description":"一家独具特色的蒙古餐厅","author":"北京美食发现","images":"https://www.cityfun.me/Public/img/yunying/shenhe/20160706/4403e4be54f398cd886b1b39d4bb2cbe.jpg","tags":"北京美食发现","mtime":"1467782482","sendtime":"1468036800","st":"1","stype":0,"summary":"https://www.cityfun.me/show/dailybj?&docid=a5622c6dfdadb83b1463be9c6932eb32&pf=9&channelid=1250101","top_type":"125","top_name":"寻味","code":"010","number":289}]
     */

    @SerializedName("result")
    private boolean result;
    /**
     * msgid : 41754
     * docid : e751fa4553c15cfcb69cf06be0d8e3fc
     * channelid : 1250101
     * title : 薯片吃腻了，来点玉米片怎么样？
     * description : 虽然在中国，玉米片还算是小众零食，但在北美地区，它的流行程度简直可与薯片相匹敌。美国最流行的咸味小零食中，薄脆饼干和薯片分居第一第二，而玉米片则紧随其后，位于第三。
     * author : 企鹅吃喝指南
     * images : https://www.cityfun.me/Public/img/yunying/shenhe/20160729/449dc3f22cfa53149cb7983fd1be9cbe.jpg
     * tags : 企鹅吃喝指南
     * mtime : 1469799834
     * sendtime : 1470204000
     * st : 1
     * stype : 0
     * summary : https://www.cityfun.me/show/dailybj?&docid=e751fa4553c15cfcb69cf06be0d8e3fc&pf=9&channelid=1250101
     * top_type : 125
     * top_name : 寻味
     * code : 0
     * number : 581
     */

    @SerializedName("data")
    private List<CityContent> data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<CityContent> getData() {
        return data;
    }

    public void setData(List<CityContent> data) {
        this.data = data;
    }


}
