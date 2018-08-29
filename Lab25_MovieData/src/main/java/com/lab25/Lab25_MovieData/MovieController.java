package com.lab25.Lab25_MovieData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lab25.Lab25_MovieData.Dao.MovieRepository;
import com.lab25.Lab25_MovieData.entity.Movie;

@RestController
public class MovieController {

	@Autowired
	MovieRepository mRepo;

	@GetMapping("/findAll")
	public List<Movie> listAll() {
		List<Movie> movieList = mRepo.findAll();

		return movieList;
	}

	@GetMapping("/findByCategory/{category}")
	public List<Movie> byCategory(@PathVariable("category") String category) {
		List<Movie> movieList = mRepo.findByCategory(category);

		return movieList;
	}

	@GetMapping("/randomPick")
	public Optional<Movie> randPick() {
		// define the range
		int max = 5;
		int min = 1;
		int range = max - min + 1;

		// generate random number from 1 to 10
		int ranNum = (int) (Math.random() * range) + min;

		Optional<Movie> movie = mRepo.findById(ranNum);

		return movie;
	}

	@GetMapping("/findRandByCategory/{category}")
	public Optional<Movie> randByCategory(@PathVariable("category") String category) {
		List<Movie> movieList = mRepo.findByCategory(category);

		int max = movieList.size();
		int min = 1;
		int range = max - min + 1;

		// generate random number from 1 to 10
		int ranNum = (int) (Math.random() * range) + min;

		Optional<Movie> movie = mRepo.findById(ranNum);

		return movie;
	}

	@GetMapping("/randomPickQty/{quantity}")
	public ArrayList<Movie> randPickQty(@PathVariable("quantity") int quantity) {
		// define the range
		int max = 5;
		int min = 1;
		int range = max - min + 1;

		ArrayList<Movie> randMovies = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {
			int ranNum = (int) (Math.random() * range) + min;
			Optional<Movie> movie = mRepo.findById(ranNum);
			Movie mov = movie.get();
			randMovies.add(mov);
		}

		return randMovies;
	}

	@GetMapping("/findCategories")
	public List<String> findCat() {
		List<String> categories = mRepo.findDistinctCategory();

		return categories;
	}

	@GetMapping("/getInfo/{title}")
	public String getInfo(@PathVariable("title") String title) {
		Movie movie = mRepo.findByTitle(title);
		
		return movie.getInfo();
	}
	
	@GetMapping("/findInfo/{keyword}")
	public List<Movie> getByKey(@PathVariable("keyword") String keyword) {
		List<Movie> movies = mRepo.findByTitleContaining(keyword);
		
		return movies;
	}
	

}
