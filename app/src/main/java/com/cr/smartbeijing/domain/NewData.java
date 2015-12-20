package com.cr.smartbeijing.domain;

import java.util.ArrayList;

/**
 * ===========================================
 * 版权所有CEIT
 * 作者：程瑞 on 15-12-16 20:57
 * 邮箱：ahchengrui@126.com
 * ==========================================
 */
public class NewData {
    public int retcode;
    public ArrayList<NewsMenuData> data ;

   public  class NewsMenuData{
        public int id;
        public String title;
        public int type;
        public String url;
        public ArrayList<NewsTabData> children;

       @Override
       public String toString() {
           return "NewsMenuData{" +
                   "title='" + title + '\'' +
                   ", children=" + children +
                   '}';
       }
   }
    public class NewsTabData{
        public int id;
        public String title;
        public int type;
        public String url;

        @Override
        public String toString() {
            return "NewsTabData{" +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewData{" +
                "retcode=" + retcode +
                ", data=" + data +
                '}';
    }
}
