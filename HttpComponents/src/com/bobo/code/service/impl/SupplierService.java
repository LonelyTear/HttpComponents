//package com.bobo.code.service.impl;
//
//
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SupplierService {
//	private ObjectMapperInf objectMapper;
//	
//	public Integer getMaxSupplierId() throws SQLException {
//		return (Integer) objectMapper.getObjectMapper("getMaxSupplierId", null);
//	}
//	public SupplierBean getSupplierById(String id) throws SQLException {
//		return (SupplierBean) objectMapper.getObjectMapper("getSupplierById", id);
//	}
//	
//	public Pager getSupplierBeanList(final Integer pageIndex,SupplierBean supplierBean) throws SQLException{
//		Pager pager = new Pager(pageIndex, GlobalConstants.PAGERSIZE);
//		Map<String, Object> queryMap = new HashMap<String, Object>();
//		queryMap.put("number", supplierBean.getNumber());
//		queryMap.put("name", supplierBean.getName());
//		queryMap.put("linkman", supplierBean.getLinkman());
//		queryMap.put("skipResult", pager.getStartOfAnyPage());
//		queryMap.put("maxResult", pager.getEndOfAnyPage());
//		List<UserBean> result = (List<UserBean>) objectMapper.getObjectListMapper("getSupplierList", queryMap);
//		int totalSize=objectMapper.getObjectTotal("getSupplierTotalSize", queryMap);
//		pager.setResult(result);
//		pager.setTotalSize(totalSize);
//		return pager;
//	}
//	
//	public void addSupplier(SupplierBean supplierBean) throws SQLException{
//		objectMapper.insertObject("addSupplier", supplierBean);
//	}
//	
//
//	public void deleteSupplier(String id) throws SQLException {
//		objectMapper.delObjectById("deleteSupplierById", id);
//	}
//
//	public void updateSupplier(SupplierBean supplierBean) throws SQLException {
//		objectMapper.updateObject("updateSupplier", supplierBean);
//	}
//	
//}
