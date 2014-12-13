package com.bin.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

	@Autowired
	private BookShopDao bookShopDao ;
	/*
	 * 事务的传播行为.
	 * 1. 使用propagation指定事务的传播行为,即当前的事务方法被另外的事务方法调用时,
	 * 如何使用事务,默认取值为REQUIRED,即使用调用方法的事务.
	 * REQUIRES_NEW 使用自己的事务,调用的方法的事务挂起.
	 * 
	 * 2. 使用isolation指定事务的隔离级别.最常用的READ_COMMITTED.
	 * 
	 * 3. 默认情况下Spring的声明式事务对所有的运行时异常进行回滚.也可以通过对应的属性进行设置.
	 * 	noRollbackFor={UserAccountException.class}对哪个异常不进行回滚.非运行时异常,
	 *  是必须捕捉的,所以可以在捕捉后自行选择是否rollback,还是提交.
	 * 
	 * 4. 使用readOnly指定事务是否为只读.表示这个事务只读取数据但不更新数据,可以帮助数据库引擎
	 * 	优化事务.若真的是一个只读取数据库值的方法,应该设置readOnly=true.
	 * 
	 * 5. 使用timeout单位是秒,指定强制回滚之前事务可以占用的时间.事务执行中,占用的时间超出了设定时间,,就强制回滚.
	 * 
	 */
	// 添加事务注解.
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,
			isolation=Isolation.READ_COMMITTED,
			noRollbackFor={UserAccountException.class},
			readOnly=false,
			timeout=1)
	@Override
	public void purchase(String username, String isbn) {
		// 1.获取书的单价.
		int price = bookShopDao.findBookPriceByIsbn(isbn) ;
		// 2.获取书的库存
		bookShopDao.updateBookStock(isbn);
		// 3.更新用户余额
		bookShopDao.updateUserAccount(username, price);
	}

}
