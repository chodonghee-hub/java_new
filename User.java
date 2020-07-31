import java.util.*;

public class User {
	Scanner sc = new Scanner(System.in);
	ArrayList<String> ID = new ArrayList<>();
	ArrayList<Integer> PW = new ArrayList<>();
	ArrayList<String> NAME = new ArrayList<>();
	ArrayList<String> ADMIN = new ArrayList<>();
	
	static String userName = null;
	static String userPos = null;
	
	User(){
		signUp("timandsunny", 1234, "donghee");
		signUp("1",1,"test");
		addAdmin("timandsunny");
		_selectMode_();
	}
	
	boolean _selectMode_() {
		System.out.println("====================");
		System.out.println("▶ Select Mode ");
		while(userName == null) {
			System.out.print("▷ Enter the next proccess \n>>> ");
			String cmd = sc.nextLine();
			
			if (cmd.equals("help")) {
				System.out.println("- [S] : sign up");
				System.out.println("- [L] : login");
				System.out.println("- [X] : finish program");
			}
			
			else if (cmd.equals("s")) {
				System.out.println("====================");
				System.out.println("▷ ");
				System.out.print("▷ Enter new ID : ");
				String id = sc.nextLine();
				System.out.print("▷ Enter new PW : ");
				int pw = Integer.parseInt(sc.nextLine());
				System.out.print("▷ Enter your name : ");
				String name = sc.nextLine();
				signUp(id, pw, name);
			}
			
			else if (cmd.equals("l")) {
				login();
				return true;
			}
			
			else if (cmd.equals("x")) {
				System.out.println("* Finish program ...");
				return false;
			}
		}return true;
	}
	
	void addAdmin(String addID) {
		if(_findID_(addID) != -1) ADMIN.add(addID);
	}
	
	boolean _chkAdmin_(String id) {
		for(String index : ADMIN) if(index.equals(id)) return true;
		return false;
	}
	
	void signUp(String id, int pw, String name) {
		ID.add(id);
		PW.add(pw);
		NAME.add(name);
		System.out.printf("* SUCCESS sign up '%s' \n",name);
	}
	
	int _findID_(String id) {
		for(int i=0; i<ID.size(); i++) if(ID.get(i).equals(id)) return i;
		System.out.println("* ERROR - Can't find your ID ...");
		return -1;
	}
	
	void login() {
		System.out.print("▷ Enter your ID : ");
		String id = sc.nextLine();
		System.out.print("▷ Enter your PW : ");
		int pw = Integer.parseInt(sc.nextLine());
		if (_findID_(id) != -1) {
			int chkID = _findID_(id);
			if(ID.get(chkID).equals(id) && PW.get(chkID) == pw) {
				userName = NAME.get(chkID);
				if (_chkAdmin_(ID.get(chkID))) userPos = "admin";
				else userPos = "user";
				System.out.printf("* SUCCESS login - '%s' [%s] \n", userName, userPos);
			}
		}
	}
	
}
