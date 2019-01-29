package jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler{
	//Ŀ�����
	private Object target;
	public MyInvocationHandler(Object target) {
		this.target = target;
	}
	/**
	 * ִ��Ŀ�����ķ���
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("-------before-------");
		Object result = method.invoke(target, args);
		System.out.println("-------after--------");
		return result;
	}
	/**
	 * ��ȡĿ�����Ĵ������
	 * @return
	 */
	public Object getProxy() {
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
	}
	
}
