package eu.albertvila.popularmovies.stage2.feature.movielist;

import java.util.List;

import eu.albertvila.popularmovies.stage2.data.model.Movie;
import eu.albertvila.popularmovies.stage2.data.repository.ShowMovieCriteria;

/**
 * Created by Albert Vila Calvo on 19/1/16.
 */
public interface MovieList {

    interface View {
        void showMovies(List<Movie> movies);
        void showProgress();
        void showMovieCriteriaDialog(ShowMovieCriteria criteria);
    }

    interface Presenter {
        void start(MovieList.View view);
        void stop();
        void newShowMovieCriteriaSelected(ShowMovieCriteria newCriteria);
        void menuItemShowMovieCriteriaClick();
        void movieSelected(Movie movie);
    }

}
