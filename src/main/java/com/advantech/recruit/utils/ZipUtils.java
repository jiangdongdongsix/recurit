package com.advantech.recruit.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    /***
     *多文件压缩
     */
    public static File Copy2Demo(String path,String s) {
        File file = new File(path);
        File filezip = new File(s+".zip");
        try {
            if(!file.exists()) {
                // 如果文件夹不存在，就创建文件夹。
                file.mkdir();
            }
            //1,创建文件输出时的文件
            ZipOutputStream zop = new ZipOutputStream(new FileOutputStream(filezip));
            zop.setComment("多文本压缩");
            isFile(zop,file,"");
            zop.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return filezip;
    }

    /**
     * 判断文件还是文件目录
     * @param zop 输出流
     * @param file 文件
     * @param dir 目录名字
     * @throws IOException
     */
    private static void isFile(ZipOutputStream zop,File file,String dir) throws IOException{
        //如果是目录

        if(file.isDirectory()){
            //将文件保存到文件数组中
            File[] fil = file.listFiles();
            //遍历出来
            for(File files: fil ){

                isFile(zop,files,dir+"\\"+file.getName());
            }
        }else{
            String entryName = null;
            //判断文件名是否为空
            if(!"".equals(dir)){
                entryName = dir+"\\"+file.getName();
            }
            else{
                entryName = file.getName();
            }
            //根据获取到的名字创建文件对象
            ZipEntry entrys = new ZipEntry(entryName);
            zop.putNextEntry(entrys);
            //创建文件输入流
            InputStream fim = new FileInputStream(file);
            int lens =fim.read();
            while(lens!=-1){
                zop.write(lens);
                lens = fim.read();
            }
            fim.close();

        }
    }




}
