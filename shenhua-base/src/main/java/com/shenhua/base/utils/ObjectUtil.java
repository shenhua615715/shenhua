package com.shenhua.base.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Object类型转换
 * Created by liuye on 2018/5/1.
 */
public class ObjectUtil {

    public static <E> List<E> iterableToList(Iterable<E> iterable) {
        if(iterable instanceof List) {
            return (List<E>) iterable;
        }
        ArrayList<E> list = new ArrayList<E>();
        if(iterable != null) {
            for(E e: iterable) {
                list.add(e);
            }
        }
        return list;
    }

    /**
     * 根据input获取返回的日期
     * @param input
     * @return
     */
    public static Date getDateValue(Object input){
        if (input == null){
            return null;
        }else{
            if (input instanceof Date){
                return (Date)input;
            }
        }
        return null;
    }
    /**
     * 根据input获取返回的整型值
     * @param input
     * @param defaultValue 默认值
     * @return
     */
    public static int getIntValue(Object input, int defaultValue)
    {
        if(input==null)
        {
            return defaultValue;
        }
        else
        {
            if(input instanceof String)
            {
                return Integer.parseInt((String)input);
            }else if(input instanceof Integer)
            {
                return ((Integer) input).intValue();
            }
            else if(input instanceof Date)
            {
                return (int)((Date)input).getTime();
            }else if(input instanceof Long)
            {
                return ((Long)input).intValue();
            }else if(input instanceof Double)
            {
                return ((Double)input).intValue();
            }else if(input instanceof Float)
            {
                return ((Float)input).intValue();
            }else if(input instanceof BigDecimal)
            {
                return ((BigDecimal)input).intValue();
            }
            else
            {
                return defaultValue;
            }
        }
    }
    /**
     * 根据input获取返回的整型值
     * @param input
     * @return
     */
    public static int getIntValue(Object input)
    {
        return getIntValue(input, 0);
    }
    /**
     * 根据input获取返回的双精度值
     * @param input
     * @param defaultValue 默认值
     * @return
     */
    public static double getDoubleValue(Object input, double defaultValue)
    {
        if(input==null)
        {
            return defaultValue;
        }
        else
        {
            if(input instanceof String)
            {
                return Double.parseDouble((String)input);
            }else if(input instanceof Integer)
            {
                return new Double((Integer)input);
            }
            else if(input instanceof Date)
            {
                return new Double(((Date)input).getTime());
            }else if(input instanceof Long)
            {
                return new Double((Long)input);
            }else if(input instanceof Double)
            {
                return ((Double)input);
            }else if(input instanceof Float)
            {
                return new Double((Float)input);
            }
            else if(input instanceof BigDecimal)
            {
                return ((BigDecimal)input).doubleValue();
            }else
            {
                return defaultValue;
            }
        }
    }
    /**
     * 根据input获取返回的双精度值
     * @param input
     * @return
     */
    public static double getDoubleValue(Object input)
    {
        return getDoubleValue(input, 0d);
    }
    /**
     * 根据input获取返回的长整形值
     * @param input
     * @param defaultValue 默认值
     * @return
     */
    public static long getLongValue(Object input, long defaultValue)
    {
        if(input==null)
        {
            return defaultValue;
        }
        else
        {
            if(input instanceof String)
            {
                return Long.parseLong((String)input);
            }else if(input instanceof Integer)
            {
                return new Long((Integer)input);
            }
            else if(input instanceof Date)
            {
                return ((Date)input).getTime();
            }else if(input instanceof Long)
            {
                return (Long)input;
            }else if(input instanceof Double)
            {
                return ((Double)input).longValue();
            }else if(input instanceof Float)
            {
                return ((Float)input).longValue();
            }
            else if(input instanceof BigDecimal)
            {
                return ((BigDecimal)input).longValue();
            }
            else
            {
                return defaultValue;
            }
        }
    }
    /**
     * 根据input获取返回的长整形值
     * @param input
     * @return
     */
    public static long getLongValue(Object input)
    {
        return getLongValue(input, 0);
    }
    /**
     * 根据input获取返回的字符串值
     * @param input
     * @param defaultValue
     * @return
     */
    public static String getStringValue(Object input, String defaultValue)
    {
        if(input==null)
        {
            return defaultValue;
        }
        else
        {
            if(input instanceof String)
            {
                String temp = (String)input;
                if(StringUtils.isEmpty(temp))
                {
                    temp = defaultValue;
                }
                return temp;
            }else if(input instanceof Integer)
            {
                return ""+input;
            }
            else if(input instanceof Long)
            {
                return ""+input;
            }
            else if(input instanceof Double)
            {
                return ""+input;
            }
            else if(input instanceof Float)
            {
                return ""+input;
            }
            else if(input instanceof Date)
            {
                return ""+((Date)input).getTime();
            }
            else
            {
                return defaultValue;
            }
        }
    }
    /**
     * 根据input获取返回的字符串值
     * @param input
     * @return
     */
    public static String getStringValue(Object input)
    {
        return getStringValue(input, "");
    }

