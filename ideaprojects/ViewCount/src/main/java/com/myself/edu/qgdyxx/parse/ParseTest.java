package com.myself.edu.qgdyxx.parse;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:38 2017/11/7
 * @Email:tao8.wang@changhong.com
 */
public class ParseTest {
    public static void main(String[] args) throws InterruptedException {
//        AbstractParse parse = new ZTreeParse("D:zuzhijigou.txt");
        AbstractParse parse = new ZTreeParse("D:DMTzuzhijigou.txt");
        parse.doParse();
//        Thread.sleep(3000);
//        parse = new DangyaunInfoParse("D:失联dangyuanInfo.txt");
//        parse.doParse();
//        Thread.sleep(3000);
//        parse.setFile("D:dangyuanInfo.txt");
//        parse.doParse();
    }

    public static void main2(String[] args) {
//        String file = "D:dangyuanInfo2_";
        //失联党员的数据和上面的数据全部重复，不执行
//        String file = "D:dangyuanInfonotconnect2_";
        String file = "D:dyzt_";
        AbstractParse parse = new DangyaunInfoParse();
        int count = 0;
        for (int i = 1; i < 10; i++) {
            parse.setFile(file + i + ".txt");
            int c = (int) parse.doParse();
            count += c;
        }
        System.out.println("一共处理了< " + count + " >条数据");
    }

    public static void main1(String[] args) {
        String file = "D:childztree_";
        AbstractParse parse = new ZTreeParse();
        int count = 0;
        for (int i = 0; i < 11; i++) {
            parse.setFile(file + i + ".txt");
            int c = (int) parse.doParse();
            count += c;
        }
        System.out.println("一共处理了< " + count + " >条数据");
    }
}
