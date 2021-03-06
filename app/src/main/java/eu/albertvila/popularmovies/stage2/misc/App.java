package eu.albertvila.popularmovies.stage2.misc;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import eu.albertvila.popularmovies.stage2.BuildConfig;
import eu.albertvila.popularmovies.stage2.misc.di.AppComponent;
import eu.albertvila.popularmovies.stage2.misc.di.AppModule;
import eu.albertvila.popularmovies.stage2.misc.di.DaggerAppComponent;
import timber.log.Timber;

/**
 * Created by Albert Vila Calvo on 29/5/16.
 */
public class App extends Application {

    private AppComponent appComponent;

    // For LeakCanary
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        // Notice that we are not calling '.apiModule(new ApiModule())'. It works without this
        // because the ApiModule doesn't have constructor arguments.
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    // Add the line number
                    return super.createStackElementTag(element) + ":" + element.getLineNumber();
                }
            });

            Stetho.initializeWithDefaults(this);
            Timber.plant(new StethoTree());

            refWatcher = LeakCanary.install(this);
        }
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static AppComponent getComponent(Context context) {
        return ((App) context.getApplicationContext()).appComponent;
    }

    public static RefWatcher getRefWatcher(Context context) {
        return ((App) context.getApplicationContext()).refWatcher;
    }

}
