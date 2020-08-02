package entity;

public class Blog {
	
	private int bid;
	private String blog_title;
	private String blog_content;
	private String pubtime;
	private String author;
	private String blog_type;
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBlog_title() {
		return blog_title;
	}
	public void setBlog_title(String blog_title) {
		this.blog_title = blog_title;
	}
	public String getBlog_content() {
		return blog_content;
	}
	public void setBlog_content(String blog_content) {
		this.blog_content = blog_content;
	}
	public String getPubtime() {
		return pubtime;
	}
	public void setPubtime(String pubtime) {
		this.pubtime = pubtime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBlog_type() {
		return blog_type;
	}
	public void setBlog_type(String blog_type) {
		this.blog_type = blog_type;
	}

	@Override
	public String toString() {
		return "Blog [bid=" + bid + ", blog_title=" + blog_title + ", blog_content=" + blog_content + ", pubtime="
				+ pubtime + ", author=" + author + ", blog_type=" + blog_type + "]";
	}
	
}
