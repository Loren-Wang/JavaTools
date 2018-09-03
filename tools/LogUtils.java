package tools;

public class LogUtils {
	private static final String TAG = "LogUtils";
	private static final String LOG_I = "log_i";
	private static final String LOG_E = "log_e";
	private static final String LOG_DIVIDE = ":::";
	

	public static void logI(String tag,String msg) {
		System.out.print(LOG_I + tag + LOG_DIVIDE + msg + "\n");
	}
	
	
	public static void logE(String msg) {
		// TODO Auto-generated method stub
		System.out.print(LOG_E + TAG + LOG_DIVIDE + msg + "\n");
	}


	public static void logE(String tag, String msg) {
		// TODO Auto-generated method stub
		System.out.print(LOG_E + tag + LOG_DIVIDE + msg + "\n");
	}
}
