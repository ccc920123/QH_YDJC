package com.stardon.lib.videolib.activity.utils;

import java.io.File;


/**类名: DeleteVideoFile
 * <br/>功能描述:视频删除类
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/11/17
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */


public class DeleteVideoFile {
    /**方法名称: deleteFile
     * <br/>方法详述: 删除
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public static void deleteFile(File file) {
        if (file == null || !file.exists()) {
            return;
        } else {
            file.delete();
        }
    }
}
