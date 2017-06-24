package tosca.bp_review.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

public class BPUtil {

	public String readFile(String path) throws IOException {
		String fileString = "";
		fileString = new String(Files.readAllBytes(Paths.get(path)));
		return fileString;
	}

	public boolean checkDirectory(String path) {
		return new File(path).exists();
	}

	public List<String> getListOfFiles(String path) throws Exception {
		List<String> fileData = new ArrayList<String>();
		File folder = new File(path);
		File[] files = folder.listFiles();

		for (File file : files) {
			String filestr = new String(FileUtils.readFileToByteArray(file));
			new Gson().fromJson(filestr, HashMap.class);
			fileData.add(filestr);
		}

		return fileData;
	}

}
