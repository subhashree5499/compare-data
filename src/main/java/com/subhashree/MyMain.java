package com.subhashree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.subhashree.data.Root;

//@Author Subhashree Sahu 

public class MyMain {
	private static final String FILE_NAME_REL = "C:\\Users\\subhashree\\Downloads\\ProductDecision_Historical data.xlsx";
	private static final String FILE_NAME_RESTI = "C:\\Users\\subhashree\\Downloads\\Restriction Level.xlsx";

	private static final String FILE_NAME_JSON_REL1 = "C:\\Users\\subhashree\\Downloads\\PB DIL Data Data\\ReleaseLevelPB Data\\2016-01-1 to 2016-12-31.json";
	private static final String FILE_NAME_JSON_REL2 = "C:\\Users\\subhashree\\Downloads\\PB DIL Data Data\\ReleaseLevelPB Data\\2017-01-1 to 2017-12-31.json";
	private static final String FILE_NAME_JSON_REL3 = "C:\\Users\\subhashree\\Downloads\\PB DIL Data Data\\ReleaseLevelPB Data\\2018-01-1 to 2018-12-31.json";
	private static final String FILE_NAME_JSON_REL4 = "C:\\Users\\subhashree\\Downloads\\PB DIL Data Data\\ReleaseLevelPB Data\\2019-01-1 to 2019-12-31.json";
	private static final String FILE_NAME_JSON_REL5 = "C:\\Users\\subhashree\\Downloads\\PB DIL Data Data\\ReleaseLevelPB Data\\2020-01-1 to 2020-12-31.json";
	private static final String FILE_NAME_JSON_REL6 = "C:\\Users\\subhashree\\Downloads\\PB DIL Data Data\\ReleaseLevelPB Data\\2021-01-1 to 2021-09-24.json";
	
	private static final String FILE_NAME_JSON_RESTI1 = "C:\\Users\\subhashree\\Downloads\\PB DIL Data Data\\RestrictionLevel PB Data\\2016-01-1 to 2016-12-31.json";
	private static final String FILE_NAME_JSON_RESTI2 = "C:\\Users\\subhashree\\Downloads\\PB DIL Data Data\\RestrictionLevel PB Data\\2017-01-1 to 2017-12-31.json";
	private static final String FILE_NAME_JSON_RESTI3 = "C:\\Users\\subhashree\\Downloads\\PB DIL Data Data\\RestrictionLevel PB Data\\2018-01-1 to 2018-12-31.json";
	private static final String FILE_NAME_JSON_RESTI4 = "C:\\Users\\subhashree\\Downloads\\PB DIL Data Data\\RestrictionLevel PB Data\\2019-01-1 to 2019-12-31.json";
	private static final String FILE_NAME_JSON_RESTI5 = "C:\\Users\\subhashree\\Downloads\\PB DIL Data Data\\RestrictionLevel PB Data\\2020-01-1 to 2020-12-31.json";
	private static final String FILE_NAME_JSON_RESTI6 = "C:\\Users\\subhashree\\Downloads\\PB DIL Data Data\\RestrictionLevel PB Data\\2021-01-1 to 2021-09-24.json";
	
//	private static final LocalDate start = LocalDate.parse("2018-01-01");
//	private static final LocalDate end = LocalDate.parse("2019-12-31");

