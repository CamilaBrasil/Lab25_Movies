package com.lab25.Lab25_MovieData.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lab25.Lab25_MovieData.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
	
	List<Movie> findByCategory(String category);
	Movie findByTitle(String title);
	List<Movie> findByTitleContaining(String keyword);
	
	@Query("SELECT DISTINCT category FROM Movie")
	List<String> findDistinctCategory();
}
