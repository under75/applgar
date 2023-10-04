package ru.sartfoms.applgar.service;

import java.util.Collection;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;

import ru.sartfoms.applgar.exception.ExcelGeneratorException;
import ru.sartfoms.applgar.model.ApplRowData;
import ru.sartfoms.applgar.util.ExcelGenerator;

public class ApplExcelGenerator extends ExcelGenerator {
	private Collection<ApplRowData> data;

	public ApplExcelGenerator(Collection<ApplRowData> rows) throws ExcelGeneratorException {
		super(rows);
		data = rows;

		createHeader();
		createBody();
	}

	@Override
	protected void createBody() {
		Font bodyFont = template.createFont();
		bodyFont.setFontName("Calibri");

		CellStyle bodyStyleAlignmentLeft = template.createCellStyle();
		bodyStyleAlignmentLeft.setFont(bodyFont);
		bodyStyleAlignmentLeft.setAlignment(HorizontalAlignment.LEFT);

		CellStyle bodyStyleAlignmentCenter = template.createCellStyle();
		bodyStyleAlignmentCenter.setFont(bodyFont);
		bodyStyleAlignmentCenter.setAlignment(HorizontalAlignment.CENTER);

		int rowNum = 2;// firstDataRow
		for (ApplRowData rowData : data) {
			int column = 0;
			XSSFRow row = sheet.createRow(rowNum++);
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentCenter), rowData.getNum());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentCenter), rowData.getSmoCode());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentCenter), rowData.getFsmoCode());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentLeft), rowData.getFsmoName());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentCenter),
					rowData.getApplDate() != null ? rowData.getApplDate().format(DATE_FORMATTER) : "");
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentCenter), rowData.getApplType());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentCenter), rowData.getApplCause());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentLeft), rowData.getPersonLastName());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentLeft), rowData.getPersonFirstName());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentLeft), rowData.getPersonPatronymic());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentCenter),
					rowData.getPersonBirsday() != null ? rowData.getPersonBirsday().format(DATE_FORMATTER) : "");
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentCenter), rowData.getPersonGender());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentCenter), rowData.getPersonHomePhone());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentCenter), rowData.getPersonWorkPhone());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentLeft), rowData.getPersonEmail());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentCenter),
					rowData.getRepresentativeHomePhone());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentCenter),
					rowData.getRepresentativeWorkPhone());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentLeft), rowData.getRepresentativeEmail());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentCenter), rowData.getInspectorCode());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentLeft), rowData.getInspectorFullName());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentLeft), rowData.getPersonAddressReg());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentLeft), rowData.getPersonAddressPr());
			setCellValue(createCellAndFormat(row, column++, bodyStyleAlignmentCenter), rowData.getPersonPolisNumber());

		}
		for (int i = 0; i < 25; i++) {
			sheet.autoSizeColumn(i);
		}

	}

	@Override
	protected void createHeader() {
		Object[] columns = new String[] { "№", "код СМО", "код филиала", "филиал СМО", "дата заявл.", "тип заявл.",
				"прич. заявл.", "фамилия", "имя", "отчество", "ДР", "пол", "телефон дом.", "телефон раб.", "email",
				"телефон предст. дом.", "телефон предст. раб.", "email предст.", "код инспектора", "ФИО инспектора",
				"адрес рег.", "адрес пр.", "№ полиса (ЕНП)" };

		CellStyle headerStyle = template.createCellStyle();
		Font headerFont = template.createFont();
		headerFont.setBold(true);
		headerFont.setFontName("Calibri");
		headerStyle.setFont(headerFont);
		headerStyle.setWrapText(true);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);

		XSSFRow row = sheet.createRow(0);
		for (int cn = 0; cn < columns.length; cn++) {
			setCellValue(createCellAndFormat(row, cn, headerStyle), columns[cn]);
		}

	}
}
