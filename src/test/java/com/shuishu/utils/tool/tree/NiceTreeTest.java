package com.shuishu.utils.tool.tree;


import cn.hutool.core.lang.tree.TreeUtil;
import com.alibaba.fastjson2.JSONArray;
import com.shuishu.utils.tool.domain.Menu;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author ：谁书-ss
 * @Date ：2024/8/15 9:37
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：
 * <p></p>
 */

public class NiceTreeTest {

    private List<Menu> getMenuList() {
        List<Menu> menuList = new ArrayList<>();

        // 一个子节点
        menuList.add(new Menu(5L, "文章管理", null, null));
        menuList.add(new Menu(6L, "分类标签", 5L, null));

        // 两个子节点
        menuList.add(new Menu(1L, "系统设置", null, null));
        menuList.add(new Menu(2L, "防火墙", 1L, null));
        menuList.add(new Menu(3L, "代理服务", 1L, null));
        menuList.add(new Menu(4L, "代理规则", 3L, null));

        // 无子节点
        menuList.add(new Menu(7L, "数据看板", null, null));

        return menuList;
    }

    @Test
    public void testBuildTree() {
        // 获取 构建的Menu数据集合
        List<Menu> menuList = getMenuList();

        // 构建为Tree结构
        List<Menu> menuTreeList = NiceTree.buildTree(menuList, root -> root.getParentMenuId() == null, (x, y) -> x.getMenuId().equals(y.getParentMenuId()), Menu::setChildrenMenuList);

        // 打印JSON格式数据，复制到其它工具类，格式化查看
        System.out.println(JSONArray.toJSONString(menuTreeList));

    }


    @Test
    public void testBuildFlat() {
        // 获取 构建的Menu数据集合
        List<Menu> menuList = getMenuList();

        // 构建为Tree结构
        List<Menu> menuTreeList = NiceTree.buildTree(menuList, root -> root.getParentMenuId() == null, (x, y) -> x.getMenuId().equals(y.getParentMenuId()), Menu::setChildrenMenuList);

        // 构建为Tree结构，打印数据
        System.out.println(JSONArray.toJSONString(menuTreeList));

        // 将Tree结构，平铺
        List<Menu> menus = NiceTree.buildFlat(menuTreeList, Menu::getChildrenMenuList, x -> x.setChildrenMenuList(null));
        for (Menu menu : menus) {
            System.out.println(menu);
        }

    }


    @Test
    public void testForPreOrder() {
        // 获取 构建的Menu数据集合
        List<Menu> menuList = getMenuList();

        // 构建为Tree结构
        List<Menu> menuTreeList = NiceTree.buildTree(menuList, root -> root.getParentMenuId() == null, (x, y) -> x.getMenuId().equals(y.getParentMenuId()), Menu::setChildrenMenuList);


        // 第一种场景：获取主键id
        System.out.println("第一种场景：获取主键id");
        List<Long> menuIdList = new ArrayList<>();
        NiceTree.forPreOrder(menuTreeList, x -> menuIdList.add(x.getMenuId()), Menu::getChildrenMenuList);
        menuIdList.forEach(System.out::println);

        // 第二种场景：打印-子节点会平铺
        System.out.println("第二种场景：打印-保留子节点数据");
        //NiceTree.forPreOrder(menuTreeList, x -> System.out.println(x), Menu::getChildrenMenuList);
        NiceTree.forPreOrder(menuTreeList, System.out::println, Menu::getChildrenMenuList);

        // 第三种场景：打印-子节点不平铺
        System.out.println("第三种场景：打印-不保留子节点数据");
        //NiceTree.forPreOrder(menuTreeList, x -> System.out.println(x), Menu::getChildrenMenuList);
        NiceTree.forPreOrder(menuTreeList, System.out::println,
                s -> {
                    s.setChildrenMenuList(null);
                    // Menu实体对象set方法无返回值，所以这里需要设置返回值
                    return null;
                }
        );

    }


