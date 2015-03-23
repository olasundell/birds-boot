package se.atrosys.birds.excel;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.junit.Assert;
import org.junit.Test;
import se.atrosys.birds.common.model.Aves;

import java.io.IOException;

public class ReadExcelTest {
	@Test
	public void shouldRead() throws OpenXML4JException, XmlException, IOException {
		Aves aves = new ReadExcel().read();

		Assert.assertNotNull(aves);
		Assert.assertNotEquals(0, aves.getOrders().size());
		Assert.assertNotEquals(0, aves.getFamilies().size());
		Assert.assertNotEquals(0, aves.getBirds().size());
	}
}