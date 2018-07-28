package com.zipe.utils.jaxb;

import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

public class Clienter {
	String name;
	int age;
	int id;
	@XmlElement(name="book")
	List<Ebook> ebook;
	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@XmlTransient
	public List<Ebook> getEbook() {
		return ebook;
	}

	public void setEbook(List<Ebook> ebook) {
		this.ebook = ebook;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ",name=" + name + ",age=" + age + "]";
	}
}
