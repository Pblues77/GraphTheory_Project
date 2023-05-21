package open_edit_txt;

public class MyNotepadModel {
	private String fileName;
	private String content;
	
	public MyNotepadModel() {
		this.fileName = "";
		this.content = "";
	}
	/**
	 * Getter and Setter
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
