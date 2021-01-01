package com.todo.dto;

import java.sql.Date;

public class TodoDTO {
	private int idx; // primary key

	private String content; // 할일

	private String name; // 해야할 사람

	private int sequence;// 우선순위

	private String type; // todo, doing, done

	private Date regDate;// 작성 날짜

	@Override
	public String toString() {
		return "idx=" + idx + " content=" + content + " name=" + name + " date=" + regDate + " seq=" + sequence
				+ " type=" + type;
	}

	public TodoDTO() {
	}

	public TodoDTO(int idx, String content, String name, int sequence, String type, Date regDate) {
		super();
		this.idx = idx;
		this.content = content;
		this.name = name;
		this.sequence = sequence;
		this.type = type;
		this.regDate = regDate;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
