package ch15;

public class BoardVO {
	String title;
	String content;
	String writer;
	
	public BoardVO(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
	@Override
	public String toString() {
		return "BoardVO [title=" + title + ", content=" + content + ", writer=" + writer + "]";
	}
}
