//package com.bobo.code.web.controller;
//
//
//import java.io.PrintWriter;
//import java.sql.SQLException;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//
//@Controller
//@RequestMapping(value = SupplierControl.DIR)
//public class SupplierControl {
//	protected static final String DIR = "!supplier/*";
//	private DicService dicService;
//	private SupplierService supplierService;
//
//	@RequestMapping("supplierlist" + GlobalConstants.CONTROL_SUFFIX)
//	public ModelAndView list(Integer pageIndex,SupplierBean supplierBean, HttpServletRequest request) {
//		List<SupplierBean> supplierBeanList=null;
//		Pager pager = null;
//		try {
//			pager = supplierService.getSupplierBeanList(pageIndex,supplierBean);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		ModelAndView mv = new ModelAndView("supplier/supplierlist");
//		mv.addObject("pager", pager);
//		return mv;
////		mv.addObject("supplierBeanList", supplierBeanList);
//	}
//	
//	@RequestMapping("go2page" + GlobalConstants.CONTROL_SUFFIX)
//	public ModelAndView go2page(String id, HttpServletRequest request) {
//		ModelAndView mv = new ModelAndView("supplier/addsupplier");
//		SupplierBean supplierBean = null;
//		if (id != null && id.trim().length() > 0) {
//			try {
//				supplierBean = supplierService.getSupplierById(id);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		try {
//			List<DicBean> departmentList = dicService
//					.getDicBeanList(DicHelpUtil.CODE_1001);
//			mv.addObject("departmentList", departmentList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		mv.addObject("supplierBean", supplierBean);
//		return mv;
//	}
//	
//	@RequestMapping("add" + GlobalConstants.CONTROL_SUFFIX)
//	public void add(SupplierBean supplierBean,PrintWriter printWriter) {//实际上意为saveOrUpdate
//		boolean flag = true;
//		AjaxRetBean ajaxRetBean = new AjaxRetBean();
//		try {
//			if(supplierBean.getId() == null){
//				Integer supplierid = supplierService.getMaxSupplierId();//新增
//				if (supplierid == null) {
//					supplierid = 1;
//				} else {
//					supplierid += 1;
//				}
//				supplierBean.setId(supplierid);
//				supplierService.addSupplier(supplierBean);
//			}else{
//				supplierService.updateSupplier(supplierBean);// 修改
//			} 
//		} catch (Exception e) {
//			flag = false;
//		}
//
//		ajaxRetBean.setFlag(flag);
//		String json = JSONObject.fromObject(ajaxRetBean).toString();
//		printWriter.write(json);
//		printWriter.flush();
//		printWriter.close();
//	}
//
//	@RequestMapping("drop" + GlobalConstants.CONTROL_SUFFIX)
//	public void drop(String id, PrintWriter printWriter) {
//		boolean flag = true;
//		try {
//			supplierService.deleteSupplier(id);
//		} catch (Exception e) {
//			flag = false;
//		}
//		AjaxRetBean ajaxRetBean = new AjaxRetBean();
//		ajaxRetBean.setFlag(flag);
//		String json = JSONObject.fromObject(ajaxRetBean).toString();
//		printWriter.write(json);
//		printWriter.flush();
//		printWriter.close();
//	}
//}
