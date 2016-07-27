import com.SCC.environment.dao.MapDao;
import com.SCC.environment.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ZJDX on 2016/7/8.
 */
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
//使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTestCase {
    @Autowired
     RedisService redisService;
    @Autowired
     private MapDao mapDao;

    @Test
    public void getTimestampTest() throws InterruptedException{
        System.out.println("第一次调用：" + redisService.getTimestamp("param"));
        Thread.sleep(2000);
        System.out.println("2秒之后调用：" + redisService.getTimestamp("param"));
        Thread.sleep(11000);
        System.out.println("再过11秒之后调用：" + redisService.getTimestamp("param"));
    }
    @Test
    public void getMap() throws InterruptedException{
        System.out.println("map：" + mapDao.read(1).getMappingX());
    }
    @Test
    public void deleteMap() throws InterruptedException{
        mapDao.delete(1);
        System.out.println("map：delete"  );
    }
}
