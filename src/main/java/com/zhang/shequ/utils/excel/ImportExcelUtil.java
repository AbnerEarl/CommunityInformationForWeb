package com.zhang.shequ.utils.excel;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * Excel导入
 */
public class ImportExcelUtil {

	//对外提供读取excel 的方法
	public static List<List<Object>> readExcel(MultipartFile file) throws IOException {
		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
		if ("xls".equals(extension)) {
			return read2003Excel(file.getInputStream());
		} else if ("xlsx".equals(extension)) {
			return read2007Excel(file.getInputStream());
		} else {
			throw new IOException("不支持的文件类型");
		}
	}

	//读取 office 2003 excel
	private static List<List<Object>> read2003Excel(InputStream stream) throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		HSSFWorkbook hwb = new HSSFWorkbook(stream);
		HSSFSheet sheet = hwb.getSheetAt(0);
		Object value = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null || isBlankHSSFRow(row)) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");// 格式化 number String
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
				DecimalFormat nf = new DecimalFormat("0");// 格式化数字
				switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case HSSFCell.CELL_TYPE_NUMERIC:
						if ("@".equals(cell.getCellStyle().getDataFormatString())) {
							value = df.format(cell.getNumericCellValue());
						} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
							value = nf.format(cell.getNumericCellValue());
						} else if(HSSFDateUtil.isCellDateFormatted(cell)){
							value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
						}else if(String.valueOf(cell.getNumericCellValue()).indexOf("E") == -1){
						    value = String.valueOf(cell.getNumericCellValue());
						}else {
						    value = new DecimalFormat("#").format(cell.getNumericCellValue()); 
						}
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:
						value = cell.getBooleanCellValue();
						break;
	                case HSSFCell.CELL_TYPE_FORMULA:
	                    value = cell.getCellFormula();
	                    break;
					case HSSFCell.CELL_TYPE_BLANK:
						value = "";
						break;
					default:
						value = cell.toString();
					}
				if (value == null) {
					continue;
				}
				linked.add(value);
			}
			if(CollectionUtils.isEmpty(linked)){
			    continue;
			}
			list.add(linked);
		}
		return list;
	}
	
	private static boolean isBlankHSSFRow(HSSFRow row){
        if(row == null) return true;
        boolean result = true;
        for(int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++){
            HSSFCell cell = row.getCell(i, HSSFRow.RETURN_BLANK_AS_NULL);
            Object value = null;
            if(cell != null){
                switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    value = String.valueOf((int) cell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    value = String.valueOf(cell.getCellFormula());
                    break;
                case HSSFCell.CELL_TYPE_BLANK:
                    value = "";
                    break;
                default:
                    value = cell.toString();
                    break;
                }
                if(!value.toString().trim().equals("")){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
	
	//读取Office 2007 excel
	private static List<List<Object>> read2007Excel(InputStream stream)
			throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(stream);
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null || isBlankXSSFRow(row)) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");// 格式化 number String
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
				DecimalFormat nf = new DecimalFormat("0");// 格式化数字
				switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case XSSFCell.CELL_TYPE_NUMERIC:
						if ("@".equals(cell.getCellStyle().getDataFormatString())) {
							value = df.format(cell.getNumericCellValue());
						} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
							value = nf.format(cell.getNumericCellValue());
						} else if(HSSFDateUtil.isCellDateFormatted(cell)){
							value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
						}else if(String.valueOf(cell.getNumericCellValue()).indexOf("E") == -1){
						    value = String.valueOf(cell.getNumericCellValue());
						}else {
						    value = new DecimalFormat("#").format(cell.getNumericCellValue()); 
						}
						break;
					case XSSFCell.CELL_TYPE_BOOLEAN:
						value = cell.getBooleanCellValue();
						break;
	                case XSSFCell.CELL_TYPE_FORMULA:
	                    value = cell.getCellFormula();
	                    break;
					case XSSFCell.CELL_TYPE_BLANK:
						value = "";
						break;
					default:
						value = cell.toString();
					}
				if (value == null || "".equals(value)) {
					continue;
				}
				linked.add(value);
			}
			if(CollectionUtils.isEmpty(linked)){
			    continue;
			}
			list.add(linked);
		}
		return list;
	}
	
	private static boolean isBlankXSSFRow(XSSFRow row){
        if(row == null) return true;
        boolean result = true;
        for(int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++){
            XSSFCell cell = row.getCell(i, XSSFRow.RETURN_BLANK_AS_NULL);
            Object value = null;
            if(cell != null){
                switch (cell.getCellType()) {
                case XSSFCell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue();
                    break;
                case XSSFCell.CELL_TYPE_NUMERIC:
                    value = String.valueOf((int) cell.getNumericCellValue());
                    break;
                case XSSFCell.CELL_TYPE_BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case XSSFCell.CELL_TYPE_FORMULA:
                    value = String.valueOf(cell.getCellFormula());
                    break;
                case XSSFCell.CELL_TYPE_BLANK:
                    value = "";
                    break;
                default:
                    value = cell.toString();
                    break;
                }
                if(!value.toString().trim().equals("")){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
	
}