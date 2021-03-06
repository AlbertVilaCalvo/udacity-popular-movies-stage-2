package eu.albertvila.popularmovies.stage2.data.repository;

import java.util.List;

import eu.albertvila.popularmovies.stage2.data.model.Movie;
import eu.albertvila.popularmovies.stage2.data.model.Review;
import eu.albertvila.popularmovies.stage2.data.model.Video;
import rx.Observable;

/**
 * Created by Albert Vila Calvo on 28/5/16.
 */
public interface MovieRepository {

    void setShowMovieCriteria(ShowMovieCriteria criteria);

    ShowMovieCriteria getShowMovieCriteria();

    Observable<List<Movie>> observeMovies();

    void setSelectedMovie(Movie movie);

    Observable<Movie> observeSelectedMovie();

    void favoriteButtonClick();

    Observable<List<Video>> observeVideosForSelectedMovie();

    Observable<List<Review>> observeReviewsForSelectedMovie();

}
