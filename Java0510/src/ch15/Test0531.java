package ch15;

import java.util.*;

public class Test0531 {

	public static void main(String[] args) {
		List<BoardVO> li = new Vector<>();
		li.add(new BoardVO("제목1", "내용1", "글쓴이1"));
		li.add(new BoardVO("제목2", "내용2", "글쓴이2"));
		li.add(new BoardVO("제목3", "내용3", "글쓴이3"));
		System.out.println(li.size());
		
		for(int i = li.size() - 1; i >= 0; i--) {
			BoardVO m = li.get(i);
			System.out.println(m);
		}
	}

}
