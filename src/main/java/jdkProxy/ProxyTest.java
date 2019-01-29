package jdkProxy;

public class ProxyTest {
	public static void main(String[] args) {
		UserService us = new UserServiceImpl();
		MyInvocationHandler mi = new MyInvocationHandler(us);
		UserService us1 = (UserService) mi.getProxy();
		us1.add();
	}
}
