package com.jsmblog.response;

import java.util.List;

import com.jsmblog.payload.BlogPostDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {
	
	private List<BlogPostDto> blogPosts;
	private int pageNumber;
	private int pageSize;
	private Long totalNumberOfRecords;
	private int totalNumberOfPages;
	
	private boolean isFirstPage;
	private boolean isLastPage;

}
