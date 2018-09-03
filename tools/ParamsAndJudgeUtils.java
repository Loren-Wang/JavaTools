package tools;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class ParamsAndJudgeUtils {
	private static final String TAG = "ParamsAndJudgeUtils";

    /**
     *    ��double��ʽ��Ϊ135.00�ĸ�ʽ
     * @param num
     * @return
     */
    public static String paramsDoubleto1(Double num){
        if(num != null) {
            DecimalFormat df = new DecimalFormat("###,###.0");
            if (num == 0) {
                return "0.0";
            } else if (num > 0 && num < 1) {
                return "0" + df.format(num);
            } else {
                return df.format(num);
            }
        }else {
            return "0.0";
        }
    }


    /**
     *    ��double��ʽ��Ϊ135.00�ĸ�ʽ
     * @param num
     * @return
     */
    public static String paramsDoubleto2(Double num){
        if(num != null) {
            DecimalFormat df = new DecimalFormat("###,###.00");
            if (num == 0) {
                return "0.00";
            } else if (num > 0 && num < 1) {
                return "0" + df.format(num);
            } else {
                return df.format(num);
            }
        }else {
            return "0.00";
        }
    }

    /**
     *    ��double��ʽ��Ϊ135.000000�ĸ�ʽ
     * @param num
     * @return
     */
    public static String paramsDoubleto6(Double num){
        if(num != null) {
            DecimalFormat df = new DecimalFormat("###,###.000000");
            if (num == 0) {
                return "0.000000";
            } else if (num > 0 && num < 1) {
                return "0" + df.format(num);
            } else {
                return df.format(num);
            }
        }else {
            return "0.000000";
        }
    }




    /**
     *    ��double��ʽ��Ϊ135.00�ĸ�ʽ
     * @param num
     * @return
     */
    public static String paramsDoubletoNumIndex(Double source, Integer num){
        if(num != null && source != null) {
            StringBuffer buffer = new StringBuffer("###,###,###");
            if (num > 0) {
                buffer.append(".");
            }
            for (int i = 0; i < num; i++) {
                buffer.append("0");
            }
            DecimalFormat df = new DecimalFormat(buffer.toString());
            if (source == 0) {
                return "0";
            } else if (source > 0 && source < 1) {
                return "0" + df.format(source);
            } else {
                return df.format(source);
            }
        }else {
            return "0";
        }
    }

    /**
     * ��ȥĩβ��0�ַ�����
     * @param doubleNum ������Ҫ��ʽ����ֵ
     * @param maxDecimalNum �����������ķ�0��С������λ��
     * @return
     */
    public static String clearEndZeroAndParamsDoubletoNumIndex(Double doubleNum, Integer maxDecimalNum){
        String str = paramsDoubletoNumIndex(doubleNum,maxDecimalNum);//�Ƚ��и�ʽ��
        if(str.indexOf(".") > 0){
            str = str.replaceAll("0+?$", "");//ȥ�������0
            str = str.replaceAll("[.]$", "");//�����һλ��.��ȥ��
        }
        return str;
    }

    /**
     * ȥ���س����з�
     * @param str
     * @return
     */
    public static String getStringNoBlank(String str) {
        if(str != null && !"".equals(str)) {
            if (str != null && !"".equals(str)) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(str);
                String strNoBlank = m.replaceAll("");
                return strNoBlank;
            } else {
                return str;
            }
        }else {
            return str;
        }
    }

    /**
     * ��ʽ���ַ������з���������ַ����б�
     * @param content Ҫ��ʽ��������
     * @param regex ������ʽ
     * @return
     */
    public static List<String> getStringToRegexStrings(String content,String regex) {
//    	LogUtils.logI(TAG,content);
    	List<String> list = new ArrayList<>();
    	if(!CheckUtils.isEmpty(content) && !CheckUtils.isEmpty( regex)) {
    		Pattern pattern = Pattern.compile(regex);
    		Matcher matcher = pattern.matcher(content);
    		while(matcher.find()) {
    			list.add(matcher.group());
    		}
    	}
    	return list;
    }
    
    /**
     * ��ʽ��string����Ϊbyte����
     * @param list
     * @return
     */
    public static byte[] paramsStringListToBytes(List<String> list,String divide) {
    	ByteOutputStream byteOutputStream = new ByteOutputStream();
    	Iterator<String> iterator = list.iterator();
    	String string;
    	boolean isHaveDivide = !CheckUtils.isEmpty(divide);
    	while(iterator.hasNext()) {
    		string = iterator.next();
    		if(!CheckUtils.isEmpty(string)) {
    			try {
    				byteOutputStream.write(string.getBytes());
        			byteOutputStream.flush();
        			if(isHaveDivide) {
            			byteOutputStream.write(divide.getBytes());
            			byteOutputStream.flush();
            		}
    			}catch(Exception e) {}
    		}
    	}
    	byte[] bytes = byteOutputStream.getBytes();
    	byteOutputStream.close();
    	byteOutputStream = null;
    	return bytes;
    }


    /**
     * ��ȡ��ǰʱ��ĺ���ֵ
     * @return
     */
    public static Long getMillisecond(){
        return new Date().getTime();
    }
    /**
     * ��ȡ��ǰʱ�����ֵ
     * @return
     */
    public static Long getSecond(){
        return new Date().getTime() / 1000;
    }


    /**
     * yyyy.MM.dd G 'at' hh:mm:ss z �� '2002-1-1 AD at 22:10:59 PSD'
     * yy/MM/dd HH:mm:ss �� '2002/1/1 17:55:00'
     * yy/MM/dd HH:mm:ss pm �� '2002/1/1 17:55:00 pm'
     * yy-MM-dd HH:mm:ss �� '2002-1-1 17:55:00'
     * yy-MM-dd HH:mm:ss am �� '2002-1-1 17:55:00 am'
     * @param pattern
     * @param dateTime
     * @return
     */
    public static String getFormatedDateTime(String pattern, long dateTime) {
        if(pattern == null || "".equals(pattern)){
            return null;
        }
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new Date(dateTime));
    }
    public static String getFormatedDateNowTime(String pattern) {
        if(pattern == null || "".equals(pattern)){
            return null;
        }
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new Date(getMillisecond() + 0));
    }

    /**
     * ��ʽ����ǰʱ�䵽ָ����ʽ�������ظø�ʽ����Ӧ����ֵ
     * @param pattern
     * @return
     */
    public static Long getFormatedNowTimeToMillisecond(String pattern) {
        if(pattern == null || "".equals(pattern)){
            return null;
        }
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        Long time = null;
        try {
            time = sDateFormat.parse(sDateFormat.format(new Date(getMillisecond() + 0))).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     *   ��������ʱ���ú�����
     * @param dateAndTime  ����ʱ�䣺"201104141302"
     * @param dateAndTimeFormat  ����ʱ��ĸ�ʽ��"yyyyMMddhhmm"
     * @return ���غ�����
     */
    public static  long getMillisecond(String dateAndTime, String dateAndTimeFormat){
        if(dateAndTime == null || "".equals(dateAndTime) || dateAndTimeFormat == null || "".equals(dateAndTimeFormat)){
            return 0l;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateAndTimeFormat);
        Long millionSeconds = 0l;
        try {
            millionSeconds = sdf.parse(dateAndTime).getTime();//����
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return millionSeconds;
    }
    /**
     *   ��������ʱ��������
     * @param dateAndTime  ����ʱ�䣺"201104141302"
     * @param dateAndTimeFormat  ����ʱ��ĸ�ʽ��"yyyyMMddhhmm"
     * @return ��������
     */
    public static  long getSecond(String dateAndTime, String dateAndTimeFormat){
        return getMillisecond(dateAndTime,dateAndTimeFormat) / 1000;
    }




    /**
     * �������������жϸ�����Ƿ������꣬���򷵻�true
     */
    public static  boolean isLeapYear(Integer year){
        if(year != null) {
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * ���������ж�����
     * @param m
     * @param d
     * @return int
     */
    public static String getConstellation(int m, int d){
        final String[] constellationArr = {"ħ����", "ˮƿ��", "˫����", "������", "��ţ��", "˫����", "��з��", "ʨ����", "��Ů��", "�����", "��Ы��", "������", "ħ����"};
        final int[] constellationEdgeDay = {20, 18, 20, 20, 20, 21, 22, 22, 22, 22, 21, 21};
        int month = m;
        int day = d;
        if (day <= constellationEdgeDay[month - 1]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArr[month];
        }
        //default to return ħ��
        return constellationArr[11];
    }

    /**
     * ����ת����
     * @param arrays
     * @param <T>
     * @return
     */
    public static <T> List<T> paramesArrayToList(T[] arrays){
        List<T> list = new ArrayList<>();
        if(arrays != null) {
            list.addAll(Arrays.asList(arrays));
        }
        return list;
    }

    /**
     * ��ȡjson�����е�����ͼƬ��ַ
     */
    public static List<String> paramsJsonToImagePaths(String jsonContent){
        List<String> list = new ArrayList<>();
        if(jsonContent != null && !"".equals(jsonContent)){
            String[] splitHttp = jsonContent.split("http");
            String[] split;//ͼƬ·��
            for (int i = 0 ; i < splitHttp.length ; i++){
                split = splitHttp[i].split("\"");
                if(split.length > 0){
                    if(CheckUtils.checkIsImage(split[0])){
                        list.add("http" + split[0]);
                    }
                }
            }
        }
        return list;
    }



    /**
     * ��map������keyֵת�ɼ���
     * @param map
     * @param <T>
     * @return
     */
    public static <K,T> List<K> paramsHashMapKeyToArrayList(Map<K,List<T>> map ){
        List<K> list = new ArrayList<>();
        if(map == null){
            return list;
        }
        Iterator<Map.Entry<K, List<T>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            list.add(iterator.next().getKey());
        }
        return list;
    }

    /**
     * ��ȡ����·�������һ���ļ�������
     * @param absolutePath
     * @return
     */
    public static String getLastDirctoryName(String absolutePath){
        if(absolutePath == null){
            return "";
        }
        //�����µģ���ֹ����ʹ��ͬһ��������ڵ��ø÷�����ֵ����Ӱ��
        String path = absolutePath.intern();
        //�ж��ǲ����ļ������ļ��Ļ���ȡ���ļ���·��
        File file = new File(path);
        if(file.isFile()){
            path = file.getParentFile().getAbsolutePath();
        }

        if(path.contains("/")) {
            //ѭ���Ƴ�ĩβ�ġ�/������ֹһ��·�����ж����/��
            while (path.substring(path.lastIndexOf("/")).intern().equals("/")) {
                path = path.substring(0, path.length() - 1);
            }
            path = path.substring(path.lastIndexOf("/") + 1);
        }
        return path;
    }

    /**
     * ��ʽ���ļ���С
     * @param fileSize
     * @return
     */
    public static String paramsFileSize(Long fileSize){
        if(fileSize.compareTo(1024l) < 0){
            return (ParamsAndJudgeUtils.paramsDoubleto2(fileSize.doubleValue()) + "B");
        }else  if(fileSize.compareTo((long) Math.pow(1024,2)) < 0){
            return (ParamsAndJudgeUtils.paramsDoubleto2(fileSize * 1.0 / 1024) + "KB");
        }else  if(fileSize.compareTo((long) Math.pow(1024,3)) < 0){
            return (ParamsAndJudgeUtils.paramsDoubleto2(fileSize * 1.0 / 1024 / 1024) + "MB");
        }else  if(fileSize.compareTo((long) Math.pow(1024,4)) < 0){
            return (ParamsAndJudgeUtils.paramsDoubleto2(fileSize * 1.0 / 1024 / 1024 / 1024) + "GB");
        }else {
            return "0B";
        }
    }

    /**
     * ��ʽ��ʱ�䵽��ֵ(�����ж�ʱ�����10λλ���뼶��)
     * @param time
     * @return
     */
    public static long paramsTimeToSecond(long time){
        if(String.valueOf(time).length() > 10){
            return time / 1000;
        }else {
            return time;
        }
    }

    /**
     * ��ʽ�������ε�ָ��λ��
     * @param time
     * @param num
     * @return
     */
    public static Long paramsLongtoNumIndex(Long time,Integer num){
        if(time != null && num != null){
            int length = String.valueOf(time).length();
            int compareTo = num.compareTo(length);
            if(compareTo == 0){
                return time;
            }else if(compareTo < 0){
                return Double.valueOf(time / Math.pow(10,(length - num))).longValue();
            }else {
                return Double.valueOf(time * Math.pow(10,(length - num))).longValue();
            }
        }
        return 0l;
    }
}
