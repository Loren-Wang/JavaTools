package tools;

import java.text.NumberFormat;
import java.util.List;

public class CheckUtils {
	 public static final String EXP_a_z = "[a-z]*";//ƥ�����е�Сд��ĸ
	    public static final String EXP_A_Z = "[A-Z]*";//ƥ�����еĴ�д��ĸ
	    public static final String EXP_a_z_A_Z = "[a-zA-Z]*";//ƥ�����е���ĸ
	    public static final String EXP_0_9 = "[0-9]*";//ƥ�����е�����
	    public static final String EXP_ALL_INTEGET_NOT_AND_ZERO = "[1-9]{1}[0-9]*";//ƥ�����е�����(������0)
	    public static final String EXP_0_9_a_z = "[0-9a-z]*";
	    public static final String EXP_0_9_a_z_A_Z = "[0-9a-zA-Z]*";
	    public static final String EXP_0_9_a_z__ = "[0-9a-z_]*";
	    public static final String EXP_EMAIL = "^([a-z0-9A-Z_]+[_|\\-|\\.]?)+[a-z0-9A-Z_]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";//EMAIL
	    public static final String EXP_PRICE = "^([1-9]\\d+|[1-9])(\\.\\d\\d?)*$";//��2λС��
	    public static final String EXP_MOBILE = "[1]{1}[0-9]{10}";//11λ�����ֻ�����
	    public static final String EXP_POSTALCODE = "[0-9]{6}";//6λ�����ʱ�
	    public static final String EXP_TEL = "[0-9]{3,4}[-]{1}[0-9]{7,8}";//�绰���룺( ��021-12345678 or 0516-12345678 )
	    public static final String EXP_IP = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";//ƥ��IP��ַ
	    public static final String EXP_CHINESE = "[\u4e00-\u9fa5]*";//ƥ������
	    public static final String EXP_0_9_a_z_A_Z_CHINESE = "[0-9a-zA-Z\u4e00-\u9fa5]*";//ƥ������,����,Сд��ĸ,��д��ĸ
	    public static final String EXP_0_9_a_z_A_Z_CHINESE_DOT = "[.��0-9a-zA-Z\u4e00-\u9fa5]*";//ƥ������,����,Сд��ĸ,��д��ĸ
		public static final String EXP_ALL_INTEGET_AND_ZERO = "^-?[0-9]\\d*$";//���е���������0
		public static final String EXP_CAR_LICENSE_NUM = "[a-zA-Z]{1}[0-9a-zA-Z]{5,6}";//ƥ�䳵�ƺ�
		public static final String EXP_OBD_SERIAL_NUM = "[0-9a-zA-Z]{4,6}";//ƥ��obd�����к�
		public static final String EXP_OBD_ACTIVATION_NUM = "[0-9a-zA-Z]{6}";//ƥ��obd��������
	    public static final String EXP_URL = "^[a-zA-z]+://[^><\"' ]+";
	    public static final String EXP_MAC = "[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}";
	    public static final String EXP_DATE = "[0-9]{4}[-]{1}[0-9]{1,2}[-]{1}[0-9]{1,2}";
	    public static final String EXP_DATETIME = "[0-9]{4}[-]{1}[0-9]{1,2}[-]{1}[0-9]{1,2}[ ]{1}[0-9]{1,2}[:]{1}[0-9]{1,2}";
	    public static final String EXP_DATETIMESECOND = "[0-9]{4}[-]{1}[0-9]{1,2}[-]{1}[0-9]{1,2}[ ]{1}[0-9]{1,2}[:]{1}[0-9]{1,2}[:]{1}[0-9]{1,2}";
	    public static final String DATESTRING_TAIL = "000000000";
	    public static final String LOGIN_OR_REG_PWD = "[0-9]{4}";//��¼����ע���ʱ���������֤������
	    public static final String CHAT_MSG_DEFAULT_EMOJI = "[[\u4e00-\u9fa5]]";//�����б����ļ������򣨲������ļ�·����


