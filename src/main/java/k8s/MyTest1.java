package k8s;

import io.kubernetes.client.Copy;
import io.kubernetes.client.Exec;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.Streams;
import io.kubernetes.client.util.exception.CopyNotSupportedException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022/9/19 14:23
 */

public class MyTest1 {
    public static void main(String[] args)
            throws Exception {
        ApiClient client = Config.fromConfig("D:\\config");
        CoreV1Api api = new CoreV1Api(client);
//        V1PodList list =
//                api.listNamespacedPod("default", null, null, null, null, null, null, null, null, null,false);
//        for (V1Pod item : list.getItems()) {
//            System.out.println(item.getMetadata().getName());
//        }

    }
}
