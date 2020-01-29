package com.jiangbin.util;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	private static final SimpleDateFormat SDateFORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	private static  Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	//private static String basePath=ImageUtil.class.getClassLoader().getResource("").getPath();
	private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	/**
	 * 将CommonsMultipartFile转成File
	 * @param cFile
	 * @return
	 */
	public static File transferCommonsMultipartToFile(CommonsMultipartFile cFile){
		File newFile = new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
		
	}
	
	/**
	 * 处理缩略图，并返回新生成图片的相对值路径
	 * @param thumbnail
	 * @param targetAddr
	 * @return
	 */
	public static String generateTumbnail(InputStream thumbnailInputstream,String fileName,String targetAddr){
		String relFileName = getRandomFileName();
		String extension = getFileExtension(fileName);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr+relFileName+extension;
		logger.debug("current relativeAddr is"+relativeAddr);
		File dest = new File(PathUtil.getImgBasePath()+relativeAddr);
		logger.debug("current complete addr is"+PathUtil.getImgBasePath()+ relativeAddr);
		try {
			Thumbnails.of(thumbnailInputstream).size(200, 200).watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath+"watermark.jpg")),0.25f)
			.outputQuality(0.8f).toFile(dest);
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return relativeAddr;
	}
	/**
	 * 创建目标路径所涉及到的目录 即 /home/work/jiangbin/xxx.jpg
	 * 那么home work jiangbin 这三个文件夹都得自动创建
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String reaFileParentPath  = PathUtil.getImgBasePath()+targetAddr;
		File dirPath = new File(reaFileParentPath);
		if(!dirPath.exists()){
			dirPath.mkdirs();
		}
	}
	/**
	 * 获取输入文件流的扩展名
	 * @param cFile
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
	/**
	 * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
	 * @return
	 */
	public static String getRandomFileName() {
		// 获取随机的五位数
		int rannum = r.nextInt(89999)+1000;
		String nowTimeStr = SDateFORMAT.format(new Date());
		
		return nowTimeStr+rannum;
	}
	/**
	 * storepath是文件的路径还是目录路径
	 * 如果storePath是文件路径则删除该文件
	 * 如果storePath是目录路径则删除该目录下的所有文件
	 * @param storePath
	 */
	public static void deletFileOrPath(String storePath){
			File fileOrPath = new File(PathUtil.getImgBasePath()+storePath);
			if(fileOrPath.exists()){
				if(fileOrPath.isDirectory()){
					File files [] = fileOrPath.listFiles();
					for(int i=0;i<files.length;i++){
						files[i].delete();
					}
					
				}
				fileOrPath.delete();
			}
	}
}
