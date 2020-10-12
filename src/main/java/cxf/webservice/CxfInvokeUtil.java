package cxf.webservice;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * @Author: yyl
 * @Date: 2019/1/28 11:51
 */
public class CxfInvokeUtil {
    public static Object doInvoke(String url,Object obj,String method){
        JaxWsDynamicClientFactory factory=JaxWsDynamicClientFactory.newInstance();
        Client client=factory.createClient(url);
        Object [] result;
        try {
            result=client.invoke(method,obj);
            return result[0];
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args){
        doInvoke("111",null,"");
    }


}
