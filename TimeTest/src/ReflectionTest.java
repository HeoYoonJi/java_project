 
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
 
public class ReflectionTest {
 
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
 
        DemoVO demoVO = new DemoVO();
 
        Class<String>[] arg = new Class[1];
        arg[0] = String.class;
       
        Method method = demoVO.getClass().
        		getDeclaredMethod("setVisit1", arg);
        method.invoke(demoVO, "abcd1234");
 
        System.out.println("visit1 : " + demoVO.getVisit1());
 
    }
 
}