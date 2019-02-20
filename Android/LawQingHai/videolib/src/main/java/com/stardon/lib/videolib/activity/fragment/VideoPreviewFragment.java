package com.stardon.lib.videolib.activity.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stardon.lib.videolib.R;
import com.stardon.lib.videolib.activity.impl.VideoPreviewImp;
import com.stardon.lib.videolib.activity.utils.DeleteVideoFile;
import com.stardon.lib.videolib.activity.utils.UniversalMediaController;
import com.stardon.lib.videolib.activity.utils.UniversalVideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 类名: VideoPreviewFragment
 * <br/>功能描述:视频录制完成预览视频界面
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/11/15
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */

public class VideoPreviewFragment extends Fragment implements UniversalVideoView.VideoViewCallback {

    public static final String TAG = VideoPreviewFragment.class.getSimpleName();

    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
    /**
     * 视频路径
     */
    private static String urlPath;
    /**
     * 视频播放
     */
    private UniversalVideoView mVideoView;
    /**
     * 视频控制中心
     */
    private UniversalMediaController mMediaController;
    /**
     * 视频区域
     */
    private View mVideoLayout;
    /**
     * 进度数
     */
    private int mSeekPosition;

    private VideoPreviewImp mCallBack;

    /**
     * 方法名称: newInstance
     * <br/>方法详述: 预览界面入口
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public static Fragment newInstance(String path) {

        urlPath = path;
        return new VideoPreviewFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_videopreview, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallBack = (VideoPreviewImp) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "你未实现VideoPreviewImp接口");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null)//到底保存的SEEK_POSITION_KEY
        {
            mSeekPosition = savedInstanceState.getInt(SEEK_POSITION_KEY);
        }
        mVideoLayout = view.findViewById(R.id.video_layout);
        mVideoView = (UniversalVideoView) view.findViewById(R.id.videoView);
        mMediaController = (UniversalMediaController) view.findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);
        setVideoAreaSize();
        mVideoView.setVideoViewCallback(this);
        if (mSeekPosition > 0) {
            mVideoView.seekTo(mSeekPosition);
        }
        mVideoView.start();
        mMediaController.getVideoRetakeTextView().setOnClickListener(click);
        //得到完成按钮
        mCallBack.getFinshText(mMediaController.getVideoFinishTextView());
        //得到重拍按钮
        mCallBack.getRetakeText(mMediaController.getVideoRetakeTextView());
        //得到左边返回按钮
        mCallBack.getVideoLeftBackButton(mMediaController.getVideoLeftBackTextView());
        //得到右边返回按钮
        mCallBack.getVideoRightBackText(mMediaController.getVideoBackTextView());

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });
        File file = new File(urlPath);
        //        mCallBack.getVideoData(getByte(file));
        mCallBack.getVideoData(urlPath);

    }

    /**
     * 方法名称:
     * <br/>方法详述: 重拍的按钮事件
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            File mRecVedioPath = new File(urlPath);
            DeleteVideoFile.deleteFile(mRecVedioPath);
            getFragmentManager().popBackStack();
        }
    };

    /**
     * 方法名称: setVideoAreaSize
     * <br/>方法详述: 置视频区域大小
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void setVideoAreaSize() {
        mVideoLayout.post(new Runnable() {
            @Override
            public void run() {
                //                int width = mVideoLayout.getWidth();
                //                cachedHeight = (int) (width * 405f / 720f);
                //                cachedHeight = (int) (width * 3f / 4f);
                //                cachedHeight = (int) (width * 9f / 16f);
                //                ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
                //                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                //                videoLayoutParams.height = cachedHeight;
                //                mVideoLayout.setLayoutParams(videoLayoutParams);
                mVideoView.setVideoPath(urlPath);
                mVideoView.requestFocus();
            }
        });
    }

    /**
     * 方法名称: switchTitleBar
     * <br/>方法详述: 标题沉浸
     * <br/>参数: show
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    private void switchTitleBar(boolean show) {
        android.support.v7.app.ActionBar supportActionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        if (supportActionBar != null) {
            if (show) {
                supportActionBar.show();
            } else {
                supportActionBar.hide();
            }
        }
    }


    /**
     * 方法名称:getByte
     * <br/>方法详述: 把一个文件转化为字节
     * <br/>参数: file
     * <br/>返回值:  return bytes;
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public byte[] getByte(File file) {
        byte[] bytes = null;
        try {
            if (file != null) {
                InputStream is = null;

                is = new FileInputStream(file);

                int length = (int) file.length();
                if (length > Integer.MAX_VALUE) // 当文件的长度超过了int的最大值
                {
                    return null;
                }
                bytes = new byte[length];
                int offset = 0;
                int numRead = 0;
                while (offset < bytes.length
                        && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                    offset += numRead;

                }
                // 如果得到的字节长度和file实际的长度不一致就可能出错了
                if (offset < bytes.length) {
                    return null;
                }
                is.close();
            }
        } catch (Exception e) {
            return null;
        }
        return bytes;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SEEK_POSITION_KEY, mSeekPosition);
    }


    @Override
    public void onScaleChange(boolean isFullscreen) {
        //        this.isFullscreen = isFullscreen;
        //        if (isFullscreen) {
        //            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
        //            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        //            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        //            mVideoLayout.setLayoutParams(layoutParams);

        //        }
        //        else {
        //            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
        //            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        //            layoutParams.height = this.cachedHeight;
        //            mVideoLayout.setLayoutParams(layoutParams);
        //            mBottomLayout.setVisibility(View.VISIBLE);
        //        }

        //        switchTitleBar(!isFullscreen);
    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {
        if (mVideoView != null && mVideoView.isPlaying()) {
            mSeekPosition = mVideoView.getCurrentPosition();
            mVideoView.pause();
        }
    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {

    }
}
