package main;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import fileSearch.FileSearchType;
import fileSearch.RegexSearch;
import tools.CheckUtils;
import tools.IOUtils;
import tools.LogUtils;
import tools.ParamsAndJudgeUtils;

public class FileContentSearch {
	private static List<String> showList;
	private static List<String> list = new ArrayList<String>();
	private static String TAG = "FileContentSearch";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File haveSaveFile = new File("C:\\\\Develop\\\\Project\\\\Android\\\\Office\\\\have.txt");
		byte[] old = IOUtils.getInstance().readBytes(new File("C:\\Develop\\Project\\Android\\Office\\result_new_uniq.txt"));
		byte[] news = IOUtils.getInstance().readBytes(new File("C:\\Develop\\Project\\Android\\Office\\result123.txt"));
		
		try {
			String oldStr = new String(old,"utf-8");
			String newStr = new String(news,"utf-8").replaceAll("\"", "").replaceAll("\\\\", "");
			String[] split = oldStr.split("\n");
			int length = split.length;
			for (int i = 0; i < length; i++) {
				int indexOf = split[i].lastIndexOf("/java");
				if(indexOf > 0 && newStr.contains(
						split[i].substring(indexOf).replaceAll("\"", "").replace("/", ""))) {
					list.add(split[i]);
				}
			}
			LogUtils.logI(TAG, "list size ::: " + list.size());
			if(list.size() > 0) {
				byte[] bytes = ParamsAndJudgeUtils.paramsStringListToBytes(list,"\n");
				if(haveSaveFile.exists()) {
					haveSaveFile.delete();
				}
				if(!haveSaveFile.getParentFile().exists()) {
					haveSaveFile.getParentFile().mkdirs();
				}
				//开始执行存储
				IOUtils.getInstance().writeToFile(haveSaveFile, bytes);
				LogUtils.logI(TAG, "scan file for success");
			}else {
				LogUtils.logI(TAG, "scan file for fail");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

//		new RegexSearch("C:\\Develop\\Project\\Android\\Office\\ZeusBrowser"
//				, "C:\\Develop\\Project\\Android\\Office\\result123.txt"
//				, ".*\\.java",5 * 1024 *102).startScan(FileSearchType.SEARCH_TYPE_FILE_NAME);
//		
		
//		byte[] readBytes = IOUtils.getInstance().readBytes(new File("C:\\Develop\\Project\\Android\\Office\\result_new_uniq.txt"));
//		if(readBytes != null && readBytes.length > 0) {
//			try {
//				String string = new String(readBytes,"utf-8");
//				String[] split = string.split("\n");
//				showList = Arrays.asList(split);
//				LogUtils.logI(TAG, "start scan file");
//				File searchFile = new File("C:\\Develop\\Project\\Android\\Office\\ZeusBrowser");
//				File resultSaveFile = new File("C:\\\\Develop\\\\Project\\\\Android\\\\Office\\\\replace111.txt");
//				boolean state = scanFileDir(searchFile);
//				if(state) {
//					byte[] bytes = ParamsAndJudgeUtils.paramsStringListToBytes(list,"\n");
//					if(resultSaveFile.exists()) {
//						resultSaveFile.delete();
//					}
//					if(!resultSaveFile.getParentFile().exists()) {
//						resultSaveFile.getParentFile().mkdirs();
//					}
//					//开始执行存储
//					IOUtils.getInstance().writeToFile(resultSaveFile, bytes);
//					LogUtils.logI(TAG, "scan file for success");
//				}else {
//					LogUtils.logI(TAG, "scan file for fail");
//				}
//				
//				
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		
		
		

	}

	
	/**
	 * 扫描文件夹
	 * @param file 文件夹
	 * @return
	 */
	private static boolean scanFileDir(File file) {
		if(file.isFile()) {
			list.add(file.getAbsolutePath());
//			Iterator<String> iterator = showList.iterator();
//			String path;
//			while(iterator.hasNext()) {
//				path = iterator.next().replaceAll("\"", "");
//				if(file.getAbsolutePath().contains(path)) {
//					list.add(path);
//					LogUtils.logI(TAG,"当前检索到的数据大小" + String.valueOf(list.size()) + "\n");
//				}
//			}
			return true;
		}else if(file.isDirectory()){
//			LogUtils.logI(TAG,file.getAbsolutePath() + "\n");
			File[] listFiles = file.listFiles();
			int length = listFiles.length;
			for (int i = 0; i < length; i++) {
				scanFileDir(listFiles[i]);
			}
		}
		return true;
	}

}
