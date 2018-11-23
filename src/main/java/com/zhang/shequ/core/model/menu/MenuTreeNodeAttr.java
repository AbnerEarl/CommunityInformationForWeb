package com.zhang.shequ.core.model.menu;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MenuTreeNodeAttr", namespace = "menutreenodeattr")
public class MenuTreeNodeAttr extends BaseColumn {

	private static final long serialVersionUID = -39805040230791070L;
	
	private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
