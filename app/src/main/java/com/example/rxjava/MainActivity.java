package com.example.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.rxjava.databinding.ActivityMainBinding;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//Newly commented
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //Adapter
    private ItemAdaptor adaptor;

    // binding
    public ActivityMainBinding binding;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //diff util to update data
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // dummy list of animals
        ArrayList animals = new ArrayList();
        animals.add("Lion");
        animals.add("Tiger");
        animals.add("Cat");
        animals.add("New cat");

        // setting up the adapter
        adaptor = new ItemAdaptor(animals, this);
        binding.setAdaptor(adaptor);

        // RxJava Observer demonstration
        Observable.just(animals)
                //RxJava subscribeOn using schedulers IO
                .subscribeOn(Schedulers.io())
                //Subscribing on Main thread
                .observeOn(AndroidSchedulers.mainThread())

                //Attaching a array list to adapter
                .subscribeWith(new Observer<ArrayList>() {

                    //shows on subscribe logs
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " + d.toString() );
                    }

                    //when on next over rides we add to list and notify the adapter
                    @Override
                    public void onNext(ArrayList arrayList) {
                        arrayList.add("Elephant");
                        arrayList.add("Cow");
                        adaptor.notifyDataSetChanged();
                    }

                    //on error is called when error occurs, error is logged
                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage() );
                    }

                    //called on completion, logged
                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: " + "Completed" );
                    }
                });
    }
}