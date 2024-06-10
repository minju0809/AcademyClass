package login;

public class LoginServiceImpl implements LoginService {
	
	private LoginDao dao ;
	
	public LoginServiceImpl(){		
	  	dao = new LoginDaoImpl();
	}
	@Override
	public String login(LoginVO vo) {		
		return dao.login(vo);
	}

}
