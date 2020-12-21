package image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-10-29
 */
public class MyTest1 {
    private static Logger logger = LoggerFactory.getLogger(MyTest1.class);

//    public static void main(String[] args) throws Exception{
//        Thumbnails.of("D:\\3.jpg").scale(1f).outputQuality(0.25f).toFile("D:\\31.jpg");
//
//    }
    public static void main(String[] args) throws Exception {
//        byte[] bytes = FileUtils.readFileToByteArray(new File("D:\\27.jpg"));
//        bytes = compressPicForScale(bytes, 50, "x");// 图片小于300kb
        new Thread(()->{
            try{
                long l = System.currentTimeMillis();
                compressPicForScale("D:\\27.jpg", "D:\\271.jpg");// 图片小于300kb
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }).start();
//        new Thread(()->{
//            try{
//                long l = System.currentTimeMillis();
//                compressPicForScale("D:\\37.jpg", "D:\\371.jpg");// 图片小于300kb
//            }catch (Exception e){
//                throw new RuntimeException(e);
//            }
//        }).start();
//        new Thread(()->{
//            try{
//                long l = System.currentTimeMillis();
//                compressPicForScale("D:\\47.jpg", "D:\\471.jpg");// 图片小于300kb
//            }catch (Exception e){
//                throw new RuntimeException(e);
//            }
//        }).start();

//        System.out.println(System.currentTimeMillis() - l);
//        FileUtils.writeByteArrayToFile(new File("D:\\271.jpg"), bytes);
        Thread.sleep(10000);
    }

    /**
     * 根据指定大小压缩图片
     * @return 压缩质量后的图片字节数组
     */
    public static void compressPicForScale(String file, String targetFile) {
        double accuracy = getAccuracy(1);
        try {
            Thumbnails.of(file)
                    .scale(accuracy)
                    .outputQuality(1)
                    .toFile(targetFile);
        } catch (Exception e) {
            logger.error("【图片压缩】msg=图片压缩失败!", e);
        }
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
