package com.topjet.util;

import com.topjet.manage.domain.bean.TreeNode;
import com.topjet.manage.domain.model.SysMenuBtnModel;
import com.topjet.manage.domain.model.SysMenuModel;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {
    private final static String MENU_ID = "menu_";

    private final static String BTN_ID = "btn_";


    List<SysMenuModel> rootMenus;
    List<SysMenuModel> childMenus;
    List<SysMenuBtnModel> childBtns;

    public TreeUtil(List<SysMenuModel> rootMenus, List<SysMenuModel> childMenus) {
        this.rootMenus = rootMenus;
        this.childMenus = childMenus;
    }

    public TreeUtil(List<SysMenuModel> rootMenus, List<SysMenuModel> childMenus, List<SysMenuBtnModel> childBtns) {
        this.rootMenus = rootMenus;
        this.childMenus = childMenus;
        this.childBtns = childBtns;
    }

    public List<TreeNode> getTreeNode() {
        return getRootNodes();
    }

    /**
     * @param menu
     * @return
     */
    private TreeNode MenuToNode(SysMenuModel menu) {
        if (menu == null) {
            return null;
        }
        TreeNode node = new TreeNode();
        node.setId(MENU_ID + menu.getId());
        node.setDataId(menu.getId());
        node.setText(menu.getName());
        node.setUrl(menu.getUrl());
        node.setCssStyle(menu.getCssStyle());
        node.setParentId(menu.getParentId());
        node.getAttributes().put("type", "0");
        node.getAttributes().put("id", menu.getId());
        return node;
    }


    /**
     * @param btn
     * @return
     */
    private TreeNode BtnToNode(SysMenuBtnModel btn) {
        if (btn == null) {
            return null;
        }
        TreeNode node = new TreeNode();
        node.setId(BTN_ID + btn.getId());
        node.setDataId(btn.getId());
        node.setText(btn.getBtnName());
        node.setParentId(btn.getMenuId());
        node.getAttributes().put("type", "1");
        node.getAttributes().put("id", btn.getId());
        return node;
    }

    private List<TreeNode> getRootNodes() {
        List<TreeNode> rootNodes = new ArrayList<TreeNode>();
        for (SysMenuModel menu : rootMenus) {
            TreeNode node = MenuToNode(menu);
            if (node != null) {
                addChlidNodes(node);
                rootNodes.add(node);
            }
        }
        return rootNodes;
    }

    /**
     * @param rootNode
     * @return
     */
    private void addChlidNodes(TreeNode rootNode) {
        List<TreeNode> childNodes = new ArrayList<TreeNode>();
        for (SysMenuModel menu : childMenus) {
            if (rootNode.getDataId().equals(menu.getParentId())) {
                TreeNode node = MenuToNode(menu);
                if (childBtns != null && !childBtns.isEmpty()) {
                    addChlidBtn(node);
                }
                childNodes.add(node);
            }
        }
        rootNode.setChildren(childNodes);
    }


    /**
     * 设置菜单button
     *
     * @param treeNode
     * @return
     */
    private void addChlidBtn(TreeNode treeNode) {
        List<TreeNode> childNodes = new ArrayList<TreeNode>();
        for (SysMenuBtnModel btn : childBtns) {
            if (treeNode.getDataId().equals(btn.getMenuId())) {
                TreeNode node = BtnToNode(btn);
                childNodes.add(node);
            }
        }
        treeNode.setChildren(childNodes);
    }


}
