package se.atrosys.service.sound.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class XenoCantoDTO {
//	"numPages" : 2,
//			"numRecordings" : "574",
//
	public int page;
	public int numPages;
	public String numRecordings;
	public String numSpecies;
	public List<Recording> recordings;

	public static class Recording {
//		"lat" : "61.1605",
		public String lat;
//		"ssp" : "",
		public String ssp;
//		"file" : "http://www.xeno-canto.org/213374/download",
		public String file;
//		"loc" : "Myllykylä, Heinola",
		public String loc;
//		"rec" : "Ilkka Heiskanen",
		public String rec;
//		"type" : "alarm call",
		public String type;
//		"q" : "no score",
		public String q;
//		"url" : "http://www.xeno-canto.org/213374",
		public String url;
//		"cnt" : "Finland",
		public String cnt;
//		"gen" : "Troglodytes",
		public String gen;
//		"lic" : "http://creativecommons.org/licenses/by-nc-sa/4.0/",
		public String lic;
//		"lng" : "25.9705",
		public String lng;
//		"sp" : "troglodytes",
		public String sp;
//		"en" : "Eurasian Wren",
		public String en;
//		"id" : "213374"
		public String id;
	}
}
