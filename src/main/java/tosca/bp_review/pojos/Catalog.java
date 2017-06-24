package tosca.bp_review.pojos;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Catalog {

	@JsonProperty(value="name")
	public String name;
	
	@JsonProperty(value="git")
	public GitData git;
	
	@JsonProperty(value="blueprint")
	public String blueprint;
	
	@JsonProperty(value="documentation")
	public String documentation;
	
	@JsonProperty(value="environment")
	public Map<String, EnvironmentData> environment;
	
	public Catalog(String name, GitData git, String blueprint, String documentation,Map<String, EnvironmentData> envData) {
		super();
		this.name = name;
		this.git = git;
		this.blueprint = blueprint;
		this.documentation = documentation;
		this.environment = envData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GitData getGit() {
		return git;
	}

	public void setGit(GitData git) {
		this.git = git;
	}

	public String getBlueprint() {
		return blueprint;
	}

	public void setBlueprint(String blueprint) {
		this.blueprint = blueprint;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public Map<String, EnvironmentData> getEnvironment() {
		return environment;
	}

	public void setEnvironment(Map<String, EnvironmentData> environment) {
		this.environment = environment;
	}
	
	
	
}