    public static String toString(Object o){
        if(o==null){
            return "";
        }
        return o.toString();
    }

    public static Integer toInteger(Object o){
        if(o==null){
            return null;
        }else{
            String s = o.toString();
            if (s != null && !"".equals(s.trim())) {
                try {
                    return Integer.parseInt(s);
                } catch (Exception e) {
                    return null;
                }
            }
            return null;
        }
    }

    public static int toint(Object o){
        if(o==null){
            return 0;
        }else{
            String s = o.toString();
            if (s != null && !"".equals(s.trim())) {
                try {
                    return Integer.parseInt(s);
                } catch (Exception e) {
                    return 0;
                }
            }
            return 0;
        }
    }

    public static Long toLong(Object o){
        if(o==null){
            return null;
        }else{
            String s = o.toString();
            if (s != null && !"".equals(s.trim())) {
                try {
                    return Long.parseLong(s);
                } catch (Exception e) {
                    return null;
                }
            }
            return null;
        }
    }

    public static long tolong(Object o){
        if(o==null){
            return 0;
        }else{
            String s = o.toString();
            if (s != null && !"".equals(s.trim())) {
                try {
                    return Long.parseLong(s);
                } catch (Exception e) {
                    return 0;
                }
            }
            return 0;
        }
    }

    public static Double toDouble(Object o){
        if(o==null){
            return null;
        }else{
            String s = o.toString();
            if (s != null && !"".equals(s.trim())) {
                try {
                    return Double.parseDouble(s);
                } catch (Exception e) {
                    return null;
                }
            }
            return null;
        }
    }

    public static double todouble(Object o){
        if(o==null){
            return 0;
        }else{
            String s = o.toString();
            if (s != null && !"".equals(s.trim())) {
                try {
                    return Double.parseDouble(s);
                } catch (Exception e) {
                    return 0;
                }
            }
            return 0;
        }
    }

    public static Float toFloat(Object o){
        if(o==null){
            return null;
        }else{
            String s = o.toString();
            if (s != null && !"".equals(s.trim())) {
                try {
                    return Float.parseFloat(s);
                } catch (Exception e) {
                    return null;
                }
            }
            return null;
        }
    }
    public static float tofloat(Object o){
        if(o==null){
            return 0;
        }else{
            String s = o.toString();
            if (s != null && !"".equals(s.trim())) {
                try {
                    return Float.parseFloat(s);
                } catch (Exception e) {
                    return 0;
                }
            }
            return 0;
        }
    }
    /**
     * 数字转字符串,如果num<=0 则输出"";
     *
     * @param num
     * @return
     */
    public static String numberToString(Object num) {
        if (num == null) {
            return null;
        } else if (num instanceof Integer && (Integer) num > 0) {
            return Integer.toString((Integer) num);
        } else if (num instanceof Long && (Long) num > 0) {
            return Long.toString((Long) num);
        } else if (num instanceof Float && (Float) num > 0) {
            return Float.toString((Float) num);
        } else if (num instanceof Double && (Double) num > 0) {
            return Double.toString((Double) num);
        } else {
            return "";
        }
    }


    /**
     * 字符串转float 如果异常返回0.00
     *
     * @param s
     *            输入的字符串
     * @return 转换后的float
     */
    public static Float toFloat(String s) {
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return new Float(0);
        }
    }

    public static int toInt(String s) {
        if (s != null && !"".equals(s.trim())) {
            try {
                return Integer.parseInt(s);
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    public static long toLong(String s) {
        try {
            if (s != null && !"".equals(s.trim()))
                return Long.parseLong(s);
        } catch (Exception exception) {
        }
        return 0L;
    }

    public static void main(String[] args) {

    }
}
