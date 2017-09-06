package org.lanqiao.util;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class IOTest {
    
	@Test
	public void test() throws IOException{
	
        IOUtil io = new IOUtil();
        io.up("E:\\蓝桥文件\\jQuery.chm","E:\\");
	}
}
