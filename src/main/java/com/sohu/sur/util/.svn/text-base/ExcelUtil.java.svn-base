package com.sohu.sur.util;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sohu.sur.model.admin.GoodUser;

/**
 * excel读取工具类
 * 
 * @author xuewuhao
 * 
 */
public class ExcelUtil {
	private static Log log = LogFactory.getLog(ExcelUtil.class);
	private List<GoodUser> list = new ArrayList<GoodUser>();

	/**
	 * 读取excel内容,返回读取excel内容（List）
	 * 
	 * @param fileName
	 * @param level
	 *            优质用户0 金牌用户1
	 * @return
	 */
	public List<GoodUser> readExcel(String fileName, String level) {
		try {
			Workbook book = Workbook.getWorkbook(new File(fileName));
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			// 得到第一列第一行的单元格
			int columnum = sheet.getColumns(); // 得到列数
			int rownum = sheet.getRows(); // 得到行数
			log.info("列=" + columnum);
			log.info("行=" + rownum);
			GoodUser gu = null;
			List listTmp = new ArrayList();
			for (int i = 0; i < rownum; i++) // 循环进行读写
			{
				for (int j = 0; j < columnum; j++) {
					Cell cell1 = sheet.getCell(j, i);
					String result = cell1.getContents();
					log.info("i=" + i + "j=" + j + result);
					gu = new GoodUser();
					gu.setName(result.trim());
					gu.setLevel(level);
					gu.setCreateTime(new Date());
					if (!listTmp.contains(result)) {
						listTmp.add(result);
						list.add(gu);
					}
				}
			}
			book.close();
		} catch (Exception e) {
			log.error(e);
		}
		return list;
	}

	/**
	 * @param datas
	 *            封装着Object[]的列表, 一般是String内容.
	 * @param title
	 *            每个sheet里的标题.
	 */
	public void writeExcel(OutputStream out, List datas, String[] title) {
		if (datas == null) {
			throw new IllegalArgumentException("写excel流需要List参数!");
		}
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(out);
			WritableSheet ws = workbook.createSheet("sheet 1", 0);
			int rowNum = 0; // 要写的行
			if (title != null) {
				putRow(ws, 0, title);// 压入标题
				rowNum = 1;
			}
			for (int i = 0; i < datas.size(); i++, rowNum++) {// 写sheet
				Object[] cells = (Object[]) datas.get(i);
				putRow(ws, rowNum, cells); // 压一行到sheet
			}

			workbook.write();
			workbook.close(); // 一定要关闭, 否则没有保存Excel
		} catch (RowsExceededException e) {
			log.error("jxl write RowsExceededException: " + e.getMessage());
		} catch (WriteException e) {
			log.error("jxl write WriteException: " + e.getMessage());
		} catch (IOException e) {
			log.error("jxl write file i/o exception!, cause by: "
					+ e.getMessage());
		}
	}
	/**
	 * 获取ins中指定属性的值
	 * 
	 * @param ins
	 *            excel的输入流
	 * @param attributes
	 *            需要获取的属性
	 */
	public String[][] getExcelContents(InputStream inS, String[] attributes) {
		String[][] contents = null;
		try {
			Workbook workbook = Workbook.getWorkbook(inS);
			Sheet sheet = workbook.getSheet(0);
			int rows = getRows(sheet);
			int columns = getColumns(sheet);
			String[] attributesAll = getAttribute(sheet, 0, columns, 0);
			String[][] cellsArray = getCells(sheet, 1, rows, 0, columns);
			contents = getContentsFromFixAttri(attributes, attributesAll,
					cellsArray);

		} catch (BiffException e) {
			log.error("jxl BiffException: " + e.getMessage());
			//e.printStackTrace();
		} catch (IOException e) {
			log.error("jxl IOException: " + e.getMessage());
			//e.printStackTrace();
		}
		return contents;
	}
	/**
	 * 获取contentsAll中指定属性的值
	 * 
	 * @param attributes
	 *            需要获取的属性
	 * @param attributeAll
	 *            所有属性
	 * @param contentsAll
	 *            所有值
	 */
	public String[][] getContentsFromFixAttri(String[] attributes,
			String[] attributeAll, String[][] contentsAll) {
		String[][] contents = null;
		List<Integer> indexS = new ArrayList<Integer>();
		if (attributes != null && attributeAll != null
				& attributeAll.length >= attributes.length) {
			for (int i = 0; i < attributes.length; i++) {
				for (int j = 0; j < attributeAll.length; j++) {
					if (attributes[i].equals(attributeAll[j])) {
						indexS.add(j);
					}
				}
			}
		}
		int columnCount = indexS.size();
		int rowCount = contentsAll.length;
		contents = new String[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				contents[i][j] = contentsAll[i][indexS.get(j)];
			}
		}
		return contents;
	}

	/**
	 * 获取属性数组
	 * 
	 * @param sheet
	 *            工作薄
	 * @param 属性所在row
	 *            行数
	 * @param startColumn
	 *            开始列
	 * @param endColumn
	 *            结束列
	 */
	public String[] getAttribute(Sheet sheet, int startColumn, int endColumn,
			int row) {
		String[] attributes = new String[endColumn - startColumn];
		int maxColumn = getColumns(sheet);
		int maxRow = getRows(sheet);
		if (row < maxRow) {
			for (int i = startColumn; i < endColumn && i < maxColumn; i++) {
				Cell attribute = sheet.getCell(i,row);
				attributes[i] = attribute.getContents();
			}
		}

		return attributes;
	}

	/**
	 * 获取每行单元格数组
	 * 
	 * @param sheet
	 *            工作薄
	 * @param startrow
	 *            行数
	 * @param endrow
	 *            结束行
	 * @param startcol
	 *            开始列
	 * @param endCol
	 *            结束列
	 * @return 每行单元格数组
	 */
	public String[][] getCells(Sheet sheet, int startrow, int endrow,
			int startcol, int endcol) {
		String[][] cellArray = new String[endrow - startrow][endcol - startcol];
		int maxRow = this.getRows(sheet);
		int maxCos = this.getColumns(sheet);
		for (int i = startrow; i < endrow && i < maxRow; i++) {

			for (int j = startcol; j < endcol && j < maxCos; j++) {

				cellArray[i - startrow][j - startcol] = sheet.getCell(j, i)
						.getContents();
			}

		}
		return cellArray;
	}

	/**
	 * 获取工作薄总行数
	 * 
	 * @param sheet
	 *            工作薄
	 * @return 工作薄总行数
	 */
	public int getRows(Sheet sheet) {
		return sheet == null ? 0 : sheet.getRows();
	}

	/**
	 * 获取最大列数
	 * 
	 * @param sheet
	 *            工作薄
	 * @return 总行数最大列数
	 */
	public int getColumns(Sheet sheet) {
		return sheet == null ? 0 : sheet.getColumns();
	}

	/**
	 * 获取每行单元格数组
	 * 
	 * @param sheet
	 *            工作薄
	 * @param row
	 *            行数
	 * @return 每行单元格数组
	 */
	public Cell[] getRows(Sheet sheet, int row) {
		return sheet == null || sheet.getRows() < row ? null : sheet
				.getRow(row);
	}

	/**
	 * 写一行
	 * 
	 * @param ws
	 * @param rowNum
	 * @param cells
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	private void putRow(WritableSheet ws, int rowNum, Object[] cells)
			throws RowsExceededException, WriteException {
		for (int j = 0; j < cells.length; j++) {// 写一行
			Label cell = new Label(j, rowNum, "" + cells[j]);
			ws.addCell(cell);
		}
	}
	
}
