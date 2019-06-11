package fileSearch;

import java.io.File;

import tools.IOUtils;
import tools.LogUtils;

/**
 *   输出文件名称列表
 * @author LorenWang
 *
 */
public class OutputFileNameList {
	private static final String TAG = "OutputFileNameList";
	private static String saveFileDir = "-keep class org.chromium.chrome.browser.File.**{*;}\r\n";
	private static String saveFile = "-keep class org.chromium.chrome.browser.File{*;}\r\n";
	private static StringBuffer saveContent = new StringBuffer("");
	
	private static String outputPath = "C:\\Users\\LorenWang\\Desktop\\paramsModal\\output.txt";
	
	public static void main(String[] args) {
		outputFileName("C:\\Develop\\Project\\Android\\Office\\ZeusBrowser\\app\\src\\main\\java\\org\\chromium\\chrome\\browser");
		
	}
	
	
	public static void outputFileName(String searchPath) {
		File file = new File(searchPath);
		if(file != null) {
			LogUtils.logI(TAG, "文件开始检索");
			File[] listFiles = file.listFiles();
			for(File childFile : listFiles) {
				if(childFile.isDirectory()) {
					saveContent.append(saveFileDir.replace("File", childFile.getName()));
				}else if(childFile.isFile()){
					saveContent.append(saveFile.replace("File", childFile.getName()));
				}
			}
			IOUtils.getInstance().writeToFile(new File(outputPath), saveContent.toString());
    		LogUtils.logI(TAG, "文件检索完成完成，并已输出");
		}
	}
}
