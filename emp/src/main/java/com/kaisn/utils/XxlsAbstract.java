package com.kaisn.utils;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * 
 * XSSF and SAX (Event API)
 * 
 */
public abstract class XxlsAbstract extends DefaultHandler {
	private SharedStringsTable sst;
	private String lastContents;
	private boolean nextIsString;
	private int sheetIndex = -1;
	private List<String> rowlist = new ArrayList<String>();
	private int curRow = 0; // ��ǰ��
	private int curCol = 0; // ��ǰ������
	private int preCol = 0; // ��һ��������
	private int titleRow = 0; // �����У�һ�������Ϊ0
	private int rowsize = 0; // ����
	// excel��¼�в�����������sheet����������������Ԫ���б�Ϊ��������sheet��һ��Ԫ�ؽ��в�����Ԫ��ΪString����

	public abstract void optRows(int sheetIndex, int curRow, List<String> rowlist) throws SQLException;
	// ֻ����һ��sheet������sheetIdΪҪ������sheet��������1��ʼ��1-3

	/**
	 * sheetIdΪҪ������sheet��������1��ʼ��1-3
	 * @param filename
	 * @param sheetId
	 * @throws Exception
	 */
	public void processOneSheet(String filename, int sheetId) throws Exception {
		OPCPackage pkg = OPCPackage.open(filename);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();
		XMLReader parser = fetchSheetParser(sst);
		// rId2 found by processing the Workbook
		// ���� rId# �� rSheet# ����sheet
		InputStream sheet2 = r.getSheet("rId" + sheetId);
		sheetIndex++;
		InputSource sheetSource = new InputSource(sheet2);
		parser.parse(sheetSource);
		sheet2.close();
	}

	/**
	 * ���� excel �ļ�
	 * @param filename
	 * @throws Exception
	 */
	public void process(String filename) throws Exception {
		OPCPackage pkg = OPCPackage.open(filename);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();
		XMLReader parser = fetchSheetParser(sst);
		Iterator<InputStream> sheets = r.getSheetsData();
		while (sheets.hasNext()) {
			curRow = 0;
			sheetIndex++;
			InputStream sheet = sheets.next();
			InputSource sheetSource = new InputSource(sheet);
			parser.parse(sheetSource);
			sheet.close();
		}
	}

	public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
		XMLReader parser = XMLReaderFactory.createXMLReader();
		// .createXMLReader("org.apache.xerces.parsers.SAXParser");
		this.sst = sst;
		parser.setContentHandler(this);
		return parser;
	}

	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		// c => ��Ԫ��
		if (name.equals("c")) {
			// �����һ��Ԫ���� SST ����������nextIsString���Ϊtrue
			String cellType = attributes.getValue("t");
			String rowStr = attributes.getValue("r");
			curCol = this.getRowIndex(rowStr);
			if (cellType != null && cellType.equals("s")) {
				nextIsString = true;
			} else {
				nextIsString = false;
			}
		}
		// �ÿ�
		lastContents = "";
	}

	public void endElement(String uri, String localName, String name) throws SAXException {
		// ����SST������ֵ�ĵ���Ԫ�������Ҫ�洢���ַ���
		// ��ʱcharacters()�������ܻᱻ���ö��
		if (nextIsString) {
			try {
				int idx = Integer.parseInt(lastContents);
				lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
			} catch (Exception e) {

			}
		}
		// v => ��Ԫ���ֵ�������Ԫ�����ַ�����v��ǩ��ֵΪ���ַ�����SST�е�����
		// ����Ԫ�����ݼ���rowlist�У�����֮ǰ��ȥ���ַ���ǰ��Ŀհ׷�
		if (name.equals("v")) {
			String value = lastContents.trim();
			value = value.equals("") ? " " : value;
			int cols = curCol - preCol;
			if (cols > 1) {
				for (int i = 0; i < cols - 1; i++) {
					rowlist.add(preCol, "");
				}
			}
			preCol = curCol;
			rowlist.add(curCol - 1, value);
		} else {
			// �����ǩ����Ϊ row ����˵���ѵ���β������ optRows() ����
			if (name.equals("row")) {
				int tmpCols = rowlist.size();
				if (curRow > this.titleRow && tmpCols < this.rowsize) {
					for (int i = 0; i < this.rowsize - tmpCols; i++) {
						rowlist.add(rowlist.size(), "");
					}
				}
				try {
					optRows(sheetIndex, curRow, rowlist);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (curRow == this.titleRow) {
					this.rowsize = rowlist.size();
				}
				rowlist.clear();
				curRow++;
				curCol = 0;
				preCol = 0;
			}
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		// �õ���Ԫ�����ݵ�ֵ
		lastContents += new String(ch, start, length);
	}

	// �õ���������ÿһ��cԪ�ص�r���Թ���Ϊ��ĸ�����ֵ���ʽ����ĸ���Ϊ���������������Ϊ��������
	// ��AB45,��ʾΪ�ڣ�A-A+1��*26+��B-A+1��*26�У�45��
	public int getRowIndex(String rowStr) {
		rowStr = rowStr.replaceAll("[^A-Z]", "");
		byte[] rowAbc = rowStr.getBytes();
		int len = rowAbc.length;
		float num = 0;
		for (int i = 0; i < len; i++) {
			num += (rowAbc[i] - 'A' + 1) * Math.pow(26, len - i - 1);
		}
		return (int) num;
	}

	public int getTitleRow() {
		return titleRow;
	}

	public void setTitleRow(int titleRow) {
		this.titleRow = titleRow;
	}
}
