import java.lang.reflect.Proxy;

public class ProxyReflectionExample {

	public static void main(String[] args) throws Exception {
		TestInterface proxyObj = (TestInterface)Proxy.newProxyInstance(
			ProxyReflectionExample.class.getClassLoader(),
			new Class[] { TestInterface.class },
			(proxy, method, args1) -> {
				System.out.println("Method: " + method.getName());
				return null;
			});

		try {
			// It will throw `NoSuchMethodException` in `native-image`.
			proxyObj.getClass().getMethod("foo").invoke(proxyObj);
			proxyObj.getClass().getMethod("bar").invoke(proxyObj);
		} catch (Throwable t) {
			t.printStackTrace();
		}

		Thread.sleep(10000);
	}
}
