package com.zhang.shequ.utils.menu;

import java.util.ArrayList;
import java.util.List;

import com.zhang.shequ.core.entity.Menu;
import com.zhang.shequ.core.model.menu.MenuTreeNode;
import com.zhang.shequ.utils.json.GsonUtil;

public class MenuTreeUtil {

	/**
	 * 生成easyui tree Json树结构
	 */
    public static String getMenuJsonByMap(List<Menu> menulist, Integer parentId) {
        StringBuilder result = new StringBuilder();
        try {
            if (menulist.size() > 0) {
                List<MenuTreeNode> nodes = new ArrayList<MenuTreeNode>();
                List<Menu> rows = new ArrayList<Menu>();
                for (int i = 0; i < menulist.size(); i++) {
                    if (parentId.equals(menulist.get(i).getParentId())) {
                        rows.add(menulist.get(i));
                    }
                }
            	if(rows.size() > 0){
                	for(Menu row : rows){
                        MenuTreeNode.Builder builder = new MenuTreeNode.Builder();
                        builder.setNodeByUserMenu(row).setState("open");
                        builder = getMenuTreeNode(menulist, row, builder);
                        nodes.add(builder.create());
                	}
            	}
                if (nodes.size() > 0) {
                    result.append(GsonUtil.objectToJson(nodes).trim().replaceAll(",\"children\":null", ""));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return result.toString();
    }
    
    private static MenuTreeNode.Builder getMenuTreeNode(List<Menu> menulist, Menu row, MenuTreeNode.Builder builder){
    	List<MenuTreeNode> children = new ArrayList<MenuTreeNode>();
        // 判断该条菜单下是否还存在子菜单
        for (int i = 0; i < menulist.size(); i++) {
            if (menulist.get(i).getParentId().equals(row.getId())) {
            	MenuTreeNode.Builder chilrenBuilder = new MenuTreeNode.Builder();
            	chilrenBuilder.setNodeByUserMenu(menulist.get(i)).setState("close");
            	chilrenBuilder = getMenuTreeNode(menulist, menulist.get(i), chilrenBuilder);
            	children.add(chilrenBuilder.create());
            }
        }
        if (children.size() > 0) {
        	builder.setChildren(children);
        }
    	return builder;
    }

    public static String getMenuListCombtreeByTable(List<Menu> menulist, Integer pId) {
        StringBuilder result = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        String treeJson = "";
        result.append(sb.toString());
        sb.setLength(0);
        try {
            if (menulist.size() > 0) {
                sb.append("[");
                sb.append("{\"id\":\"" + "-1" + "\",\"text\":\"" + "功能菜单" + "\",\"icon\":\"" + ""
                        + "\",\"attributes\"" + ":{\"url\":\"\"}");
                sb.append(",\"state\":\"closed\"");
                List<Menu> endRows = new ArrayList<Menu>();
                for (int i = 0; i < menulist.size(); i++) {
                    if (pId.equals(menulist.get(i).getParentId())) {
                        endRows.add(menulist.get(i));
                    }
                }
                if (endRows.size() > 0) {
                    sb.append(",\"children\":[");
                    for (Menu row : endRows) {
                        if (row.getParentId() != -1) {
                            sb.append("{\"id\":\"" + row.getId() + "\",\"text\":\"" + row.getName()
                                    + "\",\"icon\":\"" + row.getIcon() + "\",\"attributes\"" + ":{\"url\":\""
                                    + row.getUrl() + "\"}");
                            // 查询该菜单下的子菜单
                            List<Menu> endChildMenulist = new ArrayList<Menu>();
                            for (int i = 0; i < menulist.size(); i++) {
                                if (menulist.get(i).getParentId().equals(row.getId())) {
                                    endChildMenulist.add(menulist.get(i));
                                }
                            }
                            sb.append(",\"state\":\"closed\"");
                            sb.append(",\"children\":");
                            getMenuListCombtreeByTable(menulist, row.getId());
                            result.append(sb.toString());
                            sb.setLength(0);
                        }
                        result.append(sb.toString());
                        sb.setLength(0);
                        sb.append("},");
                    }
                }
                sb = sb.delete(sb.length() - 1, sb.length());
                sb.append("]");
            }
            sb.append("},");
            sb = sb.delete(sb.length() - 1, sb.length());
            sb.append("]");
            result.append(sb.toString());
            treeJson = result.toString();
            sb.setLength(0);
        } catch (Exception e) {
            treeJson = null;
        }
        return treeJson;
    }

    public static String getMenuListJsonByTable(List<Menu> menulist, Integer parentId) {
        StringBuilder result = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        String treeJson = "";
        result.append(sb.toString());
        sb.setLength(0);
        if (menulist.size() > 0) {
            sb.append("[");
            List<Menu> rows = new ArrayList<Menu>();
            for (int i = 0; i < menulist.size(); i++) {
                if (parentId.equals(menulist.get(i).getParentId())) {
                    rows.add(menulist.get(i));
                }
            }
            if (rows.size() > 0) {
                for (Menu row : rows) {
                    if (row.getParentId() != -1) {
                        sb.append("{\"id\":\"" + row.getId() + "\",\"text\":\"" + row.getName()
                                + "\",\"icon\":\"" + row.getIcon() + "\",\"attributes\"" + ":{\"url\":\""
                                + row.getUrl() + "\"}");
                        // 查询该菜单下是否有子菜单
                        List<Menu> childrows = new ArrayList<Menu>();
                        for (int i = 0; i < menulist.size(); i++) {
                            if (menulist.get(i).getParentId().equals(row.getId())) {
                                childrows.add(menulist.get(i));
                            }
                        }
                        if (childrows.size() > 0) {
                            sb.append(",\"state\":\"closed\"");
                            sb.append(",\"children\":");
                            sb.append(getMenuListJsonByTable(menulist, row.getId()));
                            result.append(sb.toString());
                            sb.setLength(0);
                        }
                        result.append(sb.toString());
                        sb.setLength(0);
                        sb.append("},");
                    }
                }
                sb = sb.delete(sb.length() - 1, sb.length());
            }
            sb.append("]");
            result.append(sb.toString());
            treeJson = result.toString();
            sb.setLength(0);
        }
        return treeJson;
    }
    
}