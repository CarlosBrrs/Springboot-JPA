package com.bolsadeideas.springboot.app.util.paginator;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender<T> {

    private String url;
    private Page<T> page;
    private int totalPages;
    private int elementsInPage;
    private int actualPage;

    private List<PageItem> pages;

    public String getUrl() {
        return url;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getActualPage() {
        return actualPage;
    }

    public List<PageItem> getPages() {
        return pages;
    }

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;

        pages = new ArrayList<PageItem>();

        elementsInPage = page.getSize();
        totalPages = page.getTotalPages();
        actualPage = page.getNumber() + 1;

        int from,  to;

        if (totalPages <= elementsInPage) {
            from = 1;
            to = totalPages;
        } else {
            if (actualPage <= elementsInPage/2) {
                from = 1;
                to = elementsInPage;
            } else if (actualPage >= totalPages - elementsInPage/2) {
                from = totalPages - elementsInPage +1;
                to = elementsInPage;
            } else {
                from = actualPage - elementsInPage/2;
                to = elementsInPage;
            }
        }

        for (int i = 0; i < to; i++) {
            pages.add(new PageItem(from + i, actualPage == from + i));
        }
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public boolean hasNext() {
        return page.hasNext();
    }

    public boolean hasPrevious() {
        return page.hasPrevious();
    }
}
