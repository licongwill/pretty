package cn.pretty.core.util;

import cn.pretty.core.exceptions.UtilException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

/**
 * TODO 数组工具类
 * @author licong
 * @version 1.0
 * @date 2020/5/12 11:12
 */
public class ArrayUtil {
    /* 数组中未找到下标，值为-1 */
    public static final int INDEX_NOT_FOUND = -1;

    /**
     * 判断数组是否为空
     * @param array
     * @param <T>
     * @return
     */
    public static<T> boolean isEmpty(T[] array){return array == null || array.length==0;}

    /**
     * 如果数组为空返回默认值
     * @param array
     * @param defaultArray
     * @param <T>
     * @return
     */
    public static<T> T[] defaultIfEmpty(T[] array,T[] defaultArray){ return isEmpty(array) ? defaultArray :array;}

    /**
     * 判断对象是否为数组
     * @param obj
     * @return
     */
    public static boolean isArray(Object obj){
        if(obj==null){
            //throw new NullPointerException("Object check for isArray is null");
            return false;
        }
        return obj.getClass().isArray();
    }

    /**
     * 判断object是否为空
     * @param array
     * @return
     */
    public static boolean isEmpty(Object array){
        if(array==null){
            return true;
        }else if(isArray(array)){
            return Array.getLength(array) == 0;
        }
        throw new UtilException("Object to provider is not a array");
    }

    /*判断数组是否为空*/

    public static boolean isEmpty(long[] array){return array==null || array.length==0;}

    public static boolean isEmpty(int[] array){return array==null || array.length==0;}

    public static boolean isEmpty(short[] array){return array==null || array.length==0;}

    public static boolean isEmpty(char[] array){return array==null || array.length==0;}

    public static boolean isEmpty(byte[] array){return array==null || array.length==0;}

    public static boolean isEmpty(double[] array){return array == null || array.length == 0;}

    public static boolean isEmpty(float[] array){return array == null || array.length == 0;}

    public static boolean isEmpty(boolean[] array){return array == null || array.length == 0;}

    /*判断数组是否不为空*/
    public static <T> boolean isNotEmpty(T[] array){return array != null && array.length > 0;}

    public static boolean isNotEmpty(Object array){return  !isEmpty(array);}

    public static boolean isNotEmpty(long[] array){return !isEmpty(array);}

    public static boolean isNotEmpty(int[] array){return !isEmpty(array);}

    public static boolean isNotEmpty(short[] array){return !isEmpty(array);}

    public static boolean isNotEmpty(byte[] array){return !isEmpty(array);}

    public static boolean isNotEmpty(char[] array){return !isEmpty(array);}

    public static boolean isNotEmpty(float[] array){return !isEmpty(array);}

    public static boolean isNotEmpty(boolean[] array){return !isEmpty(array);}

    /**
     * 判断数组中是否包含空元素
     * @param array
     * @param <T>
     * @return
     */
    public static <T> boolean hasNull(T... array){
        if(isEmpty(array)){return false;}
        long count = Arrays.stream(array).filter(n -> n == null).count();
        boolean flag = count > 0 ? true : false;
        return flag;
    }


    /**
     * 创建数组
     * @param componentType
     * @param newSize
     * @param <T>
     * @return
     */
    public static <T> T[] newArray(Class<?> componentType,int newSize){
        return (T[])Array.newInstance(componentType,newSize);
    }

    /**
     * 获取数组中组件类型
     * @param array
     * @return
     */
    public static Class<?> getComponentType(Object array){
        return array == null ? null : array.getClass().getComponentType();
    }

    /**
     * 获取数组类型
     * @param componentType
     * @return
     */
    public static Class<?> getArrayType(Class<?> componentType){
        return Array.newInstance(componentType,0).getClass();
    }

    public static Object[] cast(Class<?> type,Object arrayObj){
        if(null ==  arrayObj){
            throw new NullPointerException("Argument [arrayObj] is null !");
        }

        if(!arrayObj.getClass().isArray()){
            throw new IllegalArgumentException("Argument [arrayObj] is not array !");
        }

        if(null == type){
            return (Object[]) arrayObj;
        }

        final Class<?> componentType = type.isArray() ? type.getComponentType() : type ;
        final Object[] array = (Object[]) arrayObj;
        final Object[] result = ArrayUtil.newArray(componentType,array.length);
        System.arraycopy(array,0,result,0,array.length);
        return result;
    }


    /**
     * 追加数组
     * @param buffer
     * @param newElements
     * @param <T>
     * @return
     */
    public static <T> T[] append(T[] buffer,T... newElements){
        if(isEmpty(buffer)){
            return newElements;
        }
        return insert(buffer, buffer.length, newElements);
    }

    /**
     * 将新元素添加到已有数组中<br>
     * 添加新元素会生成一个新的数组，不影响原数组
     *
     * @param <T> 数组元素类型
     * @param array 已有数组
     * @param newElements 新元素
     * @return 新数组
     */
    @SafeVarargs
    public static <T> Object append(Object array, T... newElements) {
        if(isEmpty(array)) {
            return newElements;
        }
        return insert(array, length(array), newElements);
    }

    /**
     * 更新或者新增
     * @param buffer
     * @param index
     * @param value
     * @param <T>
     * @return
     */
    public static <T> T[] setOrAppend(T[] buffer, int index, T value) {
        if(index < buffer.length) {
            Array.set(buffer, index, value);
            return buffer;
        }else {
            return append(buffer, value);
        }
    }

    /**
     * 将元素值设置为数组的某个位置，当给定的index大于数组长度，则追加
     *
     * @param array 已有数组
     * @param index 位置，大于长度追加，否则替换
     * @param value 新值
     * @return 新数组或原有数组
     * @since 4.1.2
     */
    public static Object setOrAppend(Object array, int index, Object value) {
        if(index < length(array)) {
            Array.set(array, index, value);
            return array;
        }else {
            return append(array, value);
        }
    }

    /**
     * 新增数组元素
     * @param buffer
     * @param index
     * @param newElements
     * @param <T>
     * @return
     */
    public static <T> T[] insert(T[] buffer, int index, T... newElements) {
        return (T[]) insert((Object)buffer, index, newElements);
    }

    /**
     * 新增数组元素
     * @param array
     * @param index
     * @param newElements
     * @param <T>
     * @return
     */
    public static <T> Object insert(Object array,int index,T... newElements){
        if(isEmpty(newElements)){
            return array;
        }
        if(isEmpty(array)){
            return newElements;
        }

        final int len = length(array);
        if(index < 0){
            index = (index % len) + len;
        }
        final T[] result = newArray(array.getClass().getComponentType(),Math.max(len,index)+newElements.length);
        System.arraycopy(array,0,result,0,Math.min(len,index));
        System.arraycopy(newElements, 0, result, index, newElements.length);
        if (index < len) {
            System.arraycopy(array, index, result, index + newElements.length, len - index);
        }
        return result;
    }

    public static int length(Object array){
        if(null == array){
            return 0;
        }
        return Array.getLength(array);
    }

    public static <T> T[] resize(T[] data,int newSize,Class<?> componentType){
        if(newSize < 0){
            return data;
        }
        final T[] newArray = newArray(componentType,newSize);

        if(newSize > 0 && isNotEmpty(data)){
            System.arraycopy(data,0,newArray,0,Math.min(data.length,newSize));
        }
        return newArray;
    }
}
