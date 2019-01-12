package service.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.ssm.maven.core.entity.Book;
import com.ssm.maven.core.service.BookService;

/**
 * Created by 13 on 2017/3/30.
 */
@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration("classpath:applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//不添加此设置,测试service层的事务管理
//service层与dao层的测试时相同的,不同之处,在于service多数都在配置文件中配置了spring的事务管理
public class BookServiceTest {
	@Resource
    private BookService bookService;

    @Test
    public void test_getBookById() {
    	/**
    	 *  Book book1 = bookDao.getBookById("1");
        Assert.assertEquals(book1, null);
        Book book2 = bookDao.getBookById("1002");
        Assert.assertEquals(book2.getTitle(), "材料成型概论");
    	 */
    	Book book1 = bookService.getBookById("1");
    	Assert.assertEquals(book1, null);
    	Book book2 = bookService.getBookById("1002");
    	Assert.assertEquals(book2.getTitle(), "材料成型概论");
    }

}