    @Test
    public void testForLevelOrder() {
        // 获取 构建的Menu数据集合
        List<Menu> menuList = getMenuList();

        // 构建为Tree结构
        List<Menu> menuTreeList = NiceTree.buildTree(menuList, root -> root.getParentMenuId() == null, (x, y) -> x.getMenuId().equals(y.getParentMenuId()), Menu::setChildrenMenuList);


        // 第一种场景：获取主键id
        System.out.println("第一种场景：获取主键id");
        List<Long> menuIdList = new ArrayList<>();
        NiceTree.forLevelOrder(menuTreeList, x -> menuIdList.add(x.getMenuId()), Menu::getChildrenMenuList);
        menuIdList.forEach(System.out::println);

        // 第二种场景：打印-子节点会平铺
        System.out.println("第二种场景：打印-保留子节点数据");
        //NiceTree.forPreOrder(menuTreeList, x -> System.out.println(x), Menu::getChildrenMenuList);
        NiceTree.forLevelOrder(menuTreeList, System.out::println, Menu::getChildrenMenuList);

        // 第三种场景：打印-子节点不平铺
        System.out.println("第三种场景：打印-不保留子节点数据");
        //NiceTree.forPreOrder(menuTreeList, x -> System.out.println(x), Menu::getChildrenMenuList);
        NiceTree.forLevelOrder(menuTreeList, System.out::println,
                s -> {
                    //s.setChildrenMenuList(null);
                    // Menu实体对象set方法无返回值，所以这里需要设置返回值
                    return null;
                }
        );

    }


    @Test
    public void testForPostOrder() {
        // 获取 构建的Menu数据集合
        List<Menu> menuList = getMenuList();

        // 构建为Tree结构
        List<Menu> menuTreeList = NiceTree.buildTree(menuList, root -> root.getParentMenuId() == null, (x, y) -> x.getMenuId().equals(y.getParentMenuId()), Menu::setChildrenMenuList);


        // 第一种场景：获取主键id
        System.out.println("第一种场景：获取主键id");
        List<Long> menuIdList = new ArrayList<>();
        NiceTree.forPostOrder(menuTreeList, x -> menuIdList.add(x.getMenuId()), Menu::getChildrenMenuList);
        menuIdList.forEach(System.out::println);

        // 第二种场景：打印-子节点会平铺
        System.out.println("第二种场景：打印-保留子节点数据");
        //NiceTree.forPreOrder(menuTreeList, x -> System.out.println(x), Menu::getChildrenMenuList);
        NiceTree.forPostOrder(menuTreeList, System.out::println, Menu::getChildrenMenuList);

        // 第三种场景：打印-子节点不平铺
        System.out.println("第三种场景：打印-不保留子节点数据");
        //NiceTree.forPreOrder(menuTreeList, x -> System.out.println(x), Menu::getChildrenMenuList);
        NiceTree.forPostOrder(menuTreeList, System.out::println,
                s -> {
                    s.setChildrenMenuList(null);
                    // Menu实体对象set方法无返回值，所以这里需要设置返回值
                    return null;
                }
        );

    }


    @Test
    public void testSort() {
        // 获取 构建的Menu数据集合
        List<Menu> menuList = getMenuList();

        // 构建为Tree结构
        List<Menu> menuTreeList = NiceTree.buildTree(menuList, root -> root.getParentMenuId() == null, (x, y) -> x.getMenuId().equals(y.getParentMenuId()), Menu::setChildrenMenuList);

        System.out.println("排序前");
        NiceTree.forPostOrder(menuTreeList, System.out::println, Menu::getChildrenMenuList);
        System.out.println(JSONArray.toJSONString(menuTreeList));
        System.out.println("排序后");
        NiceTree.sort(menuTreeList, Comparator.comparing(Menu::getMenuId), Menu::getChildrenMenuList);
        NiceTree.forPostOrder(menuTreeList, System.out::println, Menu::getChildrenMenuList);

        System.out.println(JSONArray.toJSONString(menuTreeList));
    }

    @Test
    public void test() {
        List<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu(2L, "文章管理", 1L, null));
        menuList.add(new Menu(3L, "分类标签", 1L, null));
        menuList.add(new Menu(4L, "系统设置", 3L, null));

        List<Menu> menuTreeList = NiceTree.buildTree(menuList, null, (x, y) -> x.getMenuId().equals(y.getParentMenuId()), Menu::setChildrenMenuList);

        menuTreeList.forEach(System.out::println);
    }


}
