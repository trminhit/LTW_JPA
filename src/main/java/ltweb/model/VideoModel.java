package ltweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VideoModel {

	private String videoId;

	private String title;

	private String poster;

	private String description;

	private int views;

	private int active;
	
	private int categoryId; 
}