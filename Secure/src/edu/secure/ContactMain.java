package edu.secure;

import edu.secure.dao.ContactDao;
import edu.secure.dao.ContactDaoMySQL;
import edu.secure.dto.ContactDto;

public class ContactMain {
	public static void main(String[] args) {
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
		new DataThread(1, 1000).start();
	}
}

class DataThread extends Thread {
	private int start, end;
	
	DataThread(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public void run() {
		ContactDao dao = new ContactDaoMySQL();
		
		ContactDto dto = new ContactDto();
		for (int i = start; i <= end; i++) {
			dto.setName("name" + i);
			dto.setAddress("address" + i);
			dao.insert(dto);
		}
	}
	
}