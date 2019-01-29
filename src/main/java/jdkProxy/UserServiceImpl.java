package jdkProxy;

public class UserServiceImpl implements UserService{

	@Override
	public void add() {
		System.out.println("-----------------add--------------------");
		innerTest();
	}

	@Override
	public void innerTest() {
		System.out.println("-----------------inner test-----------------");
	}
}
