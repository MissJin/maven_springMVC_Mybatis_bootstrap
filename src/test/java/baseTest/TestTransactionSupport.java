package baseTest;

/**
 * Created by superadmin on 2017/2/13.
 * 带事务回滚的普通的测试，测试数据-不会-写到数据库中
 */

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.text.SimpleDateFormat;

@ContextConfiguration(locations = {"classpath*:applicationContext.xml","classpath:mybatis.xml"})
public class TestTransactionSupport extends AbstractTransactionalJUnit4SpringContextTests {
    protected  long startTime;
    protected  long endTime;

    /**
     * record start  run time
     * @return
     */
    protected  long start(){
        this.startTime = System.currentTimeMillis();
        return  startTime;
    }

    /**
     * record end run time
     * and print the record
     * @return
     */
    protected  long end(){
        this.endTime = System.currentTimeMillis();
        this.log();
        return  endTime;
    }

    /**
     * print log
     */
    protected  void log(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String text = "\nStartTime : " + simpleDateFormat.format(this.startTime)+ "\nEndTime : " + simpleDateFormat.format(this.endTime) + "\nRunTime : " + (this.endTime - this.startTime);
        logger.info(text);
    }
}
