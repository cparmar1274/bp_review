package tosca.bp_review.pojos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.google.gson.Gson;

import tosca.bp_review.utility.BPUtil;

public class BluePrintInput {

	public Map<String, List<String>> bpInput = new HashMap<String, List<String>>();

	public String blueprintInputPath;

	public BPUtil bpUtil;

	public List<String> processBlueprint(String blueprintInputPath) {
		List<String> message = new ArrayList<String>();
		this.blueprintInputPath = blueprintInputPath;
		this.bpUtil = new BPUtil();

		try {
			if (bpUtil.checkDirectory(blueprintInputPath + "\\DEV")) {
				bpInput.put("DEV", bpUtil.getListOfFiles(blueprintInputPath + "\\DEV"));
			} else {
				bpInput.put("DEV", new ArrayList<String>());
			}
		} catch (Exception e) {
			message.add("Invalid DEV input json file. Please review."+e.getMessage());
		}

		try {
			if (bpUtil.checkDirectory(blueprintInputPath + "\\SIT")) {
				bpInput.put("SIT", bpUtil.getListOfFiles(blueprintInputPath + "\\SIT"));
			} else {
				bpInput.put("SIT", new ArrayList<String>());
			}
		} catch (Exception e) {
			message.add("Invalid SIT input json file. Please review."+e.getMessage());
		}

		try {
			if (bpUtil.checkDirectory(blueprintInputPath + "\\SYS")) {
				bpInput.put("SYS", bpUtil.getListOfFiles(blueprintInputPath + "\\SYS"));
			} else {
				bpInput.put("SYS", new ArrayList<String>());
			}
		} catch (Exception e) {
			message.add("Invalid SYS input json file. Please review."+e.getMessage());
		}

		try {
			if (bpUtil.checkDirectory(blueprintInputPath + "\\PAT")) {
				bpInput.put("PAT", bpUtil.getListOfFiles(blueprintInputPath + "\\PAT"));
			} else {
				bpInput.put("PAT", new ArrayList<String>());
			}
		} catch (Exception e) {
			message.add("Invalid PAT input json file. Please review."+e.getMessage());
		}

		try {
			if (bpUtil.checkDirectory(blueprintInputPath + "\\PROD")) {
				bpInput.put("PROD", bpUtil.getListOfFiles(blueprintInputPath + "\\PROD"));
			} else {
				bpInput.put("PROD", new ArrayList<String>());
			}
		} catch (Exception e) {
			message.add("Invalid PROD input json file. Please review."+e.getMessage());
		}

		try {
			if (bpUtil.checkDirectory(blueprintInputPath + "\\BAT")) {
				bpInput.put("BAT", bpUtil.getListOfFiles(blueprintInputPath + "\\BAT"));
			} else {
				bpInput.put("BAT", new ArrayList<String>());
			}
		} catch (Exception e) {
			message.add("Invalid BAT input json file. Please review."+e.getMessage());
		}

		return message;
	}

	public Set<String> getDevInputs() {
		return getInputs("DEV");
	}

	public Set<String> getSITInputs() {
		return getInputs("SIT");
	}

	public Set<String> getSYSInputs() {
		return getInputs("SYS");
	}

	public Set<String> getPATInputs() {
		return getInputs("PAT");
	}

	public Set<String> getPRODInputs() {
		return getInputs("PROD");
	}

	public Set<String> getBATInputs() {
		return getInputs("BAT");
	}

	private Set<String> getInputs(String type) {
		Set<String> input = new TreeSet<String>();
		Gson gson = new Gson();
		for (String jsonString : this.bpInput.get(type)) {
			HashMap<String, String> fileData = gson.fromJson(jsonString, HashMap.class);
			input.addAll(fileData.keySet());
		}
		return input;
	}

	public void refreshSystem() {
		this.bpInput.clear();
		this.blueprintInputPath = "";
		this.bpUtil = null;
	}
}
