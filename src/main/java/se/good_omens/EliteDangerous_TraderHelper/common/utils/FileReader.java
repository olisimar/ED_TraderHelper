 package se.good_omens.EliteDangerous_TraderHelper.common.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import se.good_omens.EliteDangerous_TraderHelper.common.exceptions.FileMissingException;


public class FileReader {

	public static String readFile(String file) throws IOException, URISyntaxException {
		return FileReader.readFile(new URI(file));
	}

	public static String readFile(URI file) throws IOException {
		return new String(Files.readAllBytes(Paths.get( file )));
	}

	public static String readFile(String correctPath, String fileName) throws FileMissingException {
		try {
			return new String(Files.readAllBytes(Paths.get(correctPath, fileName)));
		} catch (IOException e) {
			throw new FileMissingException("Failed to locate file: "+ e.getMessage());
		}
	}
}
