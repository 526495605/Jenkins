package com.ykyy.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Util {
    public static final String LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	//转换数据库时间格式
	public static SimpleDateFormat TIMEFORMAT_SECONDS=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String genRandom(int length) {
//		String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String str = "0123456789";
        int range = str.length();
        Random random = new Random();  
        StringBuffer sb = new StringBuffer();  
        for(int i = 0 ; i < length; ++i){  
            int number = random.nextInt(range);//[0,62)
            sb.append(str.charAt(number));  
        }  
        return sb.toString(); 
	}

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatDate(Date date){
        return sdf.format(date);
    }
    
    /**
     * 产生当前时间
     * @return
     */
    public static String getNowTime(){
    	Date date = new Date();
    	return TIMEFORMAT_SECONDS.format(date);
    }

    public static boolean isValidDate(String str, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static int[] getSequence(int no) {
        int[] sequence = new int[no];
        for(int i = 0; i < no; i++){
            sequence[i] = i;
        }
        Random random = new Random();
        for(int i = 0; i < no; i++){
            int p = random.nextInt(no);
            int tmp = sequence[i];
            sequence[i] = sequence[p];
            sequence[p] = tmp;
        }
        random = null;
        return sequence;
    }

    /*
    去重集合中的重复项
     */
    public static void removeDuplicate(List arlList)
    {
        HashSet h = new HashSet(arlList);
        arlList.clear();
        arlList.addAll(h);
    }

    public static void main(String[] args) {
        Integer penid= Integer.parseInt("1232423423123".replaceAll("12324",""));
        System.out.print(penid);
    }
}
