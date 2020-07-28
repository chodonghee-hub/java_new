import java.util.ArrayList;

public class Article {
	String TITLE;
	String DETAIL;
	int ARTC_NUM;
	String WRITER;
	ArrayList<Reply> REPLY = new ArrayList<>();;
	static int count = 1;
	
	Article(){
		this.ARTC_NUM = count;
		this.count ++;
	}
	
	Article(String title, String detail){
		this.TITLE = title;
		this.DETAIL = detail;
		this.ARTC_NUM = count;
		this.count ++;
	}
}
