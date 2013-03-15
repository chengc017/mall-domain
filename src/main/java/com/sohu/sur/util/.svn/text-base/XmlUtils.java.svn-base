package com.sohu.sur.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XmlUtils {

	/**
	 * 将XML文件转换为Document
	 * 
	 * @param xmlFile
	 * @return
	 */
	public static Document readXmlToDocument(File xmlFile) {
		
		if (xmlFile == null)
			throw new NullPointerException("xml file is null");

		InputStream inputStream = null;
		Document document = null;

		try {
			
			inputStream = new BufferedInputStream(new FileInputStream(xmlFile));

			SAXReader reader = new SAXReader();
			document = reader.read(inputStream);

		} catch (Exception e) {

		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}

		return document;
	}
	
	/**
	 * 在环境变量的路径下读取XML文件
	 * 
	 * @param filePath		xml的路径名称,例如在src下就直接用该xml的名称即可
	 * @return
	 * @throws DocumentException
	 */
	public static Document readClassPathXmlToDocument(String filePath) throws DocumentException	{		
		
		SAXReader reader = new SAXReader();
		InputStream inputStream = XmlUtils.class.getClassLoader().getResourceAsStream(filePath);//getSystemResourceAsStream(filePath);
		Document document = reader.read(inputStream);
		
		return document;
	}
}
