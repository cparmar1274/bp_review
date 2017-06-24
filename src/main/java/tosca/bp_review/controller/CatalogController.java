package tosca.bp_review.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import tosca.bp_review.pojos.Catalog;
import tosca.bp_review.pojos.EnvironmentData;
import tosca.bp_review.pojos.GitData;

@Controller
@RequestMapping(value={"/catalog","/catalog/"})
public class CatalogController {
	
	@RequestMapping(value={"/",""})
	public String getMessage(HttpServletRequest request){
		return "catalog";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public @ResponseBody String getCatalogfile(HttpServletRequest request){
		String catalogName = request.getParameter("catalog_name");
		String tag = request.getParameter("tag_name");
		String blueprintName = request.getParameter("blueprint_name")+"/blueprint.yaml";
		
		Map<String,EnvironmentData> envData = new HashMap<String,EnvironmentData>();
		envData.put("PAT",new EnvironmentData("Enter pat input file path..."));
		envData.put("SIT",new EnvironmentData("Enter sit input file path..."));
		
		return new Gson().toJson(new Catalog(catalogName,new GitData("http://git.mgmt1.cloud.td.com/its-cloud/sit-staging-blueprints",tag),blueprintName,"todo",envData));
	}

}
