package com.scxd.lawqinghai.utils;
/**
 * @function �жϰ�ť�Ƿ��ε��
 * @author chenpan
 * @creattime 2015-6-20 
 */
public class ButtonTools {
    private static long lastClickTime;
/**
 * 
 * @param waitTime  ʱ��
 * �жϰ�ť�Ƿ��ε��
     * @return  true��ʾ�ڶ�ʱ���ڵ�����İ�ť  false��ʾ��ָ����ʱ�������Ч��
 * @return
 * @throws 
 * @throw
 */
    public static boolean isFastDoubleClick(int waitTime) {
        long time = System.currentTimeMillis();   
        if ( time - lastClickTime < waitTime) {   
            return true;   
        }   
        lastClickTime = time;   
        return false;   
    }
}