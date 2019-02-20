package com.stardon.lib.videolib.activity.impl;

import android.view.View;
import android.widget.TextView;

/**类名: VideoPreviewImp
 * <br/>功能描述:预览视频接口，主要得到视频预览界面的按钮以及数据
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/11/16
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */



public interface VideoPreviewImp {
    /**方法名称: getVideoLeftBackButton
     * <br/>方法详述:  得到左边返回按钮
     * <br/>参数: View  ImageButton（类型）
     * <br/>返回值:   ImageButton   按钮
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public void getVideoLeftBackButton(View leftButton);

    /**方法名称: getVideoRightBackText
     * <br/>方法详述:  得到右边返回按钮
     * <br/>参数: TextView  TextView（类型）
     * <br/>返回值:   TextView   按钮
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public void getVideoRightBackText(TextView right);

    /**方法名称: getFinshText
     * <br/>方法详述: d得到完成按钮
     * <br/>参数: TextView
     * <br/>返回值:  TextView完成按钮
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public void  getFinshText(TextView finsh);
    /**方法名称: getRetakeText
     * <br/>方法详述: 得到重录按钮
     * <br/>参数: TextView
     * <br/>返回值:  TextView重录按钮
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public  void  getRetakeText(TextView retake);
    /**方法名称: getVideoData
     * <br/>方法详述: 得到视频数据
     * <br/>参数: byte
     * <br/>返回值: byte
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    //    public void getVideoData(byte[] bytes);
    /**方法名称: getVideoData
     * <br/>方法详述: 得到视频数据
     * <br/>参数: String
     * <br/>返回值: String  视频路径
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public void getVideoData(String videoPath);


}
