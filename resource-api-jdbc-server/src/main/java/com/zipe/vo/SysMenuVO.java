package com.zipe.vo;

import java.util.List;

public class SysMenuVO {
    int menuId;
    String name;
    String link;
    int orderId;
    List<SysMenuVO> sub;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<SysMenuVO> getSub() {
        return sub;
    }

    public void setSub(List<SysMenuVO> sub) {
        this.sub = sub;
    }
}
