package mydao;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description 模拟mybatis
 * @createTime 2020-09-18
 */
public class MyTest1 {
    public static void main(String[] args) throws Exception{
        GradeDao gradeDao = new GradeDao();
        List<Grade> grades = gradeDao.query(null,null);
//        System.out.println(grades);
//        StudentDao studentDao = new StudentDao();
//        List<Student> students = studentDao.query(null,null);
        CompletableFuture[] completableFutures = IntStream.rangeClosed(0,999).mapToObj(index->CompletableFuture.supplyAsync(()->
                gradeDao.query(null,preparedStatement -> System.out.println(index)))).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(completableFutures).join();
    }
}
