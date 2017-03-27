package edu.utdallas.rxjavaexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getCanonicalName();
    CompositeDisposable disposables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = (TextView) findViewById(R.id.tv);
        disposables = new CompositeDisposable();
        disposables.add(subscribeCalls(ApiClient.getInterface().getConfig_rxJava(), configObserver));
    }

    private Disposable subscribeCalls(Observable o, DisposableObserver dos) {
        return (Disposable) o.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(dos);
    }

    DisposableObserver<Configuration> configObserver = new DisposableObserver<Configuration>() {
        @Override
        public void onNext(Configuration configuration) {
            Log.d(TAG, "Observable; " + configuration.toString());
            disposables.add(subscribeCalls(ApiClient.getInterface().getSchedule(configuration.getConfig().getSession_URL()), scheduleObserver));
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "OnComplete");
        }
    };

    DisposableObserver<Schedule> scheduleObserver = new DisposableObserver<Schedule>() {
        @Override
        public void onNext(Schedule schedule) {
            Log.d(TAG, "Observable; " + schedule.toString());
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "Completed");
        }
    };

    @Override
    protected void onDestroy() {
        disposables.dispose();
        super.onDestroy();
    }
}
