package tosca.bp_review.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import tosca.bp_review.pojos.GitFile;

@Controller
@RequestMapping(value={"/promotion","/promotion/"})
public class PromotionController {
	
	
	
	@RequestMapping(value={"/",""})
	public String getPromotion(HttpServletRequest request){
		return "promotion";
	}

	@RequestMapping(value="/getBlueprintContent",method=RequestMethod.POST)
	public @ResponseBody String getBlueprintContent(HttpServletRequest request){
		
		String token = request.getParameter("token_name");
		String blueprintName = request.getParameter("blueprint_name");
		String bpUrl = "http://git.cloud.td.com/api/v3/projects/3868/repository/files?private_token="+token+"&file_path="+blueprintName+"&ref=master";
		RestTemplate template = new RestTemplate();
		GitFile data = template.getForObject(bpUrl, GitFile.class);
		
		return new String(java.util.Base64.getMimeDecoder().decode(data.content));
	}
	
	@RequestMapping(value="/putBlueprintContent",method=RequestMethod.POST)
	public @ResponseBody String putBlueprintContent(HttpServletRequest request){
		
		String token = request.getParameter("token_name");
		String blueprintName = request.getParameter("blueprint_name");
		String content= request.getParameter("content");
		String bpUrl = "http://git.cloud.td.com/api/v3/projects/3868/repository/files?private_token="+token;				
		//BlueprintRequest bpRequest = new BlueprintRequest(blueprintName,content,"File updated using API");
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("file_name", blueprintName);
		params.put("branch_name","master");
		params.put("encoding","text");
		params.put("content", content);
		params.put("commit_message","file upated using API");
		
		RestTemplate template = new RestTemplate();
	    template.put(bpUrl, content, params);
			
		
		
		
		return "test";
	}
	
}
