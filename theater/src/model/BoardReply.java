package model;

public class BoardReply {
	
	private int boardReplyCode;
	private String boardReplyContent;
	private String customerId;
	private int boardCode;
	public BoardReply() {
		super();
	}
	public int getBoardReplyCode() {
		return boardReplyCode;
	}
	public void setBoardReplyCode(int boardReplyCode) {
		this.boardReplyCode = boardReplyCode;
	}
	public String getBoardReplyContent() {
		return boardReplyContent;
	}
	public void setBoardReplyContent(String boardReplyContent) {
		this.boardReplyContent = boardReplyContent;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public int getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}
}