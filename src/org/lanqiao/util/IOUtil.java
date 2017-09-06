package org.lanqiao.util;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;

public class IOUtil{
	 public void up(String f,String f1) throws IOException{ 
		  File f2 = new File(f);
	      File f3 = new File(f1);
	      System.out.println("!!!"+System.getProperty("user.dir"));
	      IOUtil io = new IOUtil();
	      io.copy(f2,f3);
	    
	 }
	 
	
	
    public void copy(File f,File f1) throws IOException{    //复制文件的方法！
    	
        //获取绝对路径 
    	if(!f1.exists()){
            f1.mkdir();
        }
        if(!f1.exists()){//路径判断，是路径还是单个的文件
            File[] cf = f.listFiles();
            for(File fn : cf){
                if(fn.isFile()){
                    FileInputStream fis = new FileInputStream(fn);
                    FileOutputStream fos = new FileOutputStream(f1 + "\\" +fn.getName());
                    byte[] b = new byte[1024];
                    int i = fis.read(b);
                    while(i != -1){
                        fos.write(b, 0, i);
                        i = fis.read(b);
                    }
                    fis.close();
                    fos.close();
                }else{
                    File fb = new File(f1 + "\\" + fn.getName());
                    fb.mkdir();
                    if(fn.listFiles() != null){//如果有子目录递归复制子目录！
                        copy(fn,fb);
                    }
                }
            }
        }else{
            FileInputStream fis = new FileInputStream(f);
            FileOutputStream fos = new FileOutputStream(f1 + "\\" +f.getName());
            byte[] b = new byte[1024];
            int i = fis.read(b);
            while(i != -1){
                fos.write(b, 0, i);
                i = fis.read(b);
            }
            fis.close();
            fos.close();
        }
    }
}