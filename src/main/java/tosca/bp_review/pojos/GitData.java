package tosca.bp_review.pojos;

public class GitData {

	public String url;
	public String tag;

	public GitData(String url, String tag) {
		super();
		this.url = url;
		this.tag = tag;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
