package eu.albertvila.popularmovies.stage2.data.repository.memory;

import java.util.List;

import eu.albertvila.popularmovies.stage2.data.api.MoviesResponse;
import eu.albertvila.popularmovies.stage2.data.api.MovieDbService;
import eu.albertvila.popularmovies.stage2.data.model.Movie;
import eu.albertvila.popularmovies.stage2.data.model.Review;
import eu.albertvila.popularmovies.stage2.data.model.Video;
import eu.albertvila.popularmovies.stage2.data.repository.MovieRepository;
import eu.albertvila.popularmovies.stage2.data.repository.ShowMovieCriteria;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Albert Vila Calvo on 28/5/16.
 */
public class MemoryMovieRepository implements MovieRepository {

    private MovieDbService movieDbService;
    private String apiKey;
    private ShowMovieCriteria showMovieCriteria;

    public MemoryMovieRepository(MovieDbService movieDbService, String apiKey, ShowMovieCriteria defaultCriteria) {
        Timber.i("New MemoryMovieRepository created");
        this.movieDbService = movieDbService;
        this.apiKey = apiKey;
        this.showMovieCriteria = defaultCriteria;
    }

    @Override
    public void setShowMovieCriteria(ShowMovieCriteria criteria) {
        this.showMovieCriteria = criteria;
        Timber.i("MemoryMovieRepository setShowMovieCriteria() to %s", criteria);
    }

    @Override
    public ShowMovieCriteria getShowMovieCriteria() {
        return showMovieCriteria;
    }

    @Override
    public Observable<List<Movie>> observeMovies() {
        // TODO We are ignoring ShowMovieCriteria for now -> fix
        Observable<MoviesResponse> observable = movieDbService.discoverMoviesRx(apiKey, MovieDbService.SORT_BY_POPULARITY);

        return observable
                .subscribeOn(Schedulers.io())
                .map(new Func1<MoviesResponse, List<Movie>>() {
                    @Override
                    public List<Movie> call(MoviesResponse moviesResponse) {
                        // Timber.i("observeMovies() map() thread: %s", Thread.currentThread().getName());
                        return moviesResponse.getMovies();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setSelectedMovie(Movie movie) {
        // TODO
    }

    @Override
    public Observable<Movie> observeSelectedMovie() {
        // TODO
        return null;
    }

    @Override
    public void favoriteButtonClick() {
        // TODO
    }

    @Override
    public Observable<List<Video>> observeVideosForSelectedMovie() {
        // TODO
        return null;
    }

    @Override
    public Observable<List<Review>> observeReviewsForSelectedMovie() {
        // TODO
        return null;
    }
}
