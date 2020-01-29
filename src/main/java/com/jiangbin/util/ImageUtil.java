package com.jiangbin.util;

import java.io.File;
import java.io.IOException;
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
	 * ��CommonsMultipartFileת��File
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
	 * ��������ͼ��������������ͼƬ�����ֵ·��
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
	 * ����Ŀ��·�����漰����Ŀ¼ �� /home/work/jiangbin/xxx.jpg
	 * ��ôhome work jiangbin �������ļ��ж����Զ�����
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
	 * ��ȡ�����ļ�������չ��
	 * @param cFile
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
	/**
	 * ��������ļ�������ǰ������Сʱ��������+��λ�����
	 * @return
	 */
	public static String getRandomFileName() {
		// ��ȡ�������λ��
		int rannum = r.nextInt(89999)+1000;
		String nowTimeStr = SDateFORMAT.format(new Date());
		
		return nowTimeStr+rannum;
	}
	public static void main(String[] args) {
	//Thumbnails.of(new File("C:/Users/zsxdmsr115/Desktop/2017061320371786788.jpg")).size(200, 200).wait(timeout, nanos);
	}
}
