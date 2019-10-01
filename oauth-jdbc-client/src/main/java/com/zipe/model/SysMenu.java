package com.zipe.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_menu")
public class SysMenu {
    private Integer menuId;
    private String menuName;
    private String path;
    private String comment;
    private Integer orderId;
    private boolean enabled;
    private Integer parentId;

    @Id
    @Column(name = "menu_id", nullable = false, length = 2, unique=true)
    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Basic
    @Column(name = "menu_name", nullable = false, length = 100)
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "path", length = 100)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "comment", length = 200)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "order_id", nullable = false, length = 2)
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "enabled",  nullable = false, columnDefinition="BOOLEAN DEFAULT false")
    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "parent_id", nullable = false, length = 2)
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysMenu that = (SysMenu) o;
        return enabled == that.enabled &&
                Objects.equals(menuId, that.menuId) &&
                Objects.equals(menuName, that.menuName) &&
                Objects.equals(path, that.path) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, menuName, path, comment, orderId, enabled, parentId);
    }
}
