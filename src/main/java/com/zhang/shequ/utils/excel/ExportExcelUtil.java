package com.zhang.shequ.utils.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Excel导出
 */
public class ExportExcelUtil {
	
	private static final Logger log = LoggerFactory.getLogger(ExportExcelUtil.class);
	
	public static <T> void createExcel(String sheetTitle, String[] headers, String[] keys, Collection<T> dataset, HttpServletResponse response) {
		exportExcel(sheetTitle, headers, keys, dataset, response, "yyyy-MM-dd");
	}
	
	public static <T> void createExcel(String sheetTitle, String[] headers, String[] keys, Collection<T> dataset, HttpServletResponse response, String pattern) {
		exportExcel(sheetTitle, headers, keys, dataset, response, pattern);
	}

	public static <T> void exportExcel(String sheetTitle, String[] headers, String[] keys, Collection<T> dataset, HttpServletResponse response, String pattern) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(sheetTitle);
		// 设置表格默认列高度为24个字节
        sheet.setDefaultRowHeightInPoints(24);
        // 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short)12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short)4, 2, (short)6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("zhang");
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
        int count = 0;
        while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T)it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			//Field[] fields = t.getClass().getDeclaredFields();
            count = headers.length < keys.length ? headers.length : keys.length;
            for (int i = 0; i < count; i++) {
                HSSFCell cell = row.createCell(i);
				String fieldName = keys[i];
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Class<? extends Object> tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					Boolean isNumber = false;
					if (value instanceof Date) {
						Date date = (Date)value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					} else if (value instanceof byte[]) {
						// 有图片时，设置行高为60px;
						row.setHeightInPoints(60);
						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short)(35.7 * 80));
						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[])value;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short)6, index, (short)6, index);
                        anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					}else if(value instanceof Number){
						isNumber = true;
						textValue = value.toString();
					} else {
						// 其它数据类型都当作字符串简单处理
						if (value != null)
							textValue = value.toString();
						else
							textValue = "";
					}
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						if (isNumber) {
							//判断value是否为整数（小数部分是否为0）
							if(textValue.matches("^[-\\+]?[\\d]*$")){
								style2.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,#0"));//数据格式只显示整数
								cell.setCellValue(Integer.parseInt(textValue));
							}else {
								// 是小数数当作double处理
								style2.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));//保留两位小数点
								cell.setCellValue(Double.parseDouble(textValue));
							}
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
							HSSFFont font3 = workbook.createFont();
							font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
						cell.setCellStyle(style2);
					}
                    if(headers[i].getBytes().length >= textValue.getBytes().length){
                        sheet.setColumnWidth(i, headers[i].getBytes().length*308);
                    }else {
                        sheet.setColumnWidth(i, textValue.getBytes().length*384);
                    }
				} catch (SecurityException e) {
					log.info(e.getMessage(), e);
				} catch (NoSuchMethodException e) {
					log.info(e.getMessage(), e);
				} catch (IllegalArgumentException e) {
					log.info(e.getMessage(), e);
				} catch (IllegalAccessException e) {
					log.info(e.getMessage(), e);
				} catch (InvocationTargetException e) {
					log.info(e.getMessage(), e);
				} finally {
					// 清理资源
				}
			}
		}
        OutputStream out = null;
		try {
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String((sheetTitle + ".xls").getBytes(), "ISO-8859-1"));
			out = response.getOutputStream();
			workbook.write(out);
		} catch (IOException e) {
			log.info(e.getMessage(), e);
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				log.info(e.getMessage(), e);
			}
		}
	}

}