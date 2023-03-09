import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyReflectionExample {

    public static void main(String[] args) throws Exception {
        TestInterface proxyObj = (TestInterface)Proxy.newProxyInstance(
            ProxyReflectionExample.class.getClassLoader(),
            new Class[] { TestInterface.class },
            (proxy, method, args1) -> {
                System.out.println("Method: " + method.getName() + ",     Declaring Class: " + method.getDeclaringClass().getName());
                return null;
            });

        try {
            // It will throw `NoSuchMethodException` in `native-image`.
            Method method = proxyObj.getClass().getMethod("foo");
            System.out.println("Method: " + method.getName() + ",     Declaring Class: " + method.getDeclaringClass().getName());
            method.invoke(proxyObj);

            method = proxyObj.getClass().getMethod("bar");
            System.out.println("Method: " + method.getName() + ",     Declaring Class: " + method.getDeclaringClass().getName());
            method.invoke(proxyObj);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        Thread.sleep(10000);
    }
}
