package com.jpetstore.servlet.persistance;

import com.jpetstore.servlet.domain.Category;
import java.util.List;
public interface CategoryDAO
{
    //select All Categories
    List<Category> getCategoryList();
    //select a Category by ID
    Category getCategory(String categoryid);
}
