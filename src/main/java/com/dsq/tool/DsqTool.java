package com.dsq.tool;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DsqTool {



    /**
     * 将 List<String> 文件输出到指定文件
     * @param array
     * @param file_name
     * @throws IOException
     */
    public static void writeTxt(List<String> array, String file_name) throws IOException{
        String path = file_name;
        path = "C:\\Users\\dsq\\Desktop\\"+file_name;
        File file = new File(path);
        if (!file.isFile()) {
            file.createNewFile();
        }

        try {
            String encoding = "UTF-8";
            if (file.exists()) {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),encoding));
                for (String string : array) {
//					System.err.println(string);
                    bufferedWriter.write(string);
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();
            } else {
                System.err.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }







    /**
     *
     * @param file_name 文件名称,默认本地桌面
     * @return
     */
    public static ArrayList<String> readTxt(String file_name){
        return readTxt(file_name, true);
    }
    /**
     *
     * @param file_name  文件的路径 桌面上的直接写文件名
     * @param isLocal	如果是桌面上的写ture,
     * @return
     */
    public static ArrayList<String> readTxt(String file_name, boolean isLocal){
        return readTxt(file_name, isLocal, "UTF-8");
    }
    /**
     *
     * @param file_name  文件的路径 桌面上的直接写文件名
     * @param isLocal 	 如果是桌面上的写ture,
     * @param encoding 		编码格式
     * @return
     */
    public static ArrayList<String> readTxt(String file_name, boolean isLocal,String encoding){
        ArrayList<String> array = new ArrayList<String>();
        String path = file_name;
        if (isLocal){
            path = "/Users/duanshaoqian/Desktop/"+file_name;
        }
        File file = new File(path);
        if (file.isFile()) {
            try {
                if (file.exists()) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),encoding));
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                        if ("".equals(lineTxt.trim())) {
                            continue;
                        }
                        array.add(lineTxt.trim());
                    }
                    bufferedReader.close();
                    return array;
                } else {
                    System.err.println("找不到指定的文件");
                }
            } catch (Exception e) {
                System.out.println("读取文件内容出错");
                e.printStackTrace();
            }
        }
        return null;
    }



    /**
     * 将 HashMap<String,Object> map 文件输出到指定文件
     * @param file_name
     * @throws IOException
     */
//	public static void writeTxt(HashMap<String,Object> map,String file_name) throws IOException{
//		ArrayList<String> array = new ArrayList<>() ;
//		Set<String> keySet = map.keySet();
//		for (String string : keySet) {
//			String new_str = string+" : "+map.get(string);
//			array.add(new_str);
//		}
//		writeTxt(array,file_name);
//	}
    public static void writeTxt(HashMap<String, Integer> map, String file_name) throws IOException {
        ArrayList<String> array = new ArrayList<>() ;
        Set<String> keySet = map.keySet();
        for (String string : keySet) {
            String new_str = string+" : "+map.get(string);
            array.add(new_str);
        }
        writeTxt(array,file_name);
    }

}
