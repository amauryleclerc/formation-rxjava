package fr.sii.rxjava.operator.scheduling;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static fr.sii.rxjava.util.rxplayground.GuiRx.createColdIntsObservable;
import static fr.sii.rxjava.util.rxplayground.GuiRx.createObserver;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;

public class ObserveOnPool {

    public static void main(String... args) {
        Executor myThreadPoolExecutor = Executors.newFixedThreadPool(3, new ThreadFactoryBuilder().setNameFormat("my-thread-%d").build());

        Scheduler myThreadPoolScheduler = Schedulers.from(myThreadPoolExecutor);

        Observable<Integer> ints1 = createColdIntsObservable(10, 10, "Ints 1", RED, 0, 5)
                .observeOn(myThreadPoolScheduler);

        createObserver(10, 350, "Observer", GREEN, ints1);
    }
}
