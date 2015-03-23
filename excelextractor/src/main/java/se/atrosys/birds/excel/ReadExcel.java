package se.atrosys.birds.excel;

import org.apache.poi.POIOLE2TextExtractor;
import org.apache.poi.POITextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.xmlbeans.XmlException;
import se.atrosys.birds.common.formatter.BirdNameFormatter;
import se.atrosys.birds.common.model.Aves;
import se.atrosys.birds.common.model.Bird;
import se.atrosys.birds.common.model.Family;
import se.atrosys.birds.common.model.Order;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ReadExcel {
	BirdNameFormatter formatter = new BirdNameFormatter();
//	STRUTHIONIFORMES	Struthionidae	Ostriches	Common Ostrich	Struthio camelus	Linnaeus, 1758	R	LC			Struthio camelus and S. molybdophanes (del Hoyo and Collar 2014) were previously lumped as S. camelus following Sibley and Monroe (1990, 1993).	del Hoyo and Collar (2014)	45020636	1016860

	public Aves read() throws IOException, OpenXML4JException, XmlException {
		InputStream is = ClassLoader.getSystemResourceAsStream("birds.xlsx");
		Workbook workbook = WorkbookFactory.create(is);

		Map<String, Bird> birds = new HashMap<>();
		Map<String, Family> families = new HashMap<>();
		Map<String, Order> orders = new HashMap<>();

		for (int i = 0 ; i < workbook.getNumberOfSheets() ; i++ ) {
			Sheet sheet = workbook.getSheetAt(i);
			for (int j = 3 ; j < sheet.getLastRowNum() ; j ++ ) {
				Row row = sheet.getRow(j);

				// not officially recognized as a species, move along.
				if (!"R".equalsIgnoreCase(row.getCell(6).getStringCellValue())) {
					continue;
				}

				Bird.Builder builder = Bird.builder();

				Order order = getOrCreateOrder(orders, row.getCell(0));
				builder.withOrder(order);

				Family family = getOrCreateFamily(order, families, row.getCell(1), row.getCell(2));
				builder.withFamily(family);

				builder.withName(Locale.ENGLISH, formatter.formatName(row.getCell(3).getStringCellValue()));
				builder.withName(Bird.LATIN, formatter.formatName(row.getCell(4).getStringCellValue()));

				Bird bird = builder.build();

				family.addBird(bird);
				birds.put(bird.getName(), bird);
			}
		}

		Aves aves = new Aves();

		aves.addBirds(birds);
		aves.addFamilies(families);
		aves.addOrders(orders);

		return aves;
	}

	private Family getOrCreateFamily(Order order, Map<String, Family> families, Cell cell, Cell cell1) {
		String name = cell.getStringCellValue();
		if (families.containsKey(name)) {
			return families.get(name);
		} else {
			Family family = new Family();
			family.addName(Bird.LATIN, name);
			family.addName(Locale.ENGLISH, cell1.getStringCellValue());
			family.setOrder(order);

			order.addFamily(family);

			families.put(name, family);

			return family;
		}
	}

	private Order getOrCreateOrder(Map<String, Order> orders, Cell cell) {
		String name = cell.getStringCellValue();
		if (orders.containsKey(name)) {
			return orders.get(name);
		} else {
			Order order = new Order(name);
			orders.put(name, order);

			return order;
		}
	}
}
