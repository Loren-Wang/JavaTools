package tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class IOUtils {
	private static final String TAG = "IOutils";
	private static IOUtils iOutils;
	public static IOUtils getInstance() {
		if(iOutils == null) {
			iOutils = new IOUtils();
			LogUtils.logI(TAG, "初始化io单例");
		}
		return iOutils;
	}
	
	
	private static final int BUFFER_SIZE = 1024; // 流转换的缓存大小
	private static final int CONNECT_TIMEOUT = 3000; // 从网络下载文件时的连接超时时间


	/**
	 * 从指定路径的文件中读取Bytes
	 */
	public byte[] readBytes(String path) {
		try {
			File file = new File(path);
			return readBytes(file);
		} catch (Exception e) {
			LogUtils.logE(e != null ? e.getMessage() : "");
			return null;
		}
	}

	/**
	 * 从File中读取Bytes
	 */
	public synchronized byte[] readBytes(File file) {
			FileInputStream fis = null;
			try {
				if (!file.exists()) {
					return null;
				}
				fis = new FileInputStream(file);
				return readBytes(fis);
			} catch (Exception e) {
				LogUtils.logE(e != null ? e.getMessage() : "");
				return null;
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
				} catch (Exception e) {
					LogUtils.logE(e != null ? e.getMessage() : "");
				}
			}
	}

	/**
	 * 从InputStream中读取Bytes
	 */
	public synchronized byte[] readBytes(InputStream is) {
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int length = 0;
			while ((length = is.read(buffer, 0, BUFFER_SIZE)) != -1) {
				baos.write(buffer, 0, length);
				baos.flush();
			}
			return baos.toByteArray();
		} catch (Exception e) {
			LogUtils.logE(e != null ? e.getMessage() : "");
			return null;
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
			} catch (Exception e) {
				LogUtils.logE(e != null ? e.getMessage() : "");
			}
		}
	}


	/**
	 * 将InputStream写入File
	 */
	public boolean writeToFile(File file, InputStream is) {
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			byte[] buffer = new byte[BUFFER_SIZE];
			int length = 0;
			while ((length = is.read(buffer, 0, BUFFER_SIZE)) != -1) {
				fos.write(buffer, 0, length);
				fos.flush();
			}
			return true;
		} catch (Exception e) {
			LogUtils.logE(e != null ? e.getMessage() : "");
			return false;
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {
				LogUtils.logE(e != null ? e.getMessage() : "");
			}
			fos = null;
		}
	}

	public boolean writeToFile(File file, String text) {
		return writeToFile(file, text, "UTF-8", false);
	}

	public boolean writeToFile(File file, String text, boolean append) {
		return writeToFile(file, text, "UTF-8", append);
	}

	public boolean writeToFile(File file, String text, String encoding) {
		try {
			return writeToFile(file, text.getBytes(encoding), false);
		} catch (UnsupportedEncodingException e) {
			LogUtils.logE(e != null ? e.getMessage() : "");
			return false;
		}
	}

	public boolean writeToFile(File file, String text, String encoding,
									  boolean append) {
		try {
			return writeToFile(file, text.getBytes(encoding), append);
		} catch (UnsupportedEncodingException e) {
			LogUtils.logE(e != null ? e.getMessage() : "");
			return false;
		}
	}

	public boolean writeToFile(File file, byte[] buffer) {
		return writeToFile(file, buffer, false);
	}

	public boolean writeToFile(File file, byte[] buffer, boolean append) {
		FileOutputStream fos = null;
		try {
			if(file.exists()){
				file.delete();
			}
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}

			fos = new FileOutputStream(file, append);
			fos.write(buffer);
			return true;
		} catch (Exception e) {
			LogUtils.logE(e != null ? e.getMessage() : "");
			return false;
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {
				LogUtils.logE(e != null ? e.getMessage() : "");
			}
		}
	}


	/**
	 * 复制单个文件
	 * @param oldPath String 原文件路径 如：c:/fqf.txt
	 * @param newPath String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public boolean copyFile(String oldPath, String newPath) {
		FileOutputStream fs = null;
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { //文件存在时
				InputStream inStream = new FileInputStream(oldPath); //读入原文件
				fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ( (byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; //字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				return true;
			}else {
				return false;
			}
		}
		catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
			return false;
		}finally {
			if(fs != null){
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				fs = null;
			}
		}

	}
	

}
