package com.telusko.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
public class Article {


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int article_id;
	@NotEmpty(message = "Please fill in this field")
	@NotNull
	@Size(max = 100, message = "Title length should be less than 100 characters")
    String title;
	@NotEmpty(message = "Please fill in this field")
	@NotNull
	@Size(max = 100, message = "Category length should be less than 100 characters")
    String Category;
	@NotEmpty(message = "Please fill in this field")
	@NotNull
	@Size(max = 300, message = "Description length should be less than 300 characters")
    String Description;
	@NotEmpty(message = "Please fill in this field")
	@NotNull()
	@Size()
    String image_link;
	@NotEmpty(message = "Please fill in this field")
	@NotNull
	@Size()
    String start_time;
	@NotEmpty(message = "Please fill in this field")
	@NotNull
	@Size()
    String end_time;
	@NotEmpty(message = "Please fill in this field")
	//@Pattern(regexp = "", message = "invalid mobile number.")
	@NotNull
	@Size()
    String official_link;
    
    
    public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getImage_link() {
		return image_link;
	}
	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}

    public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getStart_time() {
		return start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public String getOfficial_link() {
		return official_link;
	}
	public void setOfficial_link(String official_link) {
		this.official_link = official_link;
	}
	@Override
	public String toString() {
		return "Article [article_id=" + article_id + ", title=" + title + ", Category=" + Category + ", Description="
				+ Description + ", image_link=" + image_link + ", start_time=" + start_time + ", end_time=" + end_time
				+ ", official_link=" + official_link + "]";
	}
    
    

}
