package paramsModalType1;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tools.IOUtils;
import tools.LogUtils;

public class ParamsModalType2 {
	private static String TAG = "ParamsModalType1";
	private static String exp1 = "/\\*\\*[\\s\\*A-Za-z._@]*[\\S]*[\\s\\*A-Za-z._@]*/\\s*(private)\\s+[LongDoubleBooleanDateStringFloatIntegerCharlongdoublebooleandatestringfloatintegerchar]*\\s[A-Za-z_1-9]*;";
	private static String exp2 = "\\*[^\\n][^T^@][\\u4e00-\\u9fa5\\S]+";
	private static String exp3 = "(private)\\s+[LongDoubleBooleanDateStringFloatIntegerCharlongdoublebooleandatestringfloatintegerchar]*\\s*[A-Za-z_1-9]*;";
	private static String saveParams = "@ApiModelProperty(value = \"description\",hidden = false)\r\n    ";
	private static StringBuffer saveContent = new StringBuffer("");
	
	private static String sourcePath = "C:\\Users\\LorenWang\\Desktop\\paramsModal\\source.txt";
	private static String outputPath = "C:\\Users\\LorenWang\\Desktop\\paramsModal\\output.txt";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		LogUtils.logI(TAG, "开始格式化");
		File sourceFile = new File(sourcePath);
		//判断输入文件夹是否存在
		File outputFile = new File(outputPath);
		if(!outputFile.getParentFile().exists()) {
			outputFile.getParentFile().mkdirs();
		}
		
		byte[] readBytes = IOUtils.getInstance().readBytes(sourceFile);
		List<String> list = new ArrayList<>();
		if(readBytes != null) {
			LogUtils.logI(TAG, "数据获取成功");
			try {
				String readStr = new String(readBytes,"utf-8");
				
				//拆分字段
				Pattern pattern = Pattern.compile(exp1);
	    		Matcher matcher = pattern.matcher(readStr);
	    		while(matcher.find()) {
	    			list.add(matcher.group());
	    		}
	    		
	    		//开始将每个字段进行格式化
	    		Iterator<String> iterator = list.iterator();
	    		String description = null;
	    		String type = null;
	    		String name = null;
	    		String content = null;
	    		String[] split = null;
	    		String paramsData = null;
	    		while (iterator.hasNext()) {
					content = iterator.next();
					paramsData = content.intern();
					//获取注释描述
					pattern = Pattern.compile(exp2);
					matcher = pattern.matcher(paramsData);
					while(matcher.find()) {
						description = matcher.group();
					}
					//获取变量类型以及变量名字符串
					pattern = Pattern.compile(exp3);
					matcher = pattern.matcher(paramsData);
					if(matcher.find()) {
						paramsData = matcher.group();
					}
					split = paramsData.split(" ");
					if(split.length == 3) {
						type = split[1];
						name = split[2].replace(";", "");
						LogUtils.logI(TAG, name);
					}
					saveContent.append(saveParams.replace("description", description.replace("* ", "")))
					.append(content.replace("Date", "Long")).append("\r\n    ");
				}
	    		IOUtils.getInstance().writeToFile(outputFile, saveContent.toString());
	    		LogUtils.logI(TAG, "数据格式化完成，并已输出");
			} catch (UnsupportedEncodingException e) {
				LogUtils.logI(TAG, "数据格式化异常，请检查数据源");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			LogUtils.logI(TAG, "数据获取失败");
		}
	}
}
