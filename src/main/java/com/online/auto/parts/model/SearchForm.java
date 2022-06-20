package com.online.auto.parts.model;

import java.util.HashSet;
import java.util.Set;

public class SearchForm {
    private String searchKey;

    private Set<String> categories;

    private String selected;

    public SearchForm() {
        categories = new HashSet<>();
    }

    public void addCategory(String category) {
        categories.add(category);
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }


    @Override
    public String toString() {
        return "SearchForm{" +
                "searchKey='" + searchKey + '\'' +
                ", categories=" + categories +
                ", selected='" + selected + '\'' +
                '}';
    }

}
