package com.ming.demo;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransferProject {

    static String srcDirPath = "/Users/zhangiming/Work/CODE";
    private static List<File> list = new ArrayList<>();
    public static void transferFile(String pathName, int depth) throws Exception {
        File dirFile = new File(pathName);
        if (!isValidFile(dirFile)) {
            return;
        }
        //获取此目录下的所有文件名与目录名
        String[] fileList = dirFile.list();
        int currentDepth = depth + 1;
        for (int i = 0; i < fileList.length; i++) {
            String string = fileList[i];
            File file = new File(dirFile.getPath(), string);
            String name = file.getName();
            //如果是一个目录，搜索深度depth++，输出目录名后，进行递归
            if (file.isDirectory()) {
                //list.add(file);
                //递归
                transferFile(file.getCanonicalPath(), currentDepth);
            } else {
                if (name.contains(".java")) {
                    updateFileStr(file);
                    System.out.println(name + " has converted to utf8 ");
                }
            }
        }

        updateBaic();
    }

    private static void updateBaic(){
        for (File file: list){
            updateName(file);
        }
    }




    private static void updateName(File file){
        //String newFilePath = filePath.substring(0, filePath.lastIndexOf("/")) + "/" + newFileName;
        String fileName = file.getName();
        if (fileName.equals("cy")||fileName.startsWith("cy.")){
            String filePath = file.getParentFile().getPath();//.getParent();
            if (fileName.equals("cy")){
                file.renameTo(new File(filePath+File.separator+"ah"));
            }else{
                String newFileName = fileName.replaceAll("cy","ah");
                file.renameTo(new File(filePath+File.separator+newFileName));
            }

        }

    }


    private static boolean isValidFile(File dirFile) throws IOException {
        if (dirFile.exists()) {
            System.out.println("file exist："+dirFile.getName());
            return true;
        }
        if (dirFile.isDirectory()) {
            if (dirFile.isFile()) {
                System.out.println(dirFile.getCanonicalFile());
            }
            return true;
        }
        return false;
    }

    private static void updateFileStr(File file)throws Exception {
        String  content = FileUtils.readFileByEncode(file.getPath(), "UTF-8");
        content = content.replaceAll("package cy.its","package ah.its").replaceAll("import cy.its","import ah.its");
        FileUtils.writeByBufferedReader(file.getPath(), new String(content.getBytes("UTF-8"), "UTF-8"));
        ///updateName(file);
    }

    private static void readAndWrite(File file) throws Exception {
        String  content = FileUtils.readFileByEncode(file.getPath(), "GBK");
        FileUtils.writeByBufferedReader(file.getPath(), new String(content.getBytes("UTF-8"), "UTF-8"));
    }

    public static void main(String[] args) throws Exception {
        //程序入口，制定src的path
        //String path = "/Users/mac/Downloads/unit06_jdbc/src";
        transferFile(srcDirPath, 1);
        System.out.println("ok");
    }
}
