package com.alibaba.otter.node.extend.fileresolver;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.alibaba.otter.shared.etl.extend.fileresolver.FileInfo;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022/11/23 8:02
 */

public class TestFileResolver extends AbstractFileResolver{
    public FileInfo[] getFileInfo(Map<String, String> rowMap) {
        // 基本步骤：
        // 1. 获取binlog中的变更字段，比如组成文件有多个字段组成version+path
        // 2. 基于字段内容，构造一个文件路径，目前开源版本只支持本地文件的同步.(如果是网络文件，建议进行NFS mount到ndde机器).
        // 3. 返回FileInfo数组，(目前不支持目录同步，如果是目录需要展开为多个FileInfo的子文件)，如果不需要同步，则返回null.
        String path = rowMap.get("path"); //注意为大写
        if(path==null||"".equals(path)){
            return null;
        }
        String[] split = path.split("/");
        String path2 = "";
        for (int i = 0; i < split.length; i++) {
            if(i>1) {
                path2 = path2 +split[i]+"/";
            }
        }
        path2 = "/home/fastdfs/storage/data/"+path2.substring(0,path2.length()-1);
        FileInfo fileInfo = null;
        if (StringUtils.isNotEmpty(path2)) {
            fileInfo = new FileInfo(path2);
            return new FileInfo[] { fileInfo };
        } else {
            return null;
        }
    }
}
