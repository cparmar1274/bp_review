package tosca.bp_review.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlueprintReviewController{

	@Autowired(required = true)
	public BlueprintReviewEngine bpEngineService;

	@RequestMapping(value = {"/",""})
	public String landingPage(HttpServletRequest request) {
		return "blueprint";
	}

	@RequestMapping(value = "/checkYaml", method = RequestMethod.POST)
	public @ResponseBody String checkValidYaml(HttpServletRequest request) {
		String blueprint = request.getParameter("bp_path");
		return String.valueOf(bpEngineService.getYamlStatus(blueprint + "\\blueprint.yaml"));
	}

	@RequestMapping(value = "/checkJSON", method = RequestMethod.POST)
	public @ResponseBody String checkValidJSON(HttpServletRequest request) {
		String blueprint = request.getParameter("bp_path");
		return String.valueOf(bpEngineService.getJSONStatus(blueprint + "\\inputs"));
	}

	@RequestMapping(value = "/bpstatus", method = RequestMethod.POST)
	public @ResponseBody String compareInputs(HttpServletRequest request) {
		return String.valueOf(bpEngineService.getBPStatus());
	}

	@RequestMapping(value = "/bpinputstatus", method = RequestMethod.POST)
	public @ResponseBody String getBlueprintReviewStats(HttpServletRequest request) {
		return String.valueOf(bpEngineService.getBPInputStatus());
	}

}
