package com.scxd.lawqinghai.widget.checkpic;

import android.app.Activity;
import android.content.Context;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.scxd.lawqinghai.common.Common;

import java.io.File;

/**
 * 描述：多图选择框架 PictureSelector的初始化配置
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:64550
 * 时间： 2018/8/15 9:35
 * 修改人：
 * 修改时间：
 */

public class PictureSelectorConfig {

    /**
     * 初始化多图选择的配置
     *
     * @param activity
     * @param maxTotal
     */
    public static void initMultiConfig(Activity activity, int maxTotal) {
        // 进入相册 以下是例子：用不到的api可以不写
        String path=Common.PHONE_PATH + "photo/";
        String paths=Common.PIC_PATH + "cache/";

        String  newPath= paths.substring(paths.indexOf("/Android/data/"),paths.length());

        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .maxSelectNum(maxTotal)// 最大图片选择数量 int
                .minSelectNum(0)// 最小选择数量 int
                .imageSpanCount(3)// 每行显示个数 int
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .previewVideo(false)// 是否可预览视频 true or false
                .enablePreviewAudio(false) // 是否可播放音频 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)
//                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
//                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath(newPath)// 自定义拍照保存路径,可不填   测试没效果  图片在/data/data/com.scxd.lawqinghai/cache/luban_disk_cache/Luban_66450758.jpg
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
//                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
//                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.THIRD_GEAR、Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
//                .compressMaxKB(1024)//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效 int 
//                .compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效  int 
                .glideOverride(160, 160)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .withAspectRatio()// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                .hideBottomControls()// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false)// 是否显示gif图片 true or false
//                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(false)// 是否圆形裁剪 true or false
//                .showCropFrame()// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
//                .showCropGrid()// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .openClickSound(false)// 是否开启点击声音 true or false
//                .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> list
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .minimumCompressSize(200)// 小于100kb的图片不压缩
                .compressSavePath(path)
// .cropCompressQuality()// 裁剪压缩质量 默认90 int
//                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int 
//                .rotateEnabled(false) // 裁剪是否可旋转图片 true or false
//                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
//                .videoQuality()// 视频录制质量 0 or 1 int
//                .videoSecond()// 显示多少秒以内的视频or音频也可适用 int 
//                .recordVideoSecond()//视频秒数录制 默认60s int
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code


    }

    /**
     * 删除缓存
     *
     * @param mContext
     */
    public static void deleteCacheDirFile(Context mContext) {

        PictureFileUtils.deleteCacheDirFile(mContext);


    }

    /**
     * 删除文件夹下的文件
     *
     * @param path
     */
    public static void deleteFile(String path) {
        deleteDir(path);

    }

    //删除文件夹和文件夹里面的文件
    public static void deleteDir(final String pPath) {
        File dir = new File(pPath);
        deleteDirWihtFile(dir);
    }

    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
//        dir.delete();// 删除目录本身
    }


    /**
     * 初始化单张图片选择的配置
     *
     * @param activity
     */
//    public static void initSingleConfig(Activity activity) {
//        // 进入相册 以下是例子：用不到的api可以不写
//        PictureSelector.create(activity)
//                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
//                .maxSelectNum(1)// 最大图片选择数量 int
//                .minSelectNum(0)// 最小选择数量 int
//                .imageSpanCount(3)// 每行显示个数 int
//                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
//                .previewImage(true)// 是否可预览图片 true or false
//                .previewVideo(false)// 是否可预览视频 true or false
//                .enablePreviewAudio(false) // 是否可播放音频 true or false
//                .isCamera(true)// 是否显示拍照按钮 true or false
////                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
////                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
////                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
//                .enableCrop(true)// 是否裁剪 true or false
//                .compress(true)// 是否压缩 true or false
//                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.THIRD_GEAR、Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
//                .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
////                .compressMaxKB(500)//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效 int
////                .compressWH(7, 10) // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效  int
//                .glideOverride(130, 130)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
////                .withAspectRatio()// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
//                .isGif(false)// 是否显示gif图片 true or false
//                .freeStyleCropEnabled(false)// 裁剪框是否可拖拽 true or false
//                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
//                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
//                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
//                .openClickSound(false)// 是否开启点击声音 true or false
////                .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> list
//                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
////                .cropCompressQuality()// 裁剪压缩质量 默认90 int
////                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
//                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
//                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
////                .videoQuality()// 视频录制质量 0 or 1 int
////                .videoSecond()// 显示多少秒以内的视频or音频也可适用 int
////                .recordVideoSecond()//视频秒数录制 默认60s int
//                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
//    }

}
