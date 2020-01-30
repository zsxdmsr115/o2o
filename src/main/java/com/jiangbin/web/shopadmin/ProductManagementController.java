package com.jiangbin.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangbin.dto.ImageHolder;
import com.jiangbin.dto.ProductExecution;
import com.jiangbin.entity.Product;
import com.jiangbin.entity.Shop;
import com.jiangbin.enume.ProductStateEnum;
import com.jiangbin.exception.ProductOperationException;
import com.jiangbin.service.ProductCategoryService;
import com.jiangbin.service.ProductService;
import com.jiangbin.util.CodeUtil;
import com.jiangbin.util.HttpRequestUtil;

public class ProductManagementController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;

	// ֧���ϴ���Ʒ����ͼ���������
	private static final int IMAGEMAXCOUNT = 6;
	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addProduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// ��֤��У��
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "�����˴������֤��");
			return modelMap;
		}
		// ����ǰ�˲����ı����ĳ�ʼ����������Ʒ������ͼ������ͼ�б�ʵ����
		ObjectMapper mapper = new ObjectMapper();//��jsonת��ʵ�����
		Product product = null;
		ImageHolder thumbnail = null;
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		try {
			// �������д����ļ�������ȡ����ص��ļ�����������ͼ������ͼ��
			if (multipartResolver.isMultipart(request)) {
				thumbnail = handleImage(request, thumbnail, productImgList);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "�ϴ�ͼƬ����Ϊ��");
				return modelMap;
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		try {
			String productStr = HttpRequestUtil.getString(request, "productStr");
			// ���Ի�ȡǰ�˴������ı�string��������ת����Productʵ����
			product = mapper.readValue(productStr, Product.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		// ��Product��Ϣ������ͼ�Լ�����ͼ�б�Ϊ�ǿգ���ʼ������Ʒ��Ӳ���
		if (product != null && thumbnail != null && productImgList.size() > 0) {
			try {
				// ��session�л�ȡ��ǰ���̵�Id����ֵ��product�����ٶ�ǰ�����ݵ�����
				Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
				product.setShop(currentShop);
				// ִ����Ӳ���
				ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
				if (pe.getState() == ProductStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (ProductOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "��������Ʒ��Ϣ");
		}
		return modelMap;
	}
	private ImageHolder handleImage(HttpServletRequest request, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// ȡ������ͼ������ImageHolder����
		CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
		if (thumbnailFile != null) {
			thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());
		}
		// ȡ������ͼ�б�����List<ImageHolder>�б�������֧������ͼƬ�ϴ�
		for (int i = 0; i < IMAGEMAXCOUNT; i++) {
			CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartRequest.getFile("productImg" + i);
			if (productImgFile != null) {
				// ��ȡ���ĵ�i������ͼƬ�ļ�����Ϊ�գ������������ͼ�б�
				ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(),
						productImgFile.getInputStream());
				productImgList.add(productImg);
			} else {
				// ��ȡ���ĵ�i������ͼƬ�ļ���Ϊ�գ�����ֹѭ��
				break;
			}
		}
		return thumbnail;
	}
}

