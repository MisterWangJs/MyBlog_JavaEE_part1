package entity;

public class Saying {
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSaycontent() {
		return saycontent;
	}
	public void setSaycontent(String saycontent) {
		this.saycontent = saycontent;
	}
	public String getSaytime() {
		return saytime;
	}
	public void setSaytime(String saytime) {
		this.saytime = saytime;
	}
	private int sid;
	private String saycontent;
	private String saytime;
	@Override
	public String toString() {
		return "Saying [sid=" + sid + ", saycontent=" + saycontent + ", saytime=" + saytime + "]";
	}
	
	
	
}
