public class Reply {
	String TITLE;
	int REPL_NUM;
	String DETAIL;
	String WRITER;
	
	Reply(){
		
	}
	
	Reply(String title, String detail){
		TITLE = title;
		DETAIL = detail;
		WRITER = User.userName;
	}
}
