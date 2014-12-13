package com.bin.spring.transaction.xml;

import java.util.List;

import org.springframework.stereotype.Service;

@Service("cashier")
public class CashierImpl implements Cashier{
	private BookShopService bookShopService ;
	public void setBookShopService(BookShopService bookShopService) {
		this.bookShopService = bookShopService;
	}

	@Override
	public void checkout(String username, List<String> isbns) {
		for (String isbn:isbns) {
			bookShopService.purchase(username, isbn);
		}
	}

}
