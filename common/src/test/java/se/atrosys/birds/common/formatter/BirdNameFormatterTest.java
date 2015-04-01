package se.atrosys.birds.common.formatter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BirdNameFormatterTest {
	BirdNameFormatter formatter;

	@Before
	public void setup() {
		formatter = new BirdNameFormatter();
	}

	@Test
	public void shouldFormatName() {
		Assert.assertEquals("Ladlas", formatter.formatName("LADLAS"));
	}

	@Test
	public void shouldReturnEmptyStringForNullOrEmptyString() {
		Assert.assertEquals("", formatter.formatName(null));
		Assert.assertEquals("", formatter.formatName(""));
	}

	@Test
	public void shouldSplitCommas() {
		List<String> result = formatter.formatNames("Sterna repressa,Larus larus");

		Assert.assertNotNull(result);
		Assert.assertEquals(2, result.size());
	}

	@Test
	public void shouldReturnUpperCaseForSingleChar() {
		Assert.assertEquals("P", formatter.formatName("p"));
	}

	@Test
	public void shouldReturnEmptyListWhenNullAsParameter() {
		List<String> list = formatter.formatNames(null);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());
	}
}