package se.good_omens.EliteDangerous_TraderHelper.common.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FileReader {

	public static String readFile(String file) throws IOException, URISyntaxException {
		return FileReader.readFile(new URI(file));
	}

	public static String readFile(URI file) throws IOException {
		return new String(Files.readAllBytes(Paths.get( file )));
	}
}
