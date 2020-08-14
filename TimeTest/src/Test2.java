
public class Test2 {

	public static void main(String[] args) {
		
		String books="[5,6,7]";
		String temp = books.replaceAll("\\[", "").replaceAll("\\]", "");
		int bookNum=0;
		String[] booksArr=null;
		
		
		//System.out.println("temp :"+temp);
		//System.out.println(books.contains(","));
		if(temp.contentEquals("")) {
			bookNum=0;
		}else if(!books.contains(",")) {
			booksArr=new String[1];
			booksArr[0]=temp;
			bookNum=1;
		}else {
			System.out.println(temp);
			booksArr=temp.split(",");
			bookNum=booksArr.length;
		}
	
		System.out.println("bookNum : "+bookNum);
		//System.out.println("booksArr : "+booksArr);
		for(String s : booksArr)
			System.out.println(s);
	}

}
