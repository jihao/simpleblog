package cn.heapstack.beanutils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanPrinter {
	private static final Object[] NO_ARGUMENTS = new Object[0];
	public static void printBean(Object theObject) {

		Class<? extends Object> clazz = theObject.getClass();
		System.out.println( clazz.getSimpleName()+"..."+theObject);

		try {
			BeanInfo info = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] descriptor = info.getPropertyDescriptors();
			for(PropertyDescriptor pd : descriptor)
			{
				Method m = pd.getReadMethod();
				Object result = m.invoke(theObject, NO_ARGUMENTS);
				System.out.println("\t"+pd.getName()+":" +result);
			}
			
		} catch (IntrospectionException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	public static String printBeanToString(Object theObject) {
		Class<? extends Object> clazz = theObject.getClass();
		StringBuilder sb = new StringBuilder();
		sb.append(clazz.getSimpleName()+"..."+theObject+"\n");
		//System.out.println( clazz.getSimpleName()+"..."+theObject);

		try {
			BeanInfo info = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] descriptor = info.getPropertyDescriptors();
			for(PropertyDescriptor pd : descriptor)
			{
				Method m = pd.getReadMethod();
				Object result = m.invoke(theObject, NO_ARGUMENTS);
				sb.append("\t"+pd.getName()+":" +result+"\n");
				//System.out.println("\t"+pd.getName()+":" +result);
			}
		} catch (IntrospectionException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
