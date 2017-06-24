package tosca.bp_review.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import tosca.bp_review.pojos.BluePrint;
import tosca.bp_review.pojos.BluePrintInput;

@Service
public class BlueprintReviewEngine {

	public BluePrint bluePrint;

	public BluePrintInput bluePrintInput;

	public BlueprintReviewEngine() {
		super();
		this.bluePrint = new BluePrint();
		this.bluePrintInput = new BluePrintInput();
	}

	public String getYamlStatus(String bpPath) {
		String message = "Blueprint.yaml is valid. <span class=\"glyphicon glyphicon-ok\"></span>";
		try {
			// this.bluePrint.refreshSystem();
			this.bluePrint.processBluePrint(bpPath);
		} catch (Exception e) {
			e.printStackTrace();
			message = "Invalid blueprint yaml file. Please review it.<span class=\"glyphicon glyphicon-remove\"></span>";
			;
			message += "<br>" + e.getMessage();
		}
		return message;
	}

	public String getJSONStatus(String bpInputFolderPath) {
		String message = "All the input files has valid json. <span class=\"glyphicon glyphicon-ok\"></span>";
		// this.bluePrintInput.refreshSystem();
		List<String> messages = this.bluePrintInput.processBlueprint(bpInputFolderPath);
		if (messages.size() > 0) {
			message = "Some of input files has invalid json. Please review it.<span class=\"glyphicon glyphicon-remove\"></span> <BR>"
					+ new Gson().toJson(messages);
		}
		return message;
	}

	public String getBPStatus() {
		List<String> messages = new ArrayList<String>();
		messages.add("Blueprint Inputs : " + bluePrint.getInputs());
		messages.add("Blueprint Inputs Size: " + bluePrint.getInputSize());
		return new Gson().toJson(messages);
	}

	public String getBPInputStatus() {
		List<String> messages = new ArrayList<String>();

		Set<String> data = this.bluePrintInput.getDevInputs();
		if (data != null && data.size() > 0) {
			messages.add("DEV Input Size : " + data.size());
			messages.add("DEV Inputs : " + data);
		} else {
			messages.add("No DEV inputs found.");
		}
		

		data = this.bluePrintInput.getBATInputs();
		if (data != null && data.size() > 0) {
			messages.add("BAT Input Size : " + data.size());
			messages.add("BAT Inputs : " + data);
		} else {
			messages.add("No BAT inputs found.");
		}

		data = this.bluePrintInput.getSITInputs();
		if (data != null && data.size() > 0) {
			messages.add("SIT Input Size : " + data.size());
			messages.add("SIT Inputs : " + data);
		} else {
			messages.add("No SIT inputs found.");
		}
		
		
		data = this.bluePrintInput.getSYSInputs();
		if (data != null && data.size() > 0) {
			messages.add("SYS Input Size : " + data.size());
			messages.add("SYS Inputs : " + data);
		} else {
			messages.add("No SYS inputs found.");
		}
		
		
		data = this.bluePrintInput.getPATInputs();
		if (data != null && data.size() > 0) {
			messages.add("PAT Input Size : " + data.size());
			messages.add("PAT Inputs : " + data);
		} else {
			messages.add("No PAT inputs found.");
		}
		
		
		data = this.bluePrintInput.getPRODInputs();
		if (data != null && data.size() > 0) {
			messages.add("PROD Input Size : " + data.size());
			messages.add("PROD Inputs : " + data);
		} else {
			messages.add("No PROD inputs found.");
		}

		return new Gson().toJson(messages);
	}

}
