package com.zhang.shequ.core.model.menu;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.zhang.shequ.core.entity.Menu;

@XmlRootElement(name = "MenuTreeNode", namespace = "menutreenode")
public class MenuTreeNode extends BaseColumn {

	private static final long serialVersionUID = -5579805422629443609L;

	private String id;

    private String text;

    private String icon;

    private MenuTreeNodeAttr attributes;

    private String state;

    private List<MenuTreeNode> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public MenuTreeNodeAttr getAttributes() {
        return attributes;
    }

    public void setAttributes(MenuTreeNodeAttr attributes) {
        this.attributes = attributes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<MenuTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTreeNode> children) {
        this.children = children;
    }

    private MenuTreeNode() {
        super();
    }

    public static class Builder {

        private MenuTreeNode node;

        public Builder() {
            node = new MenuTreeNode();
        }

        public Builder setId(String id) {
            this.node.setId(id);
            return this;
        }

        public Builder setText(String text) {
            this.node.setText(text);
            return this;
        }

        public Builder setIcon(String icon) {
            this.node.setIcon(icon);
            return this;
        }

        public Builder setAttributes(MenuTreeNodeAttr attributes) {
            this.node.setAttributes(attributes);
            return this;
        }

        public Builder setState(String state) {
            this.node.setState(state);
            return this;
        }

        public Builder setChildren(List<MenuTreeNode> children) {
            this.node.setChildren(children);
            return this;
        }

        public Builder setNodeByUserMenu(Menu menu) {
            MenuTreeNodeAttr attributes = new MenuTreeNodeAttr();
            try {
                this.node.setId(new Integer(menu.getId()).toString());
                this.node.setText(menu.getName());
                this.node.setIcon(menu.getIcon());
                attributes.setUrl((menu.getUrl() == null ? "null" : menu.getUrl()));
                this.node.setAttributes(attributes);
                return this;
            } finally {
                attributes = null;
            }
        }

        public MenuTreeNode create() {
            return node;
        }
        
    }
    
}