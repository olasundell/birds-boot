package se.atrosys.service.info.factory;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.atrosys.birds.common.model.Aves;
import se.atrosys.birds.excel.ReadExcel;
import se.atrosys.service.common.response.FamilyResponse;

import java.io.IOException;
import java.util.Collections;

public class FamilyResponseFactoryTest {
	@Test
	public void shouldHaveBirdsInFamily() throws OpenXML4JException, XmlException, IOException {
		Aves aves = new ReadExcel().read();
		final FamilyResponseFactory familyResponseFactory = new FamilyResponseFactory();
		familyResponseFactory.setAves(aves);
		FamilyResponse response = familyResponseFactory.createResponse(Collections.singletonList("Laridae"));

		Assert.assertEquals(1, response.getFamilies().size());
		Assert.assertNotEquals(0, response.getFamilies().get(0).getBirdNames());
	}

}