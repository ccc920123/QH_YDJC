package com.scxd.lawqinghai.utils;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;
/**
 * @类名: ${type_name}
 * @功能描述:
 * @作者: ${user}
 * @时间: ${date}
 * @最后修改者:
 * @最后修改内容:
 */
public class SpeechUtils {

    private Context context;


    private static final String TAG = "SpeechUtils";
    private static SpeechUtils singleton;

    private TextToSpeech textToSpeech; // TTS对象

    public static SpeechUtils getInstance(Context context) {
        if (singleton == null) {
            synchronized (SpeechUtils.class) {
                if (singleton == null) {
                    singleton = new SpeechUtils(context);
                }
            }
        }
        return singleton;
    }

    public SpeechUtils(final Context context) {
        this.context = context;
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);
                    if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE && result != TextToSpeech.LANG_AVAILABLE){
                        ToastUtils.showToast(context, "TTS暂时不支持这种语音的朗读！");
                    } else {
                        textToSpeech.setLanguage(Locale.ENGLISH);
                        textToSpeech.setPitch(1.0f);// 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
                        textToSpeech.setSpeechRate(1.0f);
                    }
                }
            }
        });
    }

    public void speakText(String text) {
        if (textToSpeech != null) {
            textToSpeech.speak(text,
                    TextToSpeech.QUEUE_FLUSH, null);
        }

    }
}