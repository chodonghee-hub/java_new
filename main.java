import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class main {

	static ArrayList<Article> artcList = new ArrayList<Article>();
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		while(true) {
			if (new User()._selectMode_() == false) break;
			else {
				while(true) {
					System.out.println("====================");
					System.out.printf("ARTICLE : %d\n",artcList.size());
					System.out.println("====================");
					System.out.print("▷ Enter the commands : ");
					String cmd = sc.nextLine();
					if(cmd.equals("help")) {
						System.out.println("- [C] : make article");
						System.out.println("- [R] : read article");
						System.out.println("- [U] : update aritlce");
						System.out.println("- [D] : delete article");
						System.out.println("- [P] : reply article ");
						System.out.println("- [X] : finish program");
					}
					
					if(cmd.equals("x")) {
						System.out.println("* Finish program ... ");
						User.userName = null;
						User.userPos = null;
						break;
					}
					
					else if(cmd.equals("c")) {
						System.out.println("====================");
						System.out.println("▷ Add Mode ");
						Article artc = new Article();
						System.out.print("Enter the Title : ");
						String title = sc.nextLine();
						System.out.print("Enter the detail : ");
						String detail = sc.nextLine();
						artc.TITLE = title;
						artc.DETAIL = detail;
						artc.WRITER = User.userName;
						artcList.add(artc);
						System.out.println("* SUCCESS make article");
					}
					
					else if(cmd.equals("r")) {
						System.out.println("====================");
						System.out.println("▷ Read Mode ");
						System.out.print("* Select the commands \n- [A] : read all \n- [N] : not all \n- [D] : read detail \n>>> ");
						String cmdRead = sc.nextLine();
						if (cmdRead.equals("a")) {
							Map<Integer, ArrayList<Article>> rmap = _paging_(artcList);
							Set<Integer> keys = rmap.keySet();
							
//							System.out.println("keySet Size : " + keys.size());
//							for (int key : keys) System.out.println((key));
//							for(int no : _paging_(artcList).keySet()) {
							
							for(int no : keys) {
								System.out.printf("▶ Page : %d \n",no);
								for (Article index : rmap.get(no)) readTitle(index);
							}
							System.out.println("*********************");
						}
						
						else if (cmdRead.equals("n")) {
							Article rst = _getArticle_(_enterArticleNum_());
							readTitle(rst);
							System.out.println("*********************");
						}
						
						else if (cmdRead.equals("d")) {
							Article rst = _getArticle_(_enterArticleNum_());
							readDetail(rst);
							System.out.println("*********************");
						}
						
					}
					
					else if(cmd.equals("u")) {
						System.out.println("====================");
						System.out.println("▷ Update Mode ");
						// update article 
						Article artc = _getArticle_(_enterArticleNum_());
						if(_isExitArticle_(artc) && _isMyArticle_(artc))updateArticle(artc);
						else System.out.println("* ERROR - Can't touch someone's data ...");
					}
					
					else if(cmd.equals("d")) {
						System.out.println("====================");
						System.out.println("▷ Delete Mode ");
						// delete article 
						int artcNum = _enterArticleNum_();
						Article artc = _getArticle_(artcNum);
						if(_isExitArticle_(artc) && _isMyArticle_(artc)) {
							artcList.remove(artcNum);
							System.out.println("* SUCCESS delete data");
						}
						else System.out.println("* ERROR - Can't touch someone's data ...");
					}
					
					else if(cmd.equals("p")) {
						System.out.println("====================");
						System.out.println("▷ Reply Mode ");
						replyArticle();
					}
				}
			}
		}
	}
	
	static int _enterArticleNum_() {
		System.out.print("Enter the article number : ");
		int artcNum = Integer.parseInt(sc.nextLine());
		return artcNum;
	}
	
	static Article _getArticle_(int artcNum) {
		for(Article artc : artcList) if(artc.ARTC_NUM == artcNum) return artc;
		System.out.println("* ERROR - Can't find article ... ");
		return null;
	}
	
	static boolean _isMyArticle_(Article artc) {
		if(artc.WRITER.equals(User.userName)) return true;
		return false;
	}
	
	static boolean _isExitArticle_(Article artc) {
		for(Article index : artcList) if (index.TITLE.equals(artc.TITLE) && index.ARTC_NUM == artc.ARTC_NUM && index.DETAIL.equals(artc.DETAIL)) return true;
		return false;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	static HashMap<Integer, ArrayList<Article>> _paging_(ArrayList<Article> alist) {
		ArrayList<Article> temp; 
		HashMap<Integer, ArrayList<Article>> rst = new HashMap<>();
		int set_ArtcPerPage = 10;
//		int lastPage = (alist.size() % set_ArtcPerPage) > 0 ? (alist.size() / set_ArtcPerPage) + 1  : alist.size() / set_ArtcPerPage; 

		Integer startPage = 1;
		while(alist.isEmpty() != true) {
			temp = new ArrayList<>();
			for(int i=0; i<alist.size(); i++) {
				temp.add(alist.get(0));
				alist.remove(0);
				if (i+1 == set_ArtcPerPage || ((alist.size() < set_ArtcPerPage) && i == alist.size())) {
					rst.put(startPage, temp);
					startPage ++;
					break;
				}
			}
		}
		return rst;
	}
	
	static void readTitle(Article artc) {
		System.out.println("*********************");
		System.out.printf("- [Number] : %d \n- [Title] : %s \n- [Writer] : %s\n",artc.ARTC_NUM, artc.TITLE, artc.WRITER);
	}
	
	static void readDetail(Article artc) {
		System.out.println("*********************");
		System.out.printf("- [Number] : %d \n- [Title] : %s \n- [Writer] : %s\n- [Detail] : %s \n- [Reply] : %d \n", artc.ARTC_NUM, artc.TITLE, artc.WRITER, artc.DETAIL, artc.REPLY.size());
		if (artc.REPLY.isEmpty() != true) {
			System.out.println("▒▒▒▒▒▒▒▒▒▒");
			for (Reply index : artc.REPLY) {
				System.out.printf("＠ REPL : %s \n", index.TITLE);
				System.out.printf("- [Writer] : %s \n- [Detail] : %s \n", index.WRITER, index.DETAIL);
			}System.out.println("▒▒▒▒▒▒▒▒▒▒");
		}
		
	}
	
	static void updateArticle(Article artc) {
		System.out.print("▷ Enter the new data \n>>> ");
		String newData = sc.nextLine();
		artc.DETAIL = newData;
		System.out.println("* SUCCESS update new data");
	}
	
	static void replyArticle() {
		Article artc = _getArticle_(_enterArticleNum_());
		System.out.print("▷ Enter the title : ");
		String title = sc.nextLine();
		System.out.print("▷ Enter your reply : ");
		String repl = sc.nextLine();
		Reply rst = new Reply(title, repl);
		artc.REPLY.add(rst);
		System.out.println("* SUCCESS - reply the article");
	}
	
	
	
	
}
