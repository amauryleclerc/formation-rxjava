package fr.sii.rxjava.operator.timeout;

import rx.Observable;

import static fr.sii.rxjava.util.rxplayground.GuiRx.createColdIntsObservable;
import static fr.sii.rxjava.util.rxplayground.GuiRx.createObserver;
import static java.awt.Color.GREEN;
import static java.awt.Color.RED;
import static java.util.concurrent.TimeUnit.SECONDS;
import static rx.Observable.timer;

public class TimeoutDynamic {
    public static void main(String... args) {
        Observable<Integer> ints1 = createColdIntsObservable(10, 10, "Ints 1", RED, 0, 5);

        createObserver(10, 350, "Observer", GREEN, ints1.timeout(i -> timer(i, SECONDS)));
    }
}
