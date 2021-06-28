package com.example.androidshaper.doodleinctestapplication.model;

import java.util.List;

public class NameList
{
    List<SubCategoryName> subCategoryNameList;
    List<CategoryName> categoryNameList;

    public NameList()
    {
    }

    public NameList(
            List<SubCategoryName> subCategoryNameList,
            List<CategoryName> categoryNameList)
    {
        this.subCategoryNameList = subCategoryNameList;
        this.categoryNameList = categoryNameList;
    }

    public List<SubCategoryName> getSubCategoryNameList()
    {
        return subCategoryNameList;
    }

    public void setSubCategoryNameList(List<SubCategoryName> subCategoryNameList)
    {
        this.subCategoryNameList = subCategoryNameList;
    }

    public List<CategoryName> getCategoryNameList()
    {
        return categoryNameList;
    }

    public void setCategoryNameList(List<CategoryName> categoryNameList)
    {
        this.categoryNameList = categoryNameList;
    }
}