		//ɨ�����򣬶�ά��ɨ���û����ϵ�����
		public static final String SCAN_RESULT_MATCH_FOR_PEOPLE = "((http|https)://)[^\\s]*&&&\\d*";
		//��ά��ɨ��ve_box���򣬾������£�[��˾��д][���ڱ���][��Ʒ���к�]����˾��дΪYL�����ڱ���Ϊ4λ���֣�ֵ�̶�Ϊ0176����Ʒ���к���6λ������ɣ���000001��999999��
		public static final String SCAN_RESULT_MATCH_FOR_VE_BOX = "[a-zA-Z]{1}[0-9a-zA-Z]{11}#[0-9a-zA-Z]{4,}";
		//��ά��ɨ��ve_air����,�������£�[��Ʒ���][��Ʒ���к�];��Ʒ������3λ������ɣ�101��ʾVE-AIR��102��ʾVE-SEE������Ʒ�������ƣ�VE-BOX��Ʒ���⣩;��Ʒ���к���9λ������ɣ���000000001��999999999
		public static final String SCAN_RESULT_MATCH_FOR_VE_AIR = "101[0-9]{9}#[0-9a-zA-Z]{4,}";
		//ve_box���к�����
		public static final String IMEI_FOR_VE_BOX = "[a-zA-Z]{1}[0-9a-zA-Z]{11}";
		//ve_air���к�����
		public static final String IMEI_FOR_VE_AIR = "101[0-9]{9}";




	    /*--------------------------------------------------------------------------
		| ����ַ���
		--------------------------------------------------------------------------*/
		/**
		 * �ж��ַ����Ƿ�Ϊ��
		 * @param str String
		 * @return boolean
		 */
		public static boolean isEmpty(String str) {
			return (str == null || "".equals(str)) ? true : false;
		}

		/**
		 * �ж��Ƿ����ָ����������ʽ eg: [^0-9A-Za-z]
		 * @param str String
		 * @param patternStr String
		 * @return boolean
		 */
		public static boolean matches(String str, String patternStr) {
			if (isEmpty(str)) {
				return false;
			}
			return str.matches(patternStr);
		}

		/**
		 * �ж��ַ����Ƿ������� 2002-02-02
		 * @param str String
		 * @return boolean
		 */
		public static boolean isDate(String str) {
			if (isEmpty(str)) {
				return false;
			}
			return str.matches(EXP_DATE);
		}

		/**
		 * �ж��ַ����Ƿ�������+ʱ�� 2002-02-02 10��30
		 * @param str String
		 * @return boolean
		 */
		public static boolean isDateTime(String str) {
			if (isEmpty(str)) {
				return false;
			}
			return str.matches(EXP_DATETIME);
		}

