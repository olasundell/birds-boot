package se.atrosys.service.mainpage.factory;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainPageResponseFactoryTest {
	@Test
	public void shouldMapStream() {
		List<List<String>> mainList = new ArrayList<>();
		int k=0;

		for (int i = 0 ; i < 4 ; i++) {
			final ArrayList<String> list = new ArrayList<>();

			for (int j = 0 ; j < 5 ; j++) {
				list.add(String.valueOf(k++));
			}

			mainList.add(list);
		}

		String result = mainList.stream().flatMap(List::stream).collect(Collectors.joining(","));

		Assert.assertNotNull(result);
	}

}