package tosca.bp_review.pojos;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

@JsonIgnoreProperties(ignoreUnknown = true)
class BPObject {

	public Map<String, Object> inputs;
}

public class BluePrint {

	public Map<String, Object> inputs;
	
	public Integer inputSize;

	public BluePrint() {
		super();
	}

	public void processBluePrint(String blueprintFilePath) throws Exception {

		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		BPObject data = mapper.readValue(new File(blueprintFilePath), BPObject.class);
		this.inputs = data.inputs;
		this.inputSize = data.inputs.keySet().size();
	}

	public Set<String> getInputs() {
		Set<String> bpInputs = new TreeSet<String>();
		bpInputs.addAll(this.inputs.keySet());
		return bpInputs;
	}
	
	public Integer getInputSize(){
		return this.inputSize;
	}
	
	public void refreshSystem(){
		this.inputs.clear();
		this.inputSize=0;
	}
}
