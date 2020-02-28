package com.jpetstore.servlet.service;

import com.jpetstore.servlet.domain.Category;
import com.jpetstore.servlet.domain.Item;
import com.jpetstore.servlet.domain.Product;
import com.jpetstore.servlet.persistance.impl.CategoryDAOimpl;
import com.jpetstore.servlet.persistance.impl.ItemDAOimpl;
import com.jpetstore.servlet.persistance.impl.ProductDAOimpl;

import java.util.List;

public class CatalogService
{
    private CategoryDAOimpl categoryDAO;
    private ProductDAOimpl productDAO;
    private ItemDAOimpl itemDAO;
    public CatalogService()
    {
     categoryDAO=new CategoryDAOimpl();
     productDAO=new ProductDAOimpl();
     itemDAO=new ItemDAOimpl();
    }
    public String findProductListString(String name){
        List<Product> nameList=searchProductList(name);
        String res="";
        for (int i=0;i<nameList.size();i++) {
            if(i>0){
                res+=","+nameList.get(i).getName();
            }else{
                res+=nameList.get(i).getName();
            }
        }
        return res;
    }
    public List<Category> getCategoryList() {
        return categoryDAO.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return categoryDAO.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return productDAO.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productDAO.getProductListByCategory(categoryId);
    }

    // TODO enable using more than one keyword
    public List<Product> searchProductList(String keyword) {
        return productDAO.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    public List<Item> getItemListByProduct(String productId) {
        return itemDAO.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return itemDAO.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return itemDAO.getInventoryQuantity(itemId) > 0;
        }

}
