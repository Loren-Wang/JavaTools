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
			LogUtils.logI(TAG, "��ʼ��io����");
		}
		return iOutils;
	}
	
	
	private static final int BUFFER_SIZE = 1024; // ��ת���Ļ����С
	private static final int CONNECT_TIMEOUT = 3000; // �����������ļ�ʱ�����ӳ�ʱʱ��


	/**
	 * ��ָ��·�����ļ��ж�ȡBytes
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
	 * ��File�ж�ȡBytes
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
	 * ��InputStream�ж�ȡBytes
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
	 * ��InputStreamд��File
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
	 * ���Ƶ����ļ�
	 * @param oldPath String ԭ�ļ�·�� �磺c:/fqf.txt
	 * @param newPath String ���ƺ�·�� �磺f:/fqf.txt
	 * @return boolean
	 */
	public boolean copyFile(String oldPath, String newPath) {
		FileOutputStream fs = null;
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { //�ļ�����ʱ
				InputStream inStream = new FileInputStream(oldPath); //����ԭ�ļ�
				fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ( (byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; //�ֽ��� �ļ���С
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				return true;
			}else {
				return false;
			}
		}
		catch (Exception e) {
			System.out.println("���Ƶ����ļ���������");
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
