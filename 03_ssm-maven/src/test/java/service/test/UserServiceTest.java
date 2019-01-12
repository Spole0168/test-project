package service.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssm.maven.core.entity.Book;
import com.ssm.maven.core.entity.User;
import com.ssm.maven.core.service.BookService;
import com.ssm.maven.core.service.UserService;
import com.ssm.maven.core.util.MD5Util;

/**
 * Created by 13 on 2017/3/30.
 */
@RunWith(SpringJUnit4ClassRunner.class) // 指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration("classpath:applicationContext.xml")
// 不添加此设置,测试service层的事务管理
// service层与dao层的测试时相同的,不同之处,在于service多数都在配置文件中配置了spring的事务管理
public class UserServiceTest {
	@Resource
	private UserService userService;

	@Test
	public void test_login() {
		/**
		 * Book book1 = bookDao.getBookById("1"); Assert.assertEquals(book1,
		 * null); Book book2 = bookDao.getBookById("1002");
		 * Assert.assertEquals(book2.getTitle(), "材料成型概论");
		 */
		
		User user = new User();
		user.setUserName("admin");
		user.setPassword("123456");
		// 断言此姓名和密码的用户为空
		Assert.assertEquals(userService.login(user), null);
		User user2 = new User();
		user2.setUserName("admin");
		user2.setPassword(MD5Util.MD5Encode("123456", "UTF-8"));
		// 断言此姓名和密码的用户可以登录成功,且用户id为2
		Assert.assertTrue(userService.login(user2).getId() == 2);
		// Assert.assertTrue(userDao.login(user2).getId() == 3);
	}

}
