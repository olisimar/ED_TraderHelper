package se.good_omens.EliteDangerous_TraderHelper.common.utils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

import se.good_omens.EliteDangerous_TraderHelper.common.exceptions.FileMissingException;

public class FileHandler {

	public static String readFile(String file) throws IOException, URISyntaxException {
		return FileHandler.readFile(new URI(file));
	}

	public static String readFile(URI file) throws IOException {
		return new String(Files.readAllBytes(Paths.get(file)));
	}

	public static String readFile(String correctPath, String fileName) throws FileMissingException {
		try {
			return new String(Files.readAllBytes(Paths.get(correctPath, fileName)));
		} catch (IOException e) {
			throw new FileMissingException("Failed to locate file: " + e.getMessage());
		}
	}

	public static boolean writeFile(String correctPathAndFileName, String data) {
		Path toFile = Paths.get(correctPathAndFileName);
		if (Files.isWritable(toFile)) {
			try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(toFile, CREATE, TRUNCATE_EXISTING))) {
				out.write(data.getBytes(), 0, data.getBytes().length);
				out.flush();
				out.close();
				System.out.println("[File written]");
				return true;
			} catch (IOException e) {
				System.err.println(e);
			}
		}
		return false;
	}
}