	public static void main(String[] args) {
		DataDTO jsonData = readJson();
		DataDTO xlsData = readXls();

		List<String> releaseJsonProds = jsonData.getRelData();
		List<String> releaseXlsProds = xlsData.getRelData();
		List<String> OreleaseJsonProds = jsonData.getOriginalRelData();
		List<String> OreleaseXlsProds = xlsData.getOriginalRelData();
		
		System.out.println("With Duplicate");
		System.out.println("release json: t:"+releaseJsonProds.size());
		System.out.println("release xls: t:"+releaseXlsProds.size());
		System.out.println("release json: o:"+OreleaseJsonProds.size());
		System.out.println("release xls: o:"+OreleaseXlsProds.size());
		System.out.println("Without Duplicate");
		System.out.println("release json: t:"+new TreeSet<String>(releaseJsonProds).size());
		System.out.println("release xls: t:"+new TreeSet<String>(releaseXlsProds).size());
		System.out.println("release json: o:"+new TreeSet<String>(OreleaseJsonProds).size());
		System.out.println("release xls: o:"+new TreeSet<String>(OreleaseXlsProds).size());
		
		System.out.println("========================");
		
		
		List<String> restrictionJsonProds = jsonData.getResData();
		List<String> restrictionXlsProds = xlsData.getResData();
		List<String> OrestrictionJsonProds = jsonData.getOriginalRestData();
		List<String> OrestrictionXlsProds = xlsData.getOriginalRestData();
		
		System.out.println("With Duplicate");
		System.out.println("restrictions json: t:"+restrictionJsonProds.size());
		System.out.println("restrictions xls: t:"+restrictionXlsProds.size());
		System.out.println("restrictions json: o:"+OrestrictionJsonProds.size());
		System.out.println("restrictions xls: o:"+OrestrictionXlsProds.size());
		System.out.println("Without Duplicate");
		System.out.println("restrictions json: t:"+new TreeSet<String>(restrictionJsonProds).size());
		System.out.println("restrictions xls: t:"+new TreeSet<String>(restrictionXlsProds).size());
		System.out.println("restrictions json: o:"+new TreeSet<String>(OrestrictionJsonProds).size());
		System.out.println("restrictions xls: o:"+new TreeSet<String>(OrestrictionXlsProds).size());
		
		
		
		
		newProductFoundInPB(restrictionJsonProds, restrictionXlsProds, OrestrictionXlsProds, "restrictions");
		//newProductFoundInDIL(restrictionJsonProds, restrictionXlsProds, OrestrictionJsonProds, "restrictions");
		
//		newProductFoundInPB(releaseJsonProds, releaseXlsProds, OreleaseXlsProds, "release");
//		newProductFoundInDIL(releaseJsonProds, releaseXlsProds, OreleaseJsonProds, "release");
//		
//		generateXlsSingle(new TreeSet<String>(OreleaseJsonProds), "Total_Release_DIL_Products.xlsx");
//		generateXlsSingle(new TreeSet<String>(OreleaseXlsProds), "Total_Release_PB_Products.xlsx");
		
	//	generateXlsSingle(OrestrictionJsonProds, "Total_Restriction_DIL_Products.xlsx");
	//	generateXlsSingle(OrestrictionXlsProds, "Total_Restriction_PB_Products.xlsx");

	}
	
	private static void newProductFoundInPB(List<String> json, List<String> xls, List<String> originalXls,String type) {
		
		Set<String> jsonFiltered = new TreeSet<String>(json);
		Set<String> xlsFiltered = new TreeSet<String>(xls);
	
		
		xlsFiltered.removeAll(jsonFiltered);  
		List<String> finalList = new ArrayList<String>();
		for(String obj:xlsFiltered) {
			for(String obj1:originalXls) {
				if(obj.equals(obj1.replaceAll("\\s", ""))) {
					finalList.add(obj1);
					break;
				}
			}
		}
		System.out.println("NewProductFoundInPB: finalList size:"+finalList.size());
		generateXlsSingle(finalList, type+"_NewProductFoundInPB.xlsx");
	}
	
	private static void newProductFoundInDIL(List<String> json, List<String> xls, List<String> originalJson,String type) {
		
		
		Set<String> jsonFiltered = new TreeSet<String>(json);
		Set<String> xlsFiltered = new TreeSet<String>(xls);
		System.out.println("jsonFiltered:"+jsonFiltered);
		System.out.println("xlsFiltered:"+xlsFiltered);
		jsonFiltered.removeAll(xlsFiltered);  
		System.out.println("after removed jsonFiltered:"+jsonFiltered);
		List<String> finalList = new ArrayList<String>();
		for(String obj:jsonFiltered) {
			for(String obj1:originalJson) {
				if(obj.equals(obj1.replaceAll("\\s", ""))) {
					finalList.add(obj1);
					break;
				}
			}
		}
		System.out.println("NewProductFoundInDIL: finalList size:"+finalList.size());
		generateXlsSingle(finalList, type+"_NewProductFoundInDIL.xlsx");
	}

