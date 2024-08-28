package com.shuishu.utils.tool.domain;


import java.util.List;

/**
 * @Author ：谁书-ss
 * @Date ：2024/8/15 9:35
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 * <p></p>
 */
public class Menu {

    private Long menuId;

    private String menuName;

    private Long parentMenuId;

    private List<Menu> childrenMenuList;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public List<Menu> getChildrenMenuList() {
        return childrenMenuList;
    }

    public void setChildrenMenuList(List<Menu> childrenMenuList) {
        this.childrenMenuList = childrenMenuList;
    }


    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", parentMenuId=" + parentMenuId +
                ", childrenMenuList=" + childrenMenuList +
                '}';
    }

    public Menu() {
    }

    public Menu(Long menuId, String menuName, Long parentMenuId, List<Menu> childrenMenuList) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.parentMenuId = parentMenuId;
        this.childrenMenuList = childrenMenuList;
    }

}
