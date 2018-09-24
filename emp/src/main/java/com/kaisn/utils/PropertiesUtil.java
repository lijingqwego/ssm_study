package com.kaisn.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * ���������ļ�������
 * 
 * @author lijing
 *
 */
public class PropertiesUtil {

	private static Properties properties = null;

	// �ļ�����
	public static final int xmlType = 1;//xml
	public static final int defaultType = 0;//key=value

	/**
	 * ʵ�������󣨵���ģʽ��
	 */
	private static void init() {
		if (properties == null) {
			synchronized (Properties.class) {
				if (properties == null) {
					properties = new Properties();
				}
			}
		}
	}

	/**
	 * ��ȡPropertiesʵ�����������ļ�
	 * 
	 * @param fileName �ļ�����
	 * @param type �ļ�����
	 * @return
	 */
	public static Properties getProperties(String fileName, int type) {
		InputStream inputStream = null;
		try {
			init();
			inputStream = PropertiesUtil.class.getResourceAsStream(fileName);
			switch (type) {
			case xmlType:
				properties.loadFromXML(inputStream);
				break;
			default:
				properties.load(inputStream);
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return properties;
	}

	/**
	 * ��������
	 * 
	 * @param proMap �������
	 * @param fileName �ļ�����
	 * @param type �ļ�����
	 * @param comments �ļ�����
	 */
	public static void setPropertys(Map<String, String> proMap, String fileName, int type, String comments) {
		if (proMap != null) {
			init();
			FileOutputStream outputStream = null;
			try {
				outputStream = new FileOutputStream(fileName);
				for (Entry<String, String> pro : proMap.entrySet()) {
					properties.setProperty(pro.getKey(), pro.getValue());
				}
				switch (type) {
				case xmlType:
					properties.storeToXML(outputStream, comments);
					break;
				default:
					properties.store(outputStream, comments);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (outputStream != null) {
					try {
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * ��ն���
	 */
	public static void clear() {
		properties = null;
	}

}
