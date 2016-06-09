package eu.albertvila.popularmovies.stage2.data.repository;

import java.util.List;

import eu.albertvila.popularmovies.stage2.data.model.Movie;
import rx.Observable;

/**
 * Created by Albert Vila Calvo on 28/5/16.
 */
public interface MovieRepository {

    Observable<List<Movie>> getMoviesRx(String sortOrder);

}
