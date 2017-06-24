package tosca.bp_review.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitFile {

	@JsonProperty(value = "blod_id")
	public String blobId;

	@JsonProperty(value = "commit_id")
	public String commitId;

	@JsonProperty(value = "content")
	public String content;

	@JsonProperty(value = "encoding")
	public String encoding;

	@JsonProperty(value = "file_name")
	public String fileName;

	@JsonProperty(value = "file_path")
	public String filePath;

	@JsonProperty(value = "ref")
	public String ref;

	@JsonProperty(value = "file_size")
	public Integer fileSize;

	public String getBlobId() {
		return blobId;
	}

	public void setBlobId(String blobId) {
		this.blobId = blobId;
	}

	public String getCommitId() {
		return commitId;
	}

	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

}
