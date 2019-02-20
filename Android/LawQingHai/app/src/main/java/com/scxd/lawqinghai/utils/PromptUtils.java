package com.scxd.lawqinghai.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;


import com.scxd.lawqinghai.R;
import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.widget.dialog.BaseDialog;

import java.util.Timer;
import java.util.TimerTask;


/**
 */
public class PromptUtils {
	private static PromptUtils sIntance;
	private static ProgressDialog sProgressDialog;
	private static Timer sTimer;
	private Context mContext = MyApplication.getApplication();
	private static BaseDialog mDialog;

	private PromptUtils() {

	}

	public static PromptUtils getInstance() {
		if (sIntance == null) {
			sIntance = new PromptUtils();
		}
		return sIntance;
	}

	public void setContext(Context context) {
		this.mContext = context;
	}

	public void showPrompts(int textResId) {
		showPrompts(mContext.getString(textResId));
	}

	public void showPrompts(String msg) {
		showAlertDialog(mContext, msg, null);
	}

	public void showPrompts(Context context, String msg) {
		showAlertDialog(context, msg, null);
	}

	public void showPrompts(int textResId, DialogInterface.OnClickListener listener) {
		showPrompts(mContext.getString(textResId), listener);
	}

	public void showPrompts(String msg, DialogInterface.OnClickListener listener) {
		showAlertDialog(mContext, msg, listener);
	}

	public void closePrompt() {
		closeAlertDialog();
	}

	public static void closeAlertDialog() {
		if (mDialog != null) {
			mDialog.dismiss();
			mDialog = null;
		}
	}

	public static void showAlertDialog(Context context, int textResId,
                                       final DialogInterface.OnClickListener positiveListener) {
		showAlertDialog(context, context.getString(textResId), positiveListener);
	}

	public static void showAlertDialog(Context context, String msg, DialogInterface.OnClickListener positiveListener) {
		if (mDialog != null) {
			closeAlertDialog();
		}
		if (positiveListener == null) {
			positiveListener = new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					closeAlertDialog();
				}
			};
		}

		try {
			// closeAlertDialog();
			/*
			 * AlertDialog.Builder builder = new AlertDialog.Builder(context);
			 * builder.setIcon(R.drawable.query_dialog_icon);
			 * builder.setTitle(R.string.prompt); builder.setMessage(msg);
			 * builder.setPositiveButton(R.string.prompt_ok, positiveListener);
			 * builder.setOnCancelListener(new
			 * DialogInterface.OnCancelListener() {
			 * 
			 * @Override public void onCancel(DialogInterface dialog) {
			 * closeAlertDialog(); } });
			 * 
			 * sAlertDialog = builder.create();
			 */
			mDialog = BaseDialog.getDialog(context, R.string.dialog_tips, "");
			mDialog.setMessage(msg);
			mDialog.setButton1(context.getResources().getString(R.string.confirm), positiveListener);
			mDialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showAlertDialog(Context context, int textResId,
                                       final DialogInterface.OnClickListener positiveListener,
                                       final DialogInterface.OnClickListener negativeListener) {
		showAlertDialog(context, context.getString(textResId), positiveListener, negativeListener);
	}

	public static void showAlertDialog(Context context, String msg,
                                       final DialogInterface.OnClickListener positiveListener,
                                       final DialogInterface.OnClickListener negativeListener) {
		try {
			if (((Activity) context).hasWindowFocus()) {

			} else {
				context = MyApplication.getApplication();
			}
			if (mDialog != null) {
				closeAlertDialog();
			}
			mDialog = BaseDialog.getDialog(context, R.string.dialog_tips, "");
			mDialog.setMessage(msg);
			mDialog.setButton1(context.getResources().getString(R.string.confirm), positiveListener);
			mDialog.setButton2(context.getResources().getString(R.string.cancle), negativeListener);
			mDialog.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showAlertDialog(Context context, String msg, String buttonName1, String buttonName2,
                                       final DialogInterface.OnClickListener positiveListener,
                                       final DialogInterface.OnClickListener negativeListener) {
		try {
			if (((Activity) context).hasWindowFocus()) {

			} else {
				context = MyApplication.getApplication();
			}
			if (mDialog != null) {
				closeAlertDialog();
			}
			mDialog = BaseDialog.getDialog(context, R.string.dialog_tips, "");
			mDialog.setMessage(msg);
			if ("".equals(buttonName1)) {
				mDialog.setButton1(context.getResources().getString(R.string.confirm), positiveListener);
			} else {
				mDialog.setButton1(buttonName1, positiveListener);
			}
			if ("".equals(buttonName2)) {
				mDialog.setButton2(context.getResources().getString(R.string.cancle), negativeListener);
			} else {
				mDialog.setButton2(buttonName2, negativeListener);
			}

			mDialog.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showProgressDialog(Context context, int msgId) {
		showProgressDialog(context, context.getString(msgId));
	}

	public static void showProgressDialog(Context context, String msg) {
		showProgressDialog(context, msg, -1);
	}

	public static void showProgressDialog(Context context, int msgId, long maxWaitTime) {
		showProgressDialog(context, context.getString(msgId), maxWaitTime);
	}

	public static void showProgressDialog(Context context, String msg, long maxWaitTime) {
		try {
			closeProgressDialog();
			sProgressDialog = ProgressDialog.show(context, context.getString(R.string.dialog_tips), msg, false, false);

			if (maxWaitTime > 1000) {
				sTimer = new Timer();
				sTimer.schedule(new TimerTask() {
					@Override
					public void run() {
						closeProgressDialog();
					}
				}, maxWaitTime);
			}
		} catch (Exception e) {
		}
	}

	public static void setProgressMsg(String msg) {
		if (sProgressDialog != null) {
			sProgressDialog.setMessage(msg);
		}
	}

	public static void setProgressMsg(Context context, int textResId) {
		if (sProgressDialog != null) {
			sProgressDialog.setMessage(context.getString(textResId));
		}
	}

	public static void closeProgressDialog() {
		try {
			if (sProgressDialog != null) {
				sProgressDialog.dismiss();
				sProgressDialog = null;
			}
		} catch (Exception e) {
		}

		try {
			if (sTimer != null) {
				sTimer.cancel();
				sTimer = null;
			}
		} catch (Exception e) {
		}
	}

	public static boolean isProgressDialogShowing() {
		return sProgressDialog != null;
	}

}