	private static DataDTO readJson() {
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		DataDTO dataDTO = new DataDTO();
		try {
			List<String> relProds = new ArrayList<String>();
			List<String> resProds = new ArrayList<String>();
			List<String> oriRelProds = new ArrayList<String>();
			List<String> oriResProds = new ArrayList<String>();
			
			Root relroot1 = objectMapper.readValue(new File(FILE_NAME_JSON_REL1), Root.class);
			List<String> relData1 = relroot1.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber().replaceAll("\\s", ""))
					.collect(Collectors.toList());
			List<String> relData11 = relroot1.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber())
					.collect(Collectors.toList());
			
			
			
			Root relroot2 = objectMapper.readValue(new File(FILE_NAME_JSON_REL2), Root.class);
			List<String> relData2 = relroot2.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber().replaceAll("\\s", ""))
					.collect(Collectors.toList());
			List<String> relData22 = relroot2.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber())
					.collect(Collectors.toList());
			
			Root relroot3 = objectMapper.readValue(new File(FILE_NAME_JSON_REL3), Root.class);
			List<String> relData3 = relroot3.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber().replaceAll("\\s", ""))
					.collect(Collectors.toList());
			List<String> relData33 = relroot3.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber())
					.collect(Collectors.toList());
			
			Root relroot4 = objectMapper.readValue(new File(FILE_NAME_JSON_REL4), Root.class);
			List<String> relData4 = relroot4.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber().replaceAll("\\s", ""))
					.collect(Collectors.toList());
			List<String> relData44 = relroot4.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber())
					.collect(Collectors.toList());
			
			Root relroot5 = objectMapper.readValue(new File(FILE_NAME_JSON_REL5), Root.class);
			List<String> relData5 = relroot5.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber().replaceAll("\\s", ""))
					.collect(Collectors.toList());
			List<String> relData55 = relroot5.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber())
					.collect(Collectors.toList());
			
			Root relroot6 = objectMapper.readValue(new File(FILE_NAME_JSON_REL6), Root.class);
			List<String> relData6 = relroot6.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber().replaceAll("\\s", ""))
					.collect(Collectors.toList());
			List<String> relData66 = relroot6.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber())
					.collect(Collectors.toList());
			
			relProds.addAll(relData1);
			relProds.addAll(relData2);
			relProds.addAll(relData3);
			relProds.addAll(relData4);
			relProds.addAll(relData5);
			relProds.addAll(relData6);
			dataDTO.setRelData(relProds);
			
			oriRelProds.addAll(relData11);
			oriRelProds.addAll(relData22);
			oriRelProds.addAll(relData33);
			oriRelProds.addAll(relData44);
			oriRelProds.addAll(relData55);
			oriRelProds.addAll(relData66);
			dataDTO.setOriginalRelData(oriRelProds);
			
			
			
			Root resroot1 = objectMapper.readValue(new File(FILE_NAME_JSON_RESTI1), Root.class);
			List<String> resData1 = resroot1.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber().replaceAll("\\s", ""))
					.collect(Collectors.toList());
			List<String> resData11 = resroot1.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber())
					.collect(Collectors.toList());
			
			Root resroot2 = objectMapper.readValue(new File(FILE_NAME_JSON_RESTI2), Root.class);
			List<String> resData2 = resroot2.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber().replaceAll("\\s", ""))
					.collect(Collectors.toList());
			List<String> resData22 = resroot2.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber())
					.collect(Collectors.toList());
			
			Root resroot3 = objectMapper.readValue(new File(FILE_NAME_JSON_RESTI3), Root.class);
			List<String> resData3 = resroot3.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber().replaceAll("\\s", ""))
					.collect(Collectors.toList());
			List<String> resData33 = resroot3.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber())
					.collect(Collectors.toList());
			
			Root resroot4 = objectMapper.readValue(new File(FILE_NAME_JSON_RESTI4), Root.class);
			List<String> resData4 = resroot4.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber().replaceAll("\\s", ""))
					.collect(Collectors.toList());
			List<String> resData44 = resroot4.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber())
					.collect(Collectors.toList());
			
			Root resroot5 = objectMapper.readValue(new File(FILE_NAME_JSON_RESTI5), Root.class);
			List<String> resData5 = resroot5.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber().replaceAll("\\s", ""))
					.collect(Collectors.toList());
			List<String> resData55 = resroot5.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber())
					.collect(Collectors.toList());
			
			Root resroot6 = objectMapper.readValue(new File(FILE_NAME_JSON_RESTI6), Root.class);
			List<String> resData6 = resroot6.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber().replaceAll("\\s", ""))
					.collect(Collectors.toList());
			List<String> resData66 = resroot6.getData().stream().map(obj -> obj.getIdentification().getGlobalProductIdentifier().getProductNumber())
					.collect(Collectors.toList());

			resProds.addAll(resData1);
			resProds.addAll(resData2);
			resProds.addAll(resData3);
			resProds.addAll(resData4);
			resProds.addAll(resData5);
			resProds.addAll(resData6);
			dataDTO.setResData(resProds);
			
			oriResProds.addAll(resData11);
			oriResProds.addAll(resData22);
			oriResProds.addAll(resData33);
			oriResProds.addAll(resData44);
			oriResProds.addAll(resData55);
			oriResProds.addAll(resData66);
			dataDTO.setOriginalRestData(oriResProds);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataDTO;
	}

	private static DataDTO readXls() {
		List<String> histDatas = new ArrayList<>();
		List<String> restDatas = new ArrayList<>();
		List<String> oriHistDatas = new ArrayList<>();
		List<String> OriRestDatas = new ArrayList<>();
		DataDTO dataDTO = new DataDTO();
		try {
			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_REL));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				if (currentRow.getRowNum() == 0)
					continue;
				Cell cell1 = currentRow.getCell(1);
				if (cell1.getCellTypeEnum() == CellType.STRING) {
					histDatas.add(cell1.getStringCellValue().replaceAll("\\s", ""));
					oriHistDatas.add(cell1.getStringCellValue());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_RESTI));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				if (currentRow.getRowNum() == 0)
					continue;
				Cell cell1 = currentRow.getCell(1);
				if (cell1 != null) {
					if (cell1.getCellTypeEnum() == CellType.STRING) {
						restDatas.add(cell1.getStringCellValue().replaceAll("\\s", ""));
						OriRestDatas.add(cell1.getStringCellValue());
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dataDTO.setRelData(histDatas);
		dataDTO.setResData(restDatas);
		dataDTO.setOriginalRelData(oriHistDatas);
		dataDTO.setOriginalRestData(restDatas);
		return dataDTO;
	}

	private static void generateXlsSingle(List<String> data, String fileName) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();

			XSSFSheet sheet = workbook.createSheet("sheet1");// creating a blank sheet
			int rownum = 0;
			for (String d : data) {
				Row row = sheet.createRow(rownum++);
				createListSingle(d, row);

			}

			FileOutputStream out = new FileOutputStream(new File("C:\\Users\\subhashree\\Downloads\\" + fileName)); // file
																														// name
																														// with
																														// path
			workbook.write(out);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createListSingle(String user, Row row) // creating cells for each row
	{
		Cell cell = row.createCell(0);
		cell.setCellValue(user);

	}

	

}