		/**
		 * �ж��ַ����Ƿ��ǽ�� eg: 12.00
		 * @param str String
		 * @return boolean
		 */
		public static boolean isMoney(String str) {
			if (isEmpty(str)) {
				return false;
			}
			try {
				Double.valueOf(str);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		/**
		 * �ж��ַ����Ƿ�������
		 * @param str String
		 * @return boolean
		 */
		public static boolean isInteger(String str) {
			if (isEmpty(str)) {
				return false;
			}
			try {
				Integer.valueOf(str);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		/**
		 * �ж��ַ����Ƿ��ǳ�����
		 * @param str String
		 * @return boolean
		 */
		public static boolean isLong(String str) {
			if (isEmpty(str)) {
				return false;
			}
			try {
				Long.valueOf(str);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		/**
		 * �ж��ַ����Ƿ��Ǹ�����
		 * @param str String
		 * @return boolean
		 */
		public static boolean isDouble(String str) {
			if (isEmpty(str)) {
				return false;
			}
			try {
				Double.valueOf(str);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		/*--------------------------------------------------------------------------
		| ��鳬��
		--------------------------------------------------------------------------*/
		/**
		 * �ַ����Ƿ񳬳�
		 * @param str String
		 * @param len int
		 * @return boolean
		 */
		public static boolean isOverLength(String str, int len) {
			if (isEmpty(str)) {
				return false;
			}
			return str.length() > len ? true : false;
		}

		/**
		 * Double�����Ƿ񳬳�
		 * @param d Double
		 * @param len int
		 * @return boolean
		 */
		public static boolean isOverLength(Double d, int len) {
			if (d == null) {
				return false;
			}
			NumberFormat formatter = NumberFormat.getNumberInstance();
			formatter.setGroupingUsed(false); // �Ƿ�Խ�����飨��ʹ��","���飩
			formatter.setMaximumFractionDigits(0); // С��λ�����ֵ
			formatter.setMinimumFractionDigits(0); // С��λ����Сֵ
			if (formatter.format(d.doubleValue()).length() > len) {
				return true;
			}
			return false;
		}

		/**
		 * �ж϶����Ƿ��������
		 * @author wfc
		 * @param object
		 * @return
		 */
		public static boolean isBaseType(Object object) {
			if (object instanceof Integer ||
					object instanceof Double ||
					object instanceof String ||
					object instanceof Character ||
					object instanceof Byte ||
					object instanceof Long ||
					object instanceof Float ||
					object instanceof Boolean ||
					object instanceof Short) {
				return true;
			}
			return false;
		}

		/**
		 * �ж��ַ����Ƿ����б���
		 * @param item
		 * @param list
		 * @return
		 */
		public static boolean isInList(String item, List<String> list) {
			for (String listItem : list) {
				if (item.equals(listItem)) {
					return true;
				}
			}
			return false;
		}

		/**
		 * �ж϶����Ƿ����б���
		 * @param <T>
		 * @param item
		 * @param list
		 * @return
		 */
		public static <T> boolean isInList(T item, List<T> list) {
			for (T listItem : list) {
				if (item.equals(listItem)) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * �ж϶����Ƿ���������
		 * @param <T>
		 * @param item
		 * @param list
		 * @return
		 */
		public static <T> boolean isInArray(T item, T[] list) {
			for (T listItem : list) {
				if (item.equals(listItem)) {
					return true;
				}
			}
			return false;
		}

		/**
		 * ��鴫���·���Ƿ���ͼƬ
		 * @param path
		 * @return
		 */
		public static boolean checkIsImage(String path){
			if(path != null) {
				if(path.length() > 4){
					if(path.toLowerCase().substring(path.length() - 4).contains(".jpg")
							|| path.toLowerCase().substring(path.length() - 4).contains(".png")
							|| path.toLowerCase().substring(path.length() - 4).contains(".bmp")
							|| path.toLowerCase().substring(path.length() - 4).contains(".gif")
							|| path.toLowerCase().substring(path.length() - 4).contains(".psd")
							|| path.toLowerCase().substring(path.length() - 4).contains(".swf")
							|| path.toLowerCase().substring(path.length() - 4).contains(".svg")
							|| path.toLowerCase().substring(path.length() - 4).contains(".pcx")
							|| path.toLowerCase().substring(path.length() - 4).contains(".dxf")
							|| path.toLowerCase().substring(path.length() - 4).contains(".wmf")
							|| path.toLowerCase().substring(path.length() - 4).contains(".emf")
							|| path.toLowerCase().substring(path.length() - 4).contains(".lic")
							|| path.toLowerCase().substring(path.length() - 4).contains(".eps")
							|| path.toLowerCase().substring(path.length() - 4).contains(".tga")){
						return true;
					}else if(path.length() > 5){
						if(path.toLowerCase().substring(path.length() - 5).contains(".jpeg")
								|| path.toLowerCase().substring(path.length() - 5).contains(".tiff")){
							return true;
						}
					}
				}
			}

			return false;
		}

		/**
		 * ��鴫���·���Ƿ���ͼƬ
		 * @param path
		 * @return
		 */
		public static boolean checkIsVideo(String path){
			if(path != null) {
				if(path.length() > 4){
					if(path.toLowerCase().substring(path.length() - 4).contains(".mp4")){
						return true;
					}
				}
			}
			return false;
		}


		/**
		 * �жϴ�������Ƿ�Ϊ���Ƿ�Ϊ��
		 * @param object object
		 * @return boolean
		 */
		public static boolean isNotEmptyOrNull(Object object) {
			if(object == null){
				return false;
			}else if(object instanceof String && ("".equals(object) || ((String)object).isEmpty())){
				return false;
			}else {
				return true;
			}
		}
}
