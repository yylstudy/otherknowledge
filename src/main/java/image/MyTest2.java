package image;

import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-10-29
 */
public class MyTest2 {
    private static Logger logger = LoggerFactory.getLogger(MyTest2.class);

//    public static void main(String[] args) throws Exception{
//        Thumbnails.of("D:\\3.jpg").scale(1f).outputQuality(0.25f).toFile("D:\\31.jpg");
//
//    }
    public static void main(String[] args) throws Exception {
//        byte[] bytes = FileUtils.readFileToByteArray(new File("D:\\27.jpg"));
//        bytes = compressPicForScale(bytes, 50, "x");// 图片小于300kb
        try{
            long l = System.currentTimeMillis();
            compressPicForScale(new File("D:\\27.jpg"), new File("D:\\271.jpg"));// 图片小于300kb
            System.out.println(System.currentTimeMillis() - l);
//            FileUtils.writeByteArrayToFile(new File("D:\\271.jpg"), bytes);
        }catch (Error e){
            e.printStackTrace();
        }

        Thread.sleep(1000000);
    }

    /**
     * 根据指定大小压缩图片
     *
     * @return 压缩质量后的图片字节数组
     */
    public static void compressPicForScale(File file,File targetFile) throws Exception{
        Thumbnails.of(file)
                .scale(getAccuracy(1))
                .outputQuality(getAccuracy(1))
                .toFile(targetFile);
    }

    /**
     * 自动调节精度(经验数值)
     *
     * @param size 源图片大小
     * @return 图片压缩质量比
     */
//    private static double getAccuracy(long size) {
//        double accuracy;
//        if (size < 900) {
//            accuracy = 0.85;
//        } else if (size < 2047) {
//            accuracy = 0.6;
//        } else if (size < 3275) {
//            accuracy = 0.44;
//        } else {
//            accuracy = 0.4;
//        }
//        return accuracy;
//    }
    private static double getAccuracy(long size) {
        double z = Math.pow(200d/28645,1.0/3);
        return z;
    }

}
