package com.zhang.shequ.base.model.vo;

import java.util.List;

import com.zhang.shequ.base.model.response.IgnoreResponse;

public class EUTreeNode extends IgnoreResponse {

    private Long id;
    
    private Long pid;
    
    private String text;
    
    private String state = "open";//'open','closed'
    
    private Boolean checked = false;
    
    private List<EUTreeNode> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<EUTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<EUTreeNode> children) {
		this.children = children;
	}

}