package ru.sartfoms.applgar.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ru.sartfoms.applgar.exception.ExcelGeneratorException;


public abstract class ExcelGenerator {
	protected XSSFWorkbook template;
	protected XSSFSheet sheet;
	public final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

	public ExcelGenerator(Collection<?> rows) throws ExcelGeneratorException {
		if (rows.size() > 65535)
			throw new ExcelGeneratorException("Превышено допустимое количество строк для Excel, 65535 максимум");
		template = new XSSFWorkbook();
		sheet = template.createSheet();
	}

	public ByteArrayInputStream toExcel() throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		template.write(out);

		return new ByteArrayInputStream(out.toByteArray());
	}

	protected abstract void createBody();

	protected abstract void createHeader();

	protected XSSFCell createCellAndFormat(XSSFRow row, Integer index, CellStyle style) {
		XSSFCell cell = row.createCell(index);
		cell.setCellStyle(style);

		return cell;
	}

	protected void setCellValue(XSSFCell cell, Object value) {
		if (value != null) {
			cell.setCellValue((String) value);
		} else {
			cell.setCellValue("");
		}
	}

}

