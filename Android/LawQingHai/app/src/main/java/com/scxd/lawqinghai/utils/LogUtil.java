package com.scxd.lawqinghai.utils;


import com.scxd.lawqinghai.application.MyApplication;
import com.scxd.lawqinghai.common.Common;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @className LogUtil
 * @author goubaihu
 * @function 将日志写入本地
 * @createTime 2014年11月28号
 */
public class LogUtil {

	private static LogUtil mLogWriter;
	private static String FileBasePath = Common.PHONE_PATH;
	private static String FileBasePath2 = Common.PHONE_PATH;
	private static String mPath;
	private static SimpleDateFormat df;

	private LogUtil() {
		this.mPath = getFilePath();
	}

	/**
	 * 获取文件路径
	 *            路径
	 */
	private static String getFilePath() {
		return FileBasePath
				+ new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".txt";
	}

	public static LogUtil open() {
		try {
			if (mLogWriter == null) {
				mLogWriter = new LogUtil();
			}
			File mFile = new File(FileBasePath);
			if (!mFile.exists()) {
				File file2 = new File(mFile.getParent());
				file2.mkdir();
			}
			if (!mFile.isDirectory()) {
//				deleteFile();
//				deleteFile2();
				mFile.createNewFile();
			}
			df = new SimpleDateFormat("[yy-MM-dd hh:mm:ss]: ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mLogWriter;
	}
	
    public static LogUtil open2() {
        try {
            if (mLogWriter == null) {
                mLogWriter = new LogUtil();
            }
            File mFile = new File(MyApplication.getFileLocation() + "/location.txt");
            if (!mFile.exists()) {
                File file2 = new File(mFile.getParent());
                file2.mkdir();
            }
            if (!mFile.isDirectory()) {
                //				deleteFile();
                //				deleteFile2();
                mFile.createNewFile();
            }
            df = new SimpleDateFormat("[yy-MM-dd hh:mm:ss]: ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mLogWriter;
    }

	/**
	 * 删除一天之前的为日志文件
	 * 
	 * @throws Exception
	 */
	public static LogUtil deleteFile() throws Exception {
		if (mLogWriter == null) {
			mLogWriter = new LogUtil();
		}
		File pathRoot = new File(FileBasePath);
		if (pathRoot.exists() && pathRoot.listFiles().length > 0) {
			for (File file : pathRoot.listFiles()) {
				if (file.isFile()) {
					String s = file.getName().substring(0,
							file.getName().length() - 4);
					Date date = new SimpleDateFormat("yyyyMMdd").parse(s);
					if ((new Date().getTime() - date.getTime()) > 2 * 24 * 60 * 60 * 1000) {
						file.delete();
					}
				}

			}
		}
		return mLogWriter;
	}
	private static void deleteFile2() throws Exception {
		File pathRoot = new File(FileBasePath2);
		if (pathRoot.exists() && pathRoot.listFiles().length > 0) {
			for (File file : pathRoot.listFiles()) {
				String s = file.getName().substring(0,
						file.getName().length() - 4);
				Date date = new SimpleDateFormat("yyyyMMdd").parse(s);
				if ((new Date().getTime() - date.getTime()) > 2 * 24 * 60 * 60
						* 1000) {
					file.delete();
				}
			}
		}
	}
	public static void appendMethodA(String MethodName, Class cls, String log) {
		RandomAccessFile randomFile = null;
		try {
			String writerLog = df.format(new Date()) + MethodName
					+ cls.getSimpleName() + log + "\n";
			randomFile = new RandomAccessFile(mPath, writerLog);
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);// 将指针移到文件尾部，准备写入内容
			randomFile.writeBytes(writerLog);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (randomFile != null) {
				randomFile = null;
			}
		}
	}

	public static void appendMethodA(Class cls, String log) {
		RandomAccessFile randomFile = null;
		try {
			String writerLog = df.format(new Date()) + cls.getSimpleName()
					+ log + "\n";
			randomFile = new RandomAccessFile(mPath, writerLog);
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			randomFile.writeBytes(writerLog);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (randomFile != null) {
				randomFile = null;
			}
		}
	}

	public static void appendMethodA(String log) {
		RandomAccessFile randomFile = null;
		try {
			String writerLog = df.format(new Date()) + log + "\n";
			randomFile = new RandomAccessFile(mPath, writerLog);
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			randomFile.writeBytes(writerLog);
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (randomFile != null) {
				randomFile = null;
			}
		}
	}

	public static void appendMethodB(String MethodName, Class cls, String log) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(mPath, true);
			writer.write(df.format(new Date()) + MethodName
					+ cls.getSimpleName());
			writer.write(log);
			writer.write("\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer = null;
			}
		}
	}

	public static void appendMethodB(Class cls, String log) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(mPath, true);
			writer.write(df.format(new Date()) + cls.getSimpleName());
			writer.write(log);
			writer.write("\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer = null;
			}
		}
	}

	public static void appendMethodB(String log) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(mPath, true);
			writer.write(df.format(new Date()));
			writer.write(log);
			writer.write("\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer = null;
			}
		}
	}

    public static void appendMethod(String log, String path) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(MyApplication.getFileLocation() + path, true);
            writer.write(log);
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer = null;
            }
        }
    }
    public static String showInfo() {
        File file = new File(MyApplication.getFileLocation() + "/location.txt");
        String str = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            InputStream is = new FileInputStream(file);
            InputStreamReader input = new InputStreamReader(is, "UTF-8");
            BufferedReader reader = new BufferedReader(input);
            while ((str = reader.readLine()) != null) {
                stringBuffer.append(str);
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    //参数一、文件的byte流
    //参数二、文件要保存的路径
    //参数三、文件保存的名字
    public static void saveFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;

        File file = null;
        try {
            //通过创建对应路径的下是否有相应的文件夹。
            File dir = new File(filePath);
            if (!dir.exists()) {// 判断文件目录是否存在
                //如果文件存在则删除已存在的文件夹。
                dir.mkdirs();
            }

            //如果文件存在则删除文件
            file = new File(filePath, fileName);
            if(file.exists()){
                file.delete();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            //把需要保存的文件保存到SD卡中
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取文件夹内所有文件
     * @param dirPath
     * @param _type
     * @return
     */
    public static ArrayList<String> getAllFiles(String dirPath, String _type) {
        File f = new File(dirPath);
        if (!f.exists()) {//判断路径是否存在
            return null;
        }

        File[] files = f.listFiles();

        if(files==null){//判断权限
            return null;
        }

        ArrayList<String> fileList = new ArrayList<>();
        for (File _file : files) {//遍历目录
            if(_file.isFile() && (_file.getName().endsWith(_type)
                    ||_file.getName().endsWith(".JPG")
                    ||_file.getName().endsWith(".JPEG")
                    ||_file.getName().endsWith(".jpeg")
                    ||_file.getName().endsWith(".png")
                    ||_file.getName().endsWith(".PNG")
                    ||_file.getName().endsWith(".BMP")
                    ||_file.getName().endsWith(".bmp")
                    ||_file.getName().endsWith(".PSD")
                    ||_file.getName().endsWith(".psd")
                    ||_file.getName().endsWith(".GIF")
                    ||_file.getName().endsWith(".gif")
                    ||_file.getName().endsWith(".TIFF")
                    ||_file.getName().endsWith(".tiff")
                    ||_file.getName().endsWith(".TGA")
                    ||_file.getName().endsWith(".tga")
                    ||_file.getName().endsWith(".EPS")
                    ||_file.getName().endsWith(".eps"))){
                String filePath = _file.getAbsolutePath();//获取文件路径
//                String _name=_file.getName();
//                String fileName = _file.getName().substring(0,_name.length()-4);//获取文件名
                try {
                    fileList.add(filePath);
                }catch (Exception e){
                }
            } else if(_file.isDirectory()){//查询子目录
                getAllFiles(_file.getAbsolutePath(), _type);
            } else{
            }
        }
        return fileList;
    }

}
