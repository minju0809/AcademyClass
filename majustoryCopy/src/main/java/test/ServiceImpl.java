package test;

public class ServiceImpl implements ServiceTest {

	@Override
	public void insert(TestVO vo) {
		System.out.println("===> vo:" + vo);
		
	}

}
