

import java.sql.Connection;

import org.junit.Test;
import org.lanqiao.util.DBUtil;


public class test {

    @Test
	public void testConn(){
		 Connection conn = DBUtil.getConnection();
		 System.out.println(conn);

	}

}