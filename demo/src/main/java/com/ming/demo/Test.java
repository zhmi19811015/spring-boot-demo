package com.ming.demo;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    static String srcDirPath = "/Users/zhangiming/Work/2018AH009-ITMS.AnHui.code/Code/cy.its.violation/cy.its.violation.domain/src/cy/its/accident/domain/model";
    public static void main(String[] args) throws Exception {
        test1();
    }
    public static void convert(String oldFile, String oldCharset, String newFlie, String newCharset){
        BufferedReader bin;
        FileOutputStream fos;
        StringBuffer content = new StringBuffer();
        try {
            System.out.println(oldFile);
            bin = new BufferedReader(new InputStreamReader(new FileInputStream(oldFile), "gbk"));
            String line = null;
            while((line=bin.readLine())!=null){
                content.append(line);
                content.append(System.getProperty("line.separator"));
            }
            bin.close();
            File dir = new File(newFlie.substring(0, newFlie.lastIndexOf("\\")));
            if(!dir.exists()){
                dir.mkdirs();
            }
            fos = new FileOutputStream(newFlie);
            Writer out = new OutputStreamWriter(fos, newCharset);
            out.write(content.toString());
            out.close();
            fos.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void fetchFileList(String strPath, List<String> filelist, final String regex) {
        File dir = new File(strPath);
        File[] files = dir.listFiles();
        Pattern p = Pattern.compile(regex);
        if (files == null)
            return;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                fetchFileList(files[i].getAbsolutePath(), filelist, regex);
            } else {
                String strFileName = files[i].getAbsolutePath().toLowerCase();
                Matcher m = p.matcher(strFileName);
                if(m.find()){
                    filelist.add(strFileName);
                }
            }
        }
    }


    public static void test1() throws IOException{

        //转为UTF-8编码格式源码路径
        String utf8DirPath = "/tmp/src";

        //获取所有java文件
        Collection<File> javaGbkFileCol =  FileUtils.listFiles(new File(srcDirPath), new String[]{"java"}, true);

        for (File javaGbkFile : javaGbkFileCol) {
            //UTF8格式文件路径
            String utf8FilePath = utf8DirPath+javaGbkFile.getAbsolutePath().substring(srcDirPath.length());
            //使用GBK读取数据，然后用UTF-8写入数据
            FileUtils.writeLines(new File(utf8FilePath), "UTF-8", FileUtils.readLines(javaGbkFile, "GBK"));
        }
        System.out.println("ok");
    }
}
