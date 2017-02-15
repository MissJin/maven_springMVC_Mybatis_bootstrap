package testUser;

import baseTest.TestSupport;
import baseTest.TestTransactionSupport;
import com.alibaba.fastjson.JSON;
import com.hcj.demo.model.User;
import com.hcj.demo.model.UserExample;
import com.hcj.demo.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by superadmin on 2017/2/13.
 */
public class TestUserService extends TestSupport {
    private static final Logger logger = Logger.getLogger(TestUserService.class);
    @Resource
    private UserService testService;
    @Test
    public void test_addUser(){
        User user = new User();
        user.setName("黄常锦");
        user.setMobile("14787895215");
        user.setCreateTime(new Date());
        Integer result = testService.insert(user);
        System.out.println(result);
        logger.info("add " +JSON.toJSONStringWithDateFormat( result, "yyyy-MM-dd HH:mm:ss"));
        end();
    }
    //@Test
    public void test_deleteById(){
        int result = testService.delete(2);
        System.out.println("result:" + result);
        logger.info("delete " + JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm:ss"));
    }
    @Test
    public void test_selectById(){
        start();
        User user = testService.selectById(2);
        System.out.println(user.toString());
        logger.info("select " + JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
    }
    @Test
    public void test_updateUser(){
        User user = testService.selectById(1);
        user.setCreateTime(new Date());
        testService.update(user);
        System.out.println(user.toString());
        logger.info("update " + JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
    }
    @Test
    public  void test_selectAll(){
        UserExample example = new UserExample();
        example.createCriteria().andCreateTimeGreaterThan(new Date());
        testService.selectListByExample(example);
        logger.info("带条件查询" + JSON.toJSONStringWithDateFormat(testService.selectListByExample(example), "yyyy-MM-dd HH:mm:ss"));
        logger.info("查询所有 " + JSON.toJSONStringWithDateFormat(testService.selectList(), "yyyy-MM-dd HH:mm:ss"));
    }
}
