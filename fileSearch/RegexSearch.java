package fileSearch;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.html.HTML.Tag;

import tools.CheckUtils;
import tools.IOUtils;
import tools.LogUtils;
import tools.ParamsAndJudgeUtils;

/**
 * ������ʽ����
 * @author LorenWang
 *
 */
public class RegexSearch {
	private final String TAG = "RegexSearch";
	private String searchDir;//Ҫ�����ļ����ڵ��ļ��У�������һ�������Զ�����
	private String resultSaveFilePath;//��������ļ���ַ
	private String searchRegex;//Ҫ���ҵ�������ʽ
	private int maxFileSize;//����ļ���С
	private List<String> list = new ArrayList<String>();
	
	
	/**
	 * ���캯��
	 * @param searchDir Ҫ�����ļ����ڵ��ļ��У�������һ�������Զ�����
	 * @param resultSaveFilePath ��������ļ���ַ
	 * @param searchRegex Ҫ���ҵ�������ʽ
	 */
	public RegexSearch(String searchDir, String resultSaveFilePath, String searchRegex,int maxFileSize) {
		super();
		this.searchDir = searchDir;
		this.resultSaveFilePath = resultSaveFilePath;
		this.searchRegex = searchRegex;
		this.maxFileSize = maxFileSize;
	}
	
	/**
	 * ��ʼɨ��
	 */
	public void startScan(FileSearchType type) {
		if(!CheckUtils.isEmpty(searchDir) 
				&& !CheckUtils.isEmpty(resultSaveFilePath)
				&& !CheckUtils.isEmpty(searchRegex)) {
			File searchFile = new File(searchDir);
			File resultSaveFile = new File(resultSaveFilePath);
			if(!searchFile.exists()) {
				LogUtils.logE(TAG,"scan file is not exist");
				return;
			}
			LogUtils.logI(TAG, "start scan file");
			boolean state = scanFileDir(searchFile,type);
			if(state) {
				byte[] bytes = ParamsAndJudgeUtils.paramsStringListToBytes(list,"\n");
				if(resultSaveFile.exists()) {
					resultSaveFile.delete();
				}
				if(!resultSaveFile.getParentFile().exists()) {
					resultSaveFile.getParentFile().mkdirs();
				}
				//��ʼִ�д洢
				IOUtils.getInstance().writeToFile(resultSaveFile, bytes);
				LogUtils.logI(TAG, "scan file for success");
			}else {
				LogUtils.logI(TAG, "scan file for fail");
			}
			
			
		}
	}
	/**
	 * ɨ���ļ���
	 * @param file �ļ���
	 * @return
	 */
	private boolean scanFileDir(File file,FileSearchType type) {
		if(file.isFile()) {
			LogUtils.logI(TAG,file.getAbsolutePath() + "\n");
			switch(type) {
			   case SEARCH_TYPE_FILE_CONTENT:
					if(file.length() > maxFileSize) {
						return true;
					}
					
					byte[] readBytes = IOUtils.getInstance().readBytes(file);
					if(readBytes != null && readBytes.length > 0) {
						try {
							list.addAll(ParamsAndJudgeUtils.getStringToRegexStrings(new String(readBytes,"UTF-8"), searchRegex));
							LogUtils.logI(TAG,"��ǰ�����������ݴ�С" + String.valueOf(list.size()) + "\n");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return true;
					}
				   break;
			   case SEARCH_TYPE_FILE_NAME:
				   if(CheckUtils.matches(file.getAbsolutePath(), searchRegex)) {
					   list.add(file.getAbsolutePath());
					   LogUtils.logI(TAG,"��ǰ�����������ݴ�С" + String.valueOf(list.size()) + "\n");
					   return true;
					}
				   break;
			   default:
				   break;
			}
		}else if(file.isDirectory()){
			LogUtils.logI(TAG,file.getAbsolutePath() + "\n");
			File[] listFiles = file.listFiles();
			int length = listFiles.length;
			for (int i = 0; i < length; i++) {
				scanFileDir(listFiles[i],type);
			}
		}
		return true;
	}
	
	
	
	
}
