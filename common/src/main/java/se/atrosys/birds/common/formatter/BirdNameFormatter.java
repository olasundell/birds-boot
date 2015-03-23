package se.atrosys.birds.common.formatter;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BirdNameFormatter {
	public String formatName(String name) {
		if (name == null || name.isEmpty()) {
			return "";
		} else if (name.length() == 1) {
			return name.toUpperCase();
		}

		return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
	}

	/**
	 * Formats a comma-separated string of names so every string has the first letter only capitalized.
	 * @param names a comma-separated string
	 * @return a list of strings with each string having its first and only its first letter capitalized.
	 */
	public List<String> formatNames(String names) {
		if (names == null || names.isEmpty()) {
			return new ArrayList<>();
		}

		return Arrays.asList(names.split(","))
				.stream()
				.map(n -> new BirdNameFormatter().formatName(n))
				.collect(Collectors.toList());
	}
}
