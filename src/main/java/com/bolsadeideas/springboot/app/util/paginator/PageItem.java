package com.bolsadeideas.springboot.app.util.paginator;

public class PageItem {

    private int number;
    boolean isActual;

    public PageItem(int number, boolean isActual) {
        this.number = number;
        this.isActual = isActual;
    }

    public int getNumber() {
        return number;
    }

    public boolean isActual() {
        return isActual;
    }
}
