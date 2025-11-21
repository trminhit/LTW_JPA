package ltweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryModel {

	private int categoryId;

	private String categoryName;

	private String categoryCode;

	private String images;

	private int status;
}